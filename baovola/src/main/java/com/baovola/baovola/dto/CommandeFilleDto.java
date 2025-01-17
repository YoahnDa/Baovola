package com.baovola.baovola.dto;

import java.util.List;

import lombok.Data;

@Data
public class CommandeFilleDto {
    private Long id;
    private int quantite = 1;
    private CommandeDto commande;
    private ProduitDto produit;
    private List<SupplementCommandeDto> supplements;
}
