package com.web.LuxusCosmetic.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating; // 1-5 estrelas
    private String comment;
    private Instant createdAt;

    @ManyToOne @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne @JoinColumn(name="customer_id")
    private Customer customer;


}
