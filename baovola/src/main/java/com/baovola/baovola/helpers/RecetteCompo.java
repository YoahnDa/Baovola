package com.baovola.baovola.helpers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.baovola.baovola.dto.RecetteCompositionDto;
import com.baovola.baovola.models.RecetteComposition;

@Mapper(componentModel = "spring",uses = {IngredientMapper.class,ProduitGetMapper.class})
public interface RecetteCompo {
    @Mapping(target = "recettes",ignore = true)
    RecetteComposition toEntity(RecetteCompositionDto dto);
    @Mapping(target = "recettes",ignore = true)
    RecetteCompositionDto toDto(RecetteComposition entity);
}
