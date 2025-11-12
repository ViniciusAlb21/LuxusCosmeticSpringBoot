package com.web.LuxusCosmetic.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock;
    private String sku;
    @Column(length = 2000)
    private String descpription;

    @ManyToOne @JoinColumn(name ="category_id")
    private Category category;

    @ManyToOne @JoinColumn(name ="brand_id")
    private Brand brand;

    @ManyToMany
    @JoinTable(name="product_tag",
            joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns=@JoinColumn(name="tag_id"))
    private Set<Tag> tags = new HashSet<>();


}
