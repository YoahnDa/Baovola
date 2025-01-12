package com.baovola.baovola.models;

import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "supplementCommande")
public class SupplementCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_commande") 
    private CommandeFille commande;
    @ManyToOne
    @JoinColumn(name = "id_supplement") 
    private Supplement supplement;
}
