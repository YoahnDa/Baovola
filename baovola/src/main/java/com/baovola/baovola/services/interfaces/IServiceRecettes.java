package com.baovola.baovola.services.interfaces;

import java.util.List;

import com.baovola.baovola.dto.RecetteCompositionDto;
import com.baovola.baovola.dto.RecetteDto;
import com.baovola.baovola.models.RecetteComposition;
import com.baovola.baovola.models.Recettes;

public interface IServiceRecettes {
    public void deleteRecette(Long id);
    public void updateRecette(Recettes unite);
    public void createRecettes(Recettes unite);
    public Recettes findById(Long id);
    public List<RecetteDto> getAllRecette();
    public boolean existeRecetteById(Long id);
}
