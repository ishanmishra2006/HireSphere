package StudentSignup;

import javax.swing.*;
import java.awt.*;

public class StudentSignup extends JFrame {

    private JTextField studentNameField;
    private JTextField rollNoField;
    private JTextField emailField;
    private JTextField phoneField;

    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public StudentSignup() {

        // ==========================================
        // FRAME SETTINGS
        // ==========================================

        setTitle("HireSphere - Student Sign Up");
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
                "Create Student Account"
        );

        subtitleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        subtitleLabel.setForeground(
                new Color(30, 64, 175)
        );

        subtitleLabel.setBounds(
                150, 70, 300, 40
        );

        mainPanel.add(subtitleLabel);

        // ==========================================
        // DESCRIPTION
        // ==========================================

        JLabel descriptionLabel = new JLabel(
                "Join HireSphere to connect with top recruiting companies"
        );

        descriptionLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        descriptionLabel.setForeground(Color.GRAY);

        descriptionLabel.setBounds(
                95, 110, 380, 30
        );

        mainPanel.add(descriptionLabel);

        // ==========================================
        // FULL NAME
        // ==========================================

        JLabel studentNameLabel = new JLabel(
                "Full Name"
        );

        studentNameLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        studentNameLabel.setBounds(
                60, 155, 200, 25
        );

        mainPanel.add(studentNameLabel);

        studentNameField = new JTextField();

        studentNameField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        studentNameField.setBounds(
                60, 180, 420, 38
        );

        mainPanel.add(studentNameField);

        // ==========================================
        // ROLL NO / STUDENT ID
        // ==========================================

        JLabel rollNoLabel = new JLabel(
                "Roll Number / Student ID"
        );

        rollNoLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        rollNoLabel.setBounds(
                60, 225, 200, 25
        );

        mainPanel.add(rollNoLabel);

        rollNoField = new JTextField();

        rollNoField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        rollNoField.setBounds(
                60, 250, 420, 38
        );

        mainPanel.add(rollNoField);

        // ==========================================
        // EMAIL
        // ==========================================

        JLabel emailLabel = new JLabel(
                "Student Email"
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

        String studentName =
                studentNameField.getText().trim();

        String rollNo =
                rollNoField.getText().trim();

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

        // Empty field check
        if (studentName.isEmpty()
                || rollNo.isEmpty()
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

        // Password equality check
        if (!password.equals(confirmPassword)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Password Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Email check
        if (!email.contains("@") || !email.contains(".")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Invalid Email",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Student account created successfully!",
                "Registration Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Clear input fields
        studentNameField.setText("");
        rollNoField.setText("");
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

                    StudentSignup signup =
                            new StudentSignup();

                    signup.setVisible(true);
                }
        );
    }
}