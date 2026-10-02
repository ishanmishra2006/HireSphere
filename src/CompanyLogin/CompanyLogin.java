package CompanyLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import companydashboard.CompanyDashboard;

public class CompanyLogin extends JFrame {

    // Components
    private JTextField companyIdField;
    private JPasswordField passwordField;

    public CompanyLogin() {

        // ================================
        // FRAME SETTINGS
        // ================================

        setTitle("HireSphere - Company Login");
        setSize(500, 600);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);

        // ================================
        // MAIN PANEL
        // ================================

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setLayout(null);

        // ================================
        // TITLE
        // ================================

        JLabel titleLabel = new JLabel("HireSphere");

        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 36)
        );

        titleLabel.setForeground(
                new Color(8, 27, 58)
        );

        titleLabel.setBounds(145, 30, 250, 50);

        mainPanel.add(titleLabel);

        // ================================
        // SUBTITLE
        // ================================

        JLabel subtitleLabel = new JLabel("Company Login");

        subtitleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        subtitleLabel.setForeground(
                new Color(30, 64, 175)
        );

        subtitleLabel.setBounds(165, 85, 200, 40);

        mainPanel.add(subtitleLabel);

        // ================================
        // DESCRIPTION
        // ================================

        JLabel descriptionLabel = new JLabel(
                "Login to manage your recruitment activities"
        );

        descriptionLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        descriptionLabel.setForeground(Color.GRAY);

        descriptionLabel.setBounds(
                100, 125, 320, 30
        );

        mainPanel.add(descriptionLabel);

        // ================================
        // COMPANY ID LABEL
        // ================================

        JLabel companyIdLabel = new JLabel(
                "Company ID / Email"
        );

        companyIdLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        companyIdLabel.setBounds(
                70, 190, 200, 30
        );

        mainPanel.add(companyIdLabel);

        // ================================
        // COMPANY ID FIELD
        // ================================

        companyIdField = new JTextField();

        companyIdField.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        companyIdField.setBounds(
                70, 220, 350, 40
        );

        mainPanel.add(companyIdField);

        // ================================
        // PASSWORD LABEL
        // ================================

        JLabel passwordLabel = new JLabel(
                "Password"
        );

        passwordLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        passwordLabel.setBounds(
                70, 280, 200, 30
        );

        mainPanel.add(passwordLabel);

        // ================================
        // PASSWORD FIELD
        // ================================

        passwordField = new JPasswordField();

        passwordField.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        passwordField.setBounds(
                70, 310, 350, 40
        );

        mainPanel.add(passwordField);

        // ================================
        // SHOW PASSWORD
        // ================================

        JCheckBox showPassword = new JCheckBox(
                "Show Password"
        );

        showPassword.setBackground(
                new Color(245, 247, 250)
        );

        showPassword.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        showPassword.setBounds(
                70, 355, 150, 25
        );

        showPassword.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (showPassword.isSelected()) {

                            passwordField.setEchoChar((char) 0);

                        } else {

                            passwordField.setEchoChar('•');
                        }
                    }
                }
        );

        mainPanel.add(showPassword);

        // ================================
        // FORGOT PASSWORD
        // ================================

        JButton forgotButton = new JButton(
                "Forgot Password?"
        );

        forgotButton.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        forgotButton.setForeground(
                new Color(30, 64, 175)
        );

        forgotButton.setBorderPainted(false);
        forgotButton.setContentAreaFilled(false);
        forgotButton.setFocusPainted(false);

        forgotButton.setBounds(
                280, 355, 140, 25
        );

        forgotButton.addActionListener(e -> {

            CompanyPasswordRecovery.CompanyPasswordRecovery recovery =
                    new CompanyPasswordRecovery.CompanyPasswordRecovery();

            recovery.setVisible(true);

            dispose();
        });

        mainPanel.add(forgotButton);

        // ================================
        // LOGIN BUTTON
        // ================================

        JButton loginButton = new JButton(
                "LOGIN"
        );

        loginButton.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        loginButton.setForeground(Color.WHITE);

        loginButton.setBackground(
                new Color(30, 64, 175)
        );

        loginButton.setFocusPainted(false);

        loginButton.setBounds(
                70, 405, 350, 45
        );

        // LOGIN ACTION
        loginButton.addActionListener(
                e -> login()
        );

        mainPanel.add(loginButton);

        // ================================
        // SIGN UP TEXT
        // ================================

        JLabel signupText = new JLabel(
                "Don't have a company account?"
        );

        signupText.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        signupText.setBounds(
                110, 470, 200, 30
        );

        mainPanel.add(signupText);

        // ================================
        // SIGN UP BUTTON
        // ================================

        JButton signupButton = new JButton(
                "SignUp"
        );

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
                300, 470, 80, 30
        );

        signupButton.addActionListener(e -> {

            CompanySignup.CompanySignup signup =
                    new CompanySignup.CompanySignup();

            signup.setVisible(true);

            dispose();
        });

        mainPanel.add(signupButton);

        // ================================
        // ADD PANEL
        // ================================

        add(mainPanel);
    }

    // =====================================================
    // LOGIN METHOD
    // =====================================================

    private void login() {

        String companyId =
                companyIdField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        // ================================
        // CHECK EMPTY COMPANY ID
        // ================================

        if (companyId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Company ID or Email.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ================================
        // CHECK EMPTY PASSWORD
        // ================================

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ================================
        // LOGIN SUCCESS
        // ================================

        JOptionPane.showMessageDialog(
                this,
                "Login Successful!\nWelcome to HireSphere.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        // ================================
        // OPEN COMPANY DASHBOARD
        // ================================

        CompanyDashboard dashboard =
                new CompanyDashboard();

        dashboard.setVisible(true);

        // Close login window
        dispose();
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    CompanyLogin login =
                            new CompanyLogin();

                    login.setVisible(true);
                }
        );
    }
}