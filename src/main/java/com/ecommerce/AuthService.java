package com.ecommerce;
import java.sql.*;

public class AuthService {

    public static boolean login(String u, String p) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );
            ps.setString(1, u);
            ps.setString(2, p);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean register(String u, String p) {
        try {
            Connection con = DBConnection.getConnection();

            // check duplicate
            PreparedStatement check = con.prepareStatement(
                "SELECT * FROM users WHERE username=?"
            );
            check.setString(1, u);
            ResultSet rs = check.executeQuery();

            if (rs.next()) return false;

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users VALUES (?, ?)"
            );
            ps.setString(1, u);
            ps.setString(2, p);
            ps.executeUpdate();

            return true;

        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}