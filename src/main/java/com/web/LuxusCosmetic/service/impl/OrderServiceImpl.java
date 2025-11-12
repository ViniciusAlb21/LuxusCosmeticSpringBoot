package com.web.LuxusCosmetic.service.impl;

import com.web.LuxusCosmetic.dto.CreateOrderDTO;
import com.web.LuxusCosmetic.dto.OrderSummaryDTO;
import com.web.LuxusCosmetic.dto.SalesReportDTO;
import com.web.LuxusCosmetic.domain.*;
import com.web.LuxusCosmetic.domain.ids.OrderItemId;
import com.web.LuxusCosmetic.repository.*;
import com.web.LuxusCosmetic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;
    private OrderItemRepository orderItemRepository;
    private PaymentRepository paymentRepository;
    private CouponRepository couponRepository;

    @Override
    @Transactional
    public OrderSummaryDTO checkout(CreateOrderDTO dto) {
        var customer = customerRepository.findById(dto.customerId()).orElseThrow();
        var address = addressRepository.findById(dto.addressId()).orElseThrow();

        var order = Order.builder()
                .customer(customer)
                .shippingAddress(address)
                .createdAt(Instant.now())
                .subtotal(BigDecimal.ZERO)
                .discount(BigDecimal.ZERO)
                .total(BigDecimal.ZERO)
                .build();

        order = orderRepository.save(order);

        BigDecimal subtotal = BigDecimal.ZERO;
        List<OrderSummaryDTO.Line> lines = new ArrayList<>();

        for (var item : dto.items()) {
            var product = productRepository.findById(item.productId()).orElseThrow();
            if (product.getStock() < item.quantity()) {
                throw new IllegalArgumentException("Estoque insuficiente para " + product.getName());
            }
            product.setStock(product.getStock() - item.quantity());
            productRepository.save(product);

            var oi = OrderItem.builder()
                    .id(new OrderItemId(order.getId(), product.getId()))
                    .order(order)
                    .product(product)
                    .quantity(item.quantity())
                    .unitPrice(product.getPrice())
                    .lineTotal(product.getPrice().multiply(BigDecimal.valueOf(item.quantity())))
                    .build();
            orderItemRepository.save(oi);
            subtotal = subtotal.add(oi.getLineTotal());
            lines.add(new OrderSummaryDTO.Line(product.getId(), product.getName(), item.quantity(), product.getPrice(), oi.getLineTotal()));
        }

        BigDecimal discount = BigDecimal.ZERO;
        if (dto.couponCode() != null && !dto.couponCode().isBlank()) {
            var coupon = couponRepository.findByCodeAndActiveTrue(dto.couponCode()).orElse(null);
            if (coupon != null && (coupon.getValidUntil() == null || !coupon.getValidUntil().isBefore(java.time.LocalDate.now()))) {
                discount = subtotal.multiply(coupon.getPercent().divide(BigDecimal.valueOf(100)));
            }
        }
        var total = subtotal.subtract(discount);

        order.setSubtotal(subtotal);
        order.setDiscount(discount);
        order.setTotal(total);
        orderRepository.save(order);

        var payment = Payment.builder()
                .order(order)
                .amount(total)
                .status("PENDING")
                .build();
        paymentRepository.save(payment);

        return new OrderSummaryDTO(order.getId(), order.getCreatedAt(), subtotal, discount, total, lines);
    }

    @Override
    public SalesReportDTO report(Instant from, Instant to) {
        var orders = orderRepository.findByPeriod(from, to);
        var revenue = orders.stream().map(Order::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new SalesReportDTO(from, to, (long) orders.size(), revenue);
    }
}
}