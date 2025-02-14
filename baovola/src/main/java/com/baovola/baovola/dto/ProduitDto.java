package com.baovola.baovola.dto;

import java.util.List;
import lombok.Data;

@Data
public class ProduitDto {
    private Long id;
    private String nom;
    private CategorieDto categorie;
    private List<ProductionDto> productions;
    private List<CommandeFilleDto> commande;
    private RecetteDto recette;
    private List<HistoriquePrixDto> prix;
}
