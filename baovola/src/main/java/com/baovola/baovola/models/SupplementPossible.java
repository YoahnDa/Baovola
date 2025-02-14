package com.baovola.baovola.models;

import java.io.Serializable;
import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "supplementPossible")
public class SupplementPossible implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_supplement") 
    private Supplement supplement;
    @ManyToOne
    @JoinColumn(name = "id_produit") 
    private Produits produit;
}
