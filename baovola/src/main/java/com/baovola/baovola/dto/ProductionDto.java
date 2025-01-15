package com.baovola.baovola.dto;

import java.time.LocalDateTime;

import com.baovola.baovola.models.Produits;
import com.baovola.baovola.models.Status;
import lombok.Data;

@Data
public class ProductionDto {
    private Long id;
    private LocalDateTime dateProduction;
    private double quantite = 0;
    private Produits produit;
    private Status etat = Status.WAIT;
}
