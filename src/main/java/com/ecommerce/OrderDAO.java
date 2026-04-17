package com.ecommerce;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDAO 
{

		    public static void saveOrder(String name, int qty, double total) {
		        try {
		            Connection con = DBConnection.getConnection();

		            PreparedStatement ps = con.prepareStatement(
		                "INSERT INTO orders(product_name, quantity, total) VALUES (?, ?, ?)"
		            );

		            ps.setString(1, name);
		            ps.setInt(2, qty);
		            ps.setDouble(3, total);

		            ps.executeUpdate();

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

}
