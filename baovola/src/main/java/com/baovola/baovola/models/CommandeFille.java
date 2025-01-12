package com.baovola.baovola.models;
import jakarta.validation.constraints.*;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "commandeFille")
public class CommandeFille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private int quantite = 1;
    @ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande;
    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produits produit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande", orphanRemoval = true)
    private List<SupplementCommande> supplements;
}
