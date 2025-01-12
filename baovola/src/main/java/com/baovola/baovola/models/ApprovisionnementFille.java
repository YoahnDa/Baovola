package com.baovola.baovola.models;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "approvisionnementFille")
public class ApprovisionnementFille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private MatierePremiere matiere;
    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    private Fournisseur fournisseur;
    @ManyToOne
    @JoinColumn(name = "id_approvisionnement")
    private Approvisionnement parent;
    @Column(nullable = false) 
    private double quantite = 0;
}
