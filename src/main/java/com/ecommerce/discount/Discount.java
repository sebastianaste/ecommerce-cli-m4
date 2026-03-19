package com.ecommerce.discount;

import com.ecommerce.storelogic.CartItem;

import java.util.List;

public interface Discount {
    public boolean checkDiscount(List<CartItem> items);
    public String getDescription();
    public double applyDiscount(double subtotal);
}
