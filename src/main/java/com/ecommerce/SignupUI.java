package com.ecommerce;
import javax.swing.*;

public class SignupUI {

    SignupUI() {

        JFrame f = new JFrame("Signup");
        f.setSize(300, 250);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 🔹 Labels
        JLabel l1 = new JLabel("Username:");
        l1.setBounds(10, 30, 70, 25);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(10, 70, 70, 25);

        // 🔹 Fields
        JTextField user = new JTextField();
        user.setBounds(80, 30, 150, 25);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(80, 70, 150, 25);

        // 🔹 Button
        JButton btn = new JButton("Register");
        btn.setBounds(100, 120, 100, 30);

        // 🔹 Add components
        f.add(l1);
        f.add(user);
        f.add(l2);
        f.add(pass);
        f.add(btn);

        // 🔹 Button Action
        btn.addActionListener(e -> {

            String username = user.getText();
            String password = new String(pass.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Fields cannot be empty!");
                return;
            }

            if (AuthService.register(username, password)) {
                JOptionPane.showMessageDialog(f, "Registered Successfully!");

                f.dispose();          // close signup
                new LoginUI();        // redirect to login

            } else {
                JOptionPane.showMessageDialog(f, "User already exists!");
            }
        });

        f.setVisible(true);
    }
}