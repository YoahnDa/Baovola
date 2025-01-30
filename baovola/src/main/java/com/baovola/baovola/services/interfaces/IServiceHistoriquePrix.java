package com.baovola.baovola.services.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.baovola.baovola.dto.HistoriquePrixDto;
import com.baovola.baovola.dto.IngredientDto;
import com.baovola.baovola.dto.RecetteCompositionDto;
import com.baovola.baovola.models.HistoriquePrix;
import com.baovola.baovola.models.MatierePremiere;

public interface IServiceHistoriquePrix {
    public void deleteHistorique(Long id);
    public void updateHistorique(HistoriquePrix historique);
    public HistoriquePrix findById(Long id);
    public HistoriquePrix getHistoriqueRecent(Long id);
    public HistoriquePrix getHistoriqueDate(LocalDateTime date,Long id);
    public List<HistoriquePrixDto> searchIngredient(LocalDateTime dateModif,LocalDateTime dateFin);
    public List<HistoriquePrixDto> getAllHistorique(Long id);
    public void createHistorique(HistoriquePrix historique);
    public boolean existeById(Long id);
}
