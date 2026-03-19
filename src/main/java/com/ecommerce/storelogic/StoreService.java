package com.ecommerce.storelogic;

import com.ecommerce.discount.*;

import java.util.ArrayList;
import java.util.List;

public class StoreService {
    private Catalog catalog;
    private Cart cart;
    private List<Discount> discounts  = new ArrayList<>();


    public StoreService() {
        this.catalog = new Catalog();
        this.cart = new Cart();
        addDiscount(new DiscountAmount());
        addDiscount(new DiscountCategory());
    }

    public void addDiscount(Discount discount){
        discounts.add(discount);
    }

    public void showActiveDiscounts(){
        String discountDesc;

        for (Discount discount : discounts) {
            discountDesc = discount.getDescription();
            if (discount.checkDiscount(cart.getItems())) {
                discountDesc += "(Active discount in your cart)";
            }
            System.out.println(discountDesc);
        }
    }


    public Cart getCart() {
        return cart;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public Order checkout(){
         Order o = new Order(getCart().getItems());
         o.calculateOrder();
         o.applyDiscounts(discounts);
         return o;
    }

}
