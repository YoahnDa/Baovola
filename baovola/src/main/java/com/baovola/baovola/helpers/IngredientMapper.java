package com.baovola.baovola.helpers;

import com.baovola.baovola.models.Unite;
import com.baovola.baovola.models.MatierePremiere;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

import javax.xml.stream.events.EntityDeclaration;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    @Mapping(source = "unite", target = "unite")
    MatierePremiere toEntity(IngredientDto dto);
    @Mapping(source = "unite", target = "unite")
    IngredientDto toDTO(MatierePremiere ingredient);
    @Mapping(target = "matierePremiere", ignore = true)
    UniteDto toUniteDTO(Unite unite);
    @Mapping(target = "matierePremiere", ignore = true)
    Category toUniteEntity(UniteDto uniteDto);
}
