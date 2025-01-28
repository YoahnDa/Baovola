package com.baovola.baovola.helpers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.baovola.baovola.dto.ProductionFilleDto;
import com.baovola.baovola.models.ProductionFille;

@Mapper(componentModel = "spring" , uses = {ProductionBrute.class,ProduitGetMapper.class})
public interface ProductionFilleDetail {
    ProductionFilleDto toDto(ProductionFille fille);
    ProductionFille toEntity(ProductionFilleDto fille);
}
