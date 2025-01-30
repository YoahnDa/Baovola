package com.baovola.baovola.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baovola.baovola.models.HistoriquePrix;

@Repository
public interface HistoriquePrixRepository extends JpaRepository<HistoriquePrix,Long> {
    List<HistoriquePrix> findByProduits_Id(Long produitsId);
     // Récupérer l'historique le plus récent pour un produit
    @Query("SELECT h FROM HistoriquePrix h WHERE h.produits.id = :produitId ORDER BY h.dateModif DESC")
    List<HistoriquePrix> findLatestHistoriqueByProduit(@Param("produitId") Long produitId);
    @Query("SELECT h FROM HistoriquePrix h WHERE h.produits.id = :produitId AND h.dateModif <= :givenDate ORDER BY h.dateModif DESC")
    List<HistoriquePrix> findClosestHistoriqueByDate(@Param("produitId") Long produitId,@Param("givenDate") LocalDateTime givenDate);
}
