package com.baovola.baovola.helpers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.baovola.baovola.dto.ProduitDto;
import com.baovola.baovola.models.Produits;

@Mapper(componentModel = "spring" , uses = CategorieMapper.class)
public interface ProduitGetMapper {
    @Mapping(target = "productions" , ignore = true)
    @Mapping(target = "commande" , ignore = true)
    @Mapping(target = "recette" , ignore = true)
    ProduitDto toDto(Produits produit);
    @Mapping(target = "productions" , ignore = true)
    @Mapping(target = "commande" , ignore = true)
    @Mapping(target = "recette" , ignore = true)
    @Mapping(target = "supplements" , ignore = true)
    @Mapping(target = "preference" , ignore = true)
    Produits toEntity(ProduitDto productDto);
}
