package com.baovola.baovola.repository;

import com.baovola.baovola.models.MatierePremiere;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<MatierePremiere,Long> {
    @Query("SELECT u FROM MatierePremiere  u WHERE u.nom LIKE %:keyword%")
    List<MatierePremiere> findByNomContaining(@Param("keyword") String keyword);
    @Query("SELECT m FROM MatierePremiere m WHERE m.unite.id IN :uniteIds AND (:nom IS NULL OR LOWER(m.nom) LIKE LOWER(CONCAT('%', :nom, '%')))")
    List<MatierePremiere> findByUniteIdsAndName(@Param("uniteIds") List<Long> uniteIds, @Param("nom") String nom);
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN TRUE ELSE FALSE END FROM Ingredient i WHERE LOWER(i.nom) = LOWER(:nom)")
    boolean existsByNomIgnoreCase(@Param("nom")String nom);
    List<MatierePremiere> findBySupplementIsNotNull();
    List<MatierePremiere> findByIdIn(List<Long> ids);
}
