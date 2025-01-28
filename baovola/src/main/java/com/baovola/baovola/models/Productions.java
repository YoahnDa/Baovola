package com.baovola.baovola.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productions")
public class Productions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private LocalDateTime dateProduction = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Status etat = Status.WAIT;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productions", orphanRemoval = true)
    private List<ProductionFille> productionFille;
}
