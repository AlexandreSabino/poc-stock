package br.com.biscoito.service.event.listener;

import br.com.biscoito.service.event.InventoryChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryListener implements ApplicationListener<InventoryChangeEvent> {

    @Override
    public void onApplicationEvent(final InventoryChangeEvent event) {
        log.info(String.format("BI send product: %s", event.getInventory().getInventoryId().getSku()));
    }
}
