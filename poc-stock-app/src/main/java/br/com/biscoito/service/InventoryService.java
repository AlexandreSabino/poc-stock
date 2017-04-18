package br.com.biscoito.service;

import br.com.biscoito.entities.Inventory;
import br.com.biscoito.entities.InventoryMovement;
import br.com.biscoito.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    private final ApplicationEventPublisher publisher;

    public Inventory save(final Inventory inventory) {
        inventory.setLastUpdate(LocalDateTime.now());
        inventory.setSaveDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        inventoryRepository.findByInventoryIdWarehouseCodeAndInventoryIdSkuAndInventoryIdSellerIdAndInventoryIdProductOrigin(
                inventory.getInventoryId().getWarehouseCode(),
                inventory.getInventoryId().getSku(),
                inventory.getInventoryId().getSellerId(),
                inventory.getInventoryId().getProductOrigin()
        ).ifPresent(inventoryDataBase -> {
            inventory.setSaveDate(inventoryDataBase.getSaveDate());
            boolean hasChange = (inventory.getQuantity() != inventoryDataBase.getQuantity()) ||
                    (inventory.getReservedQuantity() != inventoryDataBase.getReservedQuantity());

            if (hasChange) {
                publisher.publishEvent(inventory);
            }
        });
        return inventoryRepository.save(inventory);
    }

    public Inventory findOrCreate(final InventoryMovement inventoryMovement) {
        return inventoryRepository.findByInventoryIdWarehouseCodeAndInventoryIdSkuAndInventoryIdSellerIdAndInventoryIdProductOrigin(
                inventoryMovement.getInventoryId().getWarehouseCode(),
                inventoryMovement.getInventoryId().getSku(),
                inventoryMovement.getInventoryId().getSellerId(),
                inventoryMovement.getInventoryId().getProductOrigin()
        ).orElse(new Inventory(inventoryMovement.getInventoryId(),
                0,
                inventoryMovement.getDistributionCenter(),
                0,
                0,
                inventoryMovement.getVirtual(),
                LocalDateTime.now(),
                inventoryMovement.getWarehouseName()));
    }
}