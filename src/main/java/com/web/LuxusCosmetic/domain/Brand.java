package com.web.LuxusCosmetic.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Brand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique = true)
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Product> products = new ArrayList<>();
}
