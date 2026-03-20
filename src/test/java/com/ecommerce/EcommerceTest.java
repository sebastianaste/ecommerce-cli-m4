package com.ecommerce;

import com.ecommerce.discount.DiscountAmount;
import com.ecommerce.discount.DiscountCategory;
import com.ecommerce.exceptions.InvalidQuantityException;
import com.ecommerce.storelogic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EcommerceTest {

    private Cart cart;
    private Product product;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        product = new Product(1, "N64 Controller", "Original", "N64 Controllers", 15000, 10);
    }

    @Test
    void cartTotalIsCorrect() {
        cart.addItem(product, 2);
        assertEquals(30000, cart.getBaseTotal());
    }

    @Test
    void cartTotalWithMultipleProducts() {
        Product product2 = new Product(2, "Zelda N64", "Game", "N64 Games", 40000, 5);
        cart.addItem(product, 2);   // 30000
        cart.addItem(product2, 1);  // 40000
        assertEquals(70000, cart.getBaseTotal());
    }

    @Test
    void addingZeroQuantityThrows() {
        assertThrows(InvalidQuantityException.class, () -> cart.addItem(product, 0));
    }

    @Test
    void addingNegativeQuantityThrows() {
        assertThrows(InvalidQuantityException.class, () -> cart.addItem(product, -1));
    }

    @Test
    void addingMoreThanStockThrows() {
        assertThrows(InvalidQuantityException.class, () -> cart.addItem(product, 99));
    }

    @Test
    void discountAmountDoesNotApplyBelowThreshold() {
        DiscountAmount discount = new DiscountAmount();
        cart.addItem(product, 1); // 15000 < 30000
        assertFalse(discount.checkDiscount(cart.getItems()));
    }

    @Test
    void discountAmountCalculatesCorrectly() {
        DiscountAmount discount = new DiscountAmount();
        double discountAmount = discount.applyDiscount(100000);
        assertEquals(20000, discountAmount);
    }

    @Test
    void discountCategoryDoesNotApplyForOtherCategories() {
        DiscountCategory discount = new DiscountCategory();
        Product game = new Product(4, "Zelda", "Game", "N64 Games", 40000, 5);
        cart.addItem(game, 1);
        assertFalse(discount.checkDiscount(cart.getItems()));
    }

    @Test
    void discountCategoryCalculatesCorrectly() {
        DiscountCategory discount = new DiscountCategory();
        double discountAmount = discount.applyDiscount(100000);
        assertEquals(20000, discountAmount);
    }
}

