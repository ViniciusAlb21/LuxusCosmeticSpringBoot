package com.web.LuxusCosmetic.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Entity @Table(name="orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant createdAt;

    @ManyToOne @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne @JoinColumn(name="shipping_address_id")
    private Address shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Payment payment;

    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal total;
}
