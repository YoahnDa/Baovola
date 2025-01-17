package com.baovola.baovola.services.interfaces;

import java.util.List;

import com.baovola.baovola.dto.UniteDto;
import com.baovola.baovola.models.Unite;

public interface IServiceUnite {
    public void deleteUnite(Long id);
    public void updateUnite(Unite unite);
    public void createUnite(Unite unite);
    public Unite findById(Long id);
    public List<UniteDto> searchUnite(String nom);
    public List<UniteDto> getAllUnite();
}
