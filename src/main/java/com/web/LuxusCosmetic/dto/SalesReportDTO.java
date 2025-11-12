package com.web.LuxusCosmetic.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record SalesReportDTO(Instant from, Instant to, Long orders, BigDecimal revenue) { }