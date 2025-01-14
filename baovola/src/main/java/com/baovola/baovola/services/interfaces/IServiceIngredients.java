package com.baovola.baovola.services.interfaces;

import java.util.List;

import com.baovola.baovola.dto.IngredientDto;
import com.baovola.baovola.models.MatierePremiere;

public interface IServiceIngredients {
    public void deleteIngredient(Long id);
    public void updateIngredient(MatierePremiere ingredient);
    public MatierePremiere findById(Long id);
    public List<IngredientDto> searchIngredient(List<Long> uniteIds, String nom);
    public List<MatierePremiere> getAllIngredient();
    public void createIngredients(MatierePremiere ingredient);
}
