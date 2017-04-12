package br.com.biscoito.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.time.LocalDateTime;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @PrimaryKeyColumn(name = "sku", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String sku;

    @PrimaryKeyColumn(name = "warehouseCode", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int warehouseCode;

    @PrimaryKeyColumn(name = "sellerId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String sellerId;

    @PrimaryKeyColumn(name = "productOrigin", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int productOrigin;

    @Column(value = "distributionCenter")
    private String distributionCenter;

    @Column(value = "quantity")
    private double quantity;

    @Column(value = "reservedQuantity")
    private double reservedQuantity;

    @Column(value = "virtual")
    private Boolean virtual;

    @Column(value = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(value = "warehouseName")
    private String warehouseName;
}
