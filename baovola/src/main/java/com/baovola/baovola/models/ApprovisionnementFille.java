package com.baovola.baovola.models;
import jakarta.validation.constraints.*;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "approvisionnementFille")
public class ApprovisionnementFille implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private MatierePremiere matiere;
    @ManyToOne
    @JoinColumn(name = "id_approvisionnement")
    private Approvisionnement parent;
    @Column(nullable = false) 
    private double quantite = 0;
}
