package br.com.biscoito.service;

import br.com.biscoito.entities.Inventory;
import br.com.biscoito.entities.InventoryMovement;
import br.com.biscoito.entities.Kardex;
import br.com.biscoito.entities.KardexId;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class StockService {

    private static final String TOPIC_STOCK_KAFKA =  "stock";

    private final ExecutorService pool = Executors.newCachedThreadPool();

    private final InventoryService inventoryService;

    private final KardexService kardexService;

    private final KafkaTemplate<String, String> template;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = TOPIC_STOCK_KAFKA)
    public void process(final String json) {
        try {
            final InventoryMovement inventoryMovement = objectMapper.readValue(json, InventoryMovement.class);
            this.toMove(inventoryMovement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void toMoveAsync(final InventoryMovement inventoryMovement) {
        try {
            final String json = objectMapper.writeValueAsString(inventoryMovement);

            ListenableFuture<SendResult<String, String>> future = template.send(TOPIC_STOCK_KAFKA,
                    inventoryMovement.getInventoryId().getSku(),
                    json);

            future.get().getProducerRecord().toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public void toMove(final InventoryMovement inventoryMovement) {
        try {
            final Inventory inventory = inventoryService.findOrCreate(inventoryMovement);
            final Kardex kardex = createKardex(inventory, inventoryMovement);
            inventoryMovement.getTypeMovement().calculate(inventory, inventoryMovement);
            if (inventoryMovement.getTypeMovement().isPhysical()) {
                kardex.setQuantityActual(inventory.getQuantity());
            } else {
                kardex.setQuantityActual(inventory.getReservedQuantity());
            }

            CompletableFuture
                    .runAsync(() -> inventoryService.save(inventory), pool)
                    .runAsync(() -> kardexService.save(kardex), pool)
                    .get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Kardex createKardex(final Inventory inventory, final InventoryMovement inventoryMovement) {
        final KardexId kardexId = new KardexId(inventory.getInventoryId());
        return Kardex
                .builder()
                .kardexId(kardexId)
                .distributionCenter(inventory.getDistributionCenter())
                .quantityMovement(inventoryMovement.getQuantity())
                .quantityOld(inventory.getQuantity())
                .origin(inventoryMovement.getSystemOrigin())
                .typeMovement(inventoryMovement.getTypeMovement().name())
                .build();
    }
}