package br.com.biscoito.service;

import br.com.biscoito.entities.Inventory;
import br.com.biscoito.entities.InventoryMovement;
import br.com.biscoito.entities.Kardex;
import br.com.biscoito.entities.KardexId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class StockService {

    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final InventoryService inventoryService;

    private final KardexService kardexService;

    public void toMove(final InventoryMovement inventoryMovement) throws ExecutionException, InterruptedException {
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