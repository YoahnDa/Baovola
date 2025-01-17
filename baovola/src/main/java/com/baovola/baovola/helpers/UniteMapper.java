package com.baovola.baovola.helpers;

import com.baovola.baovola.models.Unite;
import com.baovola.baovola.dto.UniteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UniteMapper {
    @Mapping(target = "ingredient", ignore = true)
    UniteDto toDto(Unite unite);
    @Mapping(target = "matierePremiere", ignore = true)
    Unite toEntity(UniteDto uniteDto);
}
