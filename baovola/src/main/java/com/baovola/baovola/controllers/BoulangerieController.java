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


    @GetMapping("/production")
    public String production(Model model) {
        model.addAttribute("body", "production");

        List<String> cssLinks = Arrays.asList(
                "/css/production.css");

        List<String> jsLinks = Arrays.asList(
                "/js/production.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/ajout-production")
    public String addproduction(Model model) {
        model.addAttribute("body", "ajout-production");

        List<String> cssLinks = Arrays.asList(
                "/css/production.css");

        List<String> jsLinks = Arrays.asList(
                "/js/production.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/produit")
    public String produit(Model model) {
        model.addAttribute("body", "produit");

        List<String> cssLinks = Arrays.asList(
                "/css/produit.css");

        List<String> jsLinks = Arrays.asList(
                "/js/produit.js",
                "/js/popper.min.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
        
        return "layout"; 
    }

    @GetMapping("/ajout-produit")
    public String addproduit(Model model) {
        model.addAttribute("body", "ajout-produit");

        List<String> cssLinks = Arrays.asList(
                "/css/produit.css");

        List<String> jsLinks = Arrays.asList(
                "/js/produit.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
        
        return "layout"; 
    }

    @GetMapping("/recette")
    public String recette(Model model) {
        model.addAttribute("body", "recette");
        List<String> cssLinks = Arrays.asList(
                "/css/recette.css");

        List<String> jsLinks = Arrays.asList(
                "/js/recette.js",
                "/js/popper.min.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
        
        return "layout"; 
    }

    @GetMapping("/ajout-recette")
    public String addrecette(Model model) {
        model.addAttribute("body", "ajout-recette");
        List<String> cssLinks = Arrays.asList(
                "/css/recette.css");

        List<String> jsLinks = Arrays.asList(
                "/js/recette.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
        
        return "layout"; 
    }

    @GetMapping("/vente")
    public String vente(Model model) {
        model.addAttribute("body", "vente");

        List<String> cssLinks = Arrays.asList(
                "/css/vente.css");

        List<String> jsLinks = Arrays.asList(
                "/js/vente.js",
                "/js/popper.min.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
       
        return "layout"; 
    }

    @GetMapping("/ajout-vente")
    public String vente(Model model) {
        model.addAttribute("body", "ajout-vente");

        List<String> cssLinks = Arrays.asList(
                "/css/vente.css");

        List<String> jsLinks = Arrays.asList(
                "/js/vente.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
       
        return "layout"; 
    }

    @GetMapping("/facture")
    public String facture(Model model) {
        model.addAttribute("body", "facture");

        List<String> cssLinks = Arrays.asList(
                "/css/facture.css");

        List<String> jsLinks = Arrays.asList(
                "/js/facture.js",
                "/js/popper.min.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
       
        return "layout"; 
    }

    @GetMapping("/liste-client")
    public String listeclient(Model model) {
        model.addAttribute("body", "liste-client");

        List<String> cssLinks = Arrays.asList(
                "/css/client.css");

        List<String> jsLinks = Arrays.asList(
                "/js/client.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
       
        return "layout"; 
    }

    @GetMapping("/ajout-client")
    public String addclient(Model model) {
        model.addAttribute("body", "ajout-client");

        List<String> cssLinks = Arrays.asList(
                "/css/client.css");

        List<String> jsLinks = Arrays.asList(
                "/js/client.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
       
        return "layout"; 
    }
}