package com.baovola.baovola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baovola.baovola.models.Productions;

@Repository
public interface ProductionRepository extends JpaRepository<Productions,Long> {
    
}
