package com.baovola.baovola.models;
import jakarta.validation.constraints.*;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "recetteComposition")
public class RecetteComposition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private double quantite = 0;
    @ManyToOne
    @JoinColumn(name = "id_recette") 
    private Recettes recettes;
    @ManyToOne
    @JoinColumn(name = "id_ingredient") 
    private MatierePremiere ingredient;
}
