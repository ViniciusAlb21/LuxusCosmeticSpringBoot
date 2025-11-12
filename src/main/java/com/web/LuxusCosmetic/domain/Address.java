package com.web.LuxusCosmetic.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String city;
    private String state;
    private String zip;

    @ManyToOne @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
