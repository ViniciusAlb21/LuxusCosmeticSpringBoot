package com.web.LuxusCosmetic.dto;

import java.util.List;

public record CreateOrderDTO(Long customerId, Long addressId, String couponCode, List<Item> items) {
    public record Item(Long productId, Integer quantity) { }
}