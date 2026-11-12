//Lloyd Musau, BBIT, 227326, 10th-11th Nov 2026

package com.musau.sufeeds;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SufeedsGUI extends JFrame {
    //declaring components
    private databaseOperations dbOps;
    private String loggedInUser = null;

    // UI Components
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Login Panel Components
    private JTextField loginUserField;
    private JPasswordField loginPassField;

    // Register Panel Components
    private JTextField regUserField;
    private JPasswordField regPassField;

    // Dashboard Panel Components
    private JTextField courseField, topicField, lecturerField;
    private JTextArea feedbackArea;
    private JTable feedbackTable;
    private DefaultTableModel tableModel;
    private JLabel welcomeLabel;

    //constructor
    public SufeedsGUI() {
        dbOps = new databaseOperations();
        setTitle("Student Feedback System ");
        setSize(550, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add views to CardLayout
        mainPanel.add(createWelcomePanel(), "WELCOME");
        mainPanel.add(createLoginPanel(), "LOGIN");
        mainPanel.add(createRegisterPanel(), "REGISTER");
        mainPanel.add(createDashboardPanel(), "DASHBOARD");

        // Copyright Footer Label
        JLabel footerLabel = new JLabel("© 2026 Lloyd Musau Mulinge. All Rights Reserved.", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        add(mainPanel, BorderLayout.CENTER);
        add(footerLabel, BorderLayout.SOUTH);

        // Start at the Welcome Screen
        cardLayout.show(mainPanel, "WELCOME");
    }

    // 1. Initial Welcome Panel
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Welcome to Sufeeds!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel subtitleLabel = new JLabel("Please choose an option to continue:", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JButton navLoginBtn = new JButton("Log In");
        JButton navRegisterBtn = new JButton("Create Account");

        // Style buttons for visual clarity
        navLoginBtn.setPreferredSize(new Dimension(180, 35));
        navRegisterBtn.setPreferredSize(new Dimension(180, 35));

        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        panel.add(subtitleLabel, gbc);

        gbc.gridy = 2;
        panel.add(navLoginBtn, gbc);

        gbc.gridy = 3;
        panel.add(navRegisterBtn, gbc);

        // Navigation Action Listeners
        navLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "LOGIN"));
        navRegisterBtn.addActionListener(e -> cardLayout.show(mainPanel, "REGISTER"));

        return panel;
    }

    // 2. Dedicated Login Panel
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel headerLabel = new JLabel("Log In to Your Account", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(headerLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        loginUserField = new JTextField();
        loginPassField = new JPasswordField();
        JButton loginBtn = new JButton("Log In");


        JButton backBtn = new JButton("Back to Welcome Screen");

        formPanel.add(new JLabel("Username:"));
        formPanel.add(loginUserField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(loginPassField);
        formPanel.add(loginBtn);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        // Action Listeners
        loginBtn.addActionListener(e -> handleLogin());
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "WELCOME"));

        return panel;
    }

    // 3. Dedicated Create Account Panel
    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel headerLabel = new JLabel("Create a New Account", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(headerLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        regUserField = new JTextField();
        regPassField = new JPasswordField();
        JButton regBtn = new JButton("Add Account");
        JButton backBtn = new JButton("Back to Welcome Screen");

        formPanel.add(new JLabel("Choose Username:"));
        formPanel.add(regUserField);
        formPanel.add(new JLabel("Choose Password:"));
        formPanel.add(regPassField);
        formPanel.add(regBtn);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        // Action Listeners
        regBtn.addActionListener(e -> handleRegister());
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "WELCOME"));

        return panel;
    }

    // 4. Main Dashboard Panel (Submit, View Feedback & Log Out)
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel topPanel = new JPanel(new BorderLayout());

        welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JButton logoutBtn = new JButton("Log Out");
        logoutBtn.addActionListener(e -> handleLogout());

        topPanel.add(welcomeLabel, BorderLayout.WEST);  // Puts welcome message on the left
        topPanel.add(logoutBtn, BorderLayout.EAST);     // Puts log out button on the right

        panel.add(topPanel, BorderLayout.NORTH);        // Adds the top header panel to the dashboard

        // Form for submitting feedback
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        courseField = new JTextField();
        topicField = new JTextField();
        lecturerField = new JTextField();
        feedbackArea = new JTextArea(3, 20);
        JButton submitBtn = new JButton("Submit Feedback");

        formPanel.add(new JLabel("Course Name:"));
        formPanel.add(courseField);
        formPanel.add(new JLabel("Unit:"));
        formPanel.add(topicField);
        formPanel.add(new JLabel("Lecturer Name:"));
        formPanel.add(lecturerField);
        formPanel.add(new JLabel("Your Feedback:"));
        formPanel.add(new JScrollPane(feedbackArea));
        formPanel.add(new JLabel(""));
        formPanel.add(submitBtn);

        // peer feedback
        tableModel = new DefaultTableModel(new String[]{"Student", "Course", "Unit name ", "Lecturer", "Feedbacks"}, 0);
        feedbackTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(feedbackTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Other students feedback"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formPanel, tableScroll);
        splitPane.setDividerLocation(200);
        panel.add(splitPane, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> handleSubmitFeedback());

        return panel;
    }

    private void handleLogout() {
        // Ask the user for confirmation before logging out
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            loggedInUser = null; // Clear the active user session

            // Clear all form input fields so the next user doesn't see old data
            courseField.setText("");
            topicField.setText("");
            lecturerField.setText("");
            feedbackArea.setText("");
            tableModel.setRowCount(0); // Clear the feedback table

            // Switch the screen back to the Welcome panel
            cardLayout.show(mainPanel, "WELCOME");
        }
    }

    private void handleLogin() {
        String user = loginUserField.getText().trim();
        String pass = new String(loginPassField.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all login fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (dbOps.loginUser(user, pass)) {
                loggedInUser = user;
                welcomeLabel.setText("Logged in as: " + loggedInUser);
                loadFeedbackTable();
                cardLayout.show(mainPanel, "DASHBOARD");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {
        String user = regUserField.getText().trim();
        String pass = new String(regPassField.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) { // Checks if registration fields are empty
            JOptionPane.showMessageDialog(this, "Please fill in all registration fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (dbOps.registerUser(user, pass)) {
                JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE); // Displays success confirmation dialog box
                regUserField.setText("");
                regPassField.setText("");
                cardLayout.show(mainPanel, "LOGIN");
            }
        } catch (SQLException ex) {
            String errorMsg = ex.getMessage().toLowerCase();
            if (errorMsg.contains("duplicate") || errorMsg.contains("already exists")) {
                JOptionPane.showMessageDialog(this, "Username exist", "Registration Error", JOptionPane.ERROR_MESSAGE); // Displays specific message when username exists
            } else {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Registration Error", JOptionPane.ERROR_MESSAGE); // Displays general database error message if different
            }
        }
    }

    private void handleSubmitFeedback() {
        String course = courseField.getText().trim();
        String topic = topicField.getText().trim();
        String lecturer = lecturerField.getText().trim();
        String feedback = feedbackArea.getText().trim();

        if (course.isEmpty() || topic.isEmpty() || lecturer.isEmpty() || feedback.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please complete all feedback fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Student newLog = new Student(loggedInUser, course, topic, lecturer, feedback);
            dbOps.addClassLog(newLog);
            JOptionPane.showMessageDialog(this, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields and refresh table
            courseField.setText("");
            topicField.setText("");
            lecturerField.setText("");
            feedbackArea.setText("");
            loadFeedbackTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving feedback: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFeedbackTable() {
        tableModel.setRowCount(0); // Clear existing rows
        try {
            // Load only other students' feedback using dbOps
            List<Student> list = dbOps.getOtherFeedback(loggedInUser);
            for (Student s : list) {
                tableModel.addRow(new Object[]{
                        s.getStudentUsername(),
                        s.getCourseName(),
                        s.getTopic(),
                        s.getLecturerName(),
                        s.getFeedback()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading feedback stream: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SufeedsGUI().setVisible(true));
    }

}