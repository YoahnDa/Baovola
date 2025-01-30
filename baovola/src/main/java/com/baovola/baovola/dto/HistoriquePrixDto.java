package com.baovola.baovola.dto;

import java.time.LocalDateTime;

import com.baovola.baovola.models.Produits;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class HistoriquePrixDto {
    private Long id;
    private Double prix;
    private Produits produits;
    private LocalDateTime dateModif;
}
