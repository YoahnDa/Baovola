package com.baovola.baovola.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baovola.baovola.dto.ProduitDto;
import com.baovola.baovola.helpers.ProduitGetMapper;
import com.baovola.baovola.models.ProduitMois;
import com.baovola.baovola.models.Produits;
import com.baovola.baovola.repository.ProduitRepository;
import com.baovola.baovola.services.interfaces.IServiceProduits;

@Service
public class ServiceProduit implements IServiceProduits {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ProduitGetMapper produitMapper;

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public void updateProduit(Produits produit) {
       produitRepository.save(produit);
    }

    @Override
    public Produits findById(Long id) {
        return produitRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ingrédient introuvable : " + id));
    }

    @Override
    public List<ProduitDto> searchProduit(Double prixMin, Double prixMax, List<Long> idCategorie, String nom) {
        List<Produits> produits = searchByNom(nom);
        if(idCategorie != null && !idCategorie.isEmpty()){
            produits = filtreIdCategorie(idCategorie, produits);
        }
        produits = filtreByPrice(prixMax, prixMin, produits);
        return produits.stream()
            .map(produitMapper::toDto)
            .toList();
    }

    @Override
    public List<ProduitDto> getAllProduit() {
        return produitRepository.findAll().stream()
            .map(produitMapper::toDto)
            .toList();
    }

    @Override
    public void createProduit(Produits ingredient) {
        produitRepository.save(ingredient);
    }

    @Override
    public boolean existeByNom(String nom) {
        return produitRepository.existsByNomIgnoreCase(nom);
    }

    public boolean existeProduitById(Long id){
        return produitRepository.existsById(id);
    }

    @Override
    public List<Produits> filtreIdCategorie(List<Long> idCategorie, List<Produits> produits) {
        return produits.stream()
            .filter(prod -> idCategorie.contains(prod.getCategorie().getId()))
            .toList();
    }

    @Override
    public List<Produits> filtreByPrice(Double prixMax,Double prixMin , List<Produits> produits) {
        return produits.stream()
                .filter(p -> (prixMin == null || p.getPrixUnitaire() >= prixMin) &&
                             (prixMax == null || p.getPrixUnitaire() <= prixMax))
                .toList();
    }

    @Override
    public List<Produits> searchByNom(String nom) {
       return produitRepository.findByNomContainingIgnoreCase(nom); 
    }
    
}
