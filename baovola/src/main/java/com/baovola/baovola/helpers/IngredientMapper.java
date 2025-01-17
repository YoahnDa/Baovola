package com.baovola.baovola.helpers;

import com.baovola.baovola.models.MatierePremiere;
import com.baovola.baovola.dto.IngredientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = UniteMapper.class)
public interface IngredientMapper {
    @Mapping(source = "unite", target = "unite")
    MatierePremiere toEntity(IngredientDto dto);
    @Mapping(source = "unite", target = "unite")
    IngredientDto toDto(MatierePremiere ingredient);
}
