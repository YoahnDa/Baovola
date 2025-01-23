package com.baovola.baovola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baovola.baovola.models.Produits;

@Repository
public interface ProduitRepository extends JpaRepository<Produits,Long> {
    boolean existsByNomIgnoreCase(@Param("nom") String nom);
    @Query("SELECT p FROM Produits p WHERE LOWER(p.nom) LIKE LOWER(CONCAT('%', :nom, '%'))")
    List<Produits> searchByNom(@Param("nom") String nom);
    List<Produits> findByNomContainingIgnoreCase(@Param("nom") String nom);
}
