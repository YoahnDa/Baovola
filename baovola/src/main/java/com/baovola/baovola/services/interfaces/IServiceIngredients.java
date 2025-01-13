package com.baovola.baovola.services.interfaces;

import java.util.List;

import com.baovola.baovola.dto.IngredientDto;
import com.baovola.baovola.dto.IngredientSearchDto;
import com.baovola.baovola.models.MatierePremiere;

public interface IServiceIngredients {
    public void deleteIngredient(Long id);
    public IngredientDto updateIngredient(Long id, IngredientDto ingredient);
    public List<IngredientDto> searchIngredient(IngredientSearchDto search);
    public List<IngredientDto> getAllIngredient();
    public IngredientDto createIngredients(IngredientDto ingredient);
}
