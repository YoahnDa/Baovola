package com.baovola.baovola.models;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "categorie")
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie", orphanRemoval = true) 
    private List<Produits> produits;
}
