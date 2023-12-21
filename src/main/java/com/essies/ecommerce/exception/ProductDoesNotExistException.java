package com.essies.ecommerce.exception;

public class ProductDoesNotExistException extends EssiesEcommerceException {
    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
