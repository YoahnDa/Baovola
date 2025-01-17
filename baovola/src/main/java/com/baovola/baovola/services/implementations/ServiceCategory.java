package com.baovola.baovola.services.implementations;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baovola.baovola.dto.CategorieDto;
import com.baovola.baovola.helpers.CategorieMapper;
import com.baovola.baovola.models.Categorie;
import com.baovola.baovola.repository.CategoryRepository;
import  com.baovola.baovola.services.interfaces.IServiceCategory;

public class ServiceCategory implements IServiceCategory {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategorieMapper categorieMapper;

    @Override
    public void deleteUnite(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateUnite(Categorie unite) {
        categoryRepository.save(unite);
    }

    @Override
    public void createUnite(Categorie unite) {
        categoryRepository.save(unite);
    }

    @Override
    public List<CategorieDto> searchUnite(String nom) {
        List<Categorie> liste = categoryRepository.searchByNom(nom);
        return liste.stream()
            .map(categorieMapper::toDto)
            .toList();
    }

    @Override
    public List<CategorieDto> getAllUnite() {
        List<Categorie> liste = categoryRepository.findAll();
        return liste.stream()
            .map(categorieMapper::toDto)
            .toList();
    }

    @Override
    public Categorie findById(Long id) {
       return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ingrédient introuvable : " + id));
    }
    
}
