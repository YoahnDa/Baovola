package com.baovola.baovola.services.interfaces;

import java.util.List;

import com.baovola.baovola.dto.CategorieDto;
import com.baovola.baovola.dto.RecetteCompositionDto;
import com.baovola.baovola.dto.RecetteDto;
import com.baovola.baovola.models.Categorie;
import com.baovola.baovola.models.RecetteComposition;
import com.baovola.baovola.models.Recettes;

public interface IServiceRecetteComposition {
    public void deleteRecetteComposition(Long id);
    public void updateRecette(RecetteComposition unite);
    public void createRecettes(RecetteComposition unite);
    public RecetteComposition findById(Long id);
    public List<RecetteCompositionDto> getAllRecetteComposition(Long id);
    public boolean existeRecetteCompositionById(Long id);
}
