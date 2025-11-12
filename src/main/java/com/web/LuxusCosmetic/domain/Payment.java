package com.web.LuxusCosmetic.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment {
    @Id
    private Long id;

    @OneToOne @MapsId
    @JoinColumn(name="id")
    private Order order;

    private Instant paidAt;
    private String method; // Pix ou Cartão
    private BigDecimal amount;
    private String status;
}
