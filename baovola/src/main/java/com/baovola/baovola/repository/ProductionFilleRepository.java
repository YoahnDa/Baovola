package com.baovola.baovola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baovola.baovola.models.ProductionFille;

@Repository
public interface ProductionFilleRepository extends JpaRepository<ProductionFille, Long> {
    List<ProductionFille> findByProductions_Id(Long productionsId);
}
