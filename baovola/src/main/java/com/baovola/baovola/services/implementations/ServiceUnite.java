package com.baovola.baovola.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baovola.baovola.dto.UniteDto;
import com.baovola.baovola.helpers.UniteMapper;
import com.baovola.baovola.models.MatierePremiere;
import com.baovola.baovola.models.Unite;
import com.baovola.baovola.repository.UniteRepository;
import com.baovola.baovola.services.interfaces.IServiceUnite;

import org.springframework.stereotype.Service;

@Service
public class ServiceUnite implements IServiceUnite {
    @Autowired
    private UniteRepository uniteRepository;

    @Autowired
    private UniteMapper uniteMapper;

    @Override
    public void deleteUnite(Long id) {
        uniteRepository.deleteById(id);
    }

    @Override
    public void updateUnite(Unite unite) {
        uniteRepository.save(unite);
    }

    @Override
    public void createUnite(Unite unite) {
        uniteRepository.save(unite);
    }

    public List<UniteDto> findNotIn(Long id) {
        return uniteRepository.findUnitesExcludingId(id).stream()
                .map(uniteMapper::toDto)
                .toList();
    }

    @Override
    public List<UniteDto> searchUnite(String nom) {
        List<Unite> liste = uniteRepository.searchByNom(nom);
        return liste.stream()
                .map(uniteMapper::toDto)
                .toList();
    }

    @Override
    public List<UniteDto> getAllUnite() {
        List<Unite> liste = uniteRepository.findAll();
        return liste.stream()
                .map(uniteMapper::toDto)
                .toList();
    }

    @Override
    public Unite findById(Long id) {
        return uniteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ingrédient introuvable : " + id));
    }

}
