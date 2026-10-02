package Home;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import AdminLogin.AdminLogin;
import AlumniLogin.AlumniLogin;
import CompanyLogin.CompanyLogin;
import StudentLogin.StudentLogin;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 1L;

    // =========================================================
    // COLORS
    // =========================================================

    private final Color NAVY = new Color(8, 27, 58);
    private final Color BLUE = new Color(30, 64, 175);
    private final Color GOLD = new Color(216, 155, 29);
    private final Color LIGHT_BACKGROUND = new Color(245, 247, 250);

    // =========================================================
    // CARD LAYOUT
    // =========================================================

    private CardLayout cardLayout;
    private JPanel mainContentPanel;

    private JButton homeButton;
    private JButton aboutButton;
    private JButton contactButton;

    // =========================================================
    // IMAGE SLIDER
    // =========================================================
   
    private JLabel sliderImageLabel;
    private JLabel sliderCaptionLabel;
    private JLabel sliderNumberLabel;

    private int currentImageIndex = 0;

    private final String[] sliderImages = {
            "Logo.png",
            "slide1.jpg",
            "slide2.jpg",
            "slide3.jpg"
    };

    private final String[] sliderCaptions = {
            "Empowering Future Professionals",
            "Connecting Talent with Top Recruiters",
            "Your Dream Career Starts Here",
            "Building Careers and Creating Opportunities"
    };

    // =========================================================
    // TYPING ANIMATION
    // =========================================================

    private JLabel typingLabel;

    private int typingMessageIndex = 0;
    private int typingCharacterIndex = 0;

    private final String[] typingMessages = {
            "Connecting Students with Opportunities...",
            "Connecting Companies with Talented Candidates...",
            "Building Successful Careers...",
            "Creating a Better Placement Experience..."
    };

    // =========================================================
    // MARQUEE
    // =========================================================

    private JLabel marqueeLabel;
    private int marqueeX;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public HomePage() {

        setTitle("HireSphere - University Placement Portal");

        setMinimumSize(new Dimension(1100, 650));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ImageIcon appIcon = loadImage("Logo.png", 60, 60);

        if (appIcon != null) {
            setIconImage(appIcon.getImage());
        }

        add(createNavigationBar(), BorderLayout.NORTH);

        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);

        mainContentPanel.add(createHomePanel(), "HOME");
        mainContentPanel.add(createAboutPanel(), "ABOUT");
        mainContentPanel.add(createContactPanel(), "CONTACT");

        add(mainContentPanel, BorderLayout.CENTER);

        startTypingAnimation();
        startMarqueeAnimation();

        cardLayout.show(mainContentPanel, "HOME");
        updateNavigationButton(homeButton);

        setVisible(true);
    }

    // =========================================================
    // NAVIGATION BAR
    // =========================================================

    private JPanel createNavigationBar() {

        JPanel navigationBar = new JPanel(new BorderLayout());

        navigationBar.setBackground(Color.WHITE);
        navigationBar.setPreferredSize(new Dimension(1366, 120));

        navigationBar.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 2, 0,
                        new Color(225, 228, 235)));

        // LOGO

        JPanel logoPanel =
                new JPanel(new FlowLayout(
                        FlowLayout.LEFT, 20, 8));

        logoPanel.setOpaque(false);

        JLabel logoLabel = new JLabel();

        ImageIcon logoIcon =
                loadImage("Logo.png", 100, 100);

        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } else {

            logoLabel.setText("HS");

            logoLabel.setFont(
                    new Font("Segoe UI",
                            Font.BOLD, 24));

            logoLabel.setForeground(GOLD);
        }

        JLabel projectNameLabel =
                new JLabel("HireSphere");

        projectNameLabel.setFont(
                new Font("Serif",
                        Font.BOLD, 28));

        projectNameLabel.setForeground(NAVY);

        JLabel taglineLabel =
                new JLabel("Where Talent Meets Opportunity");

        taglineLabel.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 12));

        taglineLabel.setForeground(
                new Color(100, 105, 115));

        JPanel titlePanel = new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS));

        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(projectNameLabel);
        titlePanel.add(taglineLabel);

        logoPanel.add(logoLabel);
        logoPanel.add(titlePanel);

        // NAVIGATION BUTTONS

        JPanel navigationButtonsPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                25,
                                20));

        navigationButtonsPanel.setOpaque(false);

        homeButton = createNavigationButton("Home");
        aboutButton = createNavigationButton("About");
        contactButton = createNavigationButton("Contact");

        homeButton.addActionListener(e -> {

            cardLayout.show(mainContentPanel, "HOME");
            updateNavigationButton(homeButton);

        });

        aboutButton.addActionListener(e -> {

            cardLayout.show(mainContentPanel, "ABOUT");
            updateNavigationButton(aboutButton);

        });

        contactButton.addActionListener(e -> {

            cardLayout.show(mainContentPanel, "CONTACT");
            updateNavigationButton(contactButton);

        });

        navigationButtonsPanel.add(homeButton);
        navigationButtonsPanel.add(aboutButton);
        navigationButtonsPanel.add(contactButton);

        // DATE AND TIME

        JPanel timePanel = new JPanel();

        timePanel.setOpaque(false);

        timePanel.setLayout(
                new BoxLayout(
                        timePanel,
                        BoxLayout.Y_AXIS));

        timePanel.setBorder(
                new EmptyBorder(
                        18, 10, 10, 25));

        JLabel dateLabel = new JLabel();
        JLabel timeLabel = new JLabel();

        dateLabel.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 13));

        dateLabel.setForeground(
                new Color(90, 95, 105));

        dateLabel.setAlignmentX(
                Component.RIGHT_ALIGNMENT);

        timeLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 16));

        timeLabel.setForeground(NAVY);

        timeLabel.setAlignmentX(
                Component.RIGHT_ALIGNMENT);

        timePanel.add(dateLabel);
        timePanel.add(Box.createVerticalStrut(4));
        timePanel.add(timeLabel);

        Timer clockTimer =
                new Timer(1000, e -> {

                    Date now = new Date();

                    dateLabel.setText(
                            new SimpleDateFormat(
                                    "EEEE, dd MMMM yyyy")
                                    .format(now));

                    timeLabel.setText(
                            new SimpleDateFormat(
                                    "hh:mm:ss a")
                                    .format(now));
                });

        clockTimer.start();

        navigationBar.add(
                logoPanel,
                BorderLayout.WEST);

        navigationBar.add(
                navigationButtonsPanel,
                BorderLayout.CENTER);

        navigationBar.add(
                timePanel,
                BorderLayout.EAST);

        return navigationBar;
    }

    // =========================================================
    // NAVIGATION BUTTON
    // =========================================================

    private JButton createNavigationButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 15));

        button.setForeground(NAVY);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        if (!button.getForeground()
                                .equals(GOLD)) {

                            button.setForeground(BLUE);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        if (!button.getForeground()
                                .equals(GOLD)) {

                            button.setForeground(NAVY);
                        }
                    }
                });

        return button;
    }

    private void updateNavigationButton(
            JButton selectedButton) {

        homeButton.setForeground(NAVY);
        aboutButton.setForeground(NAVY);
        contactButton.setForeground(NAVY);

        selectedButton.setForeground(GOLD);
    }

    // =========================================================
    // HOME PANEL
    // =========================================================

    private JPanel createHomePanel() {

        JPanel completeHome =
                new ScrollableContentPanel();

        completeHome.setLayout(
                new BoxLayout(
                        completeHome,
                        BoxLayout.Y_AXIS));

        completeHome.setBackground(
                LIGHT_BACKGROUND);

        JPanel heroPanel =
                new JPanel(
                        new GridLayout(
                                1, 2, 15, 0));

        heroPanel.setBackground(
                LIGHT_BACKGROUND);

        heroPanel.setBorder(
                new EmptyBorder(
                        30, 35, 15, 35));

        heroPanel.add(
                createLeftContentPanel());

        heroPanel.add(
                createImageSliderPanel());

        completeHome.add(heroPanel);

        completeHome.add(
                Box.createVerticalStrut(10));

        completeHome.add(
                createMarqueePanel());

        completeHome.add(
                Box.createVerticalStrut(20));

        completeHome.add(
                createCompanySection());

        JScrollPane scrollPane =
                new JScrollPane(
                        completeHome,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        completeHome.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE));

        JPanel container =
                new JPanel(
                        new BorderLayout());

        container.add(
                scrollPane,
                BorderLayout.CENTER);

        return container;
    }

    // =========================================================
    // HOME LEFT CONTENT
    // =========================================================

    private JPanel createLeftContentPanel() {

        JPanel leftPanel = new JPanel();

        leftPanel.setOpaque(false);

        leftPanel.setLayout(
                new BoxLayout(
                        leftPanel,
                        BoxLayout.Y_AXIS));

        leftPanel.setBorder(
                new EmptyBorder(
                        8, 8, 8, 8));

        JLabel smallHeading =
                new JLabel(
                        "WHERE TALENT MEETS OPPORTUNITY");

        smallHeading.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 14));

        smallHeading.setForeground(GOLD);

        smallHeading.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        JLabel mainHeading =
                new JLabel("HireSphere");

        mainHeading.setFont(
                new Font("Serif",
                        Font.BOLD, 54));

        mainHeading.setForeground(NAVY);

        mainHeading.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        JLabel subHeading =
                new JLabel(
                        "University Placement Portal");

        subHeading.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 20));

        subHeading.setForeground(BLUE);

        subHeading.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        JTextArea descriptionArea =
                new JTextArea(
                        "HireSphere is a centralized placement portal designed to connect "
                        + "students, alumni, companies and administrators. It provides an "
                        + "easy and organized platform for job applications, recruitment, "
                        + "interview scheduling and placement tracking.");

        descriptionArea.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 15));

        descriptionArea.setForeground(
                new Color(70, 75, 85));

        descriptionArea.setOpaque(false);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setRows(4);

        descriptionArea.setPreferredSize(
                new Dimension(600, 90));

        descriptionArea.setMaximumSize(
                new Dimension(620, 90));

        descriptionArea.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        typingLabel = new JLabel(" ");

        typingLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 16));

        typingLabel.setForeground(GOLD);

        typingLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        JPanel featurePanel =
                new JPanel(
                        new GridLayout(
                                2, 3, 12, 8));

        featurePanel.setOpaque(false);

        featurePanel.setMaximumSize(
                new Dimension(620, 75));

        featurePanel.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        featurePanel.add(
                createFeatureLabel("✓ Job Opportunities"));

        featurePanel.add(
                createFeatureLabel("✓ Resume Upload"));

        featurePanel.add(
                createFeatureLabel("✓ Campus Drives"));

        featurePanel.add(
                createFeatureLabel("✓ Interview Schedule"));

        featurePanel.add(
                createFeatureLabel("✓ Alumni Network"));

        featurePanel.add(
                createFeatureLabel("✓ Placement Tracking"));

        JLabel portalHeading =
                new JLabel("Choose Your Portal");

        portalHeading.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 18));

        portalHeading.setForeground(NAVY);

        portalHeading.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        JPanel portalButtonPanel =
                new JPanel(
                        new GridLayout(
                                2, 2, 15, 12));

        portalButtonPanel.setOpaque(false);

        portalButtonPanel.setMaximumSize(
                new Dimension(570, 110));

        portalButtonPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        JButton studentButton =
                createPortalButton(
                        "STUDENT PORTAL");

        JButton alumniButton =
                createPortalButton(
                        "ALUMNI PORTAL");

        JButton companyButton =
                createPortalButton(
                        "COMPANY PORTAL");

        JButton adminButton =
                createPortalButton(
                        "ADMIN PORTAL");

        studentButton.addActionListener(e -> {
            new StudentLogin().setVisible(true);
            dispose();
        });

        alumniButton.addActionListener(e -> {
            new AlumniLogin().setVisible(true);
            dispose();
        });

        companyButton.addActionListener(e -> {
            new CompanyLogin().setVisible(true);
            dispose();
        });

        adminButton.addActionListener(e -> {
            new AdminLogin().setVisible(true);
            dispose();
        });

        portalButtonPanel.add(studentButton);
        portalButtonPanel.add(alumniButton);
        portalButtonPanel.add(companyButton);
        portalButtonPanel.add(adminButton);

        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(
                                1, 4, 8, 0));

        statisticsPanel.setOpaque(false);

        statisticsPanel.setMaximumSize(
                new Dimension(620, 68));

        statisticsPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        statisticsPanel.add(
                createStatisticLabel(
                        "1200+", "Students"));

        statisticsPanel.add(
                createStatisticLabel(
                        "85+", "Companies"));

        statisticsPanel.add(
                createStatisticLabel(
                        "540+", "Jobs"));

        statisticsPanel.add(
                createStatisticLabel(
                        "950+", "Placed"));

        leftPanel.add(smallHeading);

        leftPanel.add(
                Box.createVerticalStrut(5));

        leftPanel.add(mainHeading);

        leftPanel.add(
                Box.createVerticalStrut(3));

        leftPanel.add(subHeading);

        leftPanel.add(
                Box.createVerticalStrut(12));

        leftPanel.add(descriptionArea);

        leftPanel.add(
                Box.createVerticalStrut(8));

        leftPanel.add(typingLabel);

        leftPanel.add(
                Box.createVerticalStrut(15));

        leftPanel.add(featurePanel);

        leftPanel.add(
                Box.createVerticalStrut(15));

        leftPanel.add(portalHeading);

        leftPanel.add(
                Box.createVerticalStrut(8));

        leftPanel.add(portalButtonPanel);

        leftPanel.add(
                Box.createVerticalStrut(14));

        leftPanel.add(statisticsPanel);

        return leftPanel;
    }

    // =========================================================
    // FEATURE LABEL
    // =========================================================

    private JLabel createFeatureLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 14));

        label.setForeground(
                new Color(45, 55, 70));

        return label;
    }

    // =========================================================
    // STATISTIC CARD
    // =========================================================

    private JPanel createStatisticLabel(
            String number,
            String text) {

        JPanel panel = new JPanel();

        panel.setOpaque(false);

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS));

        JLabel numberLabel =
                new JLabel(number);

        numberLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 20));

        numberLabel.setForeground(BLUE);

        numberLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JLabel textLabel =
                new JLabel(text);

        textLabel.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 12));

        textLabel.setForeground(
                new Color(95, 100, 110));

        textLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        panel.add(numberLabel);
        panel.add(textLabel);

        return panel;
    }

    // =========================================================
    // PORTAL BUTTON
    // =========================================================

    private JButton createPortalButton(String text) {

        JButton button = new JButton(text) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics graphics) {

                Graphics2D g2 =
                        (Graphics2D) graphics.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isRollover()) {
                    g2.setColor(GOLD);
                } else {
                    g2.setColor(BLUE);
                }

                g2.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        22,
                        22);

                g2.dispose();

                super.paintComponent(graphics);
            }
        };

        button.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 14));

        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // =========================================================
    // IMAGE SLIDER
    // =========================================================

    private JPanel createImageSliderPanel() {

        JPanel outerPanel =
                new JPanel(
                        new BorderLayout());

        outerPanel.setBackground(Color.WHITE);

        outerPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        224,
                                        232),
                                1),
                        new EmptyBorder(
                                12,
                                12,
                                12,
                                12)));

        sliderImageLabel = new JLabel();

        sliderImageLabel.setHorizontalAlignment(
                SwingConstants.CENTER);

        sliderImageLabel.setVerticalAlignment(
                SwingConstants.CENTER);

        sliderImageLabel.setOpaque(true);

        sliderImageLabel.setBackground(
                new Color(
                        230,
                        234,
                        242));

        sliderCaptionLabel =
                new JLabel(
                        sliderCaptions[currentImageIndex],
                        SwingConstants.CENTER);

        sliderCaptionLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 18));

        sliderCaptionLabel.setForeground(Color.WHITE);

        sliderCaptionLabel.setOpaque(true);

        sliderCaptionLabel.setBackground(NAVY);

        sliderCaptionLabel.setBorder(
                new EmptyBorder(
                        10,
                        5,
                        10,
                        5));

        sliderNumberLabel =
                new JLabel(
                        "●  ○  ○  ○",
                        SwingConstants.CENTER);

        sliderNumberLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 16));

        sliderNumberLabel.setForeground(GOLD);

        sliderNumberLabel.setBorder(
                new EmptyBorder(
                        8,
                        0,
                        5,
                        0));

        JPanel imageArea =
                new JPanel(
                        new BorderLayout());

        imageArea.setOpaque(false);

        imageArea.add(
                sliderImageLabel,
                BorderLayout.CENTER);

        imageArea.add(
                sliderCaptionLabel,
                BorderLayout.SOUTH);

        JButton previousButton =
                createSliderButton("‹");

        JButton nextButton =
                createSliderButton("›");

        previousButton.addActionListener(
                e -> previousSlide());

        nextButton.addActionListener(
                e -> nextSlide());

        outerPanel.add(
                previousButton,
                BorderLayout.WEST);

        outerPanel.add(
                imageArea,
                BorderLayout.CENTER);

        outerPanel.add(
                nextButton,
                BorderLayout.EAST);

        outerPanel.add(
                sliderNumberLabel,
                BorderLayout.SOUTH);

        showSliderImage();

        Timer sliderTimer =
                new Timer(
                        2500,
                        e -> nextSlide());

        sliderTimer.start();

        return outerPanel;
    }

    // =========================================================
    // SLIDER BUTTON
    // =========================================================

    private JButton createSliderButton(String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 22));

        button.setPreferredSize(
                new Dimension(35, 50));

        button.setForeground(NAVY);
        button.setBackground(Color.WHITE);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setForeground(GOLD);
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setForeground(NAVY);
                    }
                });

        return button;
    }

    private void nextSlide() {

        currentImageIndex++;

        if (currentImageIndex >=
                sliderImages.length) {

            currentImageIndex = 0;
        }

        showSliderImage();
    }

    private void previousSlide() {

        currentImageIndex--;

        if (currentImageIndex < 0) {

            currentImageIndex =
                    sliderImages.length - 1;
        }

        showSliderImage();
    }

    private void showSliderImage() {

        ImageIcon imageIcon =
                loadImage(
                        sliderImages[currentImageIndex],
                        430,
                        340);

        if (imageIcon != null) {

            sliderImageLabel.setIcon(imageIcon);
            sliderImageLabel.setText("");

        } else {

            sliderImageLabel.setIcon(null);

            sliderImageLabel.setText(
                    "<html><div style='text-align:center;'>"
                    + "<h2>Image not found</h2>"
                    + sliderImages[currentImageIndex]
                    + "</div></html>");

            sliderImageLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            16));

            sliderImageLabel.setForeground(NAVY);
        }

        sliderCaptionLabel.setText(
                sliderCaptions[currentImageIndex]);

        updateSliderDots();
    }

    private void updateSliderDots() {

        StringBuilder dots =
                new StringBuilder();

        for (int i = 0;
                i < sliderImages.length;
                i++) {

            if (i == currentImageIndex) {
                dots.append("●");
            } else {
                dots.append("○");
            }

            if (i < sliderImages.length - 1) {
                dots.append("   ");
            }
        }

        sliderNumberLabel.setText(
                dots.toString());
    }

    // =========================================================
    // MARQUEE
    // =========================================================

    private JPanel createMarqueePanel() {

        JPanel marqueePanel =
                new JPanel(null);

        marqueePanel.setBackground(NAVY);

        marqueePanel.setPreferredSize(
                new Dimension(1366, 42));

        marqueeLabel =
                new JLabel(
                        "★ Welcome to HireSphere     "
                        + "★ TCS Campus Drive Registration Open     "
                        + "★ Infosys Recruitment Update     "
                        + "★ Resume Submission Deadline     "
                        + "★ Internship Opportunities Available");

        marqueeLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 14));

        marqueeLabel.setForeground(Color.WHITE);

        marqueeLabel.setBounds(
                1366,
                0,
                1150,
                42);

        marqueePanel.add(marqueeLabel);

        return marqueePanel;
    }

    private void startMarqueeAnimation() {

        marqueeX = 1366;

        Timer marqueeTimer =
                new Timer(
                        20,
                        e -> {

                            marqueeX -= 2;

                            if (marqueeX +
                                    marqueeLabel.getWidth() < 0) {

                                marqueeX = getWidth();
                            }

                            marqueeLabel.setLocation(
                                    marqueeX,
                                    0);
                        });

        marqueeTimer.start();
    }

    // =========================================================
    // TYPING ANIMATION
    // =========================================================

    private void startTypingAnimation() {

        Timer typingTimer =
                new Timer(75, null);

        typingTimer.addActionListener(
                e -> {

                    String currentMessage =
                            typingMessages[
                                    typingMessageIndex];

                    if (typingCharacterIndex
                            <= currentMessage.length()) {

                        typingLabel.setText(
                                currentMessage.substring(
                                        0,
                                        typingCharacterIndex));

                        typingCharacterIndex++;

                    } else {

                        typingCharacterIndex = 0;

                        typingMessageIndex++;

                        if (typingMessageIndex
                                >= typingMessages.length) {

                            typingMessageIndex = 0;
                        }
                    }
                });

        typingTimer.start();
    }

    // =========================================================
    // COMPANY SECTION
    // =========================================================

    private JPanel createCompanySection() {

        JPanel main =
                new JPanel(
                        new BorderLayout());

        main.setBackground(Color.WHITE);

        main.setBorder(
                new EmptyBorder(
                        30,
                        50,
                        40,
                        50));

        JLabel heading =
                new JLabel(
                        "Our Recruiting Partners",
                        SwingConstants.CENTER);

        heading.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 32));

        heading.setForeground(NAVY);

        JLabel sub =
                new JLabel(
                        "Trusted by leading companies for campus recruitment",
                        SwingConstants.CENTER);

        sub.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 16));

        sub.setForeground(GOLD);

        JPanel title = new JPanel();

        title.setOpaque(false);

        title.setLayout(
                new BoxLayout(
                        title,
                        BoxLayout.Y_AXIS));

        heading.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        sub.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        title.add(heading);

        title.add(
                Box.createVerticalStrut(5));

        title.add(sub);

        JPanel companies =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                25,
                                25));

        companies.setOpaque(false);

        companies.add(
                createCompanyCard(
                        "TCS",
                        "tcs.png"));

        companies.add(
                createCompanyCard(
                        "Infosys",
                        "infosys.png"));

        companies.add(
                createCompanyCard(
                        "Accenture",
                        "accenture.png"));

        companies.add(
                createCompanyCard(
                        "Wipro",
                        "wipro.png"));

        companies.add(
                createCompanyCard(
                        "Cognizant",
                        "cognizant.png"));

        companies.add(
                createCompanyCard(
                        "Capgemini",
                        "capgemini.png"));

        main.add(
                title,
                BorderLayout.NORTH);

        main.add(
                companies,
                BorderLayout.CENTER);

        main.add(
                createFooter(),
                BorderLayout.SOUTH);

        return main;
    }

    // =========================================================
    // COMPANY CARD
    // =========================================================

    private JPanel createCompanyCard(
            String name,
            String logo) {

        JPanel card =
                new JPanel(
                        new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        235)),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15)));

        JLabel logoLabel = new JLabel();

        logoLabel.setHorizontalAlignment(
                SwingConstants.CENTER);

        ImageIcon icon =
                loadImage(
                        logo,
                        120,
                        80);

        if (icon != null) {
            logoLabel.setIcon(icon);
        }

        JLabel nameLabel =
                new JLabel(
                        name,
                        SwingConstants.CENTER);

        nameLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 16));

        nameLabel.setForeground(NAVY);

        card.add(
                logoLabel,
                BorderLayout.CENTER);

        card.add(
                nameLabel,
                BorderLayout.SOUTH);

        return card;
    }

    // =========================================================
    // FOOTER
    // =========================================================

    private JPanel createFooter() {

        JPanel footer = new JPanel();

        footer.setBackground(NAVY);

        footer.setPreferredSize(
                new Dimension(
                        1366,
                        50));

        JLabel copyright =
                new JLabel(
                        "© 2026 HireSphere | University Placement Portal | All Rights Reserved",
                        SwingConstants.CENTER);

        copyright.setForeground(Color.WHITE);

        copyright.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 14));

        footer.add(copyright);

        return footer;
    }

    // =========================================================
    // ABOUT PANEL
    // =========================================================

    private JPanel createAboutPanel() {

        JPanel completeAbout =
                new ScrollableContentPanel();

        completeAbout.setBackground(
                LIGHT_BACKGROUND);

        completeAbout.setLayout(
                new BoxLayout(
                        completeAbout,
                        BoxLayout.Y_AXIS));

        completeAbout.setBorder(
                new EmptyBorder(
                        30,
                        65,
                        40,
                        65));

        JLabel title =
                new JLabel(
                        "About HireSphere",
                        SwingConstants.CENTER);

        title.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 34));

        title.setForeground(NAVY);

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JLabel subtitle =
                new JLabel(
                        "Connecting Talent, Building Careers and Creating Opportunities",
                        SwingConstants.CENTER);

        subtitle.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 16));

        subtitle.setForeground(GOLD);

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        completeAbout.add(title);

        completeAbout.add(
                Box.createVerticalStrut(5));

        completeAbout.add(subtitle);

        completeAbout.add(
                Box.createVerticalStrut(25));

        JPanel contentPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                25,
                                0));

        contentPanel.setOpaque(false);

        JPanel aboutCard =
                createInformationCard(
                        "What is HireSphere?",
                        "HireSphere is a University Placement Portal "
                        + "developed to simplify and organize campus recruitment. "
                        + "It connects students, alumni, companies and administrators "
                        + "through one common platform.\n\n"
                        + "Students can explore opportunities, upload resumes and "
                        + "track applications. Companies can post jobs and find "
                        + "suitable candidates. Alumni can guide students, while "
                        + "administrators can manage the complete placement process.");

        JPanel detailPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                12));

        detailPanel.setOpaque(false);

        detailPanel.add(
                createSmallInformationCard(
                        "Our Vision",
                        "To build a transparent and efficient placement system "
                        + "that helps every student discover the right career."));

        detailPanel.add(
                createSmallInformationCard(
                        "Our Mission",
                        "To simplify recruitment, strengthen industry connections "
                        + "and improve communication between all portal users."));

        detailPanel.add(
                createSmallInformationCard(
                        "Technology",
                        "Java Swing for the user interface, Java for application "
                        + "logic, and MySQL with JDBC for backend."));

        contentPanel.add(aboutCard);
        contentPanel.add(detailPanel);

        completeAbout.add(contentPanel);

        completeAbout.add(
                Box.createVerticalStrut(30));

        completeAbout.add(
                createDeveloperTeamSection());

        JScrollPane scrollPane =
                new JScrollPane(
                        completeAbout,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        JPanel container =
                new JPanel(
                        new BorderLayout());

        container.add(
                scrollPane,
                BorderLayout.CENTER);

        return container;
    }

    // =========================================================
    // DEVELOPMENT TEAM
    // =========================================================

    private JPanel createDeveloperTeamSection() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout());

        mainPanel.setBackground(Color.WHITE);

        mainPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        30,
                        30));

        JLabel heading =
                new JLabel(
                        "Development Team",
                        SwingConstants.CENTER);

        heading.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 28));

        heading.setForeground(NAVY);

        JLabel subtitle =
                new JLabel(
                        "Developed by the students of CSE Department",
                        SwingConstants.CENTER);

        subtitle.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 15));

        subtitle.setForeground(GOLD);

        JPanel titlePanel = new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS));

        heading.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        titlePanel.add(heading);

        titlePanel.add(
                Box.createVerticalStrut(5));

        titlePanel.add(subtitle);

        JPanel membersPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0));

        membersPanel.setOpaque(false);

        membersPanel.setBorder(
                new EmptyBorder(
                        20,
                        0,
                        0,
                        0));

        membersPanel.add(
                createMemberCard(
                        "Ishan Mishra",
                        "25CSE005",
                        "Project Developer and Lead"));

        membersPanel.add(
                createMemberCard(
                        "Abhishek Sadangi",
                        "25CSE006",
                        "Project Developer"));

        membersPanel.add(
                createMemberCard(
                        "Ashrita Behera",
                        "25CSE001",
                        "Project Developer"));

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH);

        mainPanel.add(
                membersPanel,
                BorderLayout.CENTER);

        return mainPanel;
    }

    // =========================================================
    // MEMBER CARD
    // =========================================================

    private JPanel createMemberCard(
            String name,
            String rollNumber,
            String role) {

        JPanel card = new JPanel();

        card.setBackground(Color.WHITE);

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS));

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        224,
                                        232),
                                1),
                        new EmptyBorder(
                                20,
                                15,
                                20,
                                15)));

        JLabel icon =
                new JLabel("👤");

        icon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        38));

        icon.setForeground(BLUE);

        icon.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JLabel nameLabel =
                new JLabel(name);

        nameLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 19));

        nameLabel.setForeground(NAVY);

        nameLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JLabel rollLabel =
                new JLabel(rollNumber);

        rollLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 14));

        rollLabel.setForeground(GOLD);

        rollLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JLabel roleLabel =
                new JLabel(
                        "<html><center>"
                        + role
                        + "</center></html>",
                        SwingConstants.CENTER);

        roleLabel.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 13));

        roleLabel.setForeground(
                new Color(
                        90,
                        95,
                        105));

        roleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        card.add(icon);

        card.add(
                Box.createVerticalStrut(8));

        card.add(nameLabel);

        card.add(
                Box.createVerticalStrut(4));

        card.add(rollLabel);

        card.add(
                Box.createVerticalStrut(5));

        card.add(roleLabel);

        return card;
    }

    // =========================================================
    // INFORMATION CARD
    // =========================================================

    private JPanel createInformationCard(
            String heading,
            String description) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                15));

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        224,
                                        232)),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25)));

        JLabel headingLabel =
                new JLabel(heading);

        headingLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 23));

        headingLabel.setForeground(BLUE);

        JTextArea descriptionArea =
                new JTextArea(description);

        descriptionArea.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 16));

        descriptionArea.setForeground(
                new Color(
                        70,
                        75,
                        85));

        descriptionArea.setOpaque(false);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        descriptionArea.setPreferredSize(
                new Dimension(
                        400,
                        220));

        panel.add(
                headingLabel,
                BorderLayout.NORTH);

        panel.add(
                descriptionArea,
                BorderLayout.CENTER);

        return panel;
    }

    // =========================================================
    // SMALL INFORMATION CARD
    // =========================================================

    private JPanel createSmallInformationCard(
            String heading,
            String description) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                8));

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        224,
                                        232)),
                        new EmptyBorder(
                                15,
                                20,
                                15,
                                20)));

        JLabel headingLabel =
                new JLabel(heading);

        headingLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 18));

        headingLabel.setForeground(GOLD);

        JTextArea descriptionArea =
                new JTextArea(description);

        descriptionArea.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 14));

        descriptionArea.setForeground(
                new Color(
                        70,
                        75,
                        85));

        descriptionArea.setOpaque(false);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        panel.add(
                headingLabel,
                BorderLayout.NORTH);

        panel.add(
                descriptionArea,
                BorderLayout.CENTER);

        return panel;
    }

    // =========================================================
    // CONTACT PANEL
    // =========================================================

    private JPanel createContactPanel() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout());

        mainPanel.setBackground(
                LIGHT_BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(
                        30,
                        80,
                        40,
                        80));

        JLabel title =
                new JLabel(
                        "Contact HireSphere",
                        SwingConstants.CENTER);

        title.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 34));

        title.setForeground(NAVY);

        JLabel subtitle =
                new JLabel(
                        "We are here to assist you with placement and portal-related queries",
                        SwingConstants.CENTER);

        subtitle.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 16));

        subtitle.setForeground(GOLD);

        JPanel headingPanel =
                new JPanel();

        headingPanel.setOpaque(false);

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS));

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        headingPanel.add(title);

        headingPanel.add(
                Box.createVerticalStrut(5));

        headingPanel.add(subtitle);

        JPanel contactContent =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                25,
                                0));

        contactContent.setOpaque(false);

        contactContent.setBorder(
                new EmptyBorder(
                        30,
                        0,
                        0,
                        0));

        // LEFT CONTACT DETAILS

        JPanel detailsPanel = new JPanel();

        detailsPanel.setBackground(Color.WHITE);

        detailsPanel.setLayout(
                new BoxLayout(
                        detailsPanel,
                        BoxLayout.Y_AXIS));

        detailsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        224,
                                        232)),
                        new EmptyBorder(
                                30,
                                35,
                                30,
                                35)));

        detailsPanel.add(
                createContactHeading(
                        "HireSphere Support"));

        detailsPanel.add(
                Box.createVerticalStrut(20));

        detailsPanel.add(
                createContactText(
                        "University",
                        "GIET University, Gunupur"));

        detailsPanel.add(
                Box.createVerticalStrut(15));

        detailsPanel.add(
                createContactText(
                        "Department",
                        "Computer Science and Engineering"));

        detailsPanel.add(
                Box.createVerticalStrut(15));

        detailsPanel.add(
                createContactText(
                        "Email",
                        "hiresphere@gmail.com"));

        detailsPanel.add(
                Box.createVerticalStrut(15));

        detailsPanel.add(
                createContactText(
                        "Phone",
                        "+91 9040107065"));

        detailsPanel.add(
                Box.createVerticalStrut(15));

        detailsPanel.add(
                createContactText(
                        "Location",
                        "Gunupur, Rayagada, Odisha"));

        // RIGHT MESSAGE BOX

        JPanel messagePanel = new JPanel();

        messagePanel.setBackground(NAVY);

        messagePanel.setLayout(
                new BoxLayout(
                        messagePanel,
                        BoxLayout.Y_AXIS));

        messagePanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30));

        JLabel messageHeading =
                new JLabel(
                        "Send us a Message");

        messageHeading.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 25));

        messageHeading.setForeground(GOLD);

        messageHeading.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        JLabel messageSubheading =
                new JLabel(
                        "Have a question? Contact our support team.");

        messageSubheading.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 14));

        messageSubheading.setForeground(
                new Color(
                        225,
                        230,
                        240));

        messageSubheading.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        JLabel nameLabel =
                createFormLabel("Name");

        JTextField nameField =
                new JTextField();

        styleTextField(nameField);

        JLabel emailLabel =
                createFormLabel("Email");

        JTextField emailField =
                new JTextField();

        styleTextField(emailField);

        JLabel messageLabel =
                createFormLabel("Message");

        JTextArea messageArea =
                new JTextArea();

        messageArea.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 14));

        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        messageArea.setBorder(
                new EmptyBorder(
                        8,
                        10,
                        8,
                        10));

        JScrollPane messageScroll =
                new JScrollPane(
                        messageArea);

        messageScroll.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        100));

        messageScroll.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        JButton sendButton =
                createContactButton(
                        "Send Message");

        sendButton.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        sendButton.addActionListener(
                e -> {

                    String name =
                            nameField.getText()
                                    .trim();

                    String email =
                            emailField.getText()
                                    .trim();

                    String message =
                            messageArea.getText()
                                    .trim();

                    if (name.isEmpty()
                            || email.isEmpty()
                            || message.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Please fill in all fields.",
                                "HireSphere",
                                JOptionPane.WARNING_MESSAGE);

                        return;
                    }

                    JOptionPane.showMessageDialog(
                            this,
                            "Thank you, "
                            + name
                            + "!\n\n"
                            + "Your message has been received.",
                            "Message Sent",
                            JOptionPane.INFORMATION_MESSAGE);

                    nameField.setText("");
                    emailField.setText("");
                    messageArea.setText("");
                });

        messagePanel.add(messageHeading);

        messagePanel.add(
                Box.createVerticalStrut(5));

        messagePanel.add(messageSubheading);

        messagePanel.add(
                Box.createVerticalStrut(15));

        messagePanel.add(nameLabel);

        messagePanel.add(
                Box.createVerticalStrut(5));

        messagePanel.add(nameField);

        messagePanel.add(
                Box.createVerticalStrut(10));

        messagePanel.add(emailLabel);

        messagePanel.add(
                Box.createVerticalStrut(5));

        messagePanel.add(emailField);

        messagePanel.add(
                Box.createVerticalStrut(10));

        messagePanel.add(messageLabel);

        messagePanel.add(
                Box.createVerticalStrut(5));

        messagePanel.add(messageScroll);

        messagePanel.add(
                Box.createVerticalStrut(15));

        messagePanel.add(sendButton);

        contactContent.add(detailsPanel);
        contactContent.add(messagePanel);

        mainPanel.add(
                headingPanel,
                BorderLayout.NORTH);

        mainPanel.add(
                contactContent,
                BorderLayout.CENTER);

        return mainPanel;
    }

    // =========================================================
    // FORM LABEL
    // =========================================================

    private JLabel createFormLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 13));

        label.setForeground(Color.WHITE);

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        return label;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            JTextField field) {

        field.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 14));

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        38));

        field.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        215,
                                        225)),
                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10)));
    }

    // =========================================================
    // CONTACT HEADING
    // =========================================================

    private JLabel createContactHeading(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 23));

        label.setForeground(BLUE);

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        return label;
    }

    // =========================================================
    // CONTACT TEXT
    // =========================================================

    private JPanel createContactText(
            String heading,
            String value) {

        JPanel panel =
                new JPanel();

        panel.setOpaque(false);

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS));

        panel.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        JLabel headingLabel =
                new JLabel(heading);

        headingLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 14));

        headingLabel.setForeground(GOLD);

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font("Segoe UI",
                        Font.PLAIN, 16));

        valueLabel.setForeground(
                new Color(
                        55,
                        60,
                        70));

        panel.add(headingLabel);

        panel.add(
                Box.createVerticalStrut(3));

        panel.add(valueLabel);

        return panel;
    }

    // =========================================================
    // CONTACT BUTTON
    // =========================================================

    private JButton createContactButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 15));

        button.setForeground(NAVY);
        button.setBackground(GOLD);

        button.setFocusPainted(false);

        button.setBorder(
                new EmptyBorder(
                        11,
                        22,
                        11,
                        22));

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        return button;
    }

    // =========================================================
    // IMAGE LOADING
    // =========================================================

    private ImageIcon loadImage(
            String imageName,
            int width,
            int height) {

        try {

            java.net.URL url =
                    getClass().getResource(
                            "/images/" + imageName);

            if (url == null) {

                System.out.println(
                        "Image not found : "
                        + imageName);

                return null;
            }

            ImageIcon icon =
                    new ImageIcon(url);

            Image img =
                    icon.getImage()
                            .getScaledInstance(
                                    width,
                                    height,
                                    Image.SCALE_SMOOTH);

            return new ImageIcon(img);

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // SCROLLABLE PANEL
    // =========================================================

    private class ScrollableContentPanel
            extends JPanel
            implements Scrollable {

        private static final long serialVersionUID = 1L;

        @Override
        public Dimension
        getPreferredScrollableViewportSize() {

            return getPreferredSize();
        }

        @Override
        public int getScrollableUnitIncrement(
                Rectangle visibleRect,
                int orientation,
                int direction) {

            return 16;
        }

        @Override
        public int getScrollableBlockIncrement(
                Rectangle visibleRect,
                int orientation,
                int direction) {

            return 100;
        }

        @Override
        public boolean
        getScrollableTracksViewportWidth() {

            return true;
        }

        @Override
        public boolean
        getScrollableTracksViewportHeight() {

            return false;
        }
    }

    // =========================================================
    // WELCOME SCREEN
    // =========================================================

    private static class WelcomeScreen extends JFrame {

        private static final long serialVersionUID = 1L;

        private final Color NAVY =
                new Color(8, 27, 58);

        private final Color BLUE =
                new Color(30, 64, 175);

        private final Color GOLD =
                new Color(216, 155, 29);

        private WelcomeAnimationPanel animationPanel;

        // Loading animation
        private AnimatedProgressBar progressBar;
        private JLabel progressLabel;
        private Timer progressTimer;

        public WelcomeScreen() {

            setTitle("Welcome to HireSphere");

            setExtendedState(JFrame.MAXIMIZED_BOTH);

            setUndecorated(true);

            setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE);

            animationPanel =
                    new WelcomeAnimationPanel();

            animationPanel.setLayout(
                    new GridBagLayout());

            createWelcomeContent();

            add(animationPanel);

            setVisible(true);
        }

        // =====================================================
        // WELCOME CONTENT
        // =====================================================

        private void createWelcomeContent() {

            /*
             * Layered panel:
             *
             * 1. White card
             * 2. Logo watermark
             * 3. Text content
             *
             * This keeps the logo behind the text.
             */

            JPanel card =
                    new JPanel();

            card.setPreferredSize(
                    new Dimension(
                            650,
                            430));

            card.setBackground(
                    new Color(
                            255,
                            255,
                            255,
                            235));

            card.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    new Color(
                                            255,
                                            255,
                                            255),
                                    2),
                            new EmptyBorder(
                                    35,
                                    50,
                                    35,
                                    50)));

            card.setLayout(
                    new BorderLayout());

            // =================================================
            // WATERMARK
            // =================================================

            JPanel watermarkPanel =
                    new JPanel() {

                        private static final long serialVersionUID = 1L;

                        @Override
                        protected void paintComponent(
                                Graphics graphics) {

                            super.paintComponent(graphics);

                            Graphics2D g2 =
                                    (Graphics2D) graphics.create();

                            g2.setRenderingHint(
                                    RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);

                            try {

                                java.net.URL url =
                                        getClass().getResource(
                                                "/images/Logo.png");

                                if (url != null) {

                                    ImageIcon icon =
                                            new ImageIcon(url);

                                    Image image =
                                            icon.getImage();

                                    int logoSize = 400;

                                    int x =
                                            (getWidth()
                                                    - logoSize) / 2;

                                    int y =
                                            (getHeight()
                                                    - logoSize) / 2;

                                    // Very light watermark

                                    g2.setComposite(
                                            AlphaComposite.getInstance(
                                                    AlphaComposite.SRC_OVER,
                                                    0.08f));

                                    g2.drawImage(
                                            image,
                                            x,
                                            y,
                                            logoSize,
                                            logoSize,
                                            this);
                                }

                            } catch (Exception e) {

                                System.out.println(
                                        "Watermark image not found.");
                            }

                            g2.dispose();
                        }
                    };

            watermarkPanel.setOpaque(false);

            watermarkPanel.setLayout(
                    new GridBagLayout());

            // =================================================
            // TEXT CONTENT
            // =================================================

            JPanel textPanel =
                    new JPanel();

            textPanel.setOpaque(false);

            textPanel.setLayout(
                    new BoxLayout(
                            textPanel,
                            BoxLayout.Y_AXIS));

            JLabel smallTitle =
                    new JLabel(
                            "WELCOME TO");

            smallTitle.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            16));

            smallTitle.setForeground(GOLD);

            smallTitle.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            JLabel title =
                    new JLabel(
                            "HireSphere");

            title.setFont(
                    new Font(
                            "Serif",
                            Font.BOLD,
                            58));

            title.setForeground(NAVY);

            title.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            JLabel slogan =
                    new JLabel(
                            "Where Talent Meets Opportunity");

            slogan.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            21));

            slogan.setForeground(BLUE);

            slogan.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            JLabel line1 =
                    new JLabel(
                            "A smarter way to connect students,",
                            SwingConstants.CENTER);

            JLabel line2 =
                    new JLabel(
                            "companies, alumni and opportunities.",
                            SwingConstants.CENTER);

            line1.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            16));

            line2.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            16));

            line1.setForeground(
                    new Color(
                            65,
                            70,
                            80));

            line2.setForeground(
                    new Color(
                            65,
                            70,
                            80));

            line1.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            line2.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            JLabel welcomeText =
                    new JLabel(
                            "<html><center>"
                            + "Explore opportunities, connect with recruiters "
                            + "and build your career journey with HireSphere."
                            + "</center></html>",
                            SwingConstants.CENTER);

            welcomeText.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            14));

            welcomeText.setForeground(
                    new Color(
                            90,
                            95,
                            105));

            welcomeText.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            JButton getStarted =
                    new JButton(
                            "GET STARTED");

            getStarted.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            16));

            getStarted.setForeground(Color.WHITE);

            getStarted.setBackground(BLUE);

            getStarted.setFocusPainted(false);

            getStarted.setBorderPainted(false);

            getStarted.setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR));

            getStarted.setPreferredSize(
                    new Dimension(
                            190,
                            50));

            getStarted.setMaximumSize(
                    new Dimension(
                            190,
                            50));

            getStarted.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            getStarted.addMouseListener(
                    new MouseAdapter() {

                        @Override
                        public void mouseEntered(
                                MouseEvent e) {

                            getStarted.setBackground(GOLD);
                        }

                        @Override
                        public void mouseExited(
                                MouseEvent e) {

                            getStarted.setBackground(BLUE);
                        }
                    });

            getStarted.addActionListener(e -> startLoading(getStarted));

            textPanel.add(smallTitle);

            textPanel.add(
                    Box.createVerticalStrut(8));

            textPanel.add(title);

            textPanel.add(
                    Box.createVerticalStrut(5));

            textPanel.add(slogan);

            textPanel.add(
                    Box.createVerticalStrut(25));

            textPanel.add(line1);

            textPanel.add(
                    Box.createVerticalStrut(3));

            textPanel.add(line2);

            textPanel.add(
                    Box.createVerticalStrut(15));

            textPanel.add(welcomeText);

            textPanel.add(
                    Box.createVerticalStrut(30));

            textPanel.add(getStarted);

            // =================================================
            // ADD TEXT ON TOP OF WATERMARK
            // =================================================

            watermarkPanel.add(textPanel);

            card.add(
                    watermarkPanel,
                    BorderLayout.CENTER);

            animationPanel.add(card);
        }

        // =====================================================
        // START SMOOTH LOADING ANIMATION
        // =====================================================

        private void startLoading(JButton getStarted) {

            // Prevent multiple clicks
            getStarted.setEnabled(false);
            getStarted.setText("LOADING...");
            getStarted.setBackground(BLUE);

            // Create animated progress bar
            progressBar = new AnimatedProgressBar();
            progressBar.setMinimum(0);
            progressBar.setMaximum(100);
            progressBar.setValue(0);
            progressBar.setPreferredSize(new Dimension(350, 18));
            progressBar.setMaximumSize(new Dimension(350, 18));
            progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Percentage text
            progressLabel = new JLabel("0%");
            progressLabel.setFont(
                    new Font("Segoe UI", Font.BOLD, 13));
            progressLabel.setForeground(BLUE);
            progressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Find the text panel used by the welcome screen
            Container card = (Container) animationPanel.getComponent(0);
            Container watermarkPanel = (Container) card.getComponent(0);
            Container textPanel = (Container) watermarkPanel.getComponent(0);

            // Remove button and replace it with loading area
            textPanel.remove(getStarted);

            JLabel loadingText = new JLabel("Preparing your HireSphere experience...");
            loadingText.setFont(
                    new Font("Segoe UI", Font.BOLD, 14));
            loadingText.setForeground(GOLD);
            loadingText.setAlignmentX(Component.CENTER_ALIGNMENT);

            textPanel.add(loadingText);
            textPanel.add(Box.createVerticalStrut(10));
            textPanel.add(progressBar);

            textPanel.revalidate();
            textPanel.repaint();

            // Smoothly increase from 0 to 100
            progressTimer = new Timer(35, null);

            progressTimer.addActionListener(e -> {

                int current = progressBar.getValue();

                // Smooth progress movement
                if (current < 70) {
                    current += 1;
                } else if (current < 90) {
                    current += 1;
                } else {
                    current += 1;
                }

                progressBar.setValue(current);
                progressLabel.setText(current + "%");

                if (current >= 100) {

                    progressTimer.stop();
                    progressBar.setValue(100);
                    progressLabel.setText("100%");

                    loadingText.setText("Welcome to HireSphere!");

                    // Give the user a moment to see 100%
                    Timer openHomeTimer = new Timer(450, event -> {

                        animationPanel.stopAnimation();
                        progressBar.stopShineAnimation();

                        dispose();
                        new HomePage();
                    });

                    openHomeTimer.setRepeats(false);
                    openHomeTimer.start();
                }
            });

            progressTimer.start();
        }

        // =====================================================
        // PREMIUM ANIMATED PROGRESS BAR
        // =====================================================

        private static class AnimatedProgressBar extends JProgressBar {

            private static final long serialVersionUID = 1L;

            private int shinePosition = -60;
            private Timer shineTimer;

            private AnimatedProgressBar() {

                setOpaque(false);
                setBorderPainted(false);

                shineTimer = new Timer(18, e -> {
                    shinePosition += 5;

                    if (shinePosition > getWidth() + 60) {
                        shinePosition = -60;
                    }

                    repaint();
                });

                shineTimer.start();
            }

            private void stopShineAnimation() {
                if (shineTimer != null) {
                    shineTimer.stop();
                }
            }

            @Override
            protected void paintComponent(Graphics graphics) {

                Graphics2D g2 =
                        (Graphics2D) graphics.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();

                int arc = height;

                // Background track
                g2.setColor(new Color(225, 230, 240));
                g2.fillRoundRect(0, 0, width, height, arc, arc);

                // Progress width
                int progressWidth =
                        (int) (width *
                                (getValue() / 100.0));

                if (progressWidth > 0) {

                    GradientPaint gradient =
                            new GradientPaint(
                                    0, 0,
                                    new Color(30, 64, 175),
                                    progressWidth, height,
                                    new Color(37, 99, 235));

                    g2.setPaint(gradient);

                    g2.fillRoundRect(
                            0, 0,
                            progressWidth,
                            height,
                            arc, arc);

                    // Moving white shine
                    if (progressWidth > 10) {

                        Shape oldClip = g2.getClip();

                        g2.clip(
                                new RoundRectangle2D.Double(
                                        0, 0,
                                        progressWidth,
                                        height,
                                        arc, arc));

                        GradientPaint shine =
                                new GradientPaint(
                                        shinePosition, 0,
                                        new Color(255, 255, 255, 0),
                                        shinePosition + 30, 0,
                                        new Color(255, 255, 255, 150),
                                        true);

                        g2.setPaint(shine);
                        g2.fillRect(
                                shinePosition, 0,
                                30, height);

                        g2.setClip(oldClip);
                    }
                }

                // Clearly visible percentage inside the progress bar
                String percent = getValue() + "%";
                Font percentFont =
                        new Font("Segoe UI", Font.BOLD, 12);

                g2.setFont(percentFont);

                FontMetrics metrics = g2.getFontMetrics();
                int textWidth = metrics.stringWidth(percent);
                int textHeight = metrics.getAscent();

                int textX =
                        (width - textWidth) / 2;

                int textY =
                        (height + textHeight) / 2 - 1;

                // White text gives strong contrast over the blue
                // progress fill, while navy is used over the empty area.
                if (progressWidth >= width / 2) {
                    g2.setColor(Color.WHITE);
                } else {
                    g2.setColor(new Color(8, 27, 58));
                }

                g2.drawString(percent, textX, textY);

                g2.dispose();
            }
        }
    }

    // =========================================================
    // WELCOME ANIMATED BACKGROUND
    // =========================================================

    private static class WelcomeAnimationPanel
            extends JPanel {

        private static final long serialVersionUID = 1L;

        private final java.util.List<Particle> particles =
                new java.util.ArrayList<>();

        private Timer timer;

        public WelcomeAnimationPanel() {

            setBackground(
                    new Color(
                            5,
                            20,
                            45));

            for (int i = 0; i < 45; i++) {

                particles.add(
                        new Particle(
                                (int)
                                        (Math.random()
                                                * 950),

                                (int)
                                        (Math.random()
                                                * 600),

                                1 + (int)
                                        (Math.random()
                                                * 3)));
            }

            timer =
                    new Timer(
                            30,
                            e -> {

                                for (Particle p :
                                        particles) {

                                    p.y -= p.speed;

                                    p.x += Math.sin(
                                            p.y * 0.02)
                                            * 0.5;

                                    if (p.y < -10) {

                                        p.y =
                                                getHeight()
                                                        + 10;

                                        p.x =
                                                (int)
                                                        (Math.random()
                                                                * getWidth());
                                    }
                                }

                                repaint();
                            });

            timer.start();
        }

        public void stopAnimation() {

            if (timer != null) {
                timer.stop();
            }
        }

        @Override
        protected void paintComponent(
                Graphics graphics) {

            super.paintComponent(graphics);

            Graphics2D g2 =
                    (Graphics2D) graphics.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            // DARK GRADIENT

            GradientPaint gradient =
                    new GradientPaint(
                            0,
                            0,
                            new Color(
                                    4,
                                    18,
                                    42),

                            width,
                            height,
                            new Color(
                                    18,
                                    55,
                                    120));

            g2.setPaint(gradient);

            g2.fillRect(
                    0,
                    0,
                    width,
                    height);

            // GLOWING CIRCLES

            g2.setColor(
                    new Color(
                            30,
                            64,
                            175,
                            80));

            g2.fillOval(
                    -100,
                    -80,
                    330,
                    330);

            g2.setColor(
                    new Color(
                            216,
                            155,
                            29,
                            55));

            g2.fillOval(
                    width - 230,
                    height - 230,
                    330,
                    330);

            // PARTICLES

            for (Particle p : particles) {

                g2.setColor(
                        new Color(
                                255,
                                255,
                                255,
                                120));

                g2.fillOval(
                        p.x,
                        p.y,
                        p.size,
                        p.size);
            }

            // DECORATIVE LINES

            g2.setColor(
                    new Color(
                            255,
                            255,
                            255,
                            30));

            for (int i = 0; i < 8; i++) {

                int x =
                        80 + i * 130;

                g2.drawLine(
                        x,
                        0,
                        x - 150,
                        height);
            }

            g2.dispose();
        }
    }

    // =========================================================
    // PARTICLE
    // =========================================================

    private static class Particle {

        int x;
        int y;
        int speed;
        int size;

        Particle(
                int x,
                int y,
                int speed) {

            this.x = x;
            this.y = y;
            this.speed = speed;

            this.size =
                    2 + (int)
                            (Math.random() * 4);
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager
                                .getSystemLookAndFeelClassName());

            } catch (Exception exception) {

                System.out.println(
                        "Default look and feel will be used.");
            }

            // FIRST SHOW WELCOME SCREEN

            new WelcomeScreen();
        });
    }
}