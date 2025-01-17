package com.baovola.baovola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baovola.baovola.models.Produits;

@Repository
public interface ProduitRepository extends JpaRepository<Produits,Long> {
    
}
