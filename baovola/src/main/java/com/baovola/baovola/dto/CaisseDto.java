package com.baovola.baovola.dto;

import java.util.List;

import lombok.Data;

@Data
public class CaisseDto {
    private Long id;
    private String identifiant;
    private List<CommandeDto> commandes;
}
