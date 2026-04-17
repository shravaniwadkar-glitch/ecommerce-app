// package com.ecommerce;
// import java.util.*;

//public class Main 
//{
	
//	    static Scanner sc = new Scanner(System.in);
//
//	    public static void main(String[] args) {

//	        System.out.print("Username: ");
//	        String user = sc.nextLine();

//	        System.out.print("Password: ");
//	        String pass = sc.nextLine();

//	        if (!AuthService.login(user, pass)) {
//	            System.out.println("Invalid Login!");
//	            return;
//	        }

//	        System.out.println("Login Successful!");

//	        List<Product> products = ProductDAO.getProducts();

//	        for (Product p : products) {
//	            System.out.println(p.id + " " + p.name + " ₹" + p.price);
//	        }

//	        System.out.print("Enter Product ID: ");
//	        int id = sc.nextInt();

//	        System.out.print("Enter Quantity: ");
//	        int qty = sc.nextInt();

//	        for (Product p : products) {
//	            if (p.id == id) {
//	                CartService.addToCart(p, qty);
//	            }
//	        }

//	        CartService.viewCart();
//	    }
//}
