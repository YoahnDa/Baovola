package com.baovola.baovola.models;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productions")
public class Productions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private LocalDateTime dateProduction;
    @Column(nullable = false) 
    private double quantite = 0;
    @ManyToOne
    @JoinColumn(name = "id_produit") 
    private Produits produit;
    @Enumerated(EnumType.STRING)
    private Status etat = Status.WAIT;
}
