package com.baovola.baovola.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baovola.baovola.dto.RecetteDto;
import com.baovola.baovola.helpers.RecetteMapper;
import com.baovola.baovola.models.Recettes;
import com.baovola.baovola.repository.RecetteRepository;
import com.baovola.baovola.services.interfaces.IServiceRecettes;

@Service
public class ServiceRecette implements IServiceRecettes {
    @Autowired
    private RecetteRepository recetteRepo;
    @Autowired
    private RecetteMapper recetteMaper;

    @Override
    public void deleteRecette(Long id) {
        recetteRepo.deleteById(id);
    }

    @Override
    public void updateRecette(Recettes unite) {
        recetteRepo.save(unite);
    }

    @Override
    public void createRecettes(Recettes unite) {
        recetteRepo.save(unite);
    }

    @Override
    public Recettes findById(Long id) {
        return recetteRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("recette introuvable : " + id));
    }

    @Override
    public List<RecetteDto> getAllRecette() {
        return recetteRepo.findAll().stream()
            .map(recetteMaper::toDto)
            .toList();
    }

    @Override
    public boolean existeRecetteById(Long id) {
        return recetteRepo.existsById(id);
    }
    
}
