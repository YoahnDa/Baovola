package com.baovola.baovola.dto;

import lombok.Data;

@Data
public class IngredientDto {
    private Long id;
    private String nom;
    private UniteDto unite;
}
