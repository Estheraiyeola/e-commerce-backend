package com.essies.ecommerce.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Setter
@Getter
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long cartId;
    private LocalDate orderDate;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private Long billingAddressId;
    private Long shippingAddressId;
    private PaymentMethod paymentMethod;
    @ManyToOne
    @JoinColumn(name = "order_history_id")
    private OrderHistory orderHistory;
    private Long reviewId;
}
