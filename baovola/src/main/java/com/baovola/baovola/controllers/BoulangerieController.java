package com.baovola.baovola.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baovola.baovola.dto.IngredientDto;
import com.baovola.baovola.dto.ProductionDto;
import com.baovola.baovola.dto.ProductionFilleDto;
import com.baovola.baovola.dto.ProduitDto;
import com.baovola.baovola.dto.RecetteCompositionDto;
import com.baovola.baovola.dto.RecetteDto;
import com.baovola.baovola.dto.UniteDto;
import com.baovola.baovola.helpers.IngredientMapper;
import com.baovola.baovola.helpers.ProductionMapper;
import com.baovola.baovola.helpers.ProduitGetMapper;
import com.baovola.baovola.helpers.RecetteMapper;
import com.baovola.baovola.models.Status;
import com.baovola.baovola.services.implementations.ServiceCategory;
import com.baovola.baovola.services.implementations.ServiceIngredients;
import com.baovola.baovola.services.implementations.ServiceProduction;
import com.baovola.baovola.services.implementations.ServiceProductionFille;
import com.baovola.baovola.services.implementations.ServiceProduit;
import com.baovola.baovola.services.implementations.ServiceRecette;
import com.baovola.baovola.services.implementations.ServiceRecetteComposition;
import com.baovola.baovola.services.implementations.ServiceUnite;

@Controller
public class BoulangerieController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private ServiceUnite uniteService;
    @Autowired
    private ServiceIngredients ingredientService;
    @Autowired
    private IngredientMapper ingredientMapper;
    @Autowired
    private ServiceCategory serviceCategory;
    @Autowired
    private ServiceProduit serviceProduit;
    @Autowired
    private ProduitGetMapper produitMapper;
    @Autowired
    private ServiceRecette serviceRecette;
    @Autowired
    private ServiceRecetteComposition serviceComposition;
    @Autowired
    private RecetteMapper recetteMapper;
    @Autowired
    private ServiceProduction production;
    @Autowired
    private ServiceProductionFille fille;
    @Autowired
    private ProductionMapper mapperProd;

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
        model.addAttribute("unite", uniteService.getAllUnite());
        List<IngredientDto> ingredients = ingredientService.getAllIngredient().stream()
                .map(ingredientMapper::toDto)
                .toList();
        model.addAttribute("ingredient", ingredients);
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

    @GetMapping("/modif-ingredient")
    public String modifIngredient(@RequestParam ("id") Long id, Model model) {
        model.addAttribute("body", "ingredients/ajout-ingredient");
        IngredientDto ingredient = ingredientMapper.toDto(ingredientService.findById(id));
        List<UniteDto> unite = uniteService.findNotIn(id);
        model.addAttribute("unite", unite);
        model.addAttribute("ingredient", ingredient);
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
        model.addAttribute("productions", fille.getAllProductionFilleDetail());
        model.addAttribute("ingredient", ingredientService.getAllIngredient().stream().map(ingredientMapper::toDto).toList());
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
        model.addAttribute("produits", serviceProduit.getAllProduit());
        List<String> cssLinks = Arrays.asList(
                "/css/production.css","https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css","/css/ajout-production.css");

        List<String> jsLinks = Arrays.asList(
                "/js/production.js","/js/ajout-production.js","https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js","https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js","https://code.jquery.com/jquery-3.5.1.min.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/modif-production")
    public String modifProduction(@RequestParam(required = true) Long id, Model model) {
        model.addAttribute("body", "productions/modif-productions");
        ProductionDto liste = mapperProd.toDto(production.findById(id));
        model.addAttribute("productions", liste);
        model.addAttribute("etat", Status.values());
        model.addAttribute("produits", serviceProduit.findProduitNotInProd(liste.getProductionFille()));
        List<String> cssLinks = Arrays.asList(
                "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css","/css/modif-production.css");

        List<String> jsLinks = Arrays.asList(
                "/js/modif-production.js","https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js","https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js","https://code.jquery.com/jquery-3.5.1.min.js");

        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);

        return "layout";
    }

    @GetMapping("/produit")
    public String produit(Model model) {
        model.addAttribute("body", "produits/produit");
        model.addAttribute("categorie", serviceCategory.getAllCategorie());
        model.addAttribute("produit", serviceProduit.getAllProduit());
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
        model.addAttribute("categorie", serviceCategory.getAllCategorie());
        List<String> cssLinks = Arrays.asList(
                "/css/produit.css");

        List<String> jsLinks = Arrays.asList(
                "/js/produit.js");
        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
        return "layout"; 
    }

    @GetMapping("/modif-produit")
    public String modifProduit(@RequestParam(required = true) Long id, Model model) {
        model.addAttribute("body", "produits/ajout-produit");
        model.addAttribute("produit",produitMapper.toDto(serviceProduit.findById(id)));
        model.addAttribute("categorie", serviceCategory.getAllCategorie());
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
        model.addAttribute("recettes", serviceRecette.getAllRecette());
        return "layout"; 
    }

    @GetMapping("/ajout-recette")
    public String addrecette(@RequestParam (required = true) Long id,Model model) {
        model.addAttribute("body", "recettes/ajout-recette");
        List<String> cssLinks = Arrays.asList(
                "/css/recette.css","/css/liste-recette.css");

        List<String> jsLinks = Arrays.asList(
                "/js/liste-recette.js");
        List<RecetteCompositionDto> recette = serviceComposition.getAllRecetteComposition(id);
        model.addAttribute("cssLinks", cssLinks);
        model.addAttribute("jsLinks", jsLinks);
        model.addAttribute("composition", recette);
        model.addAttribute("recettes", recetteMapper.toDto(serviceRecette.findById(id)));
        model.addAttribute("ingredients", ingredientService.ingredientNotInRecette(recette));
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