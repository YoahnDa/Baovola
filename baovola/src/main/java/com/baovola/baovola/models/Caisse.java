package com.baovola.baovola.models;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "caisse")
public class Caisse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String identifiant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caisse", orphanRemoval = true)
    private List<Commande> commandes;
}
