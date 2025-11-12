package com.web.LuxusCosmetic.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import com.web.LuxusCosmetic.domain.ids.OrderItemId;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @ManyToOne @MapsId("orderId")
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;
}
