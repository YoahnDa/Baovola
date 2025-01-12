package com.baovola.baovola.models;

import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "supplement")
public class Supplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private double prix = 0;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ingredient") 
    private MatierePremiere ingredient;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplement", orphanRemoval = true)
    private List<SupplementPossible> composition; 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplement", orphanRemoval = true)
    private List<SupplementCommande> commande; 
}
