package com.web.LuxusCosmetic.repository;

import com.web.LuxusCosmetic.domain.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
  SELECT p FROM Product p
  WHERE (:q IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%')))
    AND (:min IS NULL OR p.price >= :min)
    AND (:max IS NULL OR p.price <= :max)
    AND (:categoryId IS NULL OR p.category.id = :categoryId)
    AND (:brandId IS NULL OR p.brand.id = :brandId)
  """)
    List<Product> search(@Param("q") String q,
                         @Param("min") BigDecimal min,
                         @Param("max") BigDecimal max,
                         @Param("categoryId") Long categoryId,
                         @Param("brandId") Long brandId);
}