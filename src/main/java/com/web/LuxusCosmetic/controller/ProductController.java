package com.web.LuxusCosmetic.controller;

import com.web.LuxusCosmetic.dto.ProductDTO;
import com.web.LuxusCosmetic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController @RequestMapping("/api/products") @RequiredArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("/search")
    public List<ProductDTO> search(@RequestParam(required=false) String q,
                                   @RequestParam(required=false) BigDecimal minPrice,
                                   @RequestParam(required=false) BigDecimal maxPrice,
                                   @RequestParam(required=false) Long categoryId,
                                   @RequestParam(required=false) Long brandId) {
        return productService.search(q, minPrice, maxPrice, categoryId, brandId);
    }

    @GetMapping("/{id}/rating/average")
    public Double avg(@PathVariable Long id) {
        return productService.averageRating(id);
    }
}

