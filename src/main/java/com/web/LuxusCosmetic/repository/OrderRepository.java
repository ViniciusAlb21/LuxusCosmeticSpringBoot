package com.web.LuxusCosmetic.repository;

import com.web.LuxusCosmetic.domain.Order;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("""
    SELECT o FROM Order o
    WHERE (:from IS NULL OR o.createdAt >= :from)
        AND (:to IS NULL OR o.createdAt <= :to)
    ORDER BY 0.createdAt DESC
    """)
    List<Order> findByPeriod(@Param("from") Instant from, @Param("to")Instant to);
}
