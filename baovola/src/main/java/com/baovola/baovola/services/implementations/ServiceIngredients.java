package com.baovola.baovola.services.implementations;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.baovola.baovola.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import com.baovola.baovola.services.interfaces.IServiceIngredients;
import com.baovola.baovola.dto.IngredientDto;
import com.baovola.baovola.helpers.IngredientMapper;
import com.baovola.baovola.models.MatierePremiere;

@Service
public class ServiceIngredients implements IServiceIngredients {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientMapper ingredientMapper;

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    public MatierePremiere findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ingrédient introuvable : " + id));
    }

    public void updateIngredient(MatierePremiere ingredient) {
        ingredientRepository.save(ingredient);
    }

    public List<IngredientDto> searchIngredient(List<Long> uniteIds, String nom) {
        List<MatierePremiere> liste = ingredientRepository.findByUniteIdsAndName(uniteIds,nom);
        return liste.stream()
                          .map(ingredientMapper::toDto)
                          .collect(Collectors.toList());
    }

    public List<MatierePremiere> getAllIngredient() {
        return ingredientRepository.findAll();
    }

    public void createIngredients(MatierePremiere ingredient) {
        ingredientRepository.save(ingredient);
    }
}
