package com.baovola.baovola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baovola.baovola.models.Categorie;

@Repository
public interface CategoryRepository extends JpaRepository<Categorie, Long> {
    @Query("SELECT u FROM Categorie u WHERE LOWER(u.nom) LIKE LOWER(CONCAT('%', :nom, '%'))")
    List<Categorie> searchByNom(@Param("nom") String nom);
}
