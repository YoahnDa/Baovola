package com.baovola.baovola.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baovola.baovola.dto.IngredientDto;
import com.baovola.baovola.dto.ProduitDto;
import com.baovola.baovola.models.Produits;
import com.baovola.baovola.services.implementations.ServiceCategory;
import com.baovola.baovola.services.implementations.ServiceProduit;

import ch.qos.logback.core.model.Model;

@RestController
@RequestMapping("api/produits")
public class ProduitRestController {
    @Autowired
    private ServiceProduit serviceProduit;
    @Autowired
    private ServiceCategory serviceCategory;

    @ResponseBody
    @GetMapping
    public List<ProduitDto> getAllProduct(Model model){
        return serviceProduit.getAllProduit();
    }

    @ResponseBody
    @PostMapping("/create-produit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createProduit(@RequestBody Map<String, Object> produitData){
        String nom = (String) produitData.get("nom");
        double prix = Double.parseDouble((String) produitData.get("prix"));
        Long idCategorie = Long.valueOf((String) produitData.get("idCategorie"));
        if(!serviceCategory.existeCategorieById(idCategorie)){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Le categorie n'existe pas");
        }

        if(serviceProduit.existeByNom(nom)){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Le produit avec le nom '" + nom + "' existe déjà.");
        }

        if(prix <=0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Problème dans les données");
        }

        Produits product = new Produits();
        product.setNom(nom);
        product.setPrixUnitaire(prix);
        product.setCategorie(serviceCategory.findById(idCategorie));
        serviceProduit.createProduit(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produit ajouté avec succès.");
    }

    @ResponseBody
    @PostMapping("/create-list-produit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createListProduit(@RequestBody List<Map<String,Object>> produitData){
        for(Map<String,Object> produit : produitData){
            String nom = (String) produit.get("nom");
            double prix = Double.parseDouble((String) produit.get("prix"));
            Long idCategorie = Long.valueOf((String) produit.get("idCategorie"));
            if(!serviceCategory.existeCategorieById(idCategorie)){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Le categorie n'existe pas");
            }

            if(serviceProduit.existeByNom(nom)){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Le produit avec le nom '" + nom + "' existe déjà.");
            }

            if(prix <=0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Problème dans les données");
            }

            Produits product = new Produits();
            product.setNom(nom);
            product.setPrixUnitaire(prix);
            product.setCategorie(serviceCategory.findById(idCategorie));
            serviceProduit.createProduit(product);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Produit ajouté avec succès.");
    }

    @ResponseBody
    @DeleteMapping("/supprimer-produit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProduit(@RequestParam Long id){
        serviceProduit.deleteProduit(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produit supprimé avec succès");
    }

    @ResponseBody
    @GetMapping("/search")
    public List<ProduitDto> searchProduit(@RequestParam(required = false) List<Long> idCategorie,
            @RequestParam(required = false,defaultValue = "") String nom, @RequestParam(required = false , defaultValue = "") String prixMin , @RequestParam(required = false , defaultValue = "") String prixMax){
                Double parsedPrixMin = prixMin.isEmpty() || prixMin == null ? null : Double.valueOf(prixMin);
                Double parsedPrixMax = prixMax.isEmpty() || prixMax == null ? null : Double.valueOf(prixMax);
                return serviceProduit.searchProduit(parsedPrixMin, parsedPrixMax, idCategorie, nom);
    }

    @ResponseBody
    @PostMapping("/modifier-produit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> modifProduit(@RequestBody Map<String,Object> produitData){
        Long id = Long.valueOf((String) produitData.get("idProduit"));
        String nom = (String) produitData.get("nom");
        double prix = Double.parseDouble((String) produitData.get("prix"));
        Long idCategorie = Long.valueOf((String) produitData.get("idCategorie"));

        if(!serviceProduit.existeProduitById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Le produit n'existe pas");
        }

        Produits product = serviceProduit.findById(id);

        if(serviceProduit.existeByNom(nom) && !product.getNom().equalsIgnoreCase(nom)){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Le produit avec le nom '" + nom + "' existe déjà.");
        }

        if(!serviceCategory.existeCategorieById(idCategorie)){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Le categorie n'existe pas");
        }

        if(prix <=0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Problème dans les données");
        }
        
        product.setNom(nom);
        product.setPrixUnitaire(prix);
        product.setCategorie(serviceCategory.findById(idCategorie));
        serviceProduit.updateProduit(product);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Produit modifié avec succès.");
    }
}
