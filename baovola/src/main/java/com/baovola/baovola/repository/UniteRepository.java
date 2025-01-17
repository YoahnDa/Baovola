package com.baovola.baovola.repository;

import com.baovola.baovola.models.Unite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniteRepository extends JpaRepository<Unite,Long> {
    boolean existsByNom(String nom);
    @Query("SELECT COUNT(u) > 0 FROM Unite u WHERE u.symbole = :symbole")
    boolean existsBySymbole(@Param("symbole") String symbole);
    @Query("SELECT u FROM Unite u WHERE LOWER(u.nom) LIKE LOWER(CONCAT('%', :nom, '%'))")
    List<Unite> searchByNom(@Param("nom") String nom);
}
