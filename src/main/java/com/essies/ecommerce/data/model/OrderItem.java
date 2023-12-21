package com.essies.ecommerce.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long orderId;
    private Long quantity;
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
