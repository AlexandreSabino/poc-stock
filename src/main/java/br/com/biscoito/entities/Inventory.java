package br.com.biscoito.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Serializable {

    @PrimaryKey
    private InventoryId inventoryId;

    @Column(value = "saveDate")
    private long saveDate;

    @Column(value = "distributionCenter")
    private String distributionCenter;

    @Column(value = "quantity")
    private double quantity;

    @Column(value = "reservedQuantity")
    private double reservedQuantity;

    public double getAvailableQuantity() {
        return this.quantity - this.reservedQuantity;
    }

    @Column(value = "virtual")
    private Boolean virtual;

    @Column(value = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(value = "warehouseName")
    private String warehouseName;
}
