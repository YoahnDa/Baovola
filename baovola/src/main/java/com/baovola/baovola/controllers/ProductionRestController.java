package com.baovola.baovola.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baovola.baovola.dto.ProductionFilleDto;
import com.baovola.baovola.helpers.ProductionFilleMapper;
import com.baovola.baovola.helpers.ProductionMapper;
import com.baovola.baovola.models.ProductionFille;
import com.baovola.baovola.models.Productions;
import com.baovola.baovola.models.Status;
import com.baovola.baovola.services.implementations.ServiceProduction;
import com.baovola.baovola.services.implementations.ServiceProductionFille;
import com.baovola.baovola.services.implementations.ServiceProduit;

import ch.qos.logback.core.model.Model;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/productions")
public class ProductionRestController {
    @Autowired
    private ServiceProduction productions;
    @Autowired
    private ServiceProductionFille fille;
    @Autowired
    private ProductionFilleMapper mapperFille;
    @Autowired
    private ProductionMapper productionMapper;
    @Autowired
    private ServiceProduit produit;

    @ResponseBody
    @GetMapping
    public List<ProductionFilleDto> getProduction(Model model) {
        return fille.getAllProductionFille();
    }

    @ResponseBody
    @PostMapping("/insert-liste-production")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> insertProduction(@RequestBody List<Map<String,Object>> productionData){
        List<ProductionFille> productionFille = new ArrayList<ProductionFille>();
        for(Map<String,Object> data : productionData){
            Long idProduit = Long.valueOf((String) data.get("product"));
            double quantite = Double.parseDouble((String) data.get("quantity"));
            if(!produit.existeProduitById(idProduit)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produit introuvable.");
            }
            ProductionFille productionFille1 = new ProductionFille();
            productionFille1.setProduit(produit.findById(idProduit));
            productionFille1.setQuantite(quantite);
            productionFille.add(productionFille1);
        }
        Productions production = new Productions();
        productions.createProduction(production, productionFille);
        return ResponseEntity.status(HttpStatus.OK).body("Production ajouté avec succès.");
    }

    @ResponseBody
    @PostMapping("/add-produit")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addProduit(@RequestBody Map<String,Object> prodData){
        Long idProduit = Long.valueOf((String) prodData.get("product"));
        Long idProduction = Long.valueOf((String) prodData.get("idProductions"));
        double quantite = Double.parseDouble((String) prodData.get("quantite"));
        if(!produit.existeProduitById(idProduit)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produit introuvable.");
        }
        ProductionFille filles = new ProductionFille();
        filles.setProduit(produit.findById(idProduit));
        filles.setQuantite(quantite);
        filles.setProductions(productions.findById(idProduction));
        fille.createProductionFille(filles);
        return ResponseEntity.status(HttpStatus.CREATED).body("Production ajouté avec succès.");
    }

    @ResponseBody
    @PostMapping("/modif-produit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> modifProduit(@RequestBody Map<String,Object> prodData){
        Long idProduit = Long.valueOf((String) prodData.get("idFille"));
        double quantite = Double.parseDouble((String) prodData.get("quantite"));
        if(!fille.existeProductionFilleById(idProduit)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Production fille introuvable.");
        } 
        ProductionFille productionFille = fille.findById(idProduit);
        productionFille.setQuantite(quantite);
        fille.updateRecette(productionFille);
        return ResponseEntity.status(HttpStatus.OK).body("Production modifié avec succès.");
    }

    @ResponseBody
    @PostMapping("/modif-production")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> modifProduction(@RequestBody Map<String,Object> prodData){
        Long idProduction = Long.valueOf((String) prodData.get("idProduction"));
        Status stat = Status.valueOf((String) prodData.get("etat"));
        LocalDateTime dateProd = LocalDateTime.parse((String) prodData.get("dateProd"));
        if(!productions.existeProduction(idProduction)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Production introuvable.");
        }
        Productions prod = productions.findById(idProduction);
        prod.setDateProduction(dateProd);
        prod.setEtat(stat);
        productions.updateProduction(prod);
        return ResponseEntity.status(HttpStatus.OK).body("Production modifié avec succès.");
    }
    
    @ResponseBody
    @DeleteMapping("/delete-fille")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteFille(@RequestParam(required = true) Long id){
        if(!fille.existeProductionFilleById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Production fille introuvable.");
        }
        fille.deleteProductionFille(id);
        return ResponseEntity.status(HttpStatus.OK).body("Production fille supprimé.");
    }

    @ResponseBody
    @DeleteMapping("/delete-production")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProduction(@RequestParam(required = true) Long id){
        if(!productions.existeProduction(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Production introuvable.");
        }
        productions.deleteProduction(id);
        return ResponseEntity.status(HttpStatus.OK).body("Production supprimé.");
    }

    @ResponseBody
    @GetMapping("/get-productions")
    public List<ProductionFilleDto> getProductionFille(@RequestParam(required = true) Long id) {
        return fille.getAllProductionByParent(id);
    }
    
}
