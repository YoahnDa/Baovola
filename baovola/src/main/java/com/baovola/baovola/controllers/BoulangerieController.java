package com.baovola.baovola.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoulangerieController {

    @GetMapping("/dashboard")
    public String accueil(Model model) {
        model.addAttribute("body", "dashboard");
        return "layout";  // Retourne le template layout.html
    }

    @GetMapping("/ingredient")
    public String ingredient(Model model) {
        model.addAttribute("body", "ingredient");
        return "layout";  // Retourne le template layout.html
    }

    @GetMapping("/produit")
    public String produit(Model model) {
        model.addAttribute("body", "produit");
        return "layout";  // Retourne le template layout.html
    }

    @GetMapping("/production")
    public String production(Model model) {
        model.addAttribute("body", "production");
        return "layout";  // Retourne le template layout.html
    }

    @GetMapping("/vente")
    public String vente(Model model) {
        model.addAttribute("body", "vente");
        return "layout";  // Retourne le template layout.html
    }

    @GetMapping("/facture")
    public String facture(Model model) {
        model.addAttribute("body", "facture");
        return "layout";  // Retourne le template layout.html
    }
}