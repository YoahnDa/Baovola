package com.baovola.baovola.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoulangerieController {

    @GetMapping("/dashboard")
    public String accueil(Model model) {
        model.addAttribute("body", "dashboard");

        List<String> cssLinks = Arrays.asList(
                "/css/dashboard.css");

        List<String> jsLinks = Arrays.asList(
                "/js/popper.min.js",
                "/js/chart.js",
                "/js/dashboard.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/approvisionnement")
    public String approvisonnement(Model model) {
        model.addAttribute("body", "approvisionnement");
        List<String> cssLinks = Arrays.asList(
                "/css/approvisionnement.css");

        List<String> jsLinks = Arrays.asList(
                "/js/popper.min.js",
                "/js/approvisionnement.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/ajout-approvisionnement")
    public String addapprovisonnement(Model model) {
        model.addAttribute("body", "ajout-approvisionnement");
        List<String> cssLinks = Arrays.asList(
                "/css/approvisionnement.css");

        List<String> jsLinks = Arrays.asList(
                "/js/popper.min.js",
                "/js/approvisionnement.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/fournisseur")
    public String fournisseur(Model model) {
        model.addAttribute("body", "fournisseur");
        List<String> cssLinks = Arrays.asList(
                "/css/fournisseur.css");

        List<String> jsLinks = Arrays.asList(
                "/js/popper.min.js",
                "/js/fournisseur.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/ajout-fournisseur")
    public String addfournisseur(Model model) {
        model.addAttribute("body", "ajout-fournisseur");
        List<String> cssLinks = Arrays.asList(
                "/css/fournisseur.css");

        List<String> jsLinks = Arrays.asList(
                "/js/popper.min.js",
                "/js/fournisseur.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/ingredient")
    public String ingredient(Model model) {
        model.addAttribute("body", "ingredient");

        List<String> cssLinks = Arrays.asList(
                "/css/ingredient.css");

        List<String> jsLinks = Arrays.asList(
                "/js/ingredient.js",
                "/js/popper.min.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/ajout-ingredient")
    public String addingredient(Model model) {
        model.addAttribute("body", "ajout-ingredient");

        List<String> cssLinks = Arrays.asList(
                "/css/ingredient.css");

        List<String> jsLinks = Arrays.asList(
                "/js/ingredient.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/produit")
    public String produit(Model model) {
        model.addAttribute("body", "produit");
        model.addAttribute("cssLink", "lien/vers/cssLink");
        model.addAttribute("jsLink", "lien/vers/jsLink");
        return "layout"; // Retourne le template layout.html
    }

    @GetMapping("/production")
    public String production(Model model) {
        model.addAttribute("body", "production");
        model.addAttribute("cssLink", "lien/vers/cssLink");
        model.addAttribute("jsLink", "lien/vers/jsLink");
        return "layout"; // Retourne le template layout.html
    }

    @GetMapping("/vente")
    public String vente(Model model) {
        model.addAttribute("body", "vente");
        model.addAttribute("cssLink", "lien/vers/cssLink");
        model.addAttribute("jsLink", "lien/vers/jsLink");
        return "layout"; // Retourne le template layout.html
    }

    @GetMapping("/facture")
    public String facture(Model model) {
        model.addAttribute("body", "facture");
        model.addAttribute("cssLink", "lien/vers/cssLink");
        model.addAttribute("jsLink", "lien/vers/jsLink");
        return "layout"; // Retourne le template layout.html
    }

    @GetMapping("/recette")
    public String recette(Model model) {
        model.addAttribute("body", "recette");
        model.addAttribute("cssLink", "lien/vers/cssLink");
        model.addAttribute("jsLink", "lien/vers/jsLink");
        return "layout"; // Retourne le template layout.html
    }
}
