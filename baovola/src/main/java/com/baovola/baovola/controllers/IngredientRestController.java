package com.baovola.baovola.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.baovola.baovola.services.implementations.ServiceIngredients;
import com.baovola.baovola.services.implementations.ServiceUnite;
import com.baovola.baovola.dto.IngredientDto;
import com.baovola.baovola.dto.UniteDto;
import com.baovola.baovola.helpers.IngredientMapper;
import com.baovola.baovola.helpers.UniteMapper;
import com.baovola.baovola.models.MatierePremiere;
import com.baovola.baovola.models.Unite;


@RestController
@RequestMapping("/api/ingredients")
public class IngredientRestController {
    @Autowired
    private ServiceIngredients ingredientService;
    @Autowired
    private IngredientMapper ingredientMapper;
    @Autowired
    private ServiceUnite uniteService;
    @Autowired
    private UniteMapper uniteMapper;
    @Autowired
    private ServiceIngredients serviceIngredients; 

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

    @ResponseBody
    @PostMapping("/ajout-ingredient")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createIngredient(@RequestBody Map<String,Object> ingredientData){
        Long idUnite = Long.valueOf((String)ingredientData.get("unit"));
        String nom = (String)ingredientData.get("nom");
        if(ingredientService.existeByNom(nom)){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body("L'ingrédient avec le nom '" + nom + "' existe déjà.");
        }
        MatierePremiere ingredient = new MatierePremiere();
        ingredient.setNom(nom);
        ingredient.setUnite(uniteService.findById(idUnite));
        ingredientService.createIngredients(ingredient);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ingrédient ajouté avec succès.");
    } 
    
    @ResponseBody
    @GetMapping("/getIngredient")
    public List<IngredientDto> getIngredient(Model model){
        return ingredientService.getAllIngredient().stream()
            .map(ingredientMapper::toDto)
            .toList();
    }

    @ResponseBody
    @PostMapping("/ajout-unite")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUnite(@RequestBody Map<String, Object> uniteData) {
        String nom = (String)uniteData.get("nom");
        String symbole = (String)uniteData.get("symbole");
        Unite unite = new Unite();
        unite.setNom(nom);
        unite.setSymbole(symbole);
        uniteService.createUnite(unite);
    }

    @ResponseBody
    @GetMapping("/getUnite")
    public List<UniteDto> getUnite(Model model) {
        return uniteService.getAllUnite();
    }

    @ResponseBody
    @PostMapping("/ajout-listeUnite")
    @ResponseStatus(HttpStatus.CREATED)
    public void createListeUnite(@RequestBody List<Map<String, Object>> uniteData) {
        for(Map<String, Object> data : uniteData){
            String nom = (String)data.get("nom");
            String symbole = (String)data.get("symbole");
            Unite unite = new Unite();
            unite.setNom(nom);
            unite.setSymbole(symbole);
            uniteService.createUnite(unite);
        }
    }
    
}