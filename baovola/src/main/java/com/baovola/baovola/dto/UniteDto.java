package com.baovola.baovola.dto;

import java.util.List;
import lombok.Data;

@Data
public class UniteDto {
    private Long id;
    private String nom;
    private String symbole;
    private List<IngredientDto> ingredient;
}
