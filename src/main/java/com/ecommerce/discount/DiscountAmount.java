package com.ecommerce.discount;

import com.ecommerce.storelogic.CartItem;

import java.util.List;

public class DiscountAmount implements Discount {
    private double discountThreshold = 30000;
    private double discountPercentage = 20;

    @Override
    public boolean checkDiscount(List<CartItem> items) {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        if (total > discountThreshold){
                return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "com.ecommerce.discount.Discount of "+discountPercentage+"% on purchases above $"+discountThreshold;
    }

    @Override
    public double applyDiscount(double subtotal) {
        return subtotal * (100 - discountPercentage)/100 ;
    }
}
