package com.ecommerce.exceptions;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(int quantity) {
        super("Invalid quantity: " + quantity + (quantity <= 0 ? " Number should be greater than 0" : " There's not enough stock, try with a different amount or product please."));
    }
}
