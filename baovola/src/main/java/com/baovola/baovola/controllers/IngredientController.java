package com.baovola.baovola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.baovola.baovola.services.implementations.ServiceIngredients;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private ServiceIngredients serviceIngredients;

    @GetMapping
    public String listIngredients(Model model) {
        model.addAttribute("ingredients","test");
        return "ingredients/list";
    }
}
