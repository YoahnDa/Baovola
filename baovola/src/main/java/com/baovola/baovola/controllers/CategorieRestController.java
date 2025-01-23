package com.baovola.baovola.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.baovola.baovola.dto.CategorieDto;
import com.baovola.baovola.dto.UniteDto;
import com.baovola.baovola.helpers.CategorieMapper;
import com.baovola.baovola.models.Categorie;
import com.baovola.baovola.models.Unite;
import com.baovola.baovola.services.implementations.ServiceCategory;

@RestController
@RequestMapping("/api/categorie")
public class CategorieRestController {
    @Autowired
    private ServiceCategory categorieServices;
    @Autowired
    private CategorieMapper categorieMapper;

    @ResponseBody
    @PostMapping("/ajout-categorie")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUnite(@RequestBody Map<String, Object> uniteData) {
        String nom = (String) uniteData.get("nom");
        if(nom == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Le nom est obligatoire");
        }else if(categorieServices.existeCategorie(nom)){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Le categorie avec le nom '" + nom + "' existe déjà.");
        }
        Categorie categorie = new Categorie();
        categorie.setNom(nom);
        categorieServices.createCategorie(categorie);
        return ResponseEntity.status(HttpStatus.CREATED).body("Categorie ajouté avec succès.");
    }

    @ResponseBody
    @PostMapping("/ajout-liste-categorie")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createListeUnite(@RequestBody List<Map<String, Object>> categorieData) {
        for(Map<String,Object> categorie : categorieData){
            String nom = (String) categorie.get("nom");
            if(nom == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Le nom est obligatoire");
            }else if(categorieServices.existeCategorie(nom)){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Le categorie avec le nom '" + nom + "' existe déjà.");
            }
            Categorie categories = new Categorie();
            categories.setNom(nom);
            categorieServices.createCategorie(categories);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Categorie ajouté avec succès.");
    }

    @ResponseBody
    @GetMapping("/getCategorie")
    public List<CategorieDto> getCategorie(Model model) {
        return categorieServices.getAllCategorie();
    }

    @ResponseBody
    @DeleteMapping("/deleteCategorie")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCategorie(@RequestParam(required = true) Long id){
        if(!categorieServices.existeCategorieById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Le categorie n'existe pas.");
        }
        categorieServices.deleteCategorie(id);
        return ResponseEntity.status(HttpStatus.OK)
                        .body("Categorie bien effacé.");
    }
}
