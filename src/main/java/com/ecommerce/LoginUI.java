package com.ecommerce;
import javax.swing.*;

public class LoginUI {

    JFrame f;
    JTextField user;
    JPasswordField pass;

    // 🔹 Constructor (THIS is important)
    public LoginUI() {

        f = new JFrame("Login");
        f.setSize(300,250);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(10,30,70,25);

        user = new JTextField();
        user.setBounds(80,30,150,25);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(10,70,70,25);

        pass = new JPasswordField();
        pass.setBounds(80,70,150,25);

        JButton login = new JButton("Login");
        JButton signup = new JButton("Signup");

        login.setBounds(50,120,80,30);
        signup.setBounds(150,120,80,30);

        f.add(l1);
        f.add(user);
        f.add(l2);
        f.add(pass);
        f.add(login);
        f.add(signup);

        // 🔹 Actions
        login.addActionListener(e -> {
            if (AuthService.login(user.getText(),
                    new String(pass.getPassword()))) {

                f.dispose();
                new DashboardUI();

            } else {
                JOptionPane.showMessageDialog(f,"Invalid Login");
            }
        });

        signup.addActionListener(e -> {
            f.dispose();
            new SignupUI();
        });

        f.setVisible(true);
    }

    // 🔹 Main method
    public static void main(String[] args) {
        new LoginUI();
    }
}