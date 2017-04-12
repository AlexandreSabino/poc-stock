package br.com.biscoito.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.mapping.Table;

import java.time.LocalDateTime;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Kardex {

    private LocalDateTime dateOfMovement;

    private String sku;

    private int warehouseCode;

    private String sellerId;

    private int productOrigin;

    private String distributionCenter;

    private double quantityMovement;

    private double quantityOld;

    private double quantityActual;

    private String origin;

    private String typeMovement;
}
