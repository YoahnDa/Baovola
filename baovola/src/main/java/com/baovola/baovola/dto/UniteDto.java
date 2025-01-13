package com.baovola.baovola.dto;

import lombok.Data;

@Data
public class UniteDto {
    private Long id;
    private String nom;
    private String symbole;
    private List<MatierePremiere> matierePremiere;
}
