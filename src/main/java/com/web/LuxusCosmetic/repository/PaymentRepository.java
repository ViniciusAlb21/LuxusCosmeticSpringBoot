package com.web.LuxusCosmetic.repository;

import com.web.LuxusCosmetic.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> { }

