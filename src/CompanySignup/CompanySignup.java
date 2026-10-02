package CompanySignup;

import javax.swing.*;
import java.awt.*;

public class CompanySignup extends JFrame {

    private JTextField companyNameField;
    private JTextField companyIdField;
    private JTextField emailField;
    private JTextField phoneField;

    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public CompanySignup() {

        // ==========================================
        // FRAME SETTINGS
        // ==========================================

        setTitle("HireSphere - Company Sign Up");
        setSize(550, 700);
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

        titleLabel.setBounds(
                170, 20, 250, 50
        );

        mainPanel.add(titleLabel);

        // ==========================================
        // SUBTITLE
        // ==========================================

        JLabel subtitleLabel = new JLabel(
                "Create Company Account"
        );

        subtitleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        subtitleLabel.setForeground(
                new Color(30, 64, 175)
        );

        subtitleLabel.setBounds(
                145, 70, 300, 40
        );

        mainPanel.add(subtitleLabel);

        // ==========================================
        // DESCRIPTION
        // ==========================================

        JLabel descriptionLabel = new JLabel(
                "Join HireSphere and connect with talented students"
        );

        descriptionLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        descriptionLabel.setForeground(Color.GRAY);

        descriptionLabel.setBounds(
                105, 110, 350, 30
        );

        mainPanel.add(descriptionLabel);


        // ==========================================
        // COMPANY NAME
        // ==========================================

        JLabel companyNameLabel = new JLabel(
                "Company Name"
        );

        companyNameLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        companyNameLabel.setBounds(
                60, 155, 200, 25
        );

        mainPanel.add(companyNameLabel);

        companyNameField = new JTextField();

        companyNameField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        companyNameField.setBounds(
                60, 180, 420, 38
        );

        mainPanel.add(companyNameField);


        // ==========================================
        // COMPANY ID
        // ==========================================

        JLabel companyIdLabel = new JLabel(
                "Company ID"
        );

        companyIdLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        companyIdLabel.setBounds(
                60, 225, 200, 25
        );

        mainPanel.add(companyIdLabel);

        companyIdField = new JTextField();

        companyIdField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        companyIdField.setBounds(
                60, 250, 420, 38
        );

        mainPanel.add(companyIdField);


        // ==========================================
        // EMAIL
        // ==========================================

        JLabel emailLabel = new JLabel(
                "Company Email"
        );

        emailLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        emailLabel.setBounds(
                60, 295, 200, 25
        );

        mainPanel.add(emailLabel);

        emailField = new JTextField();

        emailField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        emailField.setBounds(
                60, 320, 420, 38
        );

        mainPanel.add(emailField);


        // ==========================================
        // PHONE NUMBER
        // ==========================================

        JLabel phoneLabel = new JLabel(
                "Phone Number"
        );

        phoneLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        phoneLabel.setBounds(
                60, 365, 200, 25
        );

        mainPanel.add(phoneLabel);

        phoneField = new JTextField();

        phoneField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        phoneField.setBounds(
                60, 390, 420, 38
        );

        mainPanel.add(phoneField);


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
                60, 435, 200, 25
        );

        mainPanel.add(passwordLabel);

        passwordField = new JPasswordField();

        passwordField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        passwordField.setBounds(
                60, 460, 420, 38
        );

        mainPanel.add(passwordField);


        // ==========================================
        // CONFIRM PASSWORD
        // ==========================================

        JLabel confirmPasswordLabel = new JLabel(
                "Confirm Password"
        );

        confirmPasswordLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        confirmPasswordLabel.setBounds(
                60, 505, 200, 25
        );

        mainPanel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();

        confirmPasswordField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        confirmPasswordField.setBounds(
                60, 530, 420, 38
        );

        mainPanel.add(confirmPasswordField);


        // ==========================================
        // CREATE ACCOUNT BUTTON
        // ==========================================

        JButton createAccountButton = new JButton(
                "CREATE ACCOUNT"
        );

        createAccountButton.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        createAccountButton.setForeground(
                Color.WHITE
        );

        createAccountButton.setBackground(
                new Color(30, 64, 175)
        );

        createAccountButton.setFocusPainted(false);

        createAccountButton.setBounds(
                60, 585, 420, 42
        );

        createAccountButton.addActionListener(
                e -> createAccount()
        );

        mainPanel.add(createAccountButton);


        // ==========================================
        // ADD MAIN PANEL
        // ==========================================

        add(mainPanel);
    }


    // =====================================================
    // CREATE ACCOUNT METHOD
    // =====================================================

    private void createAccount() {

        String companyName =
                companyNameField.getText().trim();

        String companyId =
                companyIdField.getText().trim();

        String email =
                emailField.getText().trim();

        String phone =
                phoneField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        String confirmPassword =
                new String(
                        confirmPasswordField.getPassword()
                );


        // ==========================================
        // EMPTY FIELD VALIDATION
        // ==========================================

        if (companyName.isEmpty()
                || companyId.isEmpty()
                || email.isEmpty()
                || phone.isEmpty()
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


        // ==========================================
        // PASSWORD MATCHING
        // ==========================================

        if (!password.equals(confirmPassword)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Password Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // ==========================================
        // EMAIL VALIDATION
        // ==========================================

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


        // ==========================================
        // FRONTEND ONLY
        // DATABASE WILL BE ADDED LATER
        // ==========================================

        JOptionPane.showMessageDialog(
                this,
                "Company account created successfully!\n"
                        + "Database integration will be added later.",
                "Registration Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Clear fields

        companyNameField.setText("");
        companyIdField.setText("");
        emailField.setText("");
        phoneField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    CompanySignup signup =
                            new CompanySignup();

                    signup.setVisible(true);
                }
        );
    }
}