package com.baovola.baovola.dto;

import lombok.Data;

@Data
public class SupplementCommandeDto {
    private Long id;
    private CommandeFilleDto commande;
    private SupplementDto supplement;
}
