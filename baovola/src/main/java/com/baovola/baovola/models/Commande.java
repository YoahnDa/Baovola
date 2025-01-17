package com.baovola.baovola.models;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@Entity
@Table(name = "commande")
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime dateCommande;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status etatCommande = Status.WAIT;
    @Column(nullable = false)
    private double montantPayer = 0;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande", orphanRemoval = true)
    private List<CommandeFille> detail;
    @ManyToOne
    @JoinColumn(name = "id_caisse")
    private Caisse caisse;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
}
