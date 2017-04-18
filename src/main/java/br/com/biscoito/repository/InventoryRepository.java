package br.com.biscoito.repository;

import br.com.biscoito.entities.Inventory;
import br.com.biscoito.entities.InventoryId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InventoryRepository extends CrudRepository<Inventory, InventoryId> {

    Optional<Inventory> findByInventoryIdWarehouseCodeAndInventoryIdSkuAndInventoryIdSellerIdAndInventoryIdProductOrigin(
            int warehouseCode,
            String sku,
            String sellerId,
            int productOrigin);
}
