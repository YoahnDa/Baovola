package com.baovola.baovola.dto;

import java.util.List;

import lombok.Data;

@Data
public class RecetteDto {
    private Long id;
    private ProduitDto produit;
    private List<RecetteCompositionDto> composition;
}
