package com.baovola.baovola.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productionsFille")
public class ProductionFille implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_produit") 
    private Produits produit;
    @Column(nullable = false) 
    private double quantite = 0;
    @ManyToOne
    @JoinColumn(name = "id_fille")
    private Productions productions;
}
