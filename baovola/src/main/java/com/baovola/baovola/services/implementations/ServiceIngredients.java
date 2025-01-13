package com.baovola.baovola.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.baovola.baovola.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import com.baovola.baovola.services.interfaces.IServiceIngredients;
import com.baovola.baovola.dto.IngredientDto;
import com.baovola.baovola.dto.IngredientSearchDto;
import com.baovola.baovola.models.MatierePremiere;

@Service
public class ServiceIngredients implements IServiceIngredients {
    @Autowired
    private IngredientRepository ingredientRepository;

    public void deleteIngredient(Long id) {
      // TODO document why this method is empty
    }


    public IngredientDto updateIngredient(Long id, IngredientDto ingredient) {

    }

    public List<IngredientDto> searchIngredient(IngredientSearchDto search) {

    }

    public List<IngredientDto> getAllIngredient() {

    }

    public IngredientDto createIngredients(IngredientDto ingredient) {

    }
}
