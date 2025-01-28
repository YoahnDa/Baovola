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
    public List<IngredientDto> searchIngredients(@RequestParam(required = false) List<Long> unitIds,
            @RequestParam(required = false,defaultValue = "") String nom) {
        return ingredientService.searchIngredient(unitIds, nom);
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
    public ResponseEntity<String> createIngredient(@RequestBody Map<String, Object> ingredientData) {
        Long idUnite = Long.valueOf((String) ingredientData.get("unit"));
        String nom = (String) ingredientData.get("nom");
        if (ingredientService.existeByNom(nom)) {
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
    @PostMapping("/ajout-liste-ingredient")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createIngredient(@RequestBody List<Map<String, Object>> ingredientData) {
            for(Map<String,Object> ingredient : ingredientData){
                Long idUnite = Long.valueOf((String) ingredient.get("unit"));
            String nom = (String) ingredient.get("nom");
            if (ingredientService.existeByNom(nom)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("L'ingrédient avec le nom '" + nom + "' existe déjà.");
            }
            MatierePremiere ingredients = new MatierePremiere();
            ingredients.setNom(nom);
            ingredients.setUnite(uniteService.findById(idUnite));
            ingredientService.createIngredients(ingredients);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Ingrédient ajouté avec succès.");
    }


    @ResponseBody
    @PostMapping("/modification")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> modifIngredient(@RequestBody Map<String, Object> ingredientData) {
        Long id = Long.valueOf((String) ingredientData.get("idIngredient"));
        Long idUnite = Long.valueOf((String) ingredientData.get("unit"));
        String nom = (String) ingredientData.get("nom");
        MatierePremiere ingredient = ingredientService.findById(id);
        if (!ingredient.getNom().equalsIgnoreCase(nom)) {
            if (ingredientService.existeByNom(nom)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("L'ingrédient avec le nom '" + nom + "' existe déjà.");
            }
        }
        ingredient.setNom(nom);
        ingredient.setUnite(uniteService.findById(idUnite));
        ingredientService.updateIngredient(ingredient);
        return ResponseEntity.status(HttpStatus.OK).body("Ingrédient modifié avec succès.");
    }

    @ResponseBody
    @DeleteMapping("/supprimer-ingredient")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteIngredient(@RequestParam String id) {
        Long idUnite = Long.valueOf(id);
        if (ingredientService.findById(idUnite) != null) {
            ingredientService.deleteIngredient(idUnite);
            return ResponseEntity.status(HttpStatus.OK).body("Ingrédient supprimé avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingredient inexistante.");
        }
    }

    @ResponseBody
    @GetMapping("/getIngredient")
    public List<IngredientDto> getIngredient(Model model) {
        return ingredientService.getAllIngredient().stream()
                .map(ingredientMapper::toDto)
                .toList();
    }

    @ResponseBody
    @GetMapping("/searchByNom")
    public List<IngredientDto> getIngredient(@RequestParam String nom) {
        return ingredientService.searchIngredient(null,nom);
    }

    @ResponseBody
    @PostMapping("/ajout-unite")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUnite(@RequestBody Map<String, Object> uniteData) {
        String nom = (String) uniteData.get("nom");
        String symbole = (String) uniteData.get("symbole");
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
        for (Map<String, Object> data : uniteData) {
            String nom = (String) data.get("nom");
            String symbole = (String) data.get("symbole");
            Unite unite = new Unite();
            unite.setNom(nom);
            unite.setSymbole(symbole);
            uniteService.createUnite(unite);
        }
    }

}