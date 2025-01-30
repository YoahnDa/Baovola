package com.baovola.baovola.models;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "produits")
public class Produits implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private String nom;
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit", orphanRemoval = true)
    private List<ProductionFille> productions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit", orphanRemoval = true)
    private List<CommandeFille> commande;
    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL)
    private Recettes recette;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit", orphanRemoval = true) 
    private List<SupplementPossible> supplements;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit", orphanRemoval = true) 
    private List<ProduitMois> preference;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produits", orphanRemoval = true)
    private List<HistoriquePrix> prixUnitaire;
}