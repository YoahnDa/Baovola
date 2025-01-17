package com.baovola.baovola.dto;

import lombok.Data;

@Data
public class SupplementPossibleDto {
    private Long id; 
    private SupplementDto supplement; 
    private ProduitDto produit;
}
