package com.baovola.baovola.services.implementations;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baovola.baovola.dto.CategorieDto;
import com.baovola.baovola.helpers.CategorieMapper;
import com.baovola.baovola.models.Categorie;
import com.baovola.baovola.repository.CategoryRepository;
import  com.baovola.baovola.services.interfaces.IServiceCategory;
@Service
public class ServiceCategory implements IServiceCategory {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategorieMapper categorieMapper;

    @Override
    public void deleteCategorie(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategorie(Categorie unite) {
        categoryRepository.save(unite);
    }

    @Override
    public void createCategorie(Categorie unite) {
        categoryRepository.save(unite);
    }

    @Override
    public List<CategorieDto> searchCategorie(String nom) {
        List<Categorie> liste = categoryRepository.searchByNom(nom);
        return liste.stream()
            .map(categorieMapper::toDto)
            .toList();
    }

    @Override
    public List<CategorieDto> getAllCategorie() {
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

    @Override
    public boolean existeCategorie(String nom) {
       return categoryRepository.existsByNomIgnoreCase(nom);
    }

    @Override
    public boolean existeCategorieById(Long id) {
        return categoryRepository.existsById(id);
    }
    
}
