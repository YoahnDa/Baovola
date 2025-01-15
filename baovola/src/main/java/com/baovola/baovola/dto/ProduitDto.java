package com.baovola.baovola.dto;

import java.util.List;

import com.baovola.baovola.models.Categorie;
import com.baovola.baovola.models.CommandeFille;
import com.baovola.baovola.models.Productions;
import com.baovola.baovola.models.Recettes;

import lombok.Data;

@Data
public class ProduitDto {
    private Long id;
    private String nom;
    private double prixUnitaire = 0;
    private Categorie categorie;
    private List<Productions> productions;
    private List<CommandeFille> commande;
    private Recettes recette;
}
