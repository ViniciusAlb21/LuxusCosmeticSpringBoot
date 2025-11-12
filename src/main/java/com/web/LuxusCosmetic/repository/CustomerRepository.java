package com.web.LuxusCosmetic.repository;

import com.web.LuxusCosmetic.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }