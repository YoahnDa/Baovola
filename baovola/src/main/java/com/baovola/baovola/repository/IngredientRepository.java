package com.baovola.baovola.repository;

import com.baovola.baovola.models.MatierePremiere;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<MatierePremiere, Long> {
    List<MatierePremiere> findByNomIgnoreCaseContaining(String nom);
    boolean existsByNomIgnoreCase(@Param("nom") String nom);
    List<MatierePremiere> findBySupplementIsNotNull();
    List<MatierePremiere> findByIdIn(List<Long> ids);
}
