package com.web.LuxusCosmetic.dto;

import java.math.BigDecimal;

public record ProductDTO(Long id, String name, BigDecimal price, Integer stock, String brand, String category) { }