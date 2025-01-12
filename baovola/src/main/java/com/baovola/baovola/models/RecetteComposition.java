package com.baovola.baovola.models;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "recetteComposition")
public class RecetteComposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private double quantite = 0;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_recette") 
    private Recettes recettes;
    @ManyToOne
    @JoinColumn(name = "id_ingredient") 
    private MatierePremiere ingredient;
}
