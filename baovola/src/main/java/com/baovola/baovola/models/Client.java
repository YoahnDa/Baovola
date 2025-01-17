package com.baovola.baovola.models;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
    private List<Commande> commande;
}
