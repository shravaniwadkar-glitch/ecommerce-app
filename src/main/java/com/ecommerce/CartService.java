package com.ecommerce;
import java.util.*;

public class CartService {
    public static List<CartItem> cart = new ArrayList<>();

    public static void add(Product p, int q) {
        cart.add(new CartItem(p, q));
    }

    public static void remove(int id) {
        cart.removeIf(c -> c.product.id == id);
    }

    public static double getTotal() {
        double t = 0;
        for (CartItem c : cart) t += c.total();
        return t;
    }
}