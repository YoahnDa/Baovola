package com.baovola.baovola.services.interfaces;

import java.util.List;

import com.baovola.baovola.dto.ProduitDto;
import com.baovola.baovola.models.Produits;

public interface IServiceProduits {
    public void deleteProduit(Long id);
    public void updateProduit(Produits produit);
    public Produits findById(Long id);
    public List<ProduitDto> searchProduit(Double prixMin,Double prixMax,List<Long> idCategorie, String nom);
    public List<ProduitDto> getAllProduit();
    public void createProduit(Produits produits);
    public boolean existeByNom(String nom);
    public boolean existeProduitById(Long id);
    public List<Produits> searchByNom(String nom);
    public List<Produits> filtreIdCategorie(List<Long> idCategorie , List<Produits> produits);
    public List<Produits> filtreByPrice(Double prixMax,Double prixMin , List<Produits> produits);
}
