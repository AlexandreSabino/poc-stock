package br.com.biscoito.http;

import br.com.biscoito.entities.Inventory;
import br.com.biscoito.service.InventoryService;
import br.com.biscoito.service.TestHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    private final TestHelper testHelper;

    @PostMapping(value = "api/inventory", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Inventory> save(@RequestBody final Inventory inventory) {
        return new ResponseEntity<>(inventoryService.save(inventory), HttpStatus.OK);
    }


    @PostMapping(value = "api/inventory/mass/testing")
    public void massTesting(@RequestBody final String records) {
        testHelper.generateMassTest(Integer.parseInt(records));
    }
}