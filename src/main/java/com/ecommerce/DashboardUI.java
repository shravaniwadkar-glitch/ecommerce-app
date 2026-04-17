package com.ecommerce;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.util.List;
import java.sql.*;

public class DashboardUI {

    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> list = new JList<>(model);

    public DashboardUI() {

        JFrame f = new JFrame("E-Commerce Dashboard");
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        // 🔹 TITLE
        JLabel title = new JLabel("E-Commerce Store", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        f.add(title, BorderLayout.NORTH);

        // 🔹 PRODUCTS PANEL
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Products"));

        List<Product> products = ProductDAO.getProducts();

        for (Product p : products) {
            model.addElement(p.id + " | " + p.name + " | ₹" + p.price);
        }

        list.setFont(new Font("Arial", Font.PLAIN, 16));
        leftPanel.add(new JScrollPane(list), BorderLayout.CENTER);

        // 🔹 RIGHT PANEL
        JPanel rightPanel = new JPanel(new GridLayout(6,1,10,10));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JLabel qtyLabel = new JLabel("Quantity:");
        JTextField qty = new JTextField("1");

        JButton add = new JButton("Add to Cart");
        JButton remove = new JButton("Remove");
        JButton checkout = new JButton("Checkout");
        JButton viewOrders = new JButton("View Orders");

        rightPanel.add(qtyLabel);
        rightPanel.add(qty);
        rightPanel.add(add);
        rightPanel.add(remove);
        rightPanel.add(checkout);
        rightPanel.add(viewOrders);

        f.add(leftPanel, BorderLayout.CENTER);
        f.add(rightPanel, BorderLayout.EAST);

        // 🔹 ADD TO CART
        add.addActionListener(e -> {
            int i = list.getSelectedIndex();
            if (i == -1) {
                JOptionPane.showMessageDialog(f, "Select a product!");
                return;
            }

            try {
                int q = Integer.parseInt(qty.getText());
                if (q <= 0) {
                    JOptionPane.showMessageDialog(f, "Enter valid quantity!");
                    return;
                }

                Product p = products.get(i);
                CartService.add(p, q);

                JOptionPane.showMessageDialog(f, "Added!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid quantity!");
            }
        });

        // 🔹 REMOVE FROM CART
        remove.addActionListener(e -> {
            int i = list.getSelectedIndex();
            if (i == -1) {
                JOptionPane.showMessageDialog(f, "Select a product!");
                return;
            }

            Product p = products.get(i);
            CartService.remove(p.id);

            JOptionPane.showMessageDialog(f, "Removed!");
        });

        // 🔹 CHECKOUT + SAVE
        checkout.addActionListener(e -> {

            if (CartService.cart.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Cart Empty!");
                return;
            }

            StringBuilder sb = new StringBuilder();

            for (CartItem c : CartService.cart) {

                OrderDAO.saveOrder(
                        c.product.name,
                        c.qty,
                        c.total()
                );

                sb.append(c.product.name)
                  .append(" x").append(c.qty)
                  .append(" = ₹").append(c.total())
                  .append("\n");
            }

            sb.append("\nTotal: ₹").append(CartService.getTotal());

            JOptionPane.showMessageDialog(f, sb.toString());

            CartService.cart.clear();
        });

        // 🔹 VIEW ORDERS
        viewOrders.addActionListener(e -> {

            try {
                Connection con = DBConnection.getConnection();
                ResultSet rs = con.createStatement()
                        .executeQuery("SELECT * FROM orders");

                StringBuilder sb = new StringBuilder("Order History:\n\n");

                while (rs.next()) {
                    sb.append(rs.getString("product_name"))
                      .append(" x").append(rs.getInt("quantity"))
                      .append(" = ₹").append(rs.getDouble("total"))
                      .append("\n");
                }

                JOptionPane.showMessageDialog(f, sb.toString());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Error loading orders!");
                ex.printStackTrace();
            }
        });

        f.setVisible(true);
    }
}