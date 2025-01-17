package com.baovola.baovola.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.baovola.baovola.dto.IngredientDto;
import org.springframework.web.bind.annotation.*;
import com.baovola.baovola.services.implementations.ServiceIngredients;
import com.baovola.baovola.helpers.IngredientMapper;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private ServiceIngredients serviceIngredients;

    @Autowired
    private IngredientMapper ingredientMapper;

    @GetMapping
    public String listIngredients(Model model) {
        List<IngredientDto> ingredient = serviceIngredients.getAllIngredient().stream()
        .map(ingredientMapper::toDto)
        .toList();
        model.addAttribute("ingredients",ingredient);
        return "ingredients/list";
    }
}
