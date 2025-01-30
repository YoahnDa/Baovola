package com.baovola.baovola.helpers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.baovola.baovola.dto.HistoriquePrixDto;
import com.baovola.baovola.models.HistoriquePrix;

@Mapper(componentModel = "spring")
public interface HistoriquePrixMapper {
    @Mapping(target ="produits" , ignore = true )
    HistoriquePrix toEntity(HistoriquePrixDto historique);
    @Mapping(target ="produits" , ignore = true )
    HistoriquePrixDto toDto(HistoriquePrix historique);
}
