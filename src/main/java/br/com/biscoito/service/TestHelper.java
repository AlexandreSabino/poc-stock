package br.com.biscoito.service;

import br.com.biscoito.entities.Inventory;
import br.com.biscoito.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TestHelper {

    private final InventoryService inventoryService;

    public void generateMassTest(final int records) {
        final Inventory example = createFakeInventory();
        for (int counter = 0; counter <= records; counter++) {
            example.setSku(UUID.randomUUID().toString());
            example.setQuantity(counter);
            example.setWarehouseCode(generateRandomWarehouseCode());
            example.setDistributionCenter(generateRandomDistributionCenter());
            example.setWarehouseName("Estoque Central " + example.getDistributionCenter());
            inventoryService.save(example);
        }
    }

    private String generateRandomDistributionCenter() {
        final List<String> dcs = Arrays.asList("Barueri", "Minas Gerais", "Pernambuco");
        return dcs.get(new Random().nextInt(3));
    }

    private int generateRandomWarehouseCode() {
        final List<Integer> warehouses = Arrays.asList(9, 21, 50, 30, 31);
        return warehouses.get(new Random().nextInt(4));
    }

    private Inventory createFakeInventory() {
        return new Inventory(
                "003-4008-160-26",
                9,
                "Estoque central",
                100,
                "Barueri"
        );
    }
}
