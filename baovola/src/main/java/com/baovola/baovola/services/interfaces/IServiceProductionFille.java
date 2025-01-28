package com.baovola.baovola.services.interfaces;

import java.util.List;

import com.baovola.baovola.dto.ProductionFilleDto;
import com.baovola.baovola.dto.RecetteCompositionDto;
import com.baovola.baovola.models.ProductionFille;
import com.baovola.baovola.models.RecetteComposition;

public interface IServiceProductionFille {
    public void deleteProductionFille(Long id);
    public void updateRecette(ProductionFille unite);
    public void createProductionFille(ProductionFille unite);
    public ProductionFille findById(Long id);
    public List<ProductionFilleDto> getAllProductionFille();
    public List<ProductionFilleDto> getAllProductionFilleDetail();
    public List<ProductionFilleDto> getAllProductionByParent(Long id);
    public boolean existeProductionFilleById(Long id);
}
