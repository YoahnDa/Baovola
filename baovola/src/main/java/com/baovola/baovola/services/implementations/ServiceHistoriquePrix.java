package com.baovola.baovola.services.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baovola.baovola.dto.HistoriquePrixDto;
import com.baovola.baovola.models.HistoriquePrix;
import com.baovola.baovola.repository.HistoriquePrixRepository;
import com.baovola.baovola.services.interfaces.IServiceHistoriquePrix;

public class ServiceHistoriquePrix implements IServiceHistoriquePrix {
    @Autowired
    private HistoriquePrixRepository histo;

    @Override
    public void deleteHistorique(Long id) {
        histo.deleteById(id);
    }

    @Override
    public void updateHistorique(HistoriquePrix historique) {
        histo.save(historique);
    }

    @Override
    public HistoriquePrix findById(Long id) {
        return histo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ingrédient introuvable : " + id));
    }


    @Override
    public List<HistoriquePrixDto> getAllHistorique(Long id) {
            return histo.
    }

    @Override
    public void createHistorique(HistoriquePrix historique) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createHistorique'");
    }

    @Override
    public boolean existeById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existeById'");
    }

    @Override
    public HistoriquePrix getHistoriqueRecent(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHistoriqueRecent'");
    }

    @Override
    public HistoriquePrix getHistoriqueDate(LocalDateTime date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHistoriqueDate'");
    }

    @Override
    public List<HistoriquePrixDto> searchIngredient(LocalDateTime dateModif, LocalDateTime dateFin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchIngredient'");
    }

    @Override
    public HistoriquePrix getHistoriqueDate(LocalDateTime date, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHistoriqueDate'");
    }
    
}
