package companydashboard;

import Home.HomePage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.CardLayout;

public class CompanyDashboard extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    static final Color NAVY = new Color(10, 25, 47);
    static final Color NAVY2 = new Color(23, 42, 69);
    static final Color BLUE = new Color(14, 116, 233);
    static final Color GREEN = new Color(16, 185, 129);
    static final Color PURPLE = new Color(139, 92, 246);
    static final Color ORANGE = new Color(245, 158, 11);
    static final Color RED = new Color(239, 68, 68);

    static final Color BG = new Color(238, 242, 246);
    static final Color TEXT = new Color(15, 23, 42);
    static final Color GRAY = new Color(71, 85, 105);
    static final Color BORDER = new Color(203, 213, 225);

    // =========================================================
    // MAIN COMPONENTS
    // =========================================================

    CardLayout cards = new CardLayout();
    JPanel content = new JPanel(cards);

    JLabel pageTitle;
    JLabel clock;
    JLabel date;

    JButton activeButton;

    // =========================================================
    // SIDEBAR MENU
    // =========================================================

    String[] pages = {
        "Dashboard",
        "Manage Jobs",
        "Applications",
        "Eligible Students",
        "Shortlisted Students",
        "Interview Schedule",
        "Results",
        "Notifications",
        "Reports",
        "Settings"
    };

    String[] icons = {
        "D", "J", "A", "E", "S", "I", "R", "N", "P", "T"
    };

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public CompanyDashboard() {

        setTitle("HireSphere - Recruiter Portal");

        setSize(1440, 900);
        setMinimumSize(new Dimension(1200, 750));

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
        content.add(createManageJobs(), "Manage Jobs");
        content.add(createApplications(), "Applications");
        content.add(createEligibleStudents(), "Eligible Students");
        content.add(createShortlistedStudents(), "Shortlisted Students");
        content.add(createInterviewSchedule(), "Interview Schedule");
        content.add(createResults(), "Results");
        content.add(createNotifications(), "Notifications");
        content.add(createReports(), "Reports");
        content.add(createSettings(), "Settings");

        right.add(content, BorderLayout.CENTER);

        add(right, BorderLayout.CENTER);

        // Clock
        new Timer(1000, e -> updateTime()).start();
        updateTime();

        setVisible(true);
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    JPanel createSidebar() {

        GradientPanel side = new GradientPanel(NAVY, NAVY2);

        side.setPreferredSize(new Dimension(280, 900));
        side.setLayout(new BorderLayout());

        // -----------------------------------------------------
        // LOGO
        // -----------------------------------------------------

        JPanel logoPanel = new JPanel(new BorderLayout(12, 5));
        logoPanel.setOpaque(false);
        logoPanel.setBorder(new EmptyBorder(22, 20, 16, 16));

        JLabel logoImage = image("logo.png", 48, 48);

        JPanel logoText = new JPanel(new GridLayout(2, 1));
        logoText.setOpaque(false);

        JLabel name = label(
            "HireSphere",
            Color.WHITE,
            Font.BOLD,
            26
        );

        JLabel tagline = label(
            "COMPANY PORTAL",
            new Color(165, 215, 255),
            Font.BOLD,
            11
        );

        logoText.add(name);
        logoText.add(tagline);

        logoPanel.add(logoImage, BorderLayout.WEST);
        logoPanel.add(logoText, BorderLayout.CENTER);

        side.add(logoPanel, BorderLayout.NORTH);

        // -----------------------------------------------------
        // MENU
        // -----------------------------------------------------

        JPanel menu = new JPanel(
            new GridLayout(pages.length + 1, 1, 0, 6)
        );

        menu.setOpaque(false);
        menu.setBorder(new EmptyBorder(10, 16, 10, 16));

        for (int i = 0; i < pages.length; i++) {

            JButton b = createMenuButton(
                icons[i] + "   " + pages[i]
            );

            final int index = i;

            if (i == 0) {
                activeButton = b;
                b.setBackground(BLUE);
            }

            b.addActionListener(e ->
                showPage(pages[index], b)
            );

            menu.add(b);
        }

        // Logout
        JButton logout = createMenuButton("X   Logout");

        logout.addActionListener(e -> {

            int answer = JOptionPane.showConfirmDialog(
                this,
                "Do you want to logout?",
                "Logout Confirmation",
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

        // -----------------------------------------------------
        // FOOTER
        // -----------------------------------------------------

        JLabel footer = label(
            "HireSphere | Recruiter Suite",
            new Color(180, 200, 230),
            Font.PLAIN,
            12
        );

        JPanel footerPanel = new JPanel(new BorderLayout());

        footerPanel.setOpaque(false);
        footerPanel.setBorder(
            new EmptyBorder(12, 22, 16, 16)
        );

        footerPanel.add(footer, BorderLayout.WEST);

        side.add(footerPanel, BorderLayout.SOUTH);

        return side;
    }

    // =========================================================
    // MENU BUTTON
    // =========================================================

    JButton createMenuButton(String text) {

        JButton b = new JButton(text);

        b.setForeground(Color.WHITE);
        b.setBackground(NAVY2);

        b.setOpaque(true);
        b.setFocusPainted(false);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);

        b.setBorder(
            new EmptyBorder(12, 16, 12, 12)
        );

        b.setHorizontalAlignment(SwingConstants.LEFT);

        b.setFont(
            new Font("Segoe UI", Font.BOLD, 15)
        );

        b.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        return b;
    }

    // =========================================================
    // SHOW PAGE
    // =========================================================

    void showPage(String page, JButton button) {

        if (activeButton != null) {
            activeButton.setBackground(NAVY2);
        }

        activeButton = button;
        activeButton.setBackground(BLUE);

        cards.show(content, page);

        if (page.equals("Dashboard")) {
            pageTitle.setText("Company Dashboard");
        } else {
            pageTitle.setText(page);
        }
    }

    // =========================================================
    // HEADER
    // =========================================================

    JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());

        header.setPreferredSize(
            new Dimension(0, 110)
        );

        header.setBackground(NAVY);

        header.setBorder(
            new EmptyBorder(18, 30, 15, 30)
        );

        // Left
        JPanel left = new JPanel(new GridLayout(2, 1));
        left.setOpaque(false);

        pageTitle = label(
            "Company Dashboard",
            Color.WHITE,
            Font.BOLD,
            26
        );

        left.add(pageTitle);

        left.add(
            label(
                "Placement Drive & User Management Portal",
                new Color(203, 213, 225),
                Font.PLAIN,
                14
            )
        );

        // Right
        JPanel right = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT,
                15,
                5
            )
        );

        right.setOpaque(false);

        // Notification
        JButton notification = new JButton("!");

        notification.setPreferredSize(
            new Dimension(55, 55)
        );

        notification.setBackground(NAVY2);
        notification.setForeground(Color.WHITE);

        notification.setFont(
            new Font("Segoe UI", Font.BOLD, 18)
        );

        notification.setFocusPainted(false);
        notification.setBorderPainted(false);
        notification.setOpaque(true);

        notification.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        notification.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                "You have 3 unread recruitment alerts.",
                "Notifications",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        right.add(notification);

        // Avatar
        JLabel avatar = label(
            "TCS",
            Color.WHITE,
            Font.BOLD,
            15
        );

        avatar.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        avatar.setOpaque(true);
        avatar.setBackground(BLUE);

        avatar.setPreferredSize(
            new Dimension(55, 55)
        );

        right.add(avatar);

        // User
        JPanel user = new JPanel(
            new GridLayout(2, 1)
        );

        user.setOpaque(false);

        user.add(
            label(
                "TCS Recruitment",
                Color.WHITE,
                Font.BOLD,
                15
            )
        );

        user.add(
            label(
                "Recruiter Admin",
                new Color(203, 213, 225),
                Font.PLAIN,
                13
            )
        );

        right.add(user);

        // Time
        JPanel time = new JPanel(
            new GridLayout(2, 1)
        );

        time.setOpaque(false);

        clock = label(
            "",
            Color.WHITE,
            Font.BOLD,
            14
        );

        date = label(
            "",
            new Color(203, 213, 225),
            Font.PLAIN,
            13
        );

        time.add(clock);
        time.add(date);

        right.add(time);

        header.add(left, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);

        return header;
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    JPanel createDashboard() {

        JPanel page = new JPanel(new BorderLayout());

        page.setBackground(BG);

        page.setBorder(
            new EmptyBorder(20, 30, 20, 25)
        );

        JPanel all = new JPanel();

        all.setOpaque(false);

        all.setLayout(
            new BoxLayout(
                all,
                BoxLayout.Y_AXIS
            )
        );

        // -----------------------------------------------------
        // HERO
        // -----------------------------------------------------

        JPanel heroBanner = new RoundedPanel();

        heroBanner.setLayout(
            new BorderLayout(20, 0)
        );

        heroBanner.setBackground(NAVY);

        heroBanner.setBorder(
            new EmptyBorder(22, 28, 22, 28)
        );

        heroBanner.setMaximumSize(
            new Dimension(1300, 150)
        );

        JPanel heroText = new JPanel(
            new GridLayout(3, 1, 0, 6)
        );

        heroText.setOpaque(false);

        heroText.add(
            label(
                getGreeting() +
                ", TCS Recruitment Team 👋",
                Color.WHITE,
                Font.BOLD,
                26
            )
        );

        heroText.add(
            label(
                "Tata Consultancy Services • Campus Placement Drive 2026",
                new Color(203, 213, 225),
                Font.PLAIN,
                15
            )
        );

        JPanel tagPanel = new JPanel(
            new FlowLayout(
                FlowLayout.LEFT,
                10,
                0
            )
        );

        tagPanel.setOpaque(false);

        tagPanel.add(
            createBadge(
                "● LIVE DRIVE ACTIVE",
                GREEN
            )
        );

        tagPanel.add(
            createBadge(
                "TIER-1 RECRUITER",
                BLUE
            )
        );

        heroText.add(tagPanel);

        // TCS Logo
        JPanel tcsLogoCard = new RoundedPanel();

        tcsLogoCard.setBackground(Color.WHITE);

        tcsLogoCard.setLayout(
            new BorderLayout()
        );

        tcsLogoCard.setPreferredSize(
            new Dimension(180, 95)
        );

        tcsLogoCard.setBorder(
            new EmptyBorder(10, 15, 10, 15)
        );

        JLabel tcsImgLabel =
            image("tcs_logo.png", 140, 70);

        if ("?".equals(tcsImgLabel.getText())) {

            tcsImgLabel.setText("TCS");

            tcsImgLabel.setFont(
                new Font(
                    "Segoe UI",
                    Font.BOLD,
                    28
                )
            );

            tcsImgLabel.setForeground(NAVY);
        }

        tcsLogoCard.add(
            tcsImgLabel,
            BorderLayout.CENTER
        );

        heroBanner.add(
            heroText,
            BorderLayout.CENTER
        );

        heroBanner.add(
            tcsLogoCard,
            BorderLayout.EAST
        );

        all.add(heroBanner);

        all.add(
            Box.createVerticalStrut(18)
        );

        // -----------------------------------------------------
        // STATS
        // -----------------------------------------------------

        JPanel stats = new JPanel(
            new GridLayout(1, 4, 18, 18)
        );

        stats.setOpaque(false);

        stats.setMaximumSize(
            new Dimension(1300, 120)
        );

        stats.add(
            modernStatCard(
                "Active Drives",
                "04",
                "2 Open Positions",
                BLUE
            )
        );

        stats.add(
            modernStatCard(
                "Applications",
                "156",
                "+18 Today",
                GREEN
            )
        );

        stats.add(
            modernStatCard(
                "Interviews",
                "18",
                "Scheduled Today",
                PURPLE
            )
        );

        stats.add(
            modernStatCard(
                "Offers Issued",
                "09",
                "Finalized Candidates",
                ORANGE
            )
        );

        all.add(stats);

        all.add(
            Box.createVerticalStrut(20)
        );

        // -----------------------------------------------------
        // MIDDLE
        // -----------------------------------------------------

        JPanel middle = new JPanel(
            new GridLayout(1, 2, 20, 20)
        );

        middle.setOpaque(false);

        middle.add(
            modernDriveProgressCard()
        );

        middle.add(
            modernUpcomingInterviewsCard()
        );

        all.add(middle);

        page.add(
            scroll(all),
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // MANAGE JOBS
    // =========================================================

    JPanel createManageJobs() {

        JPanel page = createPage(
            "Manage Jobs",
            "Create, update, and monitor corporate job drives."
        );

        JPanel topBar = new JPanel(
            new FlowLayout(FlowLayout.RIGHT)
        );

        topBar.setOpaque(false);

        JButton createBtn =
            smallButton(
                "+ Add New Job Posting",
                BLUE
            );

        createBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                "Opening Job Form Modal...",
                "Manage Jobs",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        topBar.add(createBtn);

        JPanel list = createListPanel();

        addJobCard(
            list,
            "Software Engineer",
            "12 LPA",
            "B.Tech CSE/IT",
            "Active",
            BLUE
        );

        addJobCard(
            list,
            "System Engineer",
            "9 LPA",
            "B.Tech CSE/ECE/EEE",
            "Active",
            GREEN
        );

        addJobCard(
            list,
            "Data Analyst",
            "8.5 LPA",
            "B.Tech All Branches",
            "Closed",
            RED
        );

        addJobCard(
            list,
            "Cloud Support Engineer",
            "7.5 LPA",
            "B.Tech CSE/IT/ECE",
            "Active",
            PURPLE
        );

        JPanel mainContent =
            new JPanel(new BorderLayout());

        mainContent.setOpaque(false);

        mainContent.add(
            topBar,
            BorderLayout.NORTH
        );

        mainContent.add(
            scroll(list),
            BorderLayout.CENTER
        );

        page.add(
            mainContent,
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // JOB CARD
    // =========================================================

    void addJobCard(
        JPanel list,
        String role,
        String salary,
        String eligibility,
        String status,
        Color statusColor
    ) {

        JPanel card = new JPanel(
            new BorderLayout(20, 5)
        );

        card.setBackground(Color.WHITE);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    BORDER,
                    2
                ),
                new EmptyBorder(
                    16,
                    20,
                    16,
                    20
                )
            )
        );

        card.setMaximumSize(
            new Dimension(1100, 85)
        );

        JPanel info = new JPanel(
            new GridLayout(2, 1, 0, 4)
        );

        info.setOpaque(false);

        info.add(
            label(
                role + "  (" + salary + ")",
                TEXT,
                Font.BOLD,
                17
            )
        );

        info.add(
            label(
                "Eligibility: " + eligibility,
                GRAY,
                Font.PLAIN,
                14
            )
        );

        JPanel right = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT,
                12,
                5
            )
        );

        right.setOpaque(false);

        right.add(
            label(
                status,
                statusColor,
                Font.BOLD,
                14
            )
        );

        JButton editBtn =
            smallButton(
                "Edit Drive",
                BLUE
            );

        editBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                "Editing settings for: " + role,
                "Edit Posting",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        JButton closeBtn =
            smallButton(
                "Close",
                RED
            );

        closeBtn.addActionListener(e -> {

            int confirm =
                JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to close this job position?",
                    "Confirm Close",
                    JOptionPane.YES_NO_OPTION
                );

            if (confirm == JOptionPane.YES_OPTION) {

                list.remove(card);

                list.revalidate();
                list.repaint();
            }
        });

        right.add(editBtn);
        right.add(closeBtn);

        card.add(
            info,
            BorderLayout.WEST
        );

        card.add(
            right,
            BorderLayout.EAST
        );

        list.add(card);

        list.add(
            Box.createVerticalStrut(12)
        );
    }

    // =========================================================
    // APPLICATIONS
    // =========================================================

    JPanel createApplications() {

        JPanel page = createPage(
            "Applications",
            "Review and process submitted student job applications."
        );

        JPanel list = createListPanel();

        addApplicationRow(
            list,
            "Abhishek Sadangi",
            "B.Tech CSE",
            "Software Engineer",
            "Under Review",
            BLUE
        );

        addApplicationRow(
            list,
            "Ishan Mishra",
            "B.Tech CSE",
            "System Engineer",
            "Under Review",
            BLUE
        );

        addApplicationRow(
            list,
            "Priya Sharma",
            "B.Tech CSE",
            "Software Engineer",
            "Screened",
            GREEN
        );

        addApplicationRow(
            list,
            "Rohan Verma",
            "B.Tech ECE",
            "System Engineer",
            "Pending Verification",
            ORANGE
        );

        addApplicationRow(
            list,
            "Ananya Das",
            "B.Tech IT",
            "Data Analyst",
            "Screened",
            GREEN
        );

        addApplicationRow(
            list,
            "Siddharth Rao",
            "B.Tech CSE",
            "Software Engineer",
            "Shortlisted",
            PURPLE
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // APPLICATION ROW
    // =========================================================

    void addApplicationRow(
        JPanel list,
        String student,
        String dept,
        String role,
        String status,
        Color statusColor
    ) {

        JPanel card =
            new JPanel(new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    BORDER,
                    2
                ),
                new EmptyBorder(
                    16,
                    20,
                    16,
                    20
                )
            )
        );

        card.setMaximumSize(
            new Dimension(1100, 80)
        );

        JPanel info =
            new JPanel(
                new GridLayout(2, 1, 0, 4)
            );

        info.setOpaque(false);

        info.add(
            label(
                student + " — " + role,
                TEXT,
                Font.BOLD,
                17
            )
        );

        info.add(
            label(
                dept,
                GRAY,
                Font.PLAIN,
                14
            )
        );

        JPanel right =
            new JPanel(
                new FlowLayout(
                    FlowLayout.RIGHT,
                    12,
                    2
                )
            );

        right.setOpaque(false);

        right.add(
            label(
                status,
                statusColor,
                Font.BOLD,
                14
            )
        );

        JButton resumeBtn =
            smallButton(
                "View Resume",
                PURPLE
            );

        resumeBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                "Fetching candidate resume for "
                    + student + "...",
                "Resume Viewer",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        JButton approveBtn =
            smallButton(
                "Approve",
                GREEN
            );

        approveBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                "Application for "
                    + student + " approved!",
                "Status Updated",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        right.add(resumeBtn);
        right.add(approveBtn);

        card.add(
            info,
            BorderLayout.CENTER
        );

        card.add(
            right,
            BorderLayout.EAST
        );

        list.add(card);

        list.add(
            Box.createVerticalStrut(10)
        );
    }

    // =========================================================
    // ELIGIBLE STUDENTS
    // =========================================================

    JPanel createEligibleStudents() {

        JPanel page = createPage(
            "Eligible Students",
            "System-filtered students meeting drive criteria (CGPA & Branch)."
        );

        JPanel list = createListPanel();

        addStudentRow(
            list,
            "Abhishek Sadangi",
            "B.Tech CSE",
            "9.4 CGPA",
            "Eligible for All Drives",
            GREEN
        );

        addStudentRow(
            list,
            "Ishan Mishra",
            "B.Tech CSE",
            "8.8 CGPA",
            "Eligible for All Drives",
            GREEN
        );

        addStudentRow(
            list,
            "Priya Sharma",
            "B.Tech CSE",
            "9.2 CGPA",
            "Eligible for All Drives",
            GREEN
        );

        addStudentRow(
            list,
            "Ananya Das",
            "B.Tech IT",
            "9.0 CGPA",
            "Eligible for Software Engineer",
            BLUE
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // STUDENT ROW
    // =========================================================

    void addStudentRow(
        JPanel list,
        String name,
        String dept,
        String cgpa,
        String tag,
        Color tagColor
    ) {

        JPanel card =
            new JPanel(new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    BORDER,
                    2
                ),
                new EmptyBorder(
                    16,
                    20,
                    16,
                    20
                )
            )
        );

        card.setMaximumSize(
            new Dimension(1100, 80)
        );

        JPanel info =
            new JPanel(
                new GridLayout(2, 1, 0, 4)
            );

        info.setOpaque(false);

        info.add(
            label(
                name + "  (" + cgpa + ")",
                TEXT,
                Font.BOLD,
                17
            )
        );

        info.add(
            label(
                dept + " • " + tag,
                tagColor,
                Font.BOLD,
                14
            )
        );

        JButton invite =
            smallButton(
                "Send Drive Invitation",
                BLUE
            );

        invite.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                "Invitation notification dispatched to "
                    + name,
                "Invite Sent",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        card.add(
            info,
            BorderLayout.CENTER
        );

        card.add(
            invite,
            BorderLayout.EAST
        );

        list.add(card);

        list.add(
            Box.createVerticalStrut(10)
        );
    }

    // =========================================================
    // SHORTLISTED STUDENTS
    // =========================================================

    JPanel createShortlistedStudents() {

        JPanel page = createPage(
            "Shortlisted Students",
            "Candidates selected for upcoming interview rounds."
        );

        JPanel grid =
            new JPanel(
                new GridLayout(3, 2, 18, 18)
            );

        grid.setOpaque(false);

        grid.add(
            infoCard(
                "Abhishek Sadangi",
                "Role: Software Engineer\n"
                + "Status: Tech Round 1 Cleared\n"
                + "Next Step: Technical Round 2",
                GREEN
            )
        );

        grid.add(
            infoCard(
                "Ishan Mishra",
                "Role: System Engineer\n"
                + "Status: Technical Round Cleared\n"
                + "Next Step: HR Interview",
                BLUE
            )
        );

        grid.add(
            infoCard(
                "Priya Sharma",
                "Role: Software Engineer\n"
                + "Status: Shortlisted for Tech Round 2\n"
                + "Next Step: Coding Assessment",
                GREEN
            )
        );

        grid.add(
            infoCard(
                "Ananya Das",
                "Role: Software Engineer\n"
                + "Status: Shortlisted for HR Round\n"
                + "Next Step: Final Interview",
                PURPLE
            )
        );

        page.add(
            grid,
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // INTERVIEW SCHEDULE
    // =========================================================

    JPanel createInterviewSchedule() {

        JPanel page = createPage(
            "Interview Schedule",
            "Manage interview slots, panel links, and timelines."
        );

        JPanel list = createListPanel();

        addScheduleRow(
            list,
            "Technical Round 2",
            "Abhishek Sadangi",
            "Today, 03:30 PM",
            "Panel 1 (Virtual)",
            GREEN
        );

        addScheduleRow(
            list,
            "Technical Round 1",
            "Ishan Mishra",
            "Tomorrow, 10:30 AM",
            "Panel 1 (Virtual)",
            BLUE
        );

        addScheduleRow(
            list,
            "HR Interview",
            "Ananya Das",
            "Tomorrow, 02:00 PM",
            "Panel HR (Room 302)",
            GREEN
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // SCHEDULE ROW
    // =========================================================

    void addScheduleRow(
        JPanel list,
        String round,
        String candidate,
        String time,
        String venue,
        Color color
    ) {

        JPanel card =
            new JPanel(new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    BORDER,
                    2
                ),
                new EmptyBorder(
                    16,
                    20,
                    16,
                    20
                )
            )
        );

        card.setMaximumSize(
            new Dimension(1100, 80)
        );

        JPanel info =
            new JPanel(
                new GridLayout(2, 1, 0, 4)
            );

        info.setOpaque(false);

        info.add(
            label(
                round +
                " — Candidate: " +
                candidate,
                TEXT,
                Font.BOLD,
                17
            )
        );

        info.add(
            label(
                "Time: " + time +
                " | Venue: " + venue,
                GRAY,
                Font.PLAIN,
                14
            )
        );

        JButton reschedule =
            smallButton(
                "Reschedule Slot",
                color
            );

        reschedule.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                "Opening reschedule calendar for "
                    + candidate,
                "Reschedule",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        card.add(
            info,
            BorderLayout.CENTER
        );

        card.add(
            reschedule,
            BorderLayout.EAST
        );

        list.add(card);

        list.add(
            Box.createVerticalStrut(10)
        );
    }

    // =========================================================
    // RESULTS
    // =========================================================

    JPanel createResults() {

        JPanel page = createPage(
            "Results",
            "Publish placement results and manage formal job offers."
        );

        JPanel list = createListPanel();

        addResultRow(
            list,
            "Abhishek Sadangi",
            "Software Engineer",
            "12 LPA",
            "OFFER EXTENDED",
            GREEN
        );

        addResultRow(
            list,
            "Priya Sharma",
            "Software Engineer",
            "12 LPA",
            "OFFER EXTENDED",
            GREEN
        );

        addResultRow(
            list,
            "Ananya Das",
            "Software Engineer",
            "12 LPA",
            "OFFER ACCEPTED",
            BLUE
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // RESULT ROW
    // =========================================================

    void addResultRow(
        JPanel list,
        String candidate,
        String role,
        String pkg,
        String result,
        Color color
    ) {

        JPanel card =
            new JPanel(new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    BORDER,
                    2
                ),
                new EmptyBorder(
                    16,
                    20,
                    16,
                    20
                )
            )
        );

        card.setMaximumSize(
            new Dimension(1100, 80)
        );

        JPanel info =
            new JPanel(
                new GridLayout(2, 1, 0, 4)
            );

        info.setOpaque(false);

        info.add(
            label(
                candidate +
                " — " +
                role +
                " (" +
                pkg +
                ")",
                TEXT,
                Font.BOLD,
                17
            )
        );

        info.add(
            label(
                "Final Decision Status: " +
                result,
                color,
                Font.BOLD,
                14
            )
        );

        JButton offerBtn =
            smallButton(
                "Generate Offer Letter",
                color
            );

        offerBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                "Generating PDF Offer Letter for "
                    + candidate + "...",
                "Generate Document",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        card.add(
            info,
            BorderLayout.CENTER
        );

        card.add(
            offerBtn,
            BorderLayout.EAST
        );

        list.add(card);

        list.add(
            Box.createVerticalStrut(10)
        );
    }

    // =========================================================
    // NOTIFICATIONS
    // =========================================================

    JPanel createNotifications() {

        JPanel page = createPage(
            "Notifications",
            "Broadcast updates and drive notifications to applicants."
        );

        JPanel list = createListPanel();

        addNotificationRow(
            list,
            "System Engineer Interview Schedule Released",
            "Sent to 24 Shortlisted Candidates",
            "BROADCASTED",
            BLUE
        );

        addNotificationRow(
            list,
            "Document Verification Deadline Reminder",
            "Sent to All Applicants",
            "SENT",
            GREEN
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // NOTIFICATION ROW
    // =========================================================

    void addNotificationRow(
        JPanel list,
        String title,
        String target,
        String status,
        Color color
    ) {

        JPanel card =
            new JPanel(new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    BORDER,
                    2
                ),
                new EmptyBorder(
                    16,
                    20,
                    16,
                    20
                )
            )
        );

        card.setMaximumSize(
            new Dimension(1100, 80)
        );

        JPanel info =
            new JPanel(
                new GridLayout(2, 1, 0, 4)
            );

        info.setOpaque(false);

        info.add(
            label(
                title,
                TEXT,
                Font.BOLD,
                17
            )
        );

        info.add(
            label(
                "Target: " + target,
                GRAY,
                Font.PLAIN,
                14
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
                14
            ),
            BorderLayout.EAST
        );

        list.add(card);

        list.add(
            Box.createVerticalStrut(10)
        );
    }

    // =========================================================
    // REPORTS
    // =========================================================

    JPanel createReports() {

        JPanel page = createPage(
            "Reports",
            "Generate placement drive analytics and download summary reports."
        );

        JPanel grid =
            new JPanel(
                new GridLayout(2, 2, 20, 20)
            );

        grid.setOpaque(false);

        grid.add(
            reportCard(
                "Drive Summary Report",
                "Export candidate status breakdown and selections across departments.",
                "Export PDF",
                BLUE
            )
        );

        grid.add(
            reportCard(
                "Applicant Analytics",
                "Export department-wise and CGPA-wise statistical reports.",
                "Export CSV",
                GREEN
            )
        );

        page.add(
            grid,
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // REPORT CARD
    // =========================================================

    JPanel reportCard(
        String title,
        String desc,
        String btnText,
        Color color
    ) {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(0, 12)
        );

        card.setBorder(
            new EmptyBorder(
                20,
                20,
                20,
                20
            )
        );

        card.add(
            label(
                title,
                color,
                Font.BOLD,
                18
            ),
            BorderLayout.NORTH
        );

        JTextArea area =
            new JTextArea(desc);

        area.setEditable(false);
        area.setOpaque(false);
        area.setForeground(GRAY);

        area.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                14
            )
        );

        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JButton exportBtn =
            smallButton(
                btnText,
                color
            );

        exportBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                "Exporting file for: " + title,
                "Export Started",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        card.add(
            area,
            BorderLayout.CENTER
        );

        card.add(
            exportBtn,
            BorderLayout.SOUTH
        );

        return card;
    }

    // =========================================================
    // SETTINGS
    // =========================================================

    JPanel createSettings() {

        JPanel page = createPage(
            "Settings",
            "Manage recruiter user account settings and system preferences."
        );

        JPanel grid =
            new JPanel(
                new GridLayout(2, 2, 20, 20)
            );

        grid.setOpaque(false);

        grid.add(
            infoCard(
                "Recruiter Account",
                "User: TCS Recruitment Admin\n"
                + "Email: campus@tcs.com\n"
                + "Role: Corporate Administrator",
                BLUE
            )
        );

        grid.add(
            infoCard(
                "Portal Configuration",
                "HireSphere Corporate System Version: v2.6\n"
                + "Environment: Production Server",
                PURPLE
            )
        );

        page.add(
            grid,
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // CREATE PAGE
    // =========================================================

    JPanel createPage(
        String heading,
        String subtitle
    ) {

        JPanel page =
            new JPanel(
                new BorderLayout(18, 18)
            );

        page.setBackground(BG);

        page.setBorder(
            new EmptyBorder(
                22,
                30,
                22,
                25
            )
        );

        JPanel top =
            new JPanel(
                new GridLayout(2, 1, 0, 4)
            );

        top.setOpaque(false);

        top.add(
            label(
                heading,
                TEXT,
                Font.BOLD,
                28
            )
        );

        top.add(
            label(
                subtitle,
                GRAY,
                Font.PLAIN,
                15
            )
        );

        page.add(
            top,
            BorderLayout.NORTH
        );

        return page;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    JPanel modernStatCard(
        String title,
        String value,
        String subtext,
        Color accent
    ) {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(10, 6)
        );

        card.setBorder(
            new EmptyBorder(
                16,
                20,
                16,
                20
            )
        );

        JPanel top =
            new JPanel(new BorderLayout());

        top.setOpaque(false);

        top.add(
            label(
                title,
                GRAY,
                Font.BOLD,
                14
            ),
            BorderLayout.WEST
        );

        JPanel pill = new JPanel();

        pill.setPreferredSize(
            new Dimension(14, 14)
        );

        pill.setBackground(accent);

        top.add(
            pill,
            BorderLayout.EAST
        );

        JLabel valLabel =
            label(
                value,
                TEXT,
                Font.BOLD,
                32
            );

        JLabel subLabel =
            label(
                subtext,
                accent,
                Font.BOLD,
                13
            );

        card.add(
            top,
            BorderLayout.NORTH
        );

        card.add(
            valLabel,
            BorderLayout.CENTER
        );

        card.add(
            subLabel,
            BorderLayout.SOUTH
        );

        return card;
    }

    // =========================================================
    // DRIVE PROGRESS
    // =========================================================

    JPanel modernDriveProgressCard() {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(14, 14)
        );

        card.setBorder(
            new EmptyBorder(
                22,
                24,
                22,
                24
            )
        );

        JPanel header =
            new JPanel(new BorderLayout());

        header.setOpaque(false);

        header.add(
            label(
                "Drive Execution Status",
                TEXT,
                Font.BOLD,
                18
            ),
            BorderLayout.WEST
        );

        header.add(
            label(
                "70% Completed",
                BLUE,
                Font.BOLD,
                16
            ),
            BorderLayout.EAST
        );

        JProgressBar progress =
            new JProgressBar(0, 100);

        progress.setValue(70);
        progress.setStringPainted(false);

        progress.setForeground(BLUE);
        progress.setBackground(BORDER);

        progress.setPreferredSize(
            new Dimension(0, 14)
        );

        JPanel milestones =
            new JPanel(
                new GridLayout(2, 2, 12, 12)
            );

        milestones.setOpaque(false);

        milestones.add(
            statusPill(
                "✓ Job Posting Published",
                GREEN
            )
        );

        milestones.add(
            statusPill(
                "✓ Resume Shortlisting",
                GREEN
            )
        );

        milestones.add(
            statusPill(
                "⏳ Tech Round 1 Active",
                ORANGE
            )
        );

        milestones.add(
            statusPill(
                "○ HR Interviews Pending",
                GRAY
            )
        );

        JPanel centerBody =
            new JPanel(
                new BorderLayout(0, 14)
            );

        centerBody.setOpaque(false);

        centerBody.add(
            progress,
            BorderLayout.NORTH
        );

        centerBody.add(
            milestones,
            BorderLayout.CENTER
        );

        card.add(
            header,
            BorderLayout.NORTH
        );

        card.add(
            centerBody,
            BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // UPCOMING INTERVIEW
    // =========================================================

    JPanel modernUpcomingInterviewsCard() {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(14, 14)
        );

        card.setBorder(
            new EmptyBorder(
                22,
                24,
                22,
                24
            )
        );

        JPanel header =
            new JPanel(new BorderLayout());

        header.setOpaque(false);

        header.add(
            label(
                "Next Scheduled Candidate",
                TEXT,
                Font.BOLD,
                18
            ),
            BorderLayout.WEST
        );

        header.add(
            createBadge(
                "UPCOMING",
                PURPLE
            ),
            BorderLayout.EAST
        );

        JPanel candidateRow =
            new JPanel(
                new BorderLayout(16, 0)
            );

        candidateRow.setOpaque(false);

        JLabel avatar =
            label(
                "IM",
                Color.WHITE,
                Font.BOLD,
                16
            );

        avatar.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        avatar.setOpaque(true);
        avatar.setBackground(PURPLE);

        avatar.setPreferredSize(
            new Dimension(54, 54)
        );

        JPanel info =
            new JPanel(
                new GridLayout(3, 1, 0, 3)
            );

        info.setOpaque(false);

        info.add(
            label(
                "Ishan Mishra",
                TEXT,
                Font.BOLD,
                17
            )
        );

        info.add(
            label(
                "System Engineer — Technical Round 1",
                GRAY,
                Font.PLAIN,
                14
            )
        );

        info.add(
            label(
                "📅 Tomorrow at 10:30 AM  •  Panel 1",
                BLUE,
                Font.BOLD,
                13
            )
        );

        candidateRow.add(
            avatar,
            BorderLayout.WEST
        );

        candidateRow.add(
            info,
            BorderLayout.CENTER
        );

        JButton actionBtn =
            smallButton(
                "Launch Interview Slot",
                BLUE
            );

        actionBtn.addActionListener(e -> {

            cards.show(
                content,
                "Interview Schedule"
            );

            pageTitle.setText(
                "Interview Schedule"
            );
        });

        card.add(
            header,
            BorderLayout.NORTH
        );

        card.add(
            candidateRow,
            BorderLayout.CENTER
        );

        card.add(
            actionBtn,
            BorderLayout.SOUTH
        );

        return card;
    }

    // =========================================================
    // BADGE
    // =========================================================

    JLabel createBadge(
        String text,
        Color color
    ) {

        JLabel l =
            new JLabel(" " + text + " ");

        l.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                12
            )
        );

        l.setForeground(color);

        l.setOpaque(true);

        l.setBackground(
            new Color(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                45
            )
        );

        return l;
    }

    // =========================================================
    // STATUS PILL
    // =========================================================

    JLabel statusPill(
        String text,
        Color color
    ) {

        JLabel l =
            new JLabel(text);

        l.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                13
            )
        );

        l.setForeground(color);

        return l;
    }

    // =========================================================
    // INFO CARD
    // =========================================================

    JPanel infoCard(
        String title,
        String text,
        Color color
    ) {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(0, 10)
        );

        card.setBorder(
            new EmptyBorder(
                20,
                20,
                20,
                20
            )
        );

        card.add(
            label(
                title,
                color,
                Font.BOLD,
                18
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
                14
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

    // =========================================================
    // LIST PANEL
    // =========================================================

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

    // =========================================================
    // SCROLL
    // =========================================================

    JScrollPane scroll(
        javax.swing.JComponent component
    ) {

        JScrollPane scroll =
            new JScrollPane(component);

        scroll.setBorder(null);

        scroll.setOpaque(false);

        scroll.getViewport()
              .setOpaque(false);

        scroll.getVerticalScrollBar()
              .setUnitIncrement(16);

        return scroll;
    }

    // =========================================================
    // SMALL BUTTON
    // =========================================================

    JButton smallButton(
        String text,
        Color color
    ) {

        JButton b =
            new JButton(text);

        b.setBackground(color);
        b.setForeground(Color.WHITE);

        b.setFocusPainted(false);

        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);

        b.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                13
            )
        );

        b.setBorder(
            new EmptyBorder(
                8,
                16,
                8,
                16
            )
        );

        b.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        return b;
    }

    // =========================================================
    // IMAGE LOADER
    // =========================================================

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

    // =========================================================
    // LABEL
    // =========================================================

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

    // =========================================================
    // GREETING
    // =========================================================

    String getGreeting() {

        int hour =
            Integer.parseInt(
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

    // =========================================================
    // UPDATE TIME
    // =========================================================

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

    // =========================================================
    // ROUNDED PANEL
    // =========================================================

    static class RoundedPanel extends JPanel {

        RoundedPanel() {

            setOpaque(false);

            setBackground(
                Color.WHITE
            );
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

            g2.setColor(BORDER);

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

    // =========================================================
    // GRADIENT PANEL
    // =========================================================

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
        protected void paintComponent(
            Graphics g
        ) {

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

    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new CompanyDashboard()
        );
    }
}