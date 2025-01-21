package com.baovola.baovola.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.baovola.baovola.services.implementations.ServiceUnite;

@Controller
public class BoulangerieController {

    @Autowired
    private ServiceUnite uniteService;

    @GetMapping("/dashboard")
    public String accueil(Model model) {
        model.addAttribute("body", "accueil/dashboard");

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
        model.addAttribute("body", "fournisseur/fournisseur");
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
        model.addAttribute("body", "fournisseur/ajout-fournisseur");
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
        model.addAttribute("body", "ingredients/ingredient");

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
        model.addAttribute("body", "ingredients/ajout-ingredient");
        model.addAttribute("unite", uniteService.getAllUnite());
        
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
        model.addAttribute("body", "productions/production");

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
        model.addAttribute("body", "productions/ajout-production");

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
        model.addAttribute("body", "produits/produit");

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
        model.addAttribute("body", "produits/ajout-produit");

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
        model.addAttribute("body", "recettes/recette");
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
        model.addAttribute("body", "recettes/ajout-recette");
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
        model.addAttribute("body", "ventes/vente");

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
    public String ajoutvente(Model model) {
        model.addAttribute("body", "ventes/ajout-vente");

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
        model.addAttribute("body", "ventes/facture");

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
        model.addAttribute("body", "client/liste-client");

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
        model.addAttribute("body", "client/ajout-client");

        List<String> cssLinks = Arrays.asList(
                "/css/client.css");

        List<String> jsLinks = Arrays.asList(
                "/js/client.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
       
        return "layout"; 
    }
}