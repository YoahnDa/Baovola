package com.baovola.baovola.helpers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.baovola.baovola.dto.ProductionFilleDto;
import com.baovola.baovola.models.ProductionFille;
import com.baovola.baovola.models.Productions;

@Mapper(componentModel = "spring" , uses = {ProduitGetMapper.class})
public interface ProductionFilleMapper {
    @Mapping(target = "productions" , ignore = true )
    ProductionFilleDto toDto(ProductionFille fille);
    @Mapping(target = "productions" , ignore = true )
    ProductionFille toEntity(ProductionFilleDto fille);
}
