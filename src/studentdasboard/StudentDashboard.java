package studentdasboard;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Home.HomePage;

/*
 * ============================================================
 * HireSphere - Student Dashboard
 * Modern Responsive Java Swing Dashboard
 * ============================================================
 */

public class StudentDashboard extends JFrame {

    // ============================================================
    // COLORS
    // ============================================================

    static final Color NAVY = new Color(7, 18, 42);
    static final Color NAVY2 = new Color(18, 39, 88);
    static final Color NAVY3 = new Color(25, 52, 110);

    static final Color BLUE = new Color(37, 99, 235);
    static final Color BLUE2 = new Color(59, 130, 246);

    static final Color GREEN = new Color(22, 163, 74);
    static final Color PURPLE = new Color(124, 58, 237);
    static final Color ORANGE = new Color(234, 132, 24);
    static final Color RED = new Color(220, 38, 38);

    static final Color BG = new Color(245, 247, 250);
    static final Color WHITE = Color.WHITE;

    static final Color TEXT = new Color(30, 41, 59);
    static final Color GRAY = new Color(100, 116, 139);
    static final Color LIGHT_GRAY = new Color(148, 163, 184);
    static final Color BORDER = new Color(226, 232, 240);

    // ============================================================
    // MAIN COMPONENTS
    // ============================================================

    CardLayout cards = new CardLayout();
    JPanel content = new JPanel(cards);

    JLabel pageTitle;
    JLabel clock;
    JLabel date;

    JButton activeButton;

    // ============================================================
    // SIDEBAR MENU
    // ============================================================

    String[] pages = {
        "Dashboard",
        "My Profile",
        "Job Openings",
        "Applications",
        "Resume",
        "Placement Status",
        "Notifications",
        "Settings"
    };

    String[] icons = {
        "D",
        "P",
        "J",
        "A",
        "R",
        "S",
        "N",
        "T"
    };

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public StudentDashboard() {

        setTitle("HireSphere - Student Dashboard");

        setSize(1366, 768);

        setMinimumSize(new Dimension(1050, 650));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Sidebar
        add(createSidebar(), BorderLayout.WEST);

        // Right side
        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(BG);

        right.add(createHeader(), BorderLayout.NORTH);

        content.setBackground(BG);

        // Pages
        content.add(createDashboard(), "Dashboard");
        content.add(createProfile(), "My Profile");
        content.add(createJobs(), "Job Openings");
        content.add(createApplications(), "Applications");
        content.add(createResume(), "Resume");
        content.add(createStatus(), "Placement Status");
        content.add(createNotifications(), "Notifications");
        content.add(createSettings(), "Settings");

        right.add(content, BorderLayout.CENTER);

        add(right, BorderLayout.CENTER);

        // Clock
        new Timer(1000, e -> updateTime()).start();

        updateTime();

        setVisible(true);
    }

    // ============================================================
    // SIDEBAR
    // ============================================================

    JPanel createSidebar() {

        GradientPanel side = new GradientPanel(NAVY, NAVY2);

        side.setPreferredSize(new Dimension(245, 768));

        side.setLayout(new BorderLayout());

        // --------------------------------------------------------
        // LOGO
        // --------------------------------------------------------

        JPanel logoPanel = new JPanel(new BorderLayout(10, 5));

        logoPanel.setOpaque(false);

        logoPanel.setBorder(
            new EmptyBorder(20, 18, 18, 12)
        );

        JLabel logoImage = image("logo.png", 48, 48);

        JPanel logoText = new JPanel(new GridLayout(2, 1));

        logoText.setOpaque(false);

        JLabel name = label(
            "HireSphere",
            Color.WHITE,
            Font.BOLD,
            24
        );

        JLabel tagline = label(
            "WHERE TALENT MEETS OPPORTUNITY",
            new Color(165, 185, 220),
            Font.PLAIN,
            7
        );

        logoText.add(name);
        logoText.add(tagline);

        logoPanel.add(logoImage, BorderLayout.WEST);
        logoPanel.add(logoText, BorderLayout.CENTER);

        side.add(logoPanel, BorderLayout.NORTH);

        // --------------------------------------------------------
        // MENU
        // --------------------------------------------------------

        JPanel menu = new JPanel();

        menu.setLayout(
            new BoxLayout(menu, BoxLayout.Y_AXIS)
        );

        menu.setOpaque(false);

        menu.setBorder(
            new EmptyBorder(8, 12, 8, 12)
        );

        for (int i = 0; i < pages.length; i++) {

            Color normal = new Color(27, 47, 87);

            ModernButton b =
                new ModernButton(
                    icons[i] + "   " + pages[i],
                    normal
                );

            b.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 44)
            );

            b.setPreferredSize(
                new Dimension(215, 44)
            );

            b.setAlignmentX(Component.LEFT_ALIGNMENT);

            final int index = i;

            if (i == 0) {

                activeButton = b;

                b.setButtonColor(BLUE);
            }

