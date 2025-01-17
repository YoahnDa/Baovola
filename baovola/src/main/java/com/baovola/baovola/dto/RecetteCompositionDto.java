package com.baovola.baovola.dto;

import lombok.Data;

@Data
public class RecetteCompositionDto {
    private Long id;
    private double quantite = 0;
    private RecetteDto recettes;
    private IngredientDto ingredient;
}
