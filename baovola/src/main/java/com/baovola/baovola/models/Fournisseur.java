package com.baovola.baovola.models;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "fournisseur")
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private String nom;
    @Column(nullable = false) 
    private String achronyme;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fournisseur", orphanRemoval = true)
    private List<ApprovisionnementFille> approvisionnement;
}