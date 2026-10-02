package AdminDashboard;

import Home.HomePage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AdminDashboard extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

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
    // MENU
    // =========================================================

    String[] pages = {
        "Dashboard",
        "Students",
        "Companies",
        "Placement Drives",
        "Announcements",
        "Documents",
        "Reports",
        "Settings"
    };

    String[] icons = {
        "D",
        "S",
        "C",
        "P",
        "A",
        "R",
        "T",
        "T"
    };

    // =========================================================
    // TABLE MODELS
    // =========================================================

    DefaultTableModel studentModel;
    DefaultTableModel companyModel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AdminDashboard() {

        setTitle("HireSphere - Admin Dashboard");
        setSize(1366, 768);
        setMinimumSize(new Dimension(1100, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createSidebar(), BorderLayout.WEST);

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(BG);

        right.add(createHeader(), BorderLayout.NORTH);

        content.setBackground(BG);

        content.add(createDashboard(), "Dashboard");
        content.add(createStudents(), "Students");
        content.add(createCompanies(), "Companies");
        content.add(createPlacementDrives(), "Placement Drives");
        content.add(createAnnouncements(), "Announcements");
        content.add(createDocuments(), "Documents");
        content.add(createReports(), "Reports");
        content.add(createSettings(), "Settings");

        right.add(content, BorderLayout.CENTER);

        add(right, BorderLayout.CENTER);

        new Timer(1000, e -> updateTime()).start();

        updateTime();

        setVisible(true);
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    JPanel createSidebar() {

        GradientPanel side = new GradientPanel(NAVY, NAVY2);

        side.setPreferredSize(new Dimension(235, 768));
        side.setLayout(new BorderLayout());

        // Logo
        JPanel logoPanel = new JPanel(new BorderLayout(10, 5));
        logoPanel.setOpaque(false);
        logoPanel.setBorder(new EmptyBorder(20, 18, 15, 12));

        JLabel logoImage = image("Logo.png", 48, 48);

        JPanel logoText = new JPanel(new GridLayout(2, 1));
        logoText.setOpaque(false);

        JLabel name = label(
            "HireSphere",
            Color.WHITE,
            Font.BOLD,
            24
        );

        JLabel tagline = label(
            "ADMINISTRATION PORTAL",
            new Color(165, 185, 220),
            Font.PLAIN,
            8
        );

        logoText.add(name);
        logoText.add(tagline);

        logoPanel.add(logoImage, BorderLayout.WEST);
        logoPanel.add(logoText, BorderLayout.CENTER);

        side.add(logoPanel, BorderLayout.NORTH);

        // Menu
        JPanel menu = new JPanel(
            new GridLayout(pages.length + 1, 1, 0, 6)
        );

        menu.setOpaque(false);
        menu.setBorder(new EmptyBorder(8, 12, 8, 12));

        for (int i = 0; i < pages.length; i++) {

            JButton b = createMenuButton(
                icons[i] + "   " + pages[i]
            );

            final int index = i;

            if (i == 0) {
                activeButton = b;
                setButtonColor(b, BLUE);
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

        // Footer
        JLabel footer = label(
            "HireSphere  |  Admin Portal",
            new Color(150, 170, 205),
            Font.PLAIN,
            10
        );

        JPanel footerPanel = new JPanel(new BorderLayout());

        footerPanel.setOpaque(false);
        footerPanel.setBorder(
            new EmptyBorder(8, 20, 18, 10)
        );

        footerPanel.add(
            footer,
            BorderLayout.WEST
        );

        side.add(footerPanel, BorderLayout.SOUTH);

        return side;
    }

    // =========================================================
    // MENU BUTTON
    // =========================================================

    JButton createMenuButton(String text) {

        JButton b = new JButton(text);

        b.setForeground(Color.WHITE);
        b.setBackground(new Color(30, 50, 90));

        // IMPORTANT FIX FOR BUTTON COLORS
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);

        b.setBorder(
            new EmptyBorder(10, 14, 10, 8)
        );

        b.setHorizontalAlignment(
            SwingConstants.LEFT
        );

        b.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                13
            )
        );

        b.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        return b;
    }

    // =========================================================
    // BUTTON COLOR FIX
    // =========================================================

    void setButtonColor(JButton button, Color color) {

        button.setBackground(color);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    // =========================================================
    // PAGE SWITCH
    // =========================================================

    void showPage(String page, JButton button) {

        if (activeButton != null) {

            setButtonColor(
                activeButton,
                new Color(30, 50, 90)
            );
        }

        activeButton = button;

        setButtonColor(
            activeButton,
            BLUE
        );

        cards.show(content, page);

        pageTitle.setText(
            page.equals("Dashboard")
                ? "Admin Dashboard"
                : page
        );
    }

    // =========================================================
    // HEADER
    // =========================================================

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

        // Left
        JPanel left = new JPanel(
            new GridLayout(2, 1)
        );

        left.setOpaque(false);

        pageTitle = label(
            "Admin Dashboard",
            Color.WHITE,
            Font.BOLD,
            25
        );

        left.add(pageTitle);

        left.add(
            label(
                "Manage HireSphere placement activities",
                new Color(148, 163, 184),
                Font.PLAIN,
                12
            )
        );

        // Right
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

        setButtonColor(
            notification,
            new Color(30, 41, 59)
        );

        notification.setForeground(Color.WHITE);

        right.add(notification);

        // Avatar
        JLabel avatar = label(
            "AD",
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
                "Welcome, Admin",
                Color.WHITE,
                Font.BOLD,
                13
            )
        );

        user.add(
            label(
                "Administrator",
                new Color(148, 163, 184),
                Font.PLAIN,
                11
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

    // =========================================================
    // DASHBOARD
    // =========================================================

    JPanel createDashboard() {

        JPanel page = new JPanel(
            new BorderLayout()
        );

        page.setBackground(BG);

        page.setBorder(
            new EmptyBorder(20, 28, 20, 22)
        );

        JPanel all = new JPanel();

        all.setOpaque(false);

        all.setLayout(
            new BoxLayout(
                all,
                BoxLayout.Y_AXIS
            )
        );

        // Welcome
        JPanel welcome = new JPanel(
            new BorderLayout()
        );

        welcome.setBackground(
            new Color(239, 246, 255)
        );

        welcome.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(191, 219, 254)
                ),
                new EmptyBorder(
                    15,
                    20,
                    15,
                    20
                )
            )
        );

        JPanel welcomeText = new JPanel(
            new GridLayout(2, 1)
        );

        welcomeText.setOpaque(false);

        welcomeText.add(
            label(
                getGreeting() + ", Admin",
                NAVY,
                Font.BOLD,
                22
            )
        );

        welcomeText.add(
            label(
                "Here's your placement management overview.",
                GRAY,
                Font.PLAIN,
                12
            )
        );

        JButton manage = smallButton(
            "Manage Students ->",
            BLUE
        );

        manage.addActionListener(e -> {

            cards.show(content, "Students");

            pageTitle.setText("Students");
        });

        welcome.add(
            welcomeText,
            BorderLayout.CENTER
        );

        welcome.add(
            manage,
            BorderLayout.EAST
        );

        all.add(welcome);

        all.add(
            Box.createVerticalStrut(15)
        );

        // Statistics
        JPanel stats = new JPanel(
            new GridLayout(1, 4, 12, 12)
        );

        stats.setOpaque(false);

        stats.add(
            statCard(
                "Total Students",
                "248",
                "Registered",
                BLUE
            )
        );

        stats.add(
            statCard(
                "Companies",
                "32",
                "Active recruiters",
                GREEN
            )
        );

        stats.add(
            statCard(
                "Applications",
                "684",
                "Submitted",
                PURPLE
            )
        );

        stats.add(
            statCard(
                "Placed Students",
                "76",
                "Successfully placed",
                ORANGE
            )
        );

        all.add(stats);

        all.add(
            Box.createVerticalStrut(15)
        );

        // Middle
        JPanel middle = new JPanel(
            new GridLayout(1, 2, 15, 15)
        );

        middle.setOpaque(false);

        middle.add(
            placementOverviewCard()
        );

        middle.add(
            recentActivityCard()
        );

        all.add(middle);

        all.add(
            Box.createVerticalStrut(15)
        );

        // Lower
        JPanel lower = new JPanel(
            new GridLayout(1, 2, 15, 15)
        );

        lower.setOpaque(false);

        lower.add(
            quickActionsCard()
        );

        lower.add(
            announcementsPreview()
        );

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

    // =========================================================
    // STAT CARD
    // =========================================================

    JPanel statCard(
        String title,
        String value,
        String small,
        Color color
    ) {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout()
        );

        card.setBorder(
            new EmptyBorder(
                14,
                16,
                14,
                16
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

    // =========================================================
    // PLACEMENT OVERVIEW
    // =========================================================

    JPanel placementOverviewCard() {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(10, 10)
        );

        card.setBorder(
            new EmptyBorder(
                16,
                18,
                16,
                18
            )
        );

        card.add(
            label(
                "Placement Overview",
                TEXT,
                Font.BOLD,
                16
            ),
            BorderLayout.NORTH
        );

        JPanel center = new JPanel(
            new GridLayout(4, 1, 0, 7)
        );

        center.setOpaque(false);

        center.add(
            progressRow(
                "Profile Completion",
                92,
                BLUE
            )
        );

        center.add(
            progressRow(
                "Resume Upload",
                85,
                GREEN
            )
        );

        center.add(
            progressRow(
                "Applications",
                68,
                PURPLE
            )
        );

        center.add(
            progressRow(
                "Placement",
                42,
                ORANGE
            )
        );

        card.add(
            center,
            BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // PROGRESS ROW
    // =========================================================

    JPanel progressRow(
        String title,
        int value,
        Color color
    ) {

        JPanel panel = new JPanel(
            new BorderLayout(8, 3)
        );

        panel.setOpaque(false);

        JPanel top = new JPanel(
            new BorderLayout()
        );

        top.setOpaque(false);

        top.add(
            label(
                title,
                GRAY,
                Font.PLAIN,
                10
            ),
            BorderLayout.WEST
        );

        top.add(
            label(
                value + "%",
                color,
                Font.BOLD,
                10
            ),
            BorderLayout.EAST
        );

        panel.add(
            top,
            BorderLayout.NORTH
        );

        JProgressBar bar = new JProgressBar(
            0,
            100
        );

        bar.setValue(value);

        bar.setForeground(color);

        bar.setBackground(
            new Color(226, 232, 240)
        );

        bar.setBorderPainted(false);

        panel.add(
            bar,
            BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================================
    // RECENT ACTIVITY
    // =========================================================

    JPanel recentActivityCard() {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(0, 8)
        );

        card.setBorder(
            new EmptyBorder(
                15,
                18,
                15,
                18
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
            new GridLayout(5, 1, 0, 5)
        );

        list.setOpaque(false);

        list.add(
            label(
                "+  12 new students registered",
                GREEN,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "+  TCS placement drive added",
                BLUE,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "*  18 students shortlisted",
                PURPLE,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "!  5 resumes waiting for verification",
                ORANGE,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "-  Wipro registration deadline approaching",
                RED,
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

    // =========================================================
    // QUICK ACTIONS
    // =========================================================

    JPanel quickActionsCard() {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new GridLayout(4, 1, 8, 8)
        );

        card.setBorder(
            new EmptyBorder(
                15,
                18,
                15,
                18
            )
        );

        card.add(
            label(
                "Quick Actions",
                TEXT,
                Font.BOLD,
                16
            )
        );

        JButton addStudent = smallButton(
            "Add Student",
            BLUE
        );

        addStudent.addActionListener(
            e -> addStudentDialog()
        );

        card.add(addStudent);

        JButton addCompany = smallButton(
            "Add Company",
            GREEN
        );

        addCompany.addActionListener(
            e -> addCompanyDialog()
        );

        card.add(addCompany);

        JButton announce = smallButton(
            "Create Announcement",
            PURPLE
        );

        announce.addActionListener(
            e -> announcementDialog()
        );

        card.add(announce);

        return card;
    }

    // =========================================================
    // ANNOUNCEMENTS PREVIEW
    // =========================================================

    JPanel announcementsPreview() {

        RoundedPanel card = new RoundedPanel();

        card.setLayout(
            new BorderLayout(0, 8)
        );

        card.setBorder(
            new EmptyBorder(
                15,
                18,
                15,
                18
            )
        );

        card.add(
            label(
                "Latest Announcements",
                TEXT,
                Font.BOLD,
                16
            ),
            BorderLayout.NORTH
        );

        JPanel list = new JPanel(
            new GridLayout(3, 1, 0, 7)
        );

        list.setOpaque(false);

        list.add(
            label(
                "- TCS campus drive registration is open.",
                BLUE,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "- Resume verification deadline: Friday.",
                ORANGE,
                Font.PLAIN,
                11
            )
        );

        list.add(
            label(
                "- Interview schedule has been updated.",
                GREEN,
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

    // =========================================================
    // STUDENTS PAGE
    // =========================================================

    JPanel createStudents() {

        JPanel page = createPage(
            "Student Management",
            "View, search and manage registered students."
        );

        JPanel main = new JPanel(
            new BorderLayout(0, 15)
        );

        main.setOpaque(false);

        JPanel top = new JPanel(
            new BorderLayout()
        );

        top.setOpaque(false);

        JTextField search = new JTextField();

        search.setPreferredSize(
            new Dimension(300, 35)
        );

        search.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(
                    5,
                    10,
                    5,
                    10
                )
            )
        );

        top.add(
            search,
            BorderLayout.WEST
        );

        JPanel buttons = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT,
                8,
                0
            )
        );

        buttons.setOpaque(false);

        JButton add = smallButton(
            "Add Student",
            BLUE
        );

        add.addActionListener(
            e -> addStudentDialog()
        );

        JButton delete = smallButton(
            "Delete Student",
            RED
        );

        delete.addActionListener(
            e -> deleteStudent()
        );

        buttons.add(add);
        buttons.add(delete);

        top.add(
            buttons,
            BorderLayout.EAST
        );

        main.add(
            top,
            BorderLayout.NORTH
        );

        String[] columns = {
            "ID",
            "Name",
            "Email",
            "Course",
            "Year",
            "Resume",
            "Status"
        };

        studentModel = new DefaultTableModel(
            columns,
            0
        ) {
            @Override
            public boolean isCellEditable(
                int row,
                int column
            ) {
                return false;
            }
        };

        studentModel.addRow(
            new Object[] {
                "ST001",
                "Ishan Mishra",
                "ishan@example.com",
                "B.Tech CSE",
                "2nd",
                "Uploaded",
                "Verified"
            }
        );

        studentModel.addRow(
            new Object[] {
                "ST002",
                "Rahul Das",
                "rahul@example.com",
                "B.Tech CSE",
                "2nd",
                "Uploaded",
                "Verified"
            }
        );

        studentModel.addRow(
            new Object[] {
                "ST003",
                "Ananya Singh",
                "ananya@example.com",
                "B.Tech IT",
                "3rd",
                "Pending",
                "Pending"
            }
        );

        studentModel.addRow(
            new Object[] {
                "ST004",
                "Riya Patel",
                "riya@example.com",
                "B.Tech CSE",
                "4th",
                "Uploaded",
                "Verified"
            }
        );

        JTable table = new JTable(studentModel);

        styleTable(table);

        search.getDocument().addDocumentListener(
            new DocumentListener() {

                public void insertUpdate(
                    DocumentEvent e
                ) {
                    filterStudents(
                        search.getText(),
                        table
                    );
                }

                public void removeUpdate(
                    DocumentEvent e
                ) {
                    filterStudents(
                        search.getText(),
                        table
                    );
                }

                public void changedUpdate(
                    DocumentEvent e
                ) {
                    filterStudents(
                        search.getText(),
                        table
                    );
                }
            }
        );

        main.add(
            new JScrollPane(table),
            BorderLayout.CENTER
        );

        page.add(
            main,
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // FILTER STUDENTS
    // =========================================================

    void filterStudents(
        String text,
        JTable table
    ) {

        TableRowSorter<DefaultTableModel> sorter =
            new TableRowSorter<>(studentModel);

        table.setRowSorter(sorter);

        if (text.trim().isEmpty()) {

            sorter.setRowFilter(null);

        } else {

            try {

                sorter.setRowFilter(
                    RowFilter.regexFilter(
                        "(?i)" + text
                    )
                );

            } catch (Exception e) {

                sorter.setRowFilter(null);
            }
        }
    }

    // =========================================================
    // ADD STUDENT
    // =========================================================

    void addStudentDialog() {

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JTextField course = new JTextField();

        JComboBox<String> year =
            new JComboBox<>(
                new String[] {
                    "1st",
                    "2nd",
                    "3rd",
                    "4th"
                }
            );

        JComboBox<String> resumeBox =
            new JComboBox<>(
                new String[] {
                    "Uploaded",
                    "Pending"
                }
            );

        JPanel panel = new JPanel(
            new GridLayout(6, 2, 8, 8)
        );

        panel.add(new JLabel("Student ID:"));
        panel.add(id);

        panel.add(new JLabel("Name:"));
        panel.add(name);

        panel.add(new JLabel("Email:"));
        panel.add(email);

        panel.add(new JLabel("Course:"));
        panel.add(course);

        panel.add(new JLabel("Year:"));
        panel.add(year);

        panel.add(new JLabel("Resume:"));
        panel.add(resumeBox);

        int result =
            JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Student",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

        if (result == JOptionPane.OK_OPTION) {

            if (
                id.getText().trim().isEmpty()
                ||
                name.getText().trim().isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                    this,
                    "Student ID and Name are required.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String status =
                resumeBox.getSelectedItem()
                    .equals("Uploaded")
                    ? "Verified"
                    : "Pending";

            studentModel.addRow(
                new Object[] {
                    id.getText(),
                    name.getText(),
                    email.getText(),
                    course.getText(),
                    year.getSelectedItem(),
                    resumeBox.getSelectedItem(),
                    status
                }
            );

            JOptionPane.showMessageDialog(
                this,
                "Student added successfully!",
                "HireSphere",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // =========================================================
    // DELETE STUDENT
    // =========================================================

    void deleteStudent() {

        JTable table = getStudentTable();

        if (table == null) {

            JOptionPane.showMessageDialog(
                this,
                "Student table not found."
            );

            return;
        }

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a student first.",
                "Delete Student",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int modelRow =
            table.convertRowIndexToModel(row);

        int answer =
            JOptionPane.showConfirmDialog(
                this,
                "Delete selected student?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

        if (answer == JOptionPane.YES_OPTION) {

            studentModel.removeRow(modelRow);
        }
    }

    // =========================================================
    // GET STUDENT TABLE
    // =========================================================

    JTable getStudentTable() {

        for (java.awt.Component component :
            content.getComponents()) {

            if (component instanceof JPanel) {

                JTable table =
                    findTableInPanel(
                        (JPanel) component,
                        studentModel
                    );

                if (table != null) {
                    return table;
                }
            }
        }

        return null;
    }

    // =========================================================
    // FIND TABLE
    // =========================================================

    JTable findTableInPanel(
        JPanel panel,
        DefaultTableModel model
    ) {

        for (java.awt.Component c :
            panel.getComponents()) {

            if (c instanceof JScrollPane) {

                JScrollPane sp =
                    (JScrollPane) c;

                java.awt.Component view =
                    sp.getViewport().getView();

                if (view instanceof JTable) {

                    JTable table =
                        (JTable) view;

                    if (table.getModel() == model) {
                        return table;
                    }
                }
            }

            if (c instanceof JPanel) {

                JTable table =
                    findTableInPanel(
                        (JPanel) c,
                        model
                    );

                if (table != null) {
                    return table;
                }
            }
        }

        return null;
    }

    // =========================================================
    // COMPANY PAGE
    // =========================================================

    JPanel createCompanies() {

        JPanel page = createPage(
            "Company Management",
            "Manage companies participating in campus placements."
        );

        JPanel main = new JPanel(
            new BorderLayout(0, 15)
        );

        main.setOpaque(false);

        JPanel top = new JPanel(
            new BorderLayout()
        );

        top.setOpaque(false);

        JTextField search = new JTextField();

        search.setPreferredSize(
            new Dimension(300, 35)
        );

        search.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(
                    5,
                    10,
                    5,
                    10
                )
            )
        );

        top.add(
            search,
            BorderLayout.WEST
        );

        JPanel buttons = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT,
                8,
                0
            )
        );

        buttons.setOpaque(false);

        JButton add = smallButton(
            "Add Company",
            GREEN
        );

        add.addActionListener(
            e -> addCompanyDialog()
        );

        JButton delete = smallButton(
            "Delete Company",
            RED
        );

        delete.addActionListener(
            e -> deleteCompany()
        );

        buttons.add(add);
        buttons.add(delete);

        top.add(
            buttons,
            BorderLayout.EAST
        );

        main.add(
            top,
            BorderLayout.NORTH
        );

        String[] columns = {
            "ID",
            "Company",
            "Role",
            "Package",
            "Drive Date",
            "Students",
            "Status"
        };

        companyModel = new DefaultTableModel(
            columns,
            0
        ) {
            @Override
            public boolean isCellEditable(
                int row,
                int column
            ) {
                return false;
            }
        };

        companyModel.addRow(
            new Object[] {
                "CO001",
                "TCS",
                "System Engineer",
                "12 LPA",
                "25 Aug 2026",
                "120",
                "Active"
            }
        );

        companyModel.addRow(
            new Object[] {
                "CO002",
                "Infosys",
                "Digital Specialist",
                "9 LPA",
                "28 Aug 2026",
                "95",
                "Active"
            }
        );

        companyModel.addRow(
            new Object[] {
                "CO003",
                "Wipro",
                "Project Engineer",
                "8 LPA",
                "02 Sep 2026",
                "80",
                "Active"
            }
        );

        companyModel.addRow(
            new Object[] {
                "CO004",
                "Accenture",
                "Software Engineer",
                "10 LPA",
                "05 Sep 2026",
                "75",
                "Upcoming"
            }
        );

        JTable table =
            new JTable(companyModel);

        styleTable(table);

        search.getDocument().addDocumentListener(
            new DocumentListener() {

                public void insertUpdate(
                    DocumentEvent e
                ) {
                    filterCompanies(
                        search.getText(),
                        table
                    );
                }

                public void removeUpdate(
                    DocumentEvent e
                ) {
                    filterCompanies(
                        search.getText(),
                        table
                    );
                }

                public void changedUpdate(
                    DocumentEvent e
                ) {
                    filterCompanies(
                        search.getText(),
                        table
                    );
                }
            }
        );

        main.add(
            new JScrollPane(table),
            BorderLayout.CENTER
        );

        page.add(
            main,
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // FILTER COMPANIES
    // =========================================================

    void filterCompanies(
        String text,
        JTable table
    ) {

        TableRowSorter<DefaultTableModel> sorter =
            new TableRowSorter<>(companyModel);

        table.setRowSorter(sorter);

        if (text.trim().isEmpty()) {

            sorter.setRowFilter(null);

        } else {

            try {

                sorter.setRowFilter(
                    RowFilter.regexFilter(
                        "(?i)" + text
                    )
                );

            } catch (Exception e) {

                sorter.setRowFilter(null);
            }
        }
    }

    // =========================================================
    // ADD COMPANY
    // =========================================================

    void addCompanyDialog() {

        JTextField id = new JTextField();
        JTextField company = new JTextField();
        JTextField role = new JTextField();
        JTextField salary = new JTextField();
        JTextField driveDate = new JTextField();

        JPanel panel = new JPanel(
            new GridLayout(5, 2, 8, 8)
        );

        panel.add(new JLabel("Company ID:"));
        panel.add(id);

        panel.add(new JLabel("Company:"));
        panel.add(company);

        panel.add(new JLabel("Role:"));
        panel.add(role);

        panel.add(new JLabel("Package:"));
        panel.add(salary);

        panel.add(new JLabel("Drive Date:"));
        panel.add(driveDate);

        int result =
            JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Company",
                JOptionPane.OK_CANCEL_OPTION
            );

        if (result == JOptionPane.OK_OPTION) {

            if (
                id.getText().trim().isEmpty()
                ||
                company.getText().trim().isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                    this,
                    "Company ID and Company name are required."
                );

                return;
            }

            companyModel.addRow(
                new Object[] {
                    id.getText(),
                    company.getText(),
                    role.getText(),
                    salary.getText(),
                    driveDate.getText(),
                    "0",
                    "Upcoming"
                }
            );

            JOptionPane.showMessageDialog(
                this,
                "Company added successfully!",
                "HireSphere",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // =========================================================
    // DELETE COMPANY
    // =========================================================

    void deleteCompany() {

        JTable table = getCompanyTable();

        if (table == null) {

            JOptionPane.showMessageDialog(
                this,
                "Company table not found."
            );

            return;
        }

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a company first.",
                "Delete Company",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int modelRow =
            table.convertRowIndexToModel(row);

        int answer =
            JOptionPane.showConfirmDialog(
                this,
                "Delete selected company?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

        if (answer == JOptionPane.YES_OPTION) {

            companyModel.removeRow(modelRow);
        }
    }

    // =========================================================
    // GET COMPANY TABLE
    // =========================================================

    JTable getCompanyTable() {

        for (java.awt.Component component :
            content.getComponents()) {

            if (component instanceof JPanel) {

                JTable table =
                    findTableInPanel(
                        (JPanel) component,
                        companyModel
                    );

                if (table != null) {
                    return table;
                }
            }
        }

        return null;
    }

    // =========================================================
    // PLACEMENT DRIVES
    // =========================================================

    JPanel createPlacementDrives() {

        JPanel page = createPage(
            "Placement Drives",
            "Monitor upcoming and active campus placement drives."
        );

        JPanel list = createListPanel();

        addDrive(
            list,
            "TCS",
            "System Engineer",
            "12 LPA",
            "25 Aug 2026",
            BLUE
        );

        addDrive(
            list,
            "Infosys",
            "Digital Specialist",
            "9 LPA",
            "28 Aug 2026",
            GREEN
        );

        addDrive(
            list,
            "Wipro",
            "Project Engineer",
            "8 LPA",
            "02 Sep 2026",
            ORANGE
        );

        addDrive(
            list,
            "Accenture",
            "Software Engineer",
            "10 LPA",
            "05 Sep 2026",
            PURPLE
        );

        addDrive(
            list,
            "Cognizant",
            "Programmer Analyst",
            "10 LPA",
            "10 Sep 2026",
            BLUE
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // ADD DRIVE
    // =========================================================

    void addDrive(
        JPanel list,
        String company,
        String role,
        String salary,
        String driveDate,
        Color color
    ) {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(15, 5)
        );

        card.setBorder(
            new EmptyBorder(
                12,
                15,
                12,
                15
            )
        );

        JPanel info =
            new JPanel(
                new GridLayout(3, 1)
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

        info.add(
            label(
                "Drive Date: " + driveDate,
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
                    FlowLayout.RIGHT
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

        JButton view =
            smallButton(
                "Manage",
                color
            );

        view.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                company +
                " placement drive selected."
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

    // =========================================================
    // ANNOUNCEMENTS
    // =========================================================

    JPanel createAnnouncements() {

        JPanel page = createPage(
            "Announcements",
            "Send important placement announcements to students."
        );

        JPanel main = new JPanel(
            new BorderLayout(0, 15)
        );

        main.setOpaque(false);

        JButton create =
            smallButton(
                "Create Announcement",
                BLUE
            );

        create.addActionListener(
            e -> announcementDialog()
        );

        JPanel top =
            new JPanel(
                new FlowLayout(
                    FlowLayout.RIGHT
                )
            );

        top.setOpaque(false);

        top.add(create);

        main.add(
            top,
            BorderLayout.NORTH
        );

        JPanel list =
            createListPanel();

        application(
            list,
            "TCS",
            "Campus drive registration is now open.",
            "NEW",
            BLUE
        );

        application(
            list,
            "Placement Cell",
            "Resume verification deadline is Friday.",
            "IMPORTANT",
            ORANGE
        );

        application(
            list,
            "Infosys",
            "Shortlisted candidates have been notified.",
            "UPDATE",
            GREEN
        );

        application(
            list,
            "Placement Cell",
            "Interview schedule has been updated.",
            "NOTICE",
            PURPLE
        );

        main.add(
            scroll(list),
            BorderLayout.CENTER
        );

        page.add(
            main,
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // ANNOUNCEMENT DIALOG
    // =========================================================

    void announcementDialog() {

        JTextField title =
            new JTextField();

        JTextArea message =
            new JTextArea(5, 25);

        message.setLineWrap(true);
        message.setWrapStyleWord(true);

        JPanel panel =
            new JPanel(
                new BorderLayout(8, 8)
            );

        JPanel top =
            new JPanel(
                new BorderLayout(5, 5)
            );

        top.add(
            new JLabel("Title:"),
            BorderLayout.WEST
        );

        top.add(
            title,
            BorderLayout.CENTER
        );

        panel.add(
            top,
            BorderLayout.NORTH
        );

        panel.add(
            new JScrollPane(message),
            BorderLayout.CENTER
        );

        int result =
            JOptionPane.showConfirmDialog(
                this,
                panel,
                "Create Announcement",
                JOptionPane.OK_CANCEL_OPTION
            );

        if (result == JOptionPane.OK_OPTION) {

            if (
                title.getText().trim().isEmpty()
                ||
                message.getText().trim().isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter title and message.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            JOptionPane.showMessageDialog(
                this,
                "Announcement sent successfully to students!",
                "HireSphere",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // =========================================================
    // DOCUMENTS
    // =========================================================

    JPanel createDocuments() {

        JPanel page = createPage(
            "Documents & Resumes",
            "Check student resume and document verification status."
        );

        JPanel list = createListPanel();

        document(
            list,
            "Ishan Mishra",
            "ST001",
            "Resume.pdf",
            "Verified",
            GREEN
        );

        document(
            list,
            "Rahul Das",
            "ST002",
            "Resume.pdf",
            "Verified",
            GREEN
        );

        document(
            list,
            "Ananya Singh",
            "ST003",
            "Not Uploaded",
            "Pending",
            ORANGE
        );

        document(
            list,
            "Riya Patel",
            "ST004",
            "Resume.pdf",
            "Verified",
            GREEN
        );

        document(
            list,
            "Amit Kumar",
            "ST005",
            "Resume.pdf",
            "Under Review",
            BLUE
        );

        page.add(
            scroll(list),
            BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // DOCUMENT ROW
    // =========================================================

    void document(
        JPanel list,
        String name,
        String id,
        String document,
        String status,
        Color color
    ) {

        JPanel card =
            new JPanel(
                new BorderLayout()
            );

        card.setBackground(Color.WHITE);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(
                    12,
                    15,
                    12,
                    15
                )
            )
        );

        JPanel info =
            new JPanel(
                new GridLayout(3, 1)
            );

        info.setOpaque(false);

        info.add(
            label(
                name,
                TEXT,
                Font.BOLD,
                14
            )
        );

        info.add(
            label(
                "Student ID: " + id,
                GRAY,
                Font.PLAIN,
                10
            )
        );

        info.add(
            label(
                document,
                GRAY,
                Font.PLAIN,
                10
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

    // =========================================================
    // REPORTS
    // =========================================================

    JPanel createReports() {

        JPanel page = createPage(
            "Reports",
            "View placement statistics and generate reports."
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
            reportCard(
                "Student Report",
                "248 registered students",
                BLUE
            )
        );

        grid.add(
            reportCard(
                "Company Report",
                "32 participating companies",
                GREEN
            )
        );

        grid.add(
            reportCard(
                "Application Report",
                "684 applications submitted",
                PURPLE
            )
        );

        grid.add(
            reportCard(
                "Placement Report",
                "76 students placed",
                ORANGE
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
        String description,
        Color color
    ) {

        RoundedPanel card =
            new RoundedPanel();

        card.setLayout(
            new BorderLayout(0, 10)
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
                16
            ),
            BorderLayout.NORTH
        );

        card.add(
            label(
                description,
                GRAY,
                Font.PLAIN,
                12
            ),
            BorderLayout.CENTER
        );

        JButton download =
            smallButton(
                "Generate Report",
                color
            );

        download.addActionListener(
            e -> JOptionPane.showMessageDialog(
                this,
                "Report generated successfully!",
                "HireSphere",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        card.add(
            download,
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
            "Manage administrator account and portal preferences."
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
                "Update your administrator account password.",
                BLUE
            )
        );

        grid.add(
            infoCard(
                "Admin Profile",
                "Update administrator information.",
                GREEN
            )
        );

        grid.add(
            infoCard(
                "Notification Settings",
                "Manage portal notification preferences.",
                ORANGE
            )
        );

        grid.add(
            infoCard(
                "About HireSphere",
                "College Placement Management Portal.",
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
    // APPLICATION ROW
    // =========================================================

    void application(
        JPanel list,
        String company,
        String role,
        String status,
        Color color
    ) {

        JPanel card =
            new JPanel(
                new BorderLayout()
            );

        card.setBackground(Color.WHITE);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(
                    12,
                    15,
                    12,
                    15
                )
            )
        );

        JPanel info =
            new JPanel(
                new GridLayout(2, 1)
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

    // =========================================================
    // PAGE
    // =========================================================

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

    // =========================================================
    // INFORMATION CARD
    // =========================================================

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

        JButton button =
            smallButton(
                title,
                color
            );

        button.addActionListener(
            e -> JOptionPane.showMessageDialog(
                this,
                text,
                title,
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        card.add(
            button,
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

    // =========================================================
    // LIST PANEL
    // =========================================================

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

    // =========================================================
    // SCROLL
    // =========================================================

    JScrollPane scroll(
        java.awt.Component component
    ) {

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

        // IMPORTANT FIX
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);

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

        b.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        return b;
    }

    // =========================================================
    // TABLE STYLE
    // =========================================================

    void styleTable(JTable table) {

        table.setRowHeight(38);

        table.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                12
            )
        );

        table.setForeground(TEXT);
        table.setBackground(Color.WHITE);
        table.setGridColor(BORDER);

        table.setSelectionBackground(
            new Color(219, 234, 254)
        );

        table.setSelectionForeground(TEXT);

        table.setAutoCreateRowSorter(true);

        table.getTableHeader().setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                12
            )
        );

        table.getTableHeader()
            .setBackground(NAVY);

        table.getTableHeader()
            .setForeground(Color.WHITE);

        table.getTableHeader()
            .setPreferredSize(
                new Dimension(0, 40)
            );
    }

    // =========================================================
    // IMAGE LOADER
    // =========================================================

    JLabel image(
        String file,
        int width,
        int height
    ) {

        JLabel label = new JLabel();

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

            label.setPreferredSize(
                new Dimension(
                    width,
                    height
                )
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
    // TIME
    // =========================================================

    void updateTime() {

        Date now = new Date();

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

    // =========================================================
    // ROUNDED PANEL
    // =========================================================

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
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
        String[] args
    ) {

        SwingUtilities.invokeLater(
            () -> new AdminDashboard()
        );
    }
}