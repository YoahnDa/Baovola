package com.baovola.baovola.services.interfaces;

import java.util.List;

import com.baovola.baovola.dto.CategorieDto;
import com.baovola.baovola.models.Categorie;

public interface IServiceCategory {
    public void deleteUnite(Long id);
    public void updateUnite(Categorie unite);
    public void createUnite(Categorie unite);
    public Categorie findById(Long id);
    public List<CategorieDto> searchUnite(String nom);
    public List<CategorieDto> getAllUnite();
}
