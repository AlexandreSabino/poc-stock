package br.com.biscoito.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BiFunction;

@AllArgsConstructor
public enum TypeMovement {

    RESERVE(false, (actual, movementQuantity) -> actual - movementQuantity),
    ADJUSTMENT(true, (actual, movementQuantity) -> actual - movementQuantity),
    PURCHASE(true, (actual, movementQuantity) -> actual + movementQuantity),
    BILLING(true, (actual, movementQuantity) -> actual - movementQuantity);

    @Getter
    private final boolean physical;

    @Getter
    private final BiFunction<Double, Double, Double> operation;

    public void calculate(final Inventory inventory, final InventoryMovement inventoryMovement) {
        final double newQuantity = this.operation.apply(inventory.getQuantity(), inventoryMovement.getQuantity());
        if (this.isPhysical()) {
            inventory.setQuantity(newQuantity);
        }  else  {
            inventory.setReservedQuantity(newQuantity);
        }
    }
}
