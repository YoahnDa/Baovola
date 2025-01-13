package com.baovola.baovola.models;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "approvisionnement")
public class Approvisionnement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private LocalDateTime dateAppro;
    @Column(nullable = false) 
    @Enumerated(EnumType.STRING)
    private Status etat = Status.WAIT;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true)
    private List<ApprovisionnementFille> detail;
}
