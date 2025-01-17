package com.baovola.baovola.models;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "produitMois")
public class ProduitMois implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private LocalDateTime datePreference;
    @Column(nullable = false) 
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produits produit;
}
