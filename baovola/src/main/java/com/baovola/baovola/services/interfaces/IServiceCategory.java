package com.baovola.baovola.services.interfaces;

import java.util.List;

import com.baovola.baovola.dto.CategorieDto;
import com.baovola.baovola.models.Categorie;

public interface IServiceCategory {
    public void deleteCategorie(Long id);
    public void updateCategorie(Categorie unite);
    public void createCategorie(Categorie unite);
    public Categorie findById(Long id);
    public List<CategorieDto> searchCategorie(String nom);
    public List<CategorieDto> getAllCategorie();
    public boolean existeCategorie(String nom);
    public boolean existeCategorieById(Long id);
}
