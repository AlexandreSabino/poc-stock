package br.com.biscoito.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyClass
public class InventoryId implements Serializable {

    @PrimaryKeyColumn(name = "sku", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String sku;

    @PrimaryKeyColumn(name = "warehouseCode", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int warehouseCode;

    @PrimaryKeyColumn(name = "sellerId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String sellerId;

    @PrimaryKeyColumn(name = "productOrigin", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int productOrigin;
}
