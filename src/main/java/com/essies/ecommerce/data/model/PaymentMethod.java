package com.essies.ecommerce.data.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum PaymentMethod {
    CREDIT_CARD,
    DEBIT_CARD,
    BANK_TRANSFER,
    CASH_ON_DELIVERY;
}
