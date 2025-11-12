package com.web.LuxusCosmetic.repository;

import com.web.LuxusCosmetic.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId")
    Double averageRating(@Param("productId") Long productId);
}