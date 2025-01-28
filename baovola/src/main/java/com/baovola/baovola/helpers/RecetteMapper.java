package com.baovola.baovola.helpers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.baovola.baovola.dto.RecetteDto;
import com.baovola.baovola.models.Recettes;

@Mapper(componentModel = "spring" , uses = {ProduitGetMapper.class})
public interface RecetteMapper {
    @Mapping(target = "composition" , ignore = true)
    Recettes toEntity(RecetteDto dto);
    @Mapping(target = "composition" , ignore = true)
    RecetteDto toDto(Recettes entity);
}
