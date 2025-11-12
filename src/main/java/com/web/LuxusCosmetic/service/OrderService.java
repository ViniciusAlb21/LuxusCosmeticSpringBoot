package com.web.LuxusCosmetic.service;

import com.web.LuxusCosmetic.dto.CreateOrderDTO;
import com.web.LuxusCosmetic.dto.OrderSummaryDTO;
import com.web.LuxusCosmetic.dto.SalesReportDTO;
import java.time.Instant;

public interface OrderService {
    OrderSummaryDTO checkout(CreateOrderDTO dto);
    SalesReportDTO report(Instant from, Instant to);
}