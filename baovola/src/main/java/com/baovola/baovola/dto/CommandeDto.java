package com.baovola.baovola.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.baovola.baovola.models.Status;

import lombok.Data;

@Data
public class CommandeDto {
    private Long id;
    private LocalDateTime dateCommande;
    private Status etatCommande = Status.WAIT;
    private List<CommandeFilleDto> detail;
    private double montantPayer = 0;
    private CaisseDto caisse;
    private ClientDto client;
}
