package com.baovola.baovola.dto;

import com.baovola.baovola.models.Productions;
import com.baovola.baovola.models.Produits;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductionFilleDto {
    private Long id;
    private ProduitDto produit;
    private double quantite = 0;
    private ProductionDto productions;
}
