package br.com.biscoito.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovement {

    private InventoryId inventoryId;

    private TypeMovement typeMovement;

    private double quantity;

    private String systemOrigin;

    private String distributionCenter;

    private String warehouseName;

    private Boolean virtual;
}