            b.addActionListener(
                e -> showPage(pages[index], b)
            );

            menu.add(b);

            menu.add(
                Box.createVerticalStrut(7)
            );
        }

        // --------------------------------------------------------
        // LOGOUT
        // --------------------------------------------------------

        ModernButton logout =
            new ModernButton(
                "X   Logout",
                new Color(27, 47, 87)
            );

        logout.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 44)
        );

        logout.setPreferredSize(
            new Dimension(215, 44)
        );

        logout.setAlignmentX(Component.LEFT_ALIGNMENT);

        logout.addActionListener(e -> {

            int answer =
                JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

            if (answer == JOptionPane.YES_OPTION) {
                // Close the dashboard and go back to the Home page
                dispose();
                SwingUtilities.invokeLater(() -> new HomePage());
            }
        });

        menu.add(logout);

        side.add(menu, BorderLayout.CENTER);

        // --------------------------------------------------------
        // FOOTER
        // --------------------------------------------------------

        JLabel footer = label(
            "HireSphere  |  Student Portal",
            new Color(150, 170, 205),
            Font.PLAIN,
            10
        );

        JPanel footerPanel =
            new JPanel(new BorderLayout());

        footerPanel.setOpaque(false);

        footerPanel.setBorder(
            new EmptyBorder(8, 20, 18, 10)
        );

        footerPanel.add(
            footer,
            BorderLayout.WEST
        );

        side.add(
            footerPanel,
            BorderLayout.SOUTH
        );

        return side;
    }

    // ============================================================
    // PAGE SWITCH
    // ============================================================

    void showPage(String page, JButton button) {

        if (activeButton != null) {

            activeButton.setBackground(
                new Color(27, 47, 87)
            );

            if (activeButton instanceof ModernButton) {

                ((ModernButton) activeButton)
                    .setButtonColor(
                        new Color(27, 47, 87)
                    );
            }
        }

        activeButton = button;

        if (button instanceof ModernButton) {

            ((ModernButton) button)
                .setButtonColor(BLUE);

        } else {

            button.setBackground(BLUE);
        }

        cards.show(content, page);

        pageTitle.setText(
            page.equals("Dashboard")
                ? "Student Dashboard"
                : page
        );
    }

    // ============================================================
    // HEADER
    // ============================================================

    JPanel createHeader() {

        JPanel header =
            new JPanel(new BorderLayout());

        header.setPreferredSize(
            new Dimension(0, 105)
        );

        header.setBackground(
            new Color(15, 23, 42)
        );

        header.setBorder(
            new EmptyBorder(
                18, 28, 15, 25
            )
        );

        // --------------------------------------------------------
        // LEFT
        // --------------------------------------------------------

        JPanel left =
            new JPanel(new GridLayout(2, 1));

        left.setOpaque(false);

        pageTitle =
            label(
                "Student Dashboard",
                Color.WHITE,
                Font.BOLD,
                25
            );

        left.add(pageTitle);

        left.add(
            label(
                "Manage your placement journey",
                new Color(148, 163, 184),
                Font.PLAIN,
                12
            )
        );

        // --------------------------------------------------------
        // RIGHT
        // --------------------------------------------------------

        JPanel right =
            new JPanel(
                new FlowLayout(
                    FlowLayout.RIGHT,
                    12,
                    5
                )
            );

        right.setOpaque(false);

        // Notification button
        ModernButton notification =
            new ModernButton(
                "!",
                new Color(30, 41, 59)
            );

        notification.setPreferredSize(
            new Dimension(48, 48)
        );

        notification.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                18
            )
        );

        right.add(notification);

        // Avatar
        JLabel avatar =
            label(
                "IM",
                Color.WHITE,
                Font.BOLD,
                14
            );

        avatar.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        avatar.setOpaque(true);

        avatar.setBackground(BLUE);

        avatar.setPreferredSize(
            new Dimension(48, 48)
        );

        right.add(avatar);

        // User
        JPanel user =
            new JPanel(new GridLayout(2, 1));

        user.setOpaque(false);

        user.add(
            label(
                "Welcome, Ishan Mishra",
                Color.WHITE,
                Font.BOLD,
                13
            )
        );

        user.add(
            label(
                "Student",
                new Color(148, 163, 184),
                Font.PLAIN,
                11
            )
        );

        right.add(user);

        // Time
        JPanel time =
            new JPanel(new GridLayout(2, 1));

        time.setOpaque(false);

        clock =
            label(
                "",
                Color.WHITE,
                Font.BOLD,
                13
            );

        date =
            label(
                "",
                new Color(148, 163, 184),
                Font.PLAIN,
                11
            );

        time.add(clock);
        time.add(date);

        right.add(time);

        header.add(
            left,
            BorderLayout.WEST
        );

        header.add(
            right,
            BorderLayout.EAST
        );

        return header;
    }

    // ============================================================
    // DASHBOARD
    // ============================================================

    JPanel createDashboard() {

        JPanel page =
            new JPanel(new BorderLayout());

        page.setBackground(BG);

        page.setBorder(
            new EmptyBorder(
                20, 28, 20, 22
            )
        );

        JPanel all = new JPanel();

        all.setOpaque(false);

        all.setLayout(
            new BoxLayout(
                all,
                BoxLayout.Y_AXIS
            )
        );

        // --------------------------------------------------------
        // WELCOME CARD
        // --------------------------------------------------------

        JPanel welcome =
            new RoundedPanel();

        welcome.setLayout(
            new BorderLayout()
        );

        welcome.setBorder(
            new EmptyBorder(
                18, 22, 18, 22
            )
        );

        welcome.setBackground(
            new Color(239, 246, 255)
        );

        JPanel welcomeText =
            new JPanel(
                new GridLayout(2, 1)
            );

        welcomeText.setOpaque(false);

        welcomeText.add(
            label(
                getGreeting() + ", Ishan 👋",
                NAVY,
                Font.BOLD,
                22
            )
        );

        welcomeText.add(
            label(
                "Here's your placement journey at a glance.",
                GRAY,
                Font.PLAIN,
                12
            )
        );

        ModernButton explore =
            new ModernButton(
                "Explore Jobs  →",
                BLUE
            );

        explore.setPreferredSize(
            new Dimension(145, 42)
        );

        explore.addActionListener(
            e -> {

                cards.show(
                    content,
                    "Job Openings"
                );

                pageTitle.setText(
                    "Job Openings"
                );
            }
        );

        welcome.add(
            welcomeText,
            BorderLayout.CENTER
        );

        welcome.add(
            explore,
            BorderLayout.EAST
        );

        all.add(welcome);

        all.add(
            Box.createVerticalStrut(15)
        );

        // --------------------------------------------------------
        // STATISTICS
        // --------------------------------------------------------

        JPanel stats =
            new JPanel(
                new GridLayout(
                    1, 4, 12, 12
                )
            );

        stats.setOpaque(false);

        stats.add(
            statCard(
                "Applications",
                "12",
                "Submitted",
                BLUE
            )
        );

        stats.add(
            statCard(
                "Shortlisted",
                "04",
                "Companies",
                GREEN
            )
        );

        stats.add(
            statCard(
                "Interviews",
                "03",
                "Scheduled",
                PURPLE
            )
        );

        stats.add(
            statCard(
                "Offers",
                "01",
                "Received",
                ORANGE
            )
        );

        all.add(stats);

        all.add(
            Box.createVerticalStrut(15)
        );

        // --------------------------------------------------------
        // MIDDLE
        // --------------------------------------------------------

        JPanel middle =
            new JPanel(
                new GridLayout(
                    1, 2, 15, 15
                )
            );

        middle.setOpaque(false);

        middle.add(
            placementCard()
        );

        middle.add(
            interviewCard()
        );

        all.add(middle);

        all.add(
            Box.createVerticalStrut(15)
        );

        // --------------------------------------------------------
        // COMPANIES
        // --------------------------------------------------------

        all.add(
            label(
                "Recommended Companies",
                TEXT,
                Font.BOLD,
                18
            )
        );

        all.add(
            Box.createVerticalStrut(8)
        );

        JPanel companies =
            new JPanel(
                new GridLayout(
                    1, 4, 12, 12
                )
            );

        companies.setOpaque(false);

        companies.add(
            companyCard(
                "TCS",
                "tcs.png",
                "12 LPA",
                BLUE
            )
        );

        companies.add(
            companyCard(
                "Infosys",
                "infosys.png",
                "9 LPA",
                GREEN
            )
        );

        companies.add(
            companyCard(
                "Wipro",
                "wipro.png",
                "8 LPA",
                ORANGE
            )
        );

        companies.add(
            companyCard(
                "Cognizant",
                "cognizant.png",
                "10 LPA",
                PURPLE
            )
        );

        all.add(companies);

        all.add(
            Box.createVerticalStrut(15)
        );

        // --------------------------------------------------------
        // LOWER SECTION
        // --------------------------------------------------------

        JPanel lower =
            new JPanel(
                new GridLayout(
                    1, 2, 15, 15
                )
            );

        lower.setOpaque(false);

        lower.add(
            activityCard()
        );

        lower.add(
            tipsCard()
        );

        all.add(lower);

        // Scroll
        JScrollPane scroll =
            new JScrollPane(all);

        scroll.setBorder(null);

        scroll.setOpaque(false);

        scroll.getViewport()
              .setOpaque(false);

        scroll.getVerticalScrollBar()
              .setUnitIncrement(16);

        scroll.getHorizontalScrollBar()
              .setUnitIncrement(16);

        page.add(
            scroll,
            BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // STAT CARD
    // ============================================================

    JPanel statCard(
        String title,
        String value,
        String small,
        Color color
    ) {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout()
        );

        card.setBorder(
            new EmptyBorder(
                14, 17, 14, 17
            )
        );

        card.add(
            label(
                title,
                GRAY,
                Font.PLAIN,
                11
            ),
            BorderLayout.NORTH
        );

        card.add(
            label(
                value,
                color,
                Font.BOLD,
                28
            ),
            BorderLayout.CENTER
        );

        card.add(
            label(
                small,
                GRAY,
                Font.PLAIN,
                10
            ),
            BorderLayout.SOUTH
        );

        return card;
    }

    // ============================================================
    // PLACEMENT CARD
    // ============================================================

    JPanel placementCard() {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(
                10,
                10
            )
        );

        card.setBorder(
            new EmptyBorder(
                16, 18, 16, 18
            )
        );

        JPanel top =
            new JPanel(
                new BorderLayout()
            );

        top.setOpaque(false);

        top.add(
            label(
                "Placement Readiness",
                TEXT,
                Font.BOLD,
                16
            ),
            BorderLayout.WEST
        );

        top.add(
            label(
                "85%",
                BLUE,
                Font.BOLD,
                20
            ),
            BorderLayout.EAST
        );

        card.add(
            top,
            BorderLayout.NORTH
        );

        JProgressBar progress =
            new JProgressBar(
                0,
                100
            );

        progress.setValue(85);

        progress.setStringPainted(false);

        progress.setForeground(BLUE);

        progress.setBackground(
            new Color(226, 232, 240)
        );

        progress.setBorderPainted(false);

        progress.setPreferredSize(
            new Dimension(
                0,
                12
            )
        );

        card.add(
            progress,
            BorderLayout.CENTER
        );

        JPanel checks =
            new JPanel(
                new GridLayout(
                    2, 2
                )
            );

        checks.setOpaque(false);

        checks.add(
            label(
                "✓ Profile Complete",
                GREEN,
                Font.PLAIN,
                10
            )
        );

        checks.add(
            label(
                "✓ Resume Uploaded",
                GREEN,
                Font.PLAIN,
                10
            )
        );

        checks.add(
            label(
                "✓ Skills Added",
                GREEN,
                Font.PLAIN,
                10
            )
        );

        checks.add(
            label(
                "○ Interview Prep",
                ORANGE,
                Font.PLAIN,
                10
            )
        );

        card.add(
            checks,
            BorderLayout.SOUTH
        );

        return card;
    }

    // ============================================================
    // INTERVIEW CARD
    // ============================================================

    JPanel interviewCard() {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(
                10,
                5
            )
        );

        card.setBorder(
            new EmptyBorder(
                16, 18, 16, 18
            )
        );

        card.add(
            label(
                "Upcoming Interview",
                TEXT,
                Font.BOLD,
                16
            ),
            BorderLayout.NORTH
        );

        JPanel center =
            new JPanel(
                new BorderLayout(
                    12,
                    5
                )
            );

        center.setOpaque(false);

        center.add(
            image(
                "tcs.png",
                55,
                55
            ),
            BorderLayout.WEST
        );

        JPanel info =
            new JPanel(
                new GridLayout(
                    3,
                    1
                )
            );

        info.setOpaque(false);

        info.add(
            label(
                "TCS",
                BLUE,
                Font.BOLD,
                15
            )
        );

        info.add(
            label(
                "System Engineer",
                TEXT,
                Font.BOLD,
                12
            )
        );

        info.add(
            label(
                "Tomorrow • 10:30 AM",
                GRAY,
                Font.PLAIN,
                11
            )
        );

        center.add(
            info,
            BorderLayout.CENTER
        );

        card.add(
            center,
            BorderLayout.CENTER
        );

        ModernButton details =
            new ModernButton(
                "View Details",
                BLUE
            );

        details.setPreferredSize(
            new Dimension(
                120,
                32
            )
        );

        card.add(
            details,
            BorderLayout.SOUTH
        );

        return card;
    }

    // ============================================================
    // COMPANY CARD
    // ============================================================

    JPanel companyCard(
        String company,
        String file,
        String salary,
        Color color
    ) {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(
                5,
                5
            )
        );

        card.setBorder(
            new EmptyBorder(
                12, 12, 12, 12
            )
        );

        JLabel logo =
            image(
                file,
                55,
                45
            );

        logo.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        card.add(
            logo,
            BorderLayout.NORTH
        );

        JPanel info =
            new JPanel(
                new GridLayout(
                    2,
                    1
                )
            );

        info.setOpaque(false);

        info.add(
            label(
                company,
                TEXT,
                Font.BOLD,
                13
            )
        );

        info.add(
            label(
                salary,
                color,
                Font.BOLD,
                11
            )
        );

        card.add(
            info,
            BorderLayout.CENTER
        );

        ModernButton apply =
            new ModernButton(
                "Apply",
                color
            );

        apply.setPreferredSize(
            new Dimension(
                80,
                30
            )
        );

        apply.addActionListener(
            e -> JOptionPane.showMessageDialog(
                this,
                "Application started for "
                    + company,
                "HireSphere",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        card.add(
            apply,
            BorderLayout.SOUTH
        );

        return card;
    }

    // ============================================================
    // ACTIVITY CARD
    // ============================================================

    JPanel activityCard() {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(
                0,
                8
            )
        );

        card.setBorder(
            new EmptyBorder(
                15, 18, 15, 18
            )
        );

        card.add(
            label(
                "Recent Activity",
                TEXT,
                Font.BOLD,
                16
            ),
            BorderLayout.NORTH
        );

        JPanel list =
            new JPanel(
                new GridLayout(
                    4,
                    1,
                    0,
                    5
                )
            );

        list.setOpaque(false);

        list.add(
            label(
                "✓  Resume approved",
                GREEN,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "✓  TCS application submitted",
                BLUE,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "★  Infosys shortlisted you",
                PURPLE,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "!  Interview tomorrow",
                ORANGE,
                Font.PLAIN,
                11
            )
        );

        card.add(
            list,
            BorderLayout.CENTER
        );

        return card;
    }

    // ============================================================
    // TIPS CARD
    // ============================================================

    JPanel tipsCard() {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout()
        );

        card.setBorder(
            new EmptyBorder(
                15, 18, 15, 18
            )
        );

        card.add(
            label(
                "Placement Tip 💡",
                PURPLE,
                Font.BOLD,
                16
            ),
            BorderLayout.NORTH
        );

        JTextArea text =
            new JTextArea(
                "Keep your resume updated before "
                + "applying to new opportunities.\n\n"
                + "A complete profile helps recruiters "
                + "understand your skills quickly."
            );

        text.setEditable(false);

        text.setOpaque(false);

        text.setLineWrap(true);

        text.setWrapStyleWord(true);

        text.setForeground(GRAY);

        text.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                11
            )
        );

        card.add(
            text,
            BorderLayout.CENTER
        );

        return card;
    }

    // ============================================================
    // PROFILE
    // ============================================================

    JPanel createProfile() {

        JPanel page =
            createPage(
                "My Profile",
                "Manage your personal and academic information."
            );

        JPanel grid =
            new JPanel(
                new GridLayout(
                    2,
                    2,
                    15,
                    15
                )
            );

        grid.setOpaque(false);

        grid.add(
            infoCard(
                "Personal Information",
                "Name: Ishan Mishra\n"
                + "Email: ishan@example.com\n"
                + "Phone: +91 XXXXX XXXXX",
                BLUE
            )
        );

        grid.add(
            infoCard(
                "Academic Information",
                "Course: B.Tech CSE\n"
                + "University: GIET University\n"
                + "Year: 2nd Year",
                GREEN
            )
        );

        grid.add(
            infoCard(
                "Skills",
                "Java • C • Python\n"
                + "HTML • CSS • JavaScript\n"
                + "SQL • MERN Stack",
                PURPLE
            )
        );

        grid.add(
            infoCard(
                "Profile Status",
                "Profile: VERIFIED\n"
                + "Resume: UPLOADED\n"
                + "Placement Ready: YES",
                ORANGE
            )
        );

        page.add(
            grid,
            BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // JOB OPENINGS
    // ============================================================

    JPanel createJobs() {

        JPanel page =
            createPage(
                "Job Openings",
                "Explore current campus placement opportunities."
            );

        JPanel list =
            createListPanel();

        addJob(
            list,
            "TCS",
            "System Engineer",
            "12 LPA",
            "tcs.png",
            BLUE
        );

        addJob(
            list,
            "Infosys",
            "Digital Specialist",
            "9 LPA",
            "infosys.png",
            GREEN
        );

        addJob(
            list,
            "Wipro",
            "Project Engineer",
            "8 LPA",
            "wipro.png",
            ORANGE
        );

        addJob(
            list,
            "Accenture",
            "Software Engineer",
            "10 LPA",
            "accenture.png",
            PURPLE
        );

        addJob(
            list,
            "Capgemini",
            "Analyst",
            "8 LPA",
            "capgemini.png",
            BLUE
        );

        addJob(
            list,
            "Cognizant",
            "Programmer Analyst",
            "10 LPA",
            "cognizant.png",
            GREEN
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // ADD JOB
    // ============================================================

    void addJob(
        JPanel list,
        String company,
        String role,
        String salary,
        String imageFile,
        Color color
    ) {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(
                15,
                5
            )
        );

        card.setBorder(
            new EmptyBorder(
                10,
                15,
                10,
                15
            )
        );

        card.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                82
            )
        );

        card.add(
            image(
                imageFile,
                50,
                45
            ),
            BorderLayout.WEST
        );

        JPanel info =
            new JPanel(
                new GridLayout(
                    2,
                    1
                )
            );

        info.setOpaque(false);

        info.add(
            label(
                company,
                TEXT,
                Font.BOLD,
                15
            )
        );

        info.add(
            label(
                role,
                GRAY,
                Font.PLAIN,
                11
            )
        );

        card.add(
            info,
            BorderLayout.CENTER
        );

        JPanel right =
            new JPanel(
                new FlowLayout(
                    FlowLayout.RIGHT,
                    8,
                    8
                )
            );

        right.setOpaque(false);

        right.add(
            label(
                salary,
                color,
                Font.BOLD,
                12
            )
        );

        ModernButton apply =
            new ModernButton(
                "Apply",
                color
            );

        apply.setPreferredSize(
            new Dimension(
                75,
                32
            )
        );

        right.add(apply);

        card.add(
            right,
            BorderLayout.EAST
        );

        list.add(card);

        list.add(
            Box.createVerticalStrut(8)
        );
    }

    // ============================================================
    // APPLICATIONS
    // ============================================================

    JPanel createApplications() {

        JPanel page =
            createPage(
                "My Applications",
                "Track the progress of your job applications."
            );

        JPanel list =
            createListPanel();

        application(
            list,
            "TCS",
            "System Engineer",
            "Applied",
            BLUE
        );

        application(
            list,
            "Infosys",
            "Digital Specialist",
            "Shortlisted",
            GREEN
        );

        application(
            list,
            "Wipro",
            "Project Engineer",
            "Under Review",
            ORANGE
        );

        application(
            list,
            "Accenture",
            "Software Engineer",
            "Interview Scheduled",
            PURPLE
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // APPLICATION ITEM
    // ============================================================

    void application(
        JPanel list,
        String company,
        String role,
        String status,
        Color color
    ) {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout()
        );

        card.setBorder(
            new EmptyBorder(
                12,
                15,
                12,
                15
            )
        );

        card.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                75
            )
        );

        JPanel info =
            new JPanel(
                new GridLayout(
                    2,
                    1
                )
            );

        info.setOpaque(false);

        info.add(
            label(
                company,
                TEXT,
                Font.BOLD,
                15
            )
        );

        info.add(
            label(
                role,
                GRAY,
                Font.PLAIN,
                11
            )
        );

        card.add(
            info,
            BorderLayout.CENTER
        );

        card.add(
            label(
                status,
                color,
                Font.BOLD,
                11
            ),
            BorderLayout.EAST
        );

        list.add(card);

        list.add(
            Box.createVerticalStrut(8)
        );
    }

    // ============================================================
    // RESUME
    // ============================================================

    JPanel createResume() {

        JPanel page =
            createPage(
                "Resume",
                "Keep your resume updated and ready for recruiters."
            );

        JPanel grid =
            new JPanel(
                new GridLayout(
                    1,
                    2,
                    15,
                    15
                )
            );

        grid.setOpaque(false);

        // --------------------------------------------------------
        // PROGRESS
        // --------------------------------------------------------

        RoundedPanel progress =
            new RoundedPanel();

        progress.setLayout(
            new BoxLayout(
                progress,
                BoxLayout.Y_AXIS
            )
        );

        progress.setBorder(
            new EmptyBorder(
                25,
                25,
                25,
                25
            )
        );

        progress.add(
            label(
                "Resume Completion",
                TEXT,
                Font.BOLD,
                18
            )
        );

        progress.add(
            Box.createVerticalStrut(20)
        );

        JLabel percent =
            label(
                "85%",
                BLUE,
                Font.BOLD,
                45
            );

        percent.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        progress.add(percent);

        progress.add(
            Box.createVerticalStrut(10)
        );

        JProgressBar bar =
            new JProgressBar(
                0,
                100
            );

        bar.setValue(85);

        bar.setForeground(BLUE);

        bar.setBorderPainted(false);

        progress.add(bar);

        progress.add(
            Box.createVerticalStrut(15)
        );

        progress.add(
            label(
                "Your resume is almost complete.",
                GRAY,
                Font.PLAIN,
                11
            )
        );

        grid.add(progress);

        // --------------------------------------------------------
        // ACTIONS
        // --------------------------------------------------------

        JPanel actions =
            new RoundedPanel(
                new GridLayout(
                    4,
                    1,
                    8,
                    8
                )
            );

        actions.setBorder(
            new EmptyBorder(
                20,
                20,
                20,
                20
            )
        );

        actions.add(
            label(
                "Resume Actions",
                TEXT,
                Font.BOLD,
                17
            )
        );

        actions.add(
            smallButton(
                "Upload Resume",
                BLUE
            )
        );

        actions.add(
            smallButton(
                "Download Resume",
                GREEN
            )
        );

        actions.add(
            smallButton(
                "Update Resume",
                PURPLE
            )
        );

        grid.add(actions);

        page.add(
            grid,
            BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // PLACEMENT STATUS
    // ============================================================

    JPanel createStatus() {

        JPanel page =
            createPage(
                "Placement Status",
                "Monitor your overall placement progress."
            );

        JPanel grid =
            new JPanel(
                new GridLayout(
                    2,
                    2,
                    15,
                    15
                )
            );

        grid.setOpaque(false);

        grid.add(
            statCard(
                "Resume",
                "UPLOADED",
                "Ready",
                GREEN
            )
        );

        grid.add(
            statCard(
                "Profile",
                "VERIFIED",
                "Complete",
                GREEN
            )
        );

        grid.add(
            statCard(
                "Applications",
                "12",
                "Submitted",
                BLUE
            )
        );

        grid.add(
            statCard(
                "Final Status",
                "SHORTLISTED",
                "Placement",
                PURPLE
            )
        );

        page.add(
            grid,
            BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // NOTIFICATIONS
    // ============================================================

    JPanel createNotifications() {

        JPanel page =
            createPage(
                "Notifications",
                "Stay updated with placement activities."
            );

        JPanel list =
            createListPanel();

        application(
            list,
            "TCS",
            "New campus drive registration opened.",
            "NEW",
            BLUE
        );

        application(
            list,
            "Resume",
            "Your resume has been approved.",
            "APPROVED",
            GREEN
        );

        application(
            list,
            "Infosys",
            "You have been shortlisted.",
            "SHORTLISTED",
            PURPLE
        );

        application(
            list,
            "Interview",
            "Your interview is scheduled tomorrow.",
            "IMPORTANT",
            ORANGE
        );

        application(
            list,
            "Wipro",
            "Registration deadline is approaching.",
            "REMINDER",
            RED
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // SETTINGS
    // ============================================================

    JPanel createSettings() {

        JPanel page =
            createPage(
                "Settings",
                "Manage your account and application preferences."
            );

        JPanel grid =
            new JPanel(
                new GridLayout(
                    2,
                    2,
                    15,
                    15
                )
            );

        grid.setOpaque(false);

        grid.add(
            infoCard(
                "Change Password",
                "Update your account password.",
                BLUE
            )
        );

        grid.add(
            infoCard(
                "Edit Profile",
                "Update your personal information.",
                GREEN
            )
        );

        grid.add(
            infoCard(
                "Help & Support",
                "Get assistance with HireSphere.",
                ORANGE
            )
        );

        grid.add(
            infoCard(
                "About HireSphere",
                "Learn more about the portal.",
                PURPLE
            )
        );

        page.add(
            grid,
            BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // COMMON PAGE
    // ============================================================

    JPanel createPage(
        String heading,
        String subtitle
    ) {

        JPanel page =
            new JPanel(
                new BorderLayout(
                    15,
                    18
                )
            );

        page.setBackground(BG);

        page.setBorder(
            new EmptyBorder(
                22,
                28,
                22,
                22
            )
        );

        JPanel top =
            new JPanel(
                new GridLayout(
                    2,
                    1
                )
            );

        top.setOpaque(false);

        top.add(
            label(
                heading,
                TEXT,
                Font.BOLD,
                25
            )
        );

        top.add(
            label(
                subtitle,
                GRAY,
                Font.PLAIN,
                12
            )
        );

        page.add(
            top,
            BorderLayout.NORTH
        );

        return page;
    }

    // ============================================================
    // INFORMATION CARD
    // ============================================================

    JPanel infoCard(
        String title,
        String text,
        Color color
    ) {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(
                0,
                10
            )
        );

        card.setBorder(
            new EmptyBorder(
                18,
                18,
                18,
                18
            )
        );

        card.add(
            label(
                title,
                color,
                Font.BOLD,
                15
            ),
            BorderLayout.NORTH
        );

        JTextArea area =
            new JTextArea(text);

        area.setEditable(false);

        area.setOpaque(false);

        area.setForeground(GRAY);

        area.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                11
            )
        );

        area.setLineWrap(true);

        area.setWrapStyleWord(true);

        card.add(
            area,
            BorderLayout.CENTER
        );

        return card;
    }

    // ============================================================
    // LIST PANEL
    // ============================================================

    JPanel createListPanel() {

        JPanel list =
            new JPanel();

        list.setOpaque(false);

        list.setLayout(
            new BoxLayout(
                list,
                BoxLayout.Y_AXIS
            )
        );

        return list;
    }

    // ============================================================
    // SCROLL
    // ============================================================

    JScrollPane scroll(
        JComponent component
    ) {

        JScrollPane scroll =
            new JScrollPane(
                component
            );

        scroll.setBorder(null);

        scroll.setOpaque(false);

        scroll.getViewport()
              .setOpaque(false);

        scroll.getVerticalScrollBar()
              .setUnitIncrement(16);

        return scroll;
    }

    // ============================================================
    // SMALL BUTTON
    // ============================================================

    ModernButton smallButton(
        String text,
        Color color
    ) {

        ModernButton b =
            new ModernButton(
                text,
                color
            );

        b.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                10
            )
        );

        b.setBorder(
            new EmptyBorder(
                7,
                12,
                7,
                12
            )
        );

        return b;
    }

    // ============================================================
    // IMAGE LOADER
    // ============================================================

    JLabel image(
        String file,
        int width,
        int height
    ) {

        JLabel label =
            new JLabel();

        java.net.URL url =
            getClass().getResource(
                "/images/" + file
            );

        if (url != null) {

            ImageIcon icon =
                new ImageIcon(url);

            Image img =
                icon.getImage()
                    .getScaledInstance(
                        width,
                        height,
                        Image.SCALE_SMOOTH
                    );

            label.setIcon(
                new ImageIcon(img)
            );

        } else {

            label.setText("?");

            label.setHorizontalAlignment(
                SwingConstants.CENTER
            );

            label.setForeground(GRAY);
        }

        return label;
    }

    // ============================================================
    // LABEL
    // ============================================================

    JLabel label(
        String text,
        Color color,
        int style,
        int size
    ) {

        JLabel l =
            new JLabel(text);

        l.setForeground(color);

        l.setFont(
            new Font(
                "Segoe UI",
                style,
                size
            )
        );

        return l;
    }

    // ============================================================
    // GREETING
    // ============================================================

    String getGreeting() {

        int hour =
            Integer.parseInt(
                new SimpleDateFormat(
                    "HH"
                ).format(
                    new Date()
                )
            );

        if (hour < 12) {
            return "Good Morning";
        }

        if (hour < 17) {
            return "Good Afternoon";
        }

        return "Good Evening";
    }

    // ============================================================
    // TIME
    // ============================================================

    void updateTime() {

        Date now =
            new Date();

        if (clock != null) {

            clock.setText(
                new SimpleDateFormat(
                    "hh:mm:ss a"
                ).format(now)
            );
        }

        if (date != null) {

            date.setText(
                new SimpleDateFormat(
                    "EEE, dd MMM yyyy"
                ).format(now)
            );
        }
    }

    // ============================================================
    // MODERN BUTTON
    //
    // IMPORTANT:
    // This custom button paints its own background.
    //
    // Therefore Windows / Eclipse / Swing Look & Feel
    // cannot make the button suddenly gray after login.
    // ============================================================

    static class ModernButton extends JButton {

        private Color buttonColor;

        ModernButton(
            String text,
            Color color
        ) {

            super(text);

            buttonColor = color;

            setForeground(Color.WHITE);

            setBackground(color);

            setOpaque(false);

            setContentAreaFilled(false);

            setFocusPainted(false);

            setBorderPainted(false);

            setRolloverEnabled(false);

            setFont(
                new Font(
                    "Segoe UI",
                    Font.BOLD,
                    12
                )
            );

            setBorder(
                new EmptyBorder(
                    10,
                    14,
                    10,
                    14
                )
            );

            setCursor(
                new Cursor(
                    Cursor.HAND_CURSOR
                )
            );
        }

        void setButtonColor(
            Color color
        ) {

            buttonColor = color;

            setBackground(color);

            repaint();
        }

        @Override
        protected void paintComponent(
            Graphics g
        ) {

            Graphics2D g2 =
                (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            Color color =
                buttonColor;

            if (getModel().isPressed()) {

                color =
                    color.darker();

            } else if (getModel().isRollover()) {

                color =
                    color.brighter();
            }

            g2.setColor(color);

            g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                12,
                12
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // ============================================================
    // ROUNDED PANEL
    // ============================================================

    static class RoundedPanel
        extends JPanel {

        RoundedPanel() {

            setBackground(Color.WHITE);

            setOpaque(false);
        }

        RoundedPanel(
            LayoutManager layout
        ) {

            super(layout);

            setBackground(Color.WHITE);

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
            Graphics g
        ) {

            Graphics2D g2 =
                (Graphics2D)
                g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                getBackground()
            );

            g2.fillRoundRect(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1,
                18,
                18
            );

            g2.setColor(
                BORDER
            );

            g2.drawRoundRect(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1,
                18,
                18
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // ============================================================
    // GRADIENT SIDEBAR
    // ============================================================

    static class GradientPanel
        extends JPanel {

        Color first;
        Color second;

        GradientPanel(
            Color first,
            Color second
        ) {

            this.first = first;

            this.second = second;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
            Graphics g
        ) {

            Graphics2D g2 =
                (Graphics2D)
                g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            GradientPaint gradient =
                new GradientPaint(
                    0,
                    0,
                    first,
                    getWidth(),
                    getHeight(),
                    second
                );

            g2.setPaint(
                gradient
            );

            g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
            );

            g2.dispose();
        }
    }

    // ============================================================
    // MAIN
    // ============================================================

    public static void main(
        String[] args
    ) {

        SwingUtilities.invokeLater(
            () -> new StudentDashboard()
        );
    }
}