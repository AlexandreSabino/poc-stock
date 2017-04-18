package br.com.biscoito.entities;

import lombok.*;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kardex {

    @PrimaryKey
    private KardexId kardexId;

    @Column(value = "distributionCenter")
    private String distributionCenter;

    @Column(value = "quantityMovement")
    private double quantityMovement;

    @Column(value = "quantityOld")
    private double quantityOld;

    @Column(value = "quantityActual")
    private double quantityActual;

    @Column(value = "origin")
    private String origin;

    @Column(value = "typeMovement")
    private String typeMovement;
}
