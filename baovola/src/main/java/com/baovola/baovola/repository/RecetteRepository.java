package com.baovola.baovola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baovola.baovola.models.Recettes;

@Repository
public interface RecetteRepository extends JpaRepository<Recettes,Long> {
    
}
