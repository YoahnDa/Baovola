package com.baovola.baovola.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baovola.baovola.dto.ProductionFilleDto;
import com.baovola.baovola.helpers.ProductionFilleDetail;
import com.baovola.baovola.helpers.ProductionFilleMapper;
import com.baovola.baovola.models.ProductionFille;
import com.baovola.baovola.repository.ProductionFilleRepository;
import com.baovola.baovola.services.interfaces.IServiceProductionFille;

@Service
public class ServiceProductionFille implements IServiceProductionFille {
    @Autowired
    private ProductionFilleMapper mapper;
    @Autowired
    private ProductionFilleRepository repository;
    @Autowired
    private ProductionFilleDetail detail;

    @Override
    public void deleteProductionFille(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateRecette(ProductionFille unite) {
        repository.save(unite);
    }

    @Override
    public void createProductionFille(ProductionFille unite) {
        repository.save(unite);
    }

    @Override
    public ProductionFille findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("recette introuvable : " + id));
    }

    @Override
    public List<ProductionFilleDto> getAllProductionFille() {
        return repository.findAll().stream()
            .map(mapper::toDto)
            .toList();
    }

    @Override
    public List<ProductionFilleDto> getAllProductionByParent(Long id) {
        return repository.findByProductions_Id(id).stream()
            .map(mapper::toDto)
            .toList();
    }

    @Override
    public boolean existeProductionFilleById(Long id) {
       return repository.existsById(id);
    }

    @Override
    public List<ProductionFilleDto> getAllProductionFilleDetail() {
        return repository.findAll().stream()
            .map(detail::toDto)
            .toList();
    }
    
}
