package com.ecommerce.discount;

import com.ecommerce.storelogic.CartItem;

import java.util.List;

public class DiscountCategory implements Discount {
    private String categoryDiscount = "N64 Controllers";
    private double discountPercentage = 20;
    @Override
    public boolean checkDiscount(List<CartItem> items) {
        for (CartItem item : items){
            if (item.getProduct().getCategory().equals(categoryDiscount)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "com.ecommerce.discount.Discount of "+discountPercentage+"% on purchases with "+categoryDiscount;
    }

    @Override
    public double applyDiscount(double subtotal) {
        return subtotal * (100 - discountPercentage)/100 ;
    }
}
