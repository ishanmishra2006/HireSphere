package AdminSignup;

import javax.swing.*;
import java.awt.*;

public class AdminSignup extends JFrame {

    private JTextField nameField;
    private JTextField adminIdField;
    private JTextField emailField;

    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public AdminSignup() {

        setTitle("HireSphere - Admin Sign Up");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel mainPanel = new JPanel();

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        mainPanel.setLayout(null);


        // ==========================================
        // TITLE
        // ==========================================

        JLabel titleLabel = new JLabel(
                "HireSphere"
        );

        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 36)
        );

        titleLabel.setForeground(
                new Color(8, 27, 58)
        );

        titleLabel.setBounds(
                170, 25, 250, 50
        );

        mainPanel.add(titleLabel);


        // ==========================================
        // SUBTITLE
        // ==========================================

        JLabel subtitleLabel = new JLabel(
                "Create Admin Account"
        );

        subtitleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        subtitleLabel.setForeground(
                new Color(30, 64, 175)
        );

        subtitleLabel.setBounds(
                135, 75, 300, 40
        );

        mainPanel.add(subtitleLabel);


        // ==========================================
        // FULL NAME
        // ==========================================

        JLabel nameLabel = new JLabel(
                "Full Name"
        );

        nameLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        nameLabel.setBounds(
                60, 135, 200, 25
        );

        mainPanel.add(nameLabel);

        nameField = new JTextField();

        nameField.setBounds(
                60, 160, 420, 38
        );

        nameField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        mainPanel.add(nameField);


        // ==========================================
        // ADMIN ID
        // ==========================================

        JLabel adminIdLabel = new JLabel(
                "Admin ID"
        );

        adminIdLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        adminIdLabel.setBounds(
                60, 205, 200, 25
        );

        mainPanel.add(adminIdLabel);

        adminIdField = new JTextField();

        adminIdField.setBounds(
                60, 230, 420, 38
        );

        adminIdField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        mainPanel.add(adminIdField);


        // ==========================================
        // EMAIL
        // ==========================================

        JLabel emailLabel = new JLabel(
                "Email Address"
        );

        emailLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        emailLabel.setBounds(
                60, 275, 200, 25
        );

        mainPanel.add(emailLabel);

        emailField = new JTextField();

        emailField.setBounds(
                60, 300, 420, 38
        );

        emailField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        mainPanel.add(emailField);


        // ==========================================
        // PASSWORD
        // ==========================================

        JLabel passwordLabel = new JLabel(
                "Password"
        );

        passwordLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        passwordLabel.setBounds(
                60, 345, 200, 25
        );

        mainPanel.add(passwordLabel);

        passwordField = new JPasswordField();

        passwordField.setBounds(
                60, 370, 420, 38
        );

        mainPanel.add(passwordField);


        // ==========================================
        // CONFIRM PASSWORD
        // ==========================================

        JLabel confirmLabel = new JLabel(
                "Confirm Password"
        );

        confirmLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        confirmLabel.setBounds(
                60, 415, 200, 25
        );

        mainPanel.add(confirmLabel);

        confirmPasswordField = new JPasswordField();

        confirmPasswordField.setBounds(
                60, 440, 420, 38
        );

        mainPanel.add(confirmPasswordField);


        // ==========================================
        // CREATE ACCOUNT
        // ==========================================

        JButton createButton = new JButton(
                "CREATE ACCOUNT"
        );

        createButton.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        createButton.setForeground(
                Color.WHITE
        );

        createButton.setBackground(
                new Color(30, 64, 175)
        );

        createButton.setFocusPainted(false);

        createButton.setBounds(
                60, 500, 420, 42
        );

        createButton.addActionListener(
                e -> createAccount()
        );

        mainPanel.add(createButton);


        // ==========================================
        // BACK TO LOGIN
        // ==========================================

        JButton backButton = new JButton(
                "← Back to Login"
        );

        backButton.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        backButton.setForeground(
                new Color(8, 27, 58)
        );

        backButton.setBorderPainted(false);

        backButton.setContentAreaFilled(false);

        backButton.setFocusPainted(false);

        backButton.setBounds(
                190, 555, 160, 35
        );

        backButton.addActionListener(
                e -> {

                    dispose();

                    new AdminLogin.AdminLogin()
                            .setVisible(true);

                }
        );

        mainPanel.add(backButton);


        add(mainPanel);
    }


    // =====================================================
    // CREATE ACCOUNT
    // =====================================================

    private void createAccount() {

        String name =
                nameField.getText().trim();

        String adminId =
                adminIdField.getText().trim();

        String email =
                emailField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        String confirmPassword =
                new String(
                        confirmPasswordField.getPassword()
                );


        // EMPTY FIELDS

        if (name.isEmpty()
                || adminId.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all the fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // PASSWORD MATCH

        if (!password.equals(confirmPassword)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Password Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // EMAIL VALIDATION

        if (!email.contains("@")
                || !email.contains(".")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Invalid Email",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // FRONTEND ONLY

        JOptionPane.showMessageDialog(
                this,
                "Admin account created successfully!\n"
                + "Database integration will be added later.",
                "Registration Successful",
                JOptionPane.INFORMATION_MESSAGE
        );


        // CLEAR FIELDS

        nameField.setText("");
        adminIdField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    AdminSignup signup =
                            new AdminSignup();

                    signup.setVisible(true);

                }
        );
    }
}