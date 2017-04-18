package br.com.biscoito.service.event;

import br.com.biscoito.entities.Inventory;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class InventoryChangeEvent extends ApplicationEvent {

    private final Inventory inventory;

    public InventoryChangeEvent(Inventory source) {
        super(source);
        this.inventory = source;
    }
}
