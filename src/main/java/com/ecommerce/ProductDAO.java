package com.ecommerce;
import java.sql.*;
import java.util.*;

public class ProductDAO {

    public static List<Product> getProducts() {
        List<Product> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement()
                    .executeQuery("SELECT * FROM products");

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                ));
            }

        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}