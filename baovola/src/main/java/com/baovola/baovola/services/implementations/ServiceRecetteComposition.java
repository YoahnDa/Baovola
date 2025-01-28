package com.baovola.baovola.services.implementations;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baovola.baovola.dto.RecetteCompositionDto;
import com.baovola.baovola.helpers.RecetteCompo;
import com.baovola.baovola.models.RecetteComposition;
import com.baovola.baovola.repository.RecetteCompositionRepository;
import com.baovola.baovola.services.interfaces.IServiceRecetteComposition;

@Service
public class ServiceRecetteComposition implements IServiceRecetteComposition {
    @Autowired
    private RecetteCompositionRepository recetteCompo;
    @Autowired
    private RecetteCompo mapperRecette;

    @Override
    public void deleteRecetteComposition(Long id) {
        recetteCompo.deleteById(id);
    }

    @Override
    public void updateRecette(RecetteComposition compo) {
       recetteCompo.save(compo);
    }

    @Override
    public void createRecettes(RecetteComposition unite) {
        recetteCompo.save(unite);
    }

    @Override
    public RecetteComposition findById(Long id) {
        return recetteCompo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Composition introuvable : " + id));
    }

    @Override
    public List<RecetteCompositionDto> getAllRecetteComposition(Long id) {
        return recetteCompo.findByRecettes_Id(id).stream()
            .map(mapperRecette::toDto)
            .toList();
    }

    @Override
    public boolean existeRecetteCompositionById(Long id) {
        return recetteCompo.existsById(id);
    }
    
}
