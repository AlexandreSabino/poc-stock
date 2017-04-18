package br.com.biscoito.http;

import br.com.biscoito.entities.InventoryMovement;
import br.com.biscoito.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;

    @PostMapping(value = "api/stock", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity toMove(@RequestBody final InventoryMovement inventoryMovement) {
        log.info("SKU: " + inventoryMovement.getInventoryId().getSku());
        stockService.toMoveAsync(inventoryMovement);
        return new ResponseEntity(HttpStatus.OK);
    }
}
