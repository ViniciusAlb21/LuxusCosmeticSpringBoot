package com.web.LuxusCosmetic.repository;

import com.web.LuxusCosmetic.domain.OrderItem;
import com.web.LuxusCosmetic.domain.ids.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> { }