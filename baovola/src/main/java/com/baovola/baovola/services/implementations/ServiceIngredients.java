package com.baovola.baovola.services.implementations;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public boolean existeByNom(String nom){
        return ingredientRepository.existsByNomIgnoreCase(nom);
    }
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
        List<MatierePremiere> liste = ingredientRepository.findByNomIgnoreCaseContaining(nom);
        // Si uniteIds est vide ou null, ne pas appliquer de filtre supplémentaire
        if (uniteIds == null || uniteIds.isEmpty()) {
            return liste.stream()
                .map(ingredientMapper::toDto)
                .toList();
        }
        return liste.stream()
            .filter(ingredient -> uniteIds.contains(ingredient.getUnite().getId()))
            .map(ingredientMapper::toDto)
            .toList();
    }

    public List<MatierePremiere> getAllIngredient() {
        return ingredientRepository.findAll();
    }

    public void createIngredients(MatierePremiere ingredient) {
        ingredientRepository.save(ingredient);
    }
}
