package com.baovola.baovola.services.interfaces;

import java.util.List;

import com.baovola.baovola.dto.ProductionDto;
import com.baovola.baovola.dto.ProductionFilleDto;
import com.baovola.baovola.models.ProductionFille;
import com.baovola.baovola.models.Productions;

public interface IServiceProduction {
    public void deleteProduction(Long id);
    public void updateProduction(Productions prod);
    public void createProduction(Productions prod);
    public void createProduction(Productions prod,List<ProductionFille> fille);
    public Productions findById(Long id);
    public List<ProductionDto> getAllProduction();
    public boolean existeProduction(Long id);
}
