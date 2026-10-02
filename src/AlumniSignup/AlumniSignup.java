package AlumniSignup;

import javax.swing.*;
import java.awt.*;

public class AlumniSignup extends JFrame {

    private JTextField nameField;
    private JTextField alumniIdField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField graduationYearField;

    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public AlumniSignup() {

        // ==========================================
        // FRAME SETTINGS
        // ==========================================

        setTitle("HireSphere - Alumni Sign Up");
        setSize(550, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        // ==========================================
        // MAIN PANEL
        // ==========================================

        JPanel mainPanel = new JPanel();

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

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
                "Create Alumni Account"
        );

        subtitleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        subtitleLabel.setForeground(
                new Color(30, 64, 175)
        );

        subtitleLabel.setBounds(
                140, 70, 300, 40
        );

        mainPanel.add(subtitleLabel);


        // ==========================================
        // DESCRIPTION
        // ==========================================

        JLabel descriptionLabel = new JLabel(
                "Reconnect with your college community"
        );

        descriptionLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        descriptionLabel.setForeground(Color.GRAY);

        descriptionLabel.setBounds(
                150, 110, 300, 30
        );

        mainPanel.add(descriptionLabel);


        // ==========================================
        // FULL NAME
        // ==========================================

        JLabel nameLabel = new JLabel("Full Name");

        nameLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        nameLabel.setBounds(
                60, 150, 200, 25
        );

        mainPanel.add(nameLabel);

        nameField = new JTextField();

        nameField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        nameField.setBounds(
                60, 175, 420, 38
        );

        mainPanel.add(nameField);


        // ==========================================
        // ALUMNI ID
        // ==========================================

        JLabel alumniIdLabel = new JLabel("Alumni ID");

        alumniIdLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        alumniIdLabel.setBounds(
                60, 220, 200, 25
        );

        mainPanel.add(alumniIdLabel);

        alumniIdField = new JTextField();

        alumniIdField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        alumniIdField.setBounds(
                60, 245, 420, 38
        );

        mainPanel.add(alumniIdField);


        // ==========================================
        // EMAIL
        // ==========================================

        JLabel emailLabel = new JLabel("Email Address");

        emailLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        emailLabel.setBounds(
                60, 290, 200, 25
        );

        mainPanel.add(emailLabel);

        emailField = new JTextField();

        emailField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        emailField.setBounds(
                60, 315, 420, 38
        );

        mainPanel.add(emailField);


        // ==========================================
        // PHONE
        // ==========================================

        JLabel phoneLabel = new JLabel("Phone Number");

        phoneLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        phoneLabel.setBounds(
                60, 360, 200, 25
        );

        mainPanel.add(phoneLabel);

        phoneField = new JTextField();

        phoneField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        phoneField.setBounds(
                60, 385, 420, 38
        );

        mainPanel.add(phoneField);


        // ==========================================
        // GRADUATION YEAR
        // ==========================================

        JLabel graduationLabel = new JLabel(
                "Graduation Year"
        );

        graduationLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        graduationLabel.setBounds(
                60, 430, 200, 25
        );

        mainPanel.add(graduationLabel);

        graduationYearField = new JTextField();

        graduationYearField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        graduationYearField.setBounds(
                60, 455, 420, 38
        );

        mainPanel.add(graduationYearField);


        // ==========================================
        // PASSWORD
        // ==========================================

        JLabel passwordLabel = new JLabel("Password");

        passwordLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        passwordLabel.setBounds(
                60, 500, 200, 25
        );

        mainPanel.add(passwordLabel);

        passwordField = new JPasswordField();

        passwordField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        passwordField.setBounds(
                60, 525, 420, 38
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
                60, 570, 200, 25
        );

        mainPanel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();

        confirmPasswordField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        confirmPasswordField.setBounds(
                60, 595, 420, 38
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
                60, 650, 420, 42
        );

        createAccountButton.addActionListener(
                e -> createAccount()
        );

        mainPanel.add(createAccountButton);


        // ==========================================
        // ADD PANEL
        // ==========================================

        add(mainPanel);
    }


    // =====================================================
    // CREATE ACCOUNT METHOD
    // =====================================================

    private void createAccount() {

        String name =
                nameField.getText().trim();

        String alumniId =
                alumniIdField.getText().trim();

        String email =
                emailField.getText().trim();

        String phone =
                phoneField.getText().trim();

        String graduationYear =
                graduationYearField.getText().trim();

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

        if (name.isEmpty()
                || alumniId.isEmpty()
                || email.isEmpty()
                || phone.isEmpty()
                || graduationYear.isEmpty()
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
        // PASSWORD VALIDATION
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
        // ==========================================

        JOptionPane.showMessageDialog(
                this,
                "Alumni account created successfully!\n"
                        + "Database integration will be added later.",
                "Registration Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Clear fields

        nameField.setText("");
        alumniIdField.setText("");
        emailField.setText("");
        phoneField.setText("");
        graduationYearField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    AlumniSignup signup =
                            new AlumniSignup();

                    signup.setVisible(true);

                }
        );
    }
}