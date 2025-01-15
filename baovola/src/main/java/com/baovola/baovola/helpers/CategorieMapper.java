package com.baovola.baovola.helpers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.baovola.baovola.dto.CategorieDto;
import com.baovola.baovola.models.Categorie;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    @Mapping(target = "produits" , ignore = true)
    Categorie toEntity(CategorieDto categorieDto);
    @Mapping(target = "produits" , ignore = true)
    CategorieDto toDto(Categorie categorie);
}
