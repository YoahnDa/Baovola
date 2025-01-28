package com.baovola.baovola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baovola.baovola.models.RecetteComposition;

@Repository
public interface RecetteCompositionRepository extends JpaRepository<RecetteComposition, Long> {
    List<RecetteComposition> findByRecettes_Id(Long recetteId);
}
