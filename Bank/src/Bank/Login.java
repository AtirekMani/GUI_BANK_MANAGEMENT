package Bank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    // Declare labels, text fields, and buttons
    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3;

    // Constructor for the Login class
    Login() {
        // Set the title of the frame
        setTitle("AUTOMATED TELLER MACHINE");

        // Load and scale the logo image
        ImageIcon i1 = new ImageIcon("/Users/atirek/IdeaProjects/Bank/src/icons/logo.jpg");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 10, 100, 100);
        add(l11);

        // Welcome label
        l1 = new JLabel("WELCOME TO ATM");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(200, 40, 450, 40);
        add(l1);

        // Card number label and text field
        l2 = new JLabel("Card No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(125, 150, 375, 30);
        add(l2);

        tf1 = new JTextField(15);
        tf1.setBounds(300, 150, 230, 30);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        add(tf1);

        // PIN label and password field
        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(125, 220, 375, 30);
        add(l3);

        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(300, 220, 230, 30);
        add(pf2);

        // Buttons: SIGN IN, CLEAR, SIGN UP
        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300, 300, 100, 30);
        b1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add border for visibility
        add(b1);

        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430, 300, 100, 30);
        b2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add border for visibility
        add(b2);

        b3 = new JButton("SIGN UP");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(300, 350, 230, 30);
        b3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add border for visibility
        add(b3);

        // Add action listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        // Set content pane background and layout
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        // Set size and position of the frame
        setSize(800, 480);
        setLocation(550, 200);
        setVisible(true);
    }

    // Handle button actions
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                // Connect to the database and check login credentials
                Conn c1 = new Conn();
                String cardno = tf1.getText();
                String pin = pf2.getText();
                String q = "select * from login where cardno = '" + cardno + "' and pin = '" + pin + "'";

                ResultSet rs = c1.s.executeQuery(q);
                if (rs.next()) {
                    // If login credentials are correct, open the Transactions frame
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                } else {
                    // If login credentials are incorrect, show an error message
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if (ae.getSource() == b2) {
                // Clear the text fields
                tf1.setText("");
                pf2.setText("");
            } else if (ae.getSource() == b3) {
                // Open the Signup frame
                setVisible(false);
                new Signup().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create a new instance of the Login frame
        new Login().setVisible(true);
    }
}