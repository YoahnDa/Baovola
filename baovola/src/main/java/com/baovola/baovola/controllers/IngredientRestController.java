package com.baovola.baovola.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.baovola.baovola.services.implementations.ServiceIngredients;
import com.baovola.baovola.dto.IngredientDto;
import com.baovola.baovola.helpers.IngredientMapper;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientRestController {
    @Autowired
    private ServiceIngredients ingredientService;

    @Autowired
    private IngredientMapper ingredientMapper;

    @ResponseBody
    @GetMapping("/search")
    public List<IngredientDto> searchIngredients(@RequestParam(required = false) List<Long> uniteIds, 
                                                  @RequestParam(required = false) String nom) {
        uniteIds = (uniteIds != null) ? uniteIds : List.of();
        nom = (nom != null) ? nom : "";
        return ingredientService.searchIngredient(uniteIds, nom);
    }

    @ResponseBody
    @GetMapping
    public List<IngredientDto> listIngredients(Model model) {
        return ingredientService.getAllIngredient().stream()
        .map(ingredientMapper::toDto)
        .toList();
    }
}