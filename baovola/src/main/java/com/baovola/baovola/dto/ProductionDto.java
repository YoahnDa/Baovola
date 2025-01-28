package com.baovola.baovola.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.baovola.baovola.models.Status;
import lombok.Data;

@Data
public class ProductionDto {
    private Long id;
    private LocalDateTime dateProduction;
    private Status etat = Status.WAIT;
    private List<ProductionFilleDto> productionFille;
}
