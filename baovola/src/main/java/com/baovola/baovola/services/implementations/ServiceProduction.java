package com.baovola.baovola.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baovola.baovola.dto.ProductionDto;
import com.baovola.baovola.helpers.ProductionMapper;
import com.baovola.baovola.models.ProductionFille;
import com.baovola.baovola.models.Productions;
import com.baovola.baovola.repository.ProductionRepository;
import com.baovola.baovola.services.interfaces.IServiceProduction;

@Service
public class ServiceProduction implements IServiceProduction {
    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private ProductionMapper productionMapper;

    @Override
    public void deleteProduction(Long id) {
        productionRepository.deleteById(id);
    }

    @Override
    public void updateProduction(Productions prod) {
        productionRepository.save(prod);
    }

    @Override
    public void createProduction(Productions prod) {
       productionRepository.save(prod);
    }

    @Override
    public Productions findById(Long id) {
        return productionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("recette introuvable : " + id));
    }

    @Override
    public List<ProductionDto> getAllProduction() {
        return productionRepository.findAll().stream()
            .map(productionMapper::toDto)
            .toList();
    }

    @Override
    public boolean existeProduction(Long id) {
       return productionRepository.existsById(id);
    }

    @Override
    public void createProduction(Productions prod, List<ProductionFille> fille) {
        Productions savedProductions = productionRepository.save(prod);
        for(ProductionFille prodFille : fille){
            prodFille.setProductions(savedProductions);
        }
        savedProductions.setProductionFille(fille);
        updateProduction(savedProductions);
    }
    
}
