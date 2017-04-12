package br.com.biscoito.repository;

import br.com.biscoito.entities.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryRepository {

    private final CassandraOperations cassandraOperations;

    public Inventory insert(final Inventory inventory) {
        return cassandraOperations.insert(inventory);
    }

    public Inventory update(final Inventory inventory) {
        return cassandraOperations.update(inventory);
    }
}
