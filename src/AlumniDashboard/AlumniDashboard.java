package AlumniDashboard;

import Home.HomePage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class AlumniDashboard extends JFrame {

    // =====================================================
    // COLORS
    // =====================================================

    static final Color NAVY = new Color(8, 27, 58);
    static final Color NAVY2 = new Color(18, 39, 88);
    static final Color BLUE = new Color(37, 99, 235);
    static final Color GREEN = new Color(22, 163, 74);
    static final Color PURPLE = new Color(124, 58, 237);
    static final Color ORANGE = new Color(234, 132, 24);
    static final Color RED = new Color(220, 38, 38);
    static final Color BG = new Color(245, 247, 250);
    static final Color TEXT = new Color(30, 41, 59);
    static final Color GRAY = new Color(100, 116, 139);
    static final Color BORDER = new Color(226, 232, 240);

    // =====================================================
    // MAIN COMPONENTS
    // =====================================================

    CardLayout cards = new CardLayout();
    JPanel content = new JPanel(cards);

    JLabel pageTitle;
    JLabel clock;
    JLabel date;

    JButton activeButton;

    // =====================================================
    // MENU
    // =====================================================

    String[] pages = {
        "Dashboard",
        "My Profile",
        "Announcements",
        "Alumni Directory",
        "Job Opportunities",
        "Statistics"
    };

    String[] icons = {
        "D",
        "P",
        "A",
        "L",
        "J",
        "S"
    };

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AlumniDashboard() {

        setTitle("HireSphere - Alumni Dashboard");
        setSize(1366, 768);
        setMinimumSize(new Dimension(1100, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // SIDEBAR
        add(createSidebar(), BorderLayout.WEST);

        // RIGHT SIDE
        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(BG);

        right.add(createHeader(), BorderLayout.NORTH);

        content.setBackground(BG);

        // PAGES
        content.add(createDashboard(), "Dashboard");
        content.add(createProfile(), "My Profile");
        content.add(createAnnouncements(), "Announcements");
        content.add(createAlumniDirectory(), "Alumni Directory");
        content.add(createJobs(), "Job Opportunities");
        content.add(createStatistics(), "Statistics");

        right.add(content, BorderLayout.CENTER);

        add(right, BorderLayout.CENTER);

        // CLOCK
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();

        updateTime();

        setVisible(true);
    }

    // =====================================================
    // SIDEBAR
    // =====================================================

    JPanel createSidebar() {

        GradientPanel side = new GradientPanel(NAVY, NAVY2);

        side.setPreferredSize(new Dimension(235, 768));
        side.setLayout(new BorderLayout());

        // LOGO
        JPanel logoPanel = new JPanel(new BorderLayout(10, 5));
        logoPanel.setOpaque(false);
        logoPanel.setBorder(
            new EmptyBorder(20, 18, 15, 12)
        );

        JLabel logoIcon = label(
            "H",
            Color.WHITE,
            Font.BOLD,
            24
        );

        logoIcon.setHorizontalAlignment(SwingConstants.CENTER);
        logoIcon.setVerticalAlignment(SwingConstants.CENTER);
        logoIcon.setOpaque(true);
        logoIcon.setBackground(BLUE);
        logoIcon.setPreferredSize(new Dimension(48, 48));

        JPanel logoText = new JPanel(new GridLayout(2, 1));
        logoText.setOpaque(false);

        JLabel name = label(
            "HireSphere",
            Color.WHITE,
            Font.BOLD,
            24
        );

        JLabel tagline = label(
            "ALUMNI PORTAL",
            new Color(165, 185, 220),
            Font.PLAIN,
            8
        );

        logoText.add(name);
        logoText.add(tagline);

        logoPanel.add(logoIcon, BorderLayout.WEST);
        logoPanel.add(logoText, BorderLayout.CENTER);

        side.add(logoPanel, BorderLayout.NORTH);

        // MENU
        JPanel menu = new JPanel();

        menu.setLayout(
            new BoxLayout(menu, BoxLayout.Y_AXIS)
        );

        menu.setOpaque(false);

        menu.setBorder(
            new EmptyBorder(8, 12, 8, 12)
        );

        // MENU BUTTONS
        for (int i = 0; i < pages.length; i++) {

            JButton button = createMenuButton(
                icons[i] + "   " + pages[i]
            );

            final int index = i;

            if (i == 0) {
                activeButton = button;
                button.setBackground(BLUE);
            }

            button.addActionListener(
                e -> showPage(pages[index], button)
            );

            button.setAlignmentX(Component.LEFT_ALIGNMENT);

            menu.add(button);
            menu.add(Box.createVerticalStrut(6));
        }

        // LOGOUT
        JButton logout = createMenuButton("X   Logout");

        logout.setAlignmentX(Component.LEFT_ALIGNMENT);

        logout.addActionListener(e -> {

            int answer = JOptionPane.showConfirmDialog(
                this,
                "Do you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION
            );

            if (answer == JOptionPane.YES_OPTION) {
                // Close the dashboard and go back to the Home page
                dispose();
                SwingUtilities.invokeLater(() -> new HomePage());
            }
        });

        menu.add(logout);

        side.add(menu, BorderLayout.CENTER);

        // FOOTER
        JLabel footer = label(
            "HireSphere  |  Alumni Portal",
            new Color(150, 170, 205),
            Font.PLAIN,
            10
        );

        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setOpaque(false);

        footerPanel.setBorder(
            new EmptyBorder(8, 20, 18, 10)
        );

        footerPanel.add(footer, BorderLayout.WEST);

        side.add(footerPanel, BorderLayout.SOUTH);

        return side;
    }

    // =====================================================
    // MENU BUTTON
    // =====================================================

    JButton createMenuButton(String text) {

        JButton button = new JButton(text);

        button.setForeground(Color.WHITE);

        button.setBackground(
            new Color(30, 50, 90)
        );

        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setPreferredSize(
            new Dimension(210, 45)
        );

        button.setMinimumSize(
            new Dimension(210, 45)
        );

        button.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 45)
        );

        button.setHorizontalAlignment(
            SwingConstants.LEFT
        );

        button.setVerticalAlignment(
            SwingConstants.CENTER
        );

        button.setMargin(
            new Insets(0, 0, 0, 0)
        );

        button.setBorder(
            BorderFactory.createEmptyBorder(
                10, 14, 10, 8
            )
        );

        button.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                13
            )
        );

        button.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    // =====================================================
    // PAGE SWITCH
    // =====================================================

    void showPage(String page, JButton button) {

        if (activeButton != null) {
            activeButton.setBackground(
                new Color(30, 50, 90)
            );
        }

        activeButton = button;

        activeButton.setBackground(BLUE);
        activeButton.setOpaque(true);
        activeButton.setContentAreaFilled(true);

        cards.show(content, page);

        pageTitle.setText(
            page.equals("Dashboard")
                ? "Alumni Dashboard"
                : page
        );
    }

    // =====================================================
    // HEADER
    // =====================================================

    JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());

        header.setPreferredSize(
            new Dimension(0, 105)
        );

        header.setBackground(
            new Color(15, 23, 42)
        );

        header.setBorder(
            new EmptyBorder(18, 28, 15, 25)
        );

        // LEFT
        JPanel left = new JPanel(new GridLayout(2, 1));
        left.setOpaque(false);

        pageTitle = label(
            "Alumni Dashboard",
            Color.WHITE,
            Font.BOLD,
            25
        );

        left.add(pageTitle);

        left.add(
            label(
                "Stay connected with your college community",
                new Color(148, 163, 184),
                Font.PLAIN,
                12
            )
        );

        // RIGHT
        JPanel right = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT,
                12,
                5
            )
        );

        right.setOpaque(false);

        JButton notification = new JButton("!");

        notification.setPreferredSize(
            new Dimension(55, 55)
        );

        notification.setBackground(
            new Color(30, 41, 59)
        );

        notification.setForeground(Color.WHITE);
        notification.setFocusPainted(false);
        notification.setBorderPainted(false);

        right.add(notification);

        JLabel avatar = label(
            "AL",
            Color.WHITE,
            Font.BOLD,
            14
        );

        avatar.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        avatar.setVerticalAlignment(
            SwingConstants.CENTER
        );

        avatar.setOpaque(true);
        avatar.setBackground(BLUE);

        avatar.setPreferredSize(
            new Dimension(55, 55)
        );

        right.add(avatar);

        JPanel user = new JPanel(new GridLayout(2, 1));
        user.setOpaque(false);

        user.add(
            label(
                "Welcome, Alumni",
                Color.WHITE,
                Font.BOLD,
                13
            )
        );

        user.add(
            label(
                "Alumni",
                new Color(148, 163, 184),
                Font.PLAIN,
                11
            )
        );

        right.add(user);

        JPanel time = new JPanel(new GridLayout(2, 1));
        time.setOpaque(false);

        clock = label(
            "",
            Color.WHITE,
            Font.BOLD,
            13
        );

        date = label(
            "",
            new Color(148, 163, 184),
            Font.PLAIN,
            11
        );

        time.add(clock);
        time.add(date);

        right.add(time);

        header.add(left, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);

        return header;
    }

    // =====================================================
    // DASHBOARD
    // =====================================================

    JPanel createDashboard() {

        JPanel page = new JPanel(new BorderLayout());

        page.setBackground(BG);

        page.setBorder(
            new EmptyBorder(20, 28, 20, 22)
        );

        JPanel all = new JPanel();

        all.setOpaque(false);

        all.setLayout(
            new BoxLayout(all, BoxLayout.Y_AXIS)
        );

        // WELCOME
        JPanel welcome = new JPanel(new BorderLayout());

        welcome.setBackground(
            new Color(239, 246, 255)
        );

        welcome.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(191, 219, 254)
                ),
                new EmptyBorder(
                    15, 20, 15, 20
                )
            )
        );

        JPanel welcomeText = new JPanel(new GridLayout(2, 1));
        welcomeText.setOpaque(false);

        welcomeText.add(
            label(
                getGreeting() + ", Alumni",
                NAVY,
                Font.BOLD,
                22
            )
        );

        welcomeText.add(
            label(
                "Connect, contribute and grow with the HireSphere community.",
                GRAY,
                Font.PLAIN,
                12
            )
        );

        JButton explore = smallButton(
            "Explore Opportunities",
            BLUE
        );

        explore.addActionListener(e -> {

            cards.show(
                content,
                "Job Opportunities"
            );

            pageTitle.setText(
                "Job Opportunities"
            );
        });

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

        // STATISTICS
        JPanel stats = new JPanel(
            new GridLayout(1, 4, 12, 12)
        );

        stats.setOpaque(false);

        stats.add(
            statCard(
                "Total Alumni",
                "125",
                "Registered",
                BLUE
            )
        );

        stats.add(
            statCard(
                "Working Alumni",
                "105",
                "Professionals",
                GREEN
            )
        );

        stats.add(
            statCard(
                "Job Opportunities",
                "18",
                "Available",
                PURPLE
            )
        );

        stats.add(
            statCard(
                "Announcements",
                "06",
                "New Updates",
                ORANGE
            )
        );

        all.add(stats);

        all.add(
            Box.createVerticalStrut(15)
        );

        // MIDDLE
        JPanel middle = new JPanel(
            new GridLayout(1, 2, 15, 15)
        );

        middle.setOpaque(false);

        middle.add(alumniProgressCard());
        middle.add(connectionCard());

        all.add(middle);

        all.add(
            Box.createVerticalStrut(15)
        );

        // COMMUNITY
        all.add(
            label(
                "Alumni Community",
                TEXT,
                Font.BOLD,
                18
            )
        );

        all.add(
            Box.createVerticalStrut(8)
        );

        JPanel community = new JPanel(
            new GridLayout(1, 3, 12, 12)
        );

        community.setOpaque(false);

        community.add(
            communityCard(
                "Rahul Sharma",
                "CSE • 2022",
                "TCS",
                BLUE
            )
        );

        community.add(
            communityCard(
                "Priya Das",
                "CSE • 2023",
                "Infosys",
                GREEN
            )
        );

        community.add(
            communityCard(
                "Amit Kumar",
                "ECE • 2021",
                "Wipro",
                ORANGE
            )
        );

        all.add(community);

        all.add(
            Box.createVerticalStrut(15)
        );

        // LOWER
        JPanel lower = new JPanel(
            new GridLayout(1, 2, 15, 15)
        );

        lower.setOpaque(false);

        lower.add(activityCard());
        lower.add(tipsCard());

        all.add(lower);

        JScrollPane scroll = new JScrollPane(all);

        scroll.setBorder(null);
        scroll.setOpaque(false);

        scroll.getViewport().setOpaque(false);

        scroll.getVerticalScrollBar()
              .setUnitIncrement(15);

        page.add(
            scroll,
            BorderLayout.CENTER
        );

        return page;
    }

    // =====================================================
    // STAT CARD
    // =====================================================

    JPanel statCard(
        String title,
        String value,
        String small,
        Color color
    ) {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(new BorderLayout());

        card.setBorder(
            new EmptyBorder(
                14, 16, 14, 16
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
                27
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

    // =====================================================
    // ALUMNI PROGRESS
    // =====================================================

    JPanel alumniProgressCard() {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(10, 10)
        );

        card.setBorder(
            new EmptyBorder(
                16, 18, 16, 18
            )
        );

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        top.add(
            label(
                "Community Engagement",
                TEXT,
                Font.BOLD,
                16
            ),
            BorderLayout.WEST
        );

        top.add(
            label(
                "80%",
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
            new JProgressBar(0, 100);

        progress.setValue(80);
        progress.setStringPainted(false);
        progress.setForeground(BLUE);

        progress.setBackground(
            new Color(226, 232, 240)
        );

        progress.setPreferredSize(
            new Dimension(0, 12)
        );

        card.add(
            progress,
            BorderLayout.CENTER
        );

        JPanel checks = new JPanel(
            new GridLayout(2, 2)
        );

        checks.setOpaque(false);

        checks.add(
            label(
                "✓ Profile Updated",
                GREEN,
                Font.PLAIN,
                10
            )
        );

        checks.add(
            label(
                "✓ Community Joined",
                GREEN,
                Font.PLAIN,
                10
            )
        );

        checks.add(
            label(
                "✓ Contact Verified",
                GREEN,
                Font.PLAIN,
                10
            )
        );

        checks.add(
            label(
                "○ Mentorship",
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

    // =====================================================
    // CONNECTION CARD
    // =====================================================

    JPanel connectionCard() {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(10, 5)
        );

        card.setBorder(
            new EmptyBorder(
                16, 18, 16, 18
            )
        );

        card.add(
            label(
                "Community Connections",
                TEXT,
                Font.BOLD,
                16
            ),
            BorderLayout.NORTH
        );

        JPanel center = new JPanel(
            new GridLayout(3, 1, 0, 5)
        );

        center.setOpaque(false);

        center.add(
            label(
                "125 Alumni Registered",
                BLUE,
                Font.BOLD,
                13
            )
        );

        center.add(
            label(
                "105 Currently Working",
                GREEN,
                Font.BOLD,
                13
            )
        );

        center.add(
            label(
                "18 Opportunities Shared",
                PURPLE,
                Font.BOLD,
                13
            )
        );

        card.add(
            center,
            BorderLayout.CENTER
        );

        JButton view = smallButton(
            "View Directory",
            BLUE
        );

        view.addActionListener(e -> {

            cards.show(
                content,
                "Alumni Directory"
            );

            pageTitle.setText(
                "Alumni Directory"
            );
        });

        card.add(
            view,
            BorderLayout.SOUTH
        );

        return card;
    }

    // =====================================================
    // COMMUNITY CARD
    // =====================================================

    JPanel communityCard(
        String name,
        String details,
        String company,
        Color color
    ) {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(5, 5)
        );

        card.setBorder(
            new EmptyBorder(
                12, 12, 12, 12
            )
        );

        JLabel avatar = label(
            name.substring(0, 1),
            Color.WHITE,
            Font.BOLD,
            20
        );

        avatar.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        avatar.setOpaque(true);
        avatar.setBackground(color);

        avatar.setPreferredSize(
            new Dimension(50, 45)
        );

        card.add(
            avatar,
            BorderLayout.WEST
        );

        JPanel info = new JPanel(
            new GridLayout(3, 1)
        );

        info.setOpaque(false);

        info.add(
            label(
                name,
                TEXT,
                Font.BOLD,
                13
            )
        );

        info.add(
            label(
                details,
                GRAY,
                Font.PLAIN,
                10
            )
        );

        info.add(
            label(
                company,
                color,
                Font.BOLD,
                11
            )
        );

        card.add(
            info,
            BorderLayout.CENTER
        );

        return card;
    }

    // =====================================================
    // ACTIVITY
    // =====================================================

    JPanel activityCard() {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(0, 8)
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

        JPanel list = new JPanel(
            new GridLayout(4, 1, 0, 5)
        );

        list.setOpaque(false);

        list.add(
            label(
                "✓  Profile information updated",
                GREEN,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "✓  New alumni joined the community",
                BLUE,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "★  New job opportunity posted",
                PURPLE,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "!  Alumni meet announcement",
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

    // =====================================================
    // TIPS
    // =====================================================

    JPanel tipsCard() {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(new BorderLayout());

        card.setBorder(
            new EmptyBorder(
                15, 18, 15, 18
            )
        );

        card.add(
            label(
                "Alumni Tip",
                PURPLE,
                Font.BOLD,
                16
            ),
            BorderLayout.NORTH
        );

        JTextArea text = new JTextArea(
            "Stay connected with your college "
            + "community and share valuable career "
            + "opportunities with current students.\n\n"
            + "Your experience can help the next "
            + "generation of students succeed."
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

    // =====================================================
    // PROFILE
    // =====================================================

    JPanel createProfile() {

        JPanel page = createPage(
            "My Profile",
            "Manage your personal and professional information."
        );

        JPanel grid = new JPanel(
            new GridLayout(2, 2, 15, 15)
        );

        grid.setOpaque(false);

        grid.add(
            infoCard(
                "Personal Information",
                "Name: Alumni Name\n"
                + "Email: alumni@example.com\n"
                + "Phone: +91 XXXXX XXXXX",
                BLUE
            )
        );

        grid.add(
            infoCard(
                "Academic Information",
                "Course: B.Tech CSE\n"
                + "University: GIET University\n"
                + "Graduation Year: 2024",
                GREEN
            )
        );

        grid.add(
            infoCard(
                "Professional Information",
                "Company: ABC Technologies\n"
                + "Role: Software Engineer\n"
                + "Experience: 2 Years",
                PURPLE
            )
        );

        grid.add(
            infoCard(
                "Profile Status",
                "Profile: VERIFIED\n"
                + "Email: VERIFIED\n"
                + "Alumni Status: ACTIVE",
                ORANGE
            )
        );

        page.add(
            grid,
            BorderLayout.CENTER
        );

        return page;
    }

    // =====================================================
    // ANNOUNCEMENTS
    // =====================================================

    JPanel createAnnouncements() {

        JPanel page = createPage(
            "Announcements",
            "Stay updated with college and alumni activities."
        );

        JPanel list = createListPanel();

        addAnnouncement(
            list,
            "Campus Placement Drive",
            "A new campus placement drive has been announced.",
            "NEW",
            BLUE
        );

        addAnnouncement(
            list,
            "Alumni Meet",
            "Annual alumni meet registration is now open.",
            "EVENT",
            GREEN
        );

        addAnnouncement(
            list,
            "Career Opportunities",
            "New job opportunities have been shared by alumni.",
            "JOBS",
            PURPLE
        );

        addAnnouncement(
            list,
            "Alumni Registration",
            "Please keep your professional information updated.",
            "REMINDER",
            ORANGE
        );

        addAnnouncement(
            list,
            "Community Update",
            "More alumni have recently joined the HireSphere network.",
            "UPDATE",
            RED
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // =====================================================
    // ADD ANNOUNCEMENT
    // =====================================================

    void addAnnouncement(
        JPanel list,
        String title,
        String description,
        String status,
        Color color
    ) {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(15, 5)
        );

        card.setBorder(
            new EmptyBorder(
                12, 15, 12, 15
            )
        );

        JPanel info = new JPanel(
            new GridLayout(2, 1)
        );

        info.setOpaque(false);

        info.add(
            label(
                title,
                TEXT,
                Font.BOLD,
                14
            )
        );

        info.add(
            label(
                description,
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

    // =====================================================
    // ALUMNI DIRECTORY
    // =====================================================

    JPanel createAlumniDirectory() {

        JPanel page = createPage(
            "Alumni Directory",
            "Explore and connect with members of the alumni community."
        );

        String[] columns = {
            "Alumni ID",
            "Name",
            "Branch",
            "Graduation Year",
            "Company"
        };

        Object[][] data = {
            {
                "A001",
                "Rahul Sharma",
                "CSE",
                "2022",
                "TCS"
            },
            {
                "A002",
                "Priya Das",
                "CSE",
                "2023",
                "Infosys"
            },
            {
                "A003",
                "Amit Kumar",
                "ECE",
                "2021",
                "Wipro"
            },
            {
                "A004",
                "Sneha Patel",
                "CSE",
                "2022",
                "Accenture"
            },
            {
                "A005",
                "Rohan Mishra",
                "IT",
                "2023",
                "Cognizant"
            }
        };

        JTable table = new JTable(
            data,
            columns
        );

        table.setRowHeight(38);

        table.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                13
            )
        );

        table.getTableHeader().setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                13
            )
        );

        table.setGridColor(BORDER);

        table.setSelectionBackground(
            new Color(219, 234, 254)
        );

        table.setSelectionForeground(TEXT);

        page.add(
            new JScrollPane(table),
            BorderLayout.CENTER
        );

        return page;
    }

    // =====================================================
    // JOB OPPORTUNITIES
    // =====================================================

    JPanel createJobs() {

        JPanel page = createPage(
            "Job Opportunities",
            "Explore career opportunities shared by companies and alumni."
        );

        JPanel list = createListPanel();

        addJob(
            list,
            "ABC Technologies",
            "Software Engineer",
            "Bangalore",
            "0-2 Years",
            "10 LPA",
            BLUE
        );

        addJob(
            list,
            "XYZ Solutions",
            "Data Analyst",
            "Hyderabad",
            "1-3 Years",
            "8 LPA",
            GREEN
        );

        addJob(
            list,
            "TechWorld",
            "Java Developer",
            "Pune",
            "0-2 Years",
            "9 LPA",
            PURPLE
        );

        addJob(
            list,
            "Global Systems",
            "Software Developer",
            "Bangalore",
            "1-2 Years",
            "11 LPA",
            ORANGE
        );

        addJob(
            list,
            "Digital Labs",
            "Business Analyst",
            "Hyderabad",
            "0-2 Years",
            "7 LPA",
            BLUE
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // =====================================================
    // ADD JOB
    // =====================================================

    void addJob(
        JPanel list,
        String company,
        String role,
        String location,
        String experience,
        String salary,
        Color color
    ) {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(15, 5)
        );

        card.setBorder(
            new EmptyBorder(
                10, 15, 10, 15
            )
        );

        JLabel logo = label(
            company.substring(0, 1),
            Color.WHITE,
            Font.BOLD,
            20
        );

        logo.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        logo.setOpaque(true);
        logo.setBackground(color);

        logo.setPreferredSize(
            new Dimension(50, 45)
        );

        card.add(
            logo,
            BorderLayout.WEST
        );

        JPanel info = new JPanel(
            new GridLayout(3, 1)
        );

        info.setOpaque(false);

        info.add(
            label(
                company,
                TEXT,
                Font.BOLD,
                14
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

        info.add(
            label(
                location + "  |  " + experience,
                GRAY,
                Font.PLAIN,
                10
            )
        );

        card.add(
            info,
            BorderLayout.CENTER
        );

        JPanel right = new JPanel(
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

        JButton view = smallButton(
            "View",
            color
        );

        view.addActionListener(
            e -> JOptionPane.showMessageDialog(
                this,
                "Job Role: " + role
                + "\nCompany: " + company
                + "\nLocation: " + location
                + "\nExperience: " + experience
                + "\nSalary: " + salary,
                "Job Details",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        right.add(view);

        card.add(
            right,
            BorderLayout.EAST
        );

        list.add(card);

        list.add(
            Box.createVerticalStrut(8)
        );
    }

    // =====================================================
    // STATISTICS
    // =====================================================

    JPanel createStatistics() {

        JPanel page = createPage(
            "Statistics",
            "View an overview of the HireSphere alumni community."
        );

        JPanel all = new JPanel();

        all.setOpaque(false);

        all.setLayout(
            new BoxLayout(all, BoxLayout.Y_AXIS)
        );

        JPanel cards = new JPanel(
            new GridLayout(1, 4, 12, 12)
        );

        cards.setOpaque(false);

        cards.add(
            statCard(
                "Registered Alumni",
                "125",
                "Total",
                BLUE
            )
        );

        cards.add(
            statCard(
                "Working",
                "105",
                "Professionals",
                GREEN
            )
        );

        cards.add(
            statCard(
                "Higher Studies",
                "12",
                "Students",
                PURPLE
            )
        );

        cards.add(
            statCard(
                "Entrepreneurs",
                "08",
                "Business Owners",
                ORANGE
            )
        );

        all.add(cards);

        all.add(
            Box.createVerticalStrut(20)
        );

        RoundedPanel overview = new RoundedPanel();

        overview.setLayout(
            new BorderLayout()
        );

        overview.setBorder(
            new EmptyBorder(
                20, 22, 20, 22
            )
        );

        overview.add(
            label(
                "Alumni Overview",
                TEXT,
                Font.BOLD,
                18
            ),
            BorderLayout.NORTH
        );

        JTextArea details = new JTextArea(
            "Total Registered Alumni : 125\n\n"
            + "Currently Working       : 105\n\n"
            + "Higher Studies           : 12\n\n"
            + "Entrepreneurs            : 08\n\n"
            + "Active Job Opportunities : 18\n\n"
            + "Latest Announcements    : 06"
        );

        details.setEditable(false);
        details.setOpaque(false);
        details.setForeground(GRAY);

        details.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                13
            )
        );

        overview.add(
            details,
            BorderLayout.CENTER
        );

        all.add(overview);

        page.add(
            all,
            BorderLayout.CENTER
        );

        return page;
    }

    // =====================================================
    // BASIC PAGE
    // =====================================================

    JPanel createPage(
        String heading,
        String subtitle
    ) {

        JPanel page = new JPanel(
            new BorderLayout(15, 18)
        );

        page.setBackground(BG);

        page.setBorder(
            new EmptyBorder(
                22, 28, 22, 22
            )
        );

        JPanel top = new JPanel(
            new GridLayout(2, 1)
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

    // =====================================================
    // INFORMATION CARD
    // =====================================================

    JPanel infoCard(
        String title,
        String text,
        Color color
    ) {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(0, 10)
        );

        card.setBorder(
            new EmptyBorder(
                18, 18, 18, 18
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

        JTextArea area = new JTextArea(text);

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

    // =====================================================
    // LIST PANEL
    // =====================================================

    JPanel createListPanel() {

        JPanel list = new JPanel();

        list.setOpaque(false);

        list.setLayout(
            new BoxLayout(
                list,
                BoxLayout.Y_AXIS
            )
        );

        return list;
    }

    // =====================================================
    // SCROLL
    // =====================================================

    JScrollPane scroll(JPanel component) {

        JScrollPane scroll =
            new JScrollPane(component);

        scroll.setBorder(null);
        scroll.setOpaque(false);

        scroll.getViewport()
              .setOpaque(false);

        scroll.getVerticalScrollBar()
              .setUnitIncrement(15);

        return scroll;
    }

    // =====================================================
    // SMALL BUTTON
    // =====================================================

    JButton smallButton(
        String text,
        Color color
    ) {

        JButton button = new JButton(text);

        button.setBackground(color);
        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);

        button.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                10
            )
        );

        button.setBorder(
            new EmptyBorder(
                7, 12, 7, 12
            )
        );

        button.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    // =====================================================
    // LABEL
    // =====================================================

    JLabel label(
        String text,
        Color color,
        int style,
        int size
    ) {

        JLabel label = new JLabel(text);

        label.setForeground(color);

        label.setFont(
            new Font(
                "Segoe UI",
                style,
                size
            )
        );

        return label;
    }

    // =====================================================
    // GREETING
    // =====================================================

    String getGreeting() {

        int hour = Integer.parseInt(
            new SimpleDateFormat("HH")
                .format(new Date())
        );

        if (hour < 12) {
            return "Good Morning";
        }

        if (hour < 17) {
            return "Good Afternoon";
        }

        return "Good Evening";
    }

    // =====================================================
    // TIME
    // =====================================================

    void updateTime() {

        Date now = new Date();

        clock.setText(
            new SimpleDateFormat(
                "hh:mm:ss a"
            ).format(now)
        );

        date.setText(
            new SimpleDateFormat(
                "EEE, dd MMM yyyy"
            ).format(now)
        );
    }

    // =====================================================
    // ROUNDED PANEL
    // =====================================================

    static class RoundedPanel extends JPanel {

        RoundedPanel() {

            setBackground(Color.WHITE);
            setOpaque(false);
        }

        RoundedPanel(LayoutManager layout) {

            super(layout);

            setBackground(Color.WHITE);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 =
                (Graphics2D) g.create();

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
                16,
                16
            );

            g2.setColor(BORDER);

            g2.drawRoundRect(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1,
                16,
                16
            );

            g2.dispose();
        }
    }

    // =====================================================
    // GRADIENT PANEL
    // =====================================================

    static class GradientPanel extends JPanel {

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
        protected void paintComponent(Graphics g) {

            Graphics2D g2 =
                (Graphics2D) g.create();

            GradientPaint gradient =
                new GradientPaint(
                    0,
                    0,
                    first,
                    getWidth(),
                    getHeight(),
                    second
                );

            g2.setPaint(gradient);

            g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new AlumniDashboard()
        );
    }
}