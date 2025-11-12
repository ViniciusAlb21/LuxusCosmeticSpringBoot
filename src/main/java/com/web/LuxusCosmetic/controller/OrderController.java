package com.web.LuxusCosmetic.controller;

import com.web.LuxusCosmetic.dto.CreateOrderDTO;
import com.web.LuxusCosmetic.dto.OrderSummaryDTO;
import com.web.LuxusCosmetic.dto.SalesReportDTO;
import com.web.LuxusCosmetic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController @RequestMapping("/api/orders") @RequiredArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/checkout")
    public OrderSummaryDTO checkout(@RequestBody CreateOrderDTO dto) {
        return orderService.checkout(dto);
    }

    @GetMapping("/report")
    public SalesReportDTO report(@RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
                                 @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to) {
        return orderService.report(from, to);
    }
}
