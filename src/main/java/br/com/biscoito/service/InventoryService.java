package br.com.biscoito.service;

import br.com.biscoito.entities.Inventory;
import br.com.biscoito.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Inventory save(final Inventory inventory) {
        return inventoryRepository.insert(inventory);
    }
}
