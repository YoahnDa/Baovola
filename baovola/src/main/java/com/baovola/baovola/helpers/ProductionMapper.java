package com.baovola.baovola.helpers;

import org.mapstruct.Mapper;

import com.baovola.baovola.dto.ProductionDto;
import com.baovola.baovola.models.Productions;

@Mapper(componentModel ="spring" , uses = {ProductionFilleMapper.class} )
public interface ProductionMapper {
    ProductionDto toDto(Productions prod);
    Productions toEntity(ProductionDto prod);
}
