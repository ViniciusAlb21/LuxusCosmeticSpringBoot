package com.web.LuxusCosmetic.service.impl;

import com.web.LuxusCosmetic.dto.ProductDTO;
import com.web.LuxusCosmetic.domain.Product;
import com.web.LuxusCosmetic.repository.ProductRepository;
import com.web.LuxusCosmetic.repository.ReviewRepository;
import com.web.LuxusCosmetic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service @RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ReviewRepository reviewRepository;

    @Override
    public List<ProductDTO> search(String q, BigDecimal min, BigDecimal max, Long categoryId, Long brandId) {
        return productRepository.search(q, min, max, categoryId, brandId)
                .stream().map(this::toDTO).toList();
    }

    private ProductDTO toDTO(Product p) {
        return new ProductDTO(p.getId(), p.getName(), p.getPrice(), p.getStock(),
                p.getBrand() != null ? p.getBrand().getName() : null,
                p.getCategory() != null ? p.getCategory().getName() : null);
    }

    @Override
    public Double averageRating(Long productId) {
        return reviewRepository.averageRating(productId);
    }
}