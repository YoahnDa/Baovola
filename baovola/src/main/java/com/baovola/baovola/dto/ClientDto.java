package com.baovola.baovola.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClientDto {
    private Long id;
    private String nom;
    private List<CommandeDto> commande;
}
