package com.baovola.baovola.dto;

import java.util.List;

import lombok.Data;

@Data
public class SupplementDto {
    private Long id; 
    private double prix = 0;
    private IngredientDto ingredient;
    private List<SupplementPossibleDto> composition; 
    private List<SupplementCommandeDto> commande; 
}
