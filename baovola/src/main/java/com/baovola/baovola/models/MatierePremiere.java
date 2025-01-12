package com.baovola.baovola.models;
import jakarta.validation.constraints.*;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "matierePremiere")
public class MatierePremiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private String nom;
    @ManyToOne
    @JoinColumn(name = "id_unite") 
    private Unite unite;
    @OneToOne(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private Supplement supplement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingredient", orphanRemoval = true)
    private List<RecetteComposition> recettes;
}
