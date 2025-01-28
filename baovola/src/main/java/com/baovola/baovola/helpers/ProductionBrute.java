package com.baovola.baovola.helpers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.baovola.baovola.dto.ProductionDto;
import com.baovola.baovola.models.Productions;

@Mapper(componentModel = "spring")
public interface ProductionBrute {
    @Mapping(target = "productionFille" , ignore = true)
    ProductionDto toDto(Productions prod);
    @Mapping(target = "productionFille" , ignore = true)
    Productions toEntity(ProductionDto prod);
}
