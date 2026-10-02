package AdminLogin;

import javax.swing.*;
import java.awt.*;

public class AdminLogin extends JFrame {

    private JTextField adminIdField;
    private JPasswordField passwordField;

    public AdminLogin() {

        // ==========================================
        // FRAME SETTINGS
        // ==========================================

        setTitle("HireSphere - Admin Login");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ==========================================
        // MAIN PANEL
        // ==========================================

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setLayout(null);

        // ==========================================
        // TITLE
        // ==========================================

        JLabel titleLabel = new JLabel("HireSphere");

        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 36)
        );

        titleLabel.setForeground(
                new Color(8, 27, 58)
        );

        titleLabel.setBounds(145, 30, 250, 50);

        mainPanel.add(titleLabel);

        // ==========================================
        // SUBTITLE
        // ==========================================

        JLabel subtitleLabel = new JLabel("Admin Login");

        subtitleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        subtitleLabel.setForeground(
                new Color(30, 64, 175)
        );

        subtitleLabel.setBounds(165, 85, 200, 40);

        mainPanel.add(subtitleLabel);

        // ==========================================
        // DESCRIPTION
        // ==========================================

        JLabel descriptionLabel = new JLabel(
                "Manage placements, students and companies"
        );

        descriptionLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        descriptionLabel.setForeground(Color.GRAY);

        descriptionLabel.setBounds(105, 125, 300, 30);

        mainPanel.add(descriptionLabel);

        // ==========================================
        // ADMIN ID LABEL
        // ==========================================

        JLabel adminIdLabel = new JLabel("Admin ID / Email");

        adminIdLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        adminIdLabel.setBounds(70, 190, 200, 30);

        mainPanel.add(adminIdLabel);

        // ==========================================
        // ADMIN ID FIELD
        // ==========================================

        adminIdField = new JTextField();

        adminIdField.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        adminIdField.setBounds(70, 220, 350, 40);

        mainPanel.add(adminIdField);

        // ==========================================
        // PASSWORD LABEL
        // ==========================================

        JLabel passwordLabel = new JLabel("Password");

        passwordLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        passwordLabel.setBounds(70, 280, 200, 30);

        mainPanel.add(passwordLabel);

        // ==========================================
        // PASSWORD FIELD
        // ==========================================

        passwordField = new JPasswordField();

        passwordField.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        passwordField.setBounds(70, 310, 350, 40);

        mainPanel.add(passwordField);

        // ==========================================
        // SHOW PASSWORD
        // ==========================================

        JCheckBox showPassword =
                new JCheckBox("Show Password");

        showPassword.setBackground(
                new Color(245, 247, 250)
        );

        showPassword.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        showPassword.setBounds(70, 355, 150, 25);

        showPassword.addActionListener(e -> {

            if (showPassword.isSelected()) {

                passwordField.setEchoChar((char) 0);

            } else {

                passwordField.setEchoChar('•');
            }
        });

        mainPanel.add(showPassword);

        // ==========================================
        // FORGOT PASSWORD
        // ==========================================

        JButton forgotButton =
                new JButton("Forgot Password?");

        forgotButton.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        forgotButton.setForeground(
                new Color(30, 64, 175)
        );

        forgotButton.setBorderPainted(false);
        forgotButton.setContentAreaFilled(false);
        forgotButton.setFocusPainted(false);

        forgotButton.setBounds(280, 355, 140, 25);

        forgotButton.addActionListener(e -> {

            AdminPasswordRecovery.AdminPasswordRecovery recovery =
                    new AdminPasswordRecovery.AdminPasswordRecovery();

            recovery.setVisible(true);

            dispose();
        });

        mainPanel.add(forgotButton);

        // ==========================================
        // LOGIN BUTTON
        // ==========================================

        JButton loginButton =
                new JButton("LOGIN");

        loginButton.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        // IMPORTANT FOR BLUE COLOR
        loginButton.setForeground(Color.WHITE);

        loginButton.setBackground(
                new Color(30, 64, 175)
        );

        loginButton.setOpaque(true);

        loginButton.setContentAreaFilled(true);

        loginButton.setBorderPainted(false);

        loginButton.setFocusPainted(false);

        loginButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        loginButton.setBounds(
                70, 405, 350, 45
        );

        // ==========================================
        // LOGIN BUTTON ACTION
        // ==========================================

        loginButton.addActionListener(e -> login());

        mainPanel.add(loginButton);

        // ==========================================
        // SIGN UP TEXT
        // ==========================================

        JLabel signupText =
                new JLabel("Don't have an admin account?");

        signupText.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        signupText.setBounds(
                105, 470, 210, 30
        );

        mainPanel.add(signupText);

        // ==========================================
        // SIGN UP BUTTON
        // ==========================================

        JButton signupButton =
                new JButton("SignUp");

        signupButton.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        signupButton.setForeground(
                new Color(216, 155, 29)
        );

        signupButton.setBorderPainted(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setFocusPainted(false);

        signupButton.setBounds(
                305, 470, 80, 30
        );

        signupButton.addActionListener(e -> {

            AdminSignup.AdminSignup signup =
                    new AdminSignup.AdminSignup();

            signup.setVisible(true);

            dispose();
        });

        mainPanel.add(signupButton);

        // ==========================================
        // ADD PANEL
        // ==========================================

        add(mainPanel);
    }

    // =====================================================
    // LOGIN METHOD
    // =====================================================

    private void login() {

        String adminId =
                adminIdField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        // ==========================================
        // CHECK ADMIN ID
        // ==========================================

        if (adminId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your Admin ID or Email.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ==========================================
        // CHECK PASSWORD
        // ==========================================

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ==========================================
        // LOGIN SUCCESS
        // ==========================================

        JOptionPane.showMessageDialog(
                this,
                "Login Successful!\nWelcome to HireSphere Admin Portal.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        // ==========================================
        // OPEN ADMIN DASHBOARD
        // ==========================================

        AdminDashboard.AdminDashboard dashboard =
                new AdminDashboard.AdminDashboard();

        dashboard.setVisible(true);

        // ==========================================
        // CLOSE LOGIN WINDOW
        // ==========================================

        dispose();
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            AdminLogin login =
                    new AdminLogin();

            login.setVisible(true);
        });
    }
}