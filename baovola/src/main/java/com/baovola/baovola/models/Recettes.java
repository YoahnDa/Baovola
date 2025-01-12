package com.baovola.baovola.models;
import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recettes")
public class Recettes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_produit") 
    private Produits produit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recettes", orphanRemoval = true) 
    private List<RecetteComposition> composition;
}
