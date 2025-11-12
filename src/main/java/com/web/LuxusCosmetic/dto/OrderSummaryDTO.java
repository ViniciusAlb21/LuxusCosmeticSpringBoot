package com.web.LuxusCosmetic.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderSummaryDTO(Long id, Instant createdAt, BigDecimal subtotal, BigDecimal discount, BigDecimal total,
                              List<Line> lines) {
    public record Line(Long productId, String productName, Integer quantity, BigDecimal unitPrice, BigDecimal lineTotal) { }
}
