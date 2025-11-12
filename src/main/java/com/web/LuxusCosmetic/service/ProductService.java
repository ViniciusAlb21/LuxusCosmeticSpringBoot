package com.web.LuxusCosmetic.service;

import com.web.LuxusCosmetic.dto.ProductDTO;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductDTO> search(String q, BigDecimal min, BigDecimal max, Long categoryId, Long brandId);
    Double averageRating(Long productId);
}

