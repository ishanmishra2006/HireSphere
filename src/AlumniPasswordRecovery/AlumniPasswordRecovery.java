package AlumniPasswordRecovery;

import javax.swing.*;
import java.awt.*;

public class AlumniPasswordRecovery extends JFrame {

    private JTextField emailField;

    public AlumniPasswordRecovery() {

        // ==========================================
        // FRAME SETTINGS
        // ==========================================

        setTitle("HireSphere - Alumni Password Recovery");
        setSize(500, 500);
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

        JLabel titleLabel = new JLabel(
                "HireSphere"
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        36
                )
        );

        titleLabel.setForeground(
                new Color(8, 27, 58)
        );

        titleLabel.setBounds(
                150, 40, 250, 50
        );

        mainPanel.add(titleLabel);


        // ==========================================
        // SUBTITLE
        // ==========================================

        JLabel subtitleLabel = new JLabel(
                "Password Recovery"
        );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        subtitleLabel.setForeground(
                new Color(30, 64, 175)
        );

        subtitleLabel.setBounds(
                135, 95, 300, 40
        );

        mainPanel.add(subtitleLabel);


        // ==========================================
        // DESCRIPTION
        // ==========================================

        JLabel descriptionLabel = new JLabel(
                "<html><center>"
                + "Enter your registered alumni email address.<br>"
                + "We will help you recover your account."
                + "</center></html>"
        );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        descriptionLabel.setForeground(
                Color.GRAY
        );

        descriptionLabel.setBounds(
                80, 145, 340, 55
        );

        mainPanel.add(descriptionLabel);


        // ==========================================
        // EMAIL LABEL
        // ==========================================

        JLabel emailLabel = new JLabel(
                "Registered Email"
        );

        emailLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        emailLabel.setBounds(
                60, 225, 200, 30
        );

        mainPanel.add(emailLabel);


        // ==========================================
        // EMAIL FIELD
        // ==========================================

        emailField = new JTextField();

        emailField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        emailField.setBounds(
                60, 255, 380, 42
        );

        mainPanel.add(emailField);


        // ==========================================
        // SEND RESET LINK BUTTON
        // ==========================================

        JButton resetButton = new JButton(
                "SEND RESET LINK"
        );

        resetButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        resetButton.setForeground(
                Color.WHITE
        );

        resetButton.setBackground(
                new Color(30, 64, 175)
        );

        resetButton.setFocusPainted(
                false
        );

        resetButton.setBounds(
                60, 325, 380, 45
        );

        resetButton.addActionListener(
                e -> sendResetLink()
        );

        mainPanel.add(resetButton);


        // ==========================================
        // BACK TO LOGIN
        // ==========================================

        JButton backButton = new JButton(
                "← Back to Login"
        );

        backButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        backButton.setForeground(
                new Color(8, 27, 58)
        );

        backButton.setBorderPainted(false);

        backButton.setContentAreaFilled(false);

        backButton.setFocusPainted(false);

        backButton.setBounds(
                170, 395, 160, 35
        );

        backButton.addActionListener(
                e -> {

                    dispose();

                    new AlumniLogin.AlumniLogin()
                            .setVisible(true);

                }
        );

        mainPanel.add(backButton);


        // ==========================================
        // ADD PANEL
        // ==========================================

        add(mainPanel);
    }


    // =====================================================
    // SEND RESET LINK
    // =====================================================

    private void sendResetLink() {

        String email =
                emailField.getText().trim();


        // ==========================================
        // EMPTY FIELD VALIDATION
        // ==========================================

        if (email.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your registered email.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
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
                "Password recovery request submitted!\n\n"
                + "Email verification will be connected "
                + "after database integration.",
                "Request Submitted",
                JOptionPane.INFORMATION_MESSAGE
        );

    }


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    AlumniPasswordRecovery recovery =
                            new AlumniPasswordRecovery();

                    recovery.setVisible(true);

                }
        );
    }
}