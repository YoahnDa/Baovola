package com.baovola.baovola.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "unite")
public class Unite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String symbole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unite")
    private List<MatierePremiere> matierePremiere;
}
