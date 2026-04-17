package com.ecommerce;

public class CartItem {
    Product product;
    int qty;

    public CartItem(Product p, int q) {
        product = p;
        qty = q;
    }

    public double total() {
        return product.price * qty;
    }
}