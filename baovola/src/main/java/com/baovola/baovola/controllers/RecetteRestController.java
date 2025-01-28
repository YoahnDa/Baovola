package com.baovola.baovola.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baovola.baovola.dto.RecetteCompositionDto;
import com.baovola.baovola.dto.RecetteDto;
import com.baovola.baovola.models.RecetteComposition;
import com.baovola.baovola.services.implementations.ServiceIngredients;
import com.baovola.baovola.services.implementations.ServiceRecette;
import com.baovola.baovola.services.implementations.ServiceRecetteComposition;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/recettes")
public class RecetteRestController {
    @Autowired
    private ServiceRecette serviceRecette;
    @Autowired
    private ServiceRecetteComposition serviceComposition;
    @Autowired
    private ServiceIngredients serviceIngredient;

    @ResponseBody
    @GetMapping
    public List<RecetteDto> getAllRecettes(Model model){
        return serviceRecette.getAllRecette();
    }

    @ResponseBody
    @PostMapping("/ajout-ingredient")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> ajoutIngredient(@RequestBody Map<String,Object> recetteData) {
        Long id = Long.valueOf((String) recetteData.get("idRecette"));
        Long idIngredient = Long.valueOf((String) recetteData.get("idIngredient"));
        double quantite = Double.parseDouble((String) recetteData.get("quantite"));
        if(!serviceRecette.existeRecetteById(id) || !serviceIngredient.existeById(idIngredient)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ressources introuvable.");
        }
        RecetteComposition ingredients = new RecetteComposition();
        ingredients.setIngredient(serviceIngredient.findById(idIngredient));
        ingredients.setRecettes(serviceRecette.findById(id));
        ingredients.setQuantite(quantite);
        serviceComposition.createRecettes(ingredients);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ingredient ajouté avec succès.");
    }

    @ResponseBody
    @PostMapping("/modifier-ingredient")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> modifierIngredient(@RequestBody Map<String,Object> recetteData){
        Long id = Long.valueOf((String) recetteData.get("idComposition"));
        double quantite = Double.parseDouble((String)recetteData.get("quantite"));
        if(!serviceComposition.existeRecetteCompositionById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("La composition n'existe pas.");
        }
        RecetteComposition composition = serviceComposition.findById(id);
        composition.setQuantite(quantite);
        serviceComposition.updateRecette(composition);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body("La quantité a été modifiée avec succés.");
    }
    
    @ResponseBody
    @DeleteMapping("/delete-composition")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteComposition(@RequestParam Long id){
        if(!serviceComposition.existeRecetteCompositionById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Composition introuvable");
        }
        serviceComposition.deleteRecetteComposition(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body("Composition supprimer avec succès.");
    }

    @ResponseBody
    @GetMapping("/get-composition")
    @ResponseStatus(HttpStatus.OK)
    public List<RecetteCompositionDto> getComposition(@RequestParam(required = true) Long id){
        return serviceComposition.getAllRecetteComposition(id);
    }
}
