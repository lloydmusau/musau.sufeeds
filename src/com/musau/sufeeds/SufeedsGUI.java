//Lloyd Musau, BBIT, 227326, 10th-11th Nov 2026

package com.musau.sufeeds;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SufeedsGUI extends JFrame {
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


    public SufeedsGUI() {
        dbOps = new databaseOperations();
        setTitle("Student Feedback System - com.musau.sufeeds");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createAuthPanel(), "AUTH");
        mainPanel.add(createDashboardPanel(), "DASHBOARD");

        // Copyright Footer Label
        JLabel footerLabel = new JLabel("© 2026 Lloyd Musau Mulinge. All Rights Reserved.", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        add(mainPanel, BorderLayout.CENTER);
        add(footerLabel, BorderLayout.SOUTH);

        cardLayout.show(mainPanel, "AUTH");
    }

    // 1. Authentication Panel using JTabbedPane (Login & Register Tabs)
    private JPanel createAuthPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTabbedPane tabbedPane = new JTabbedPane();

        // Login Sub-panel
        JPanel loginPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        loginUserField = new JTextField();
        loginPassField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(loginUserField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(loginPassField);
        loginPanel.add(loginBtn);

        // Register Sub-panel
        JPanel regPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        regPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        regUserField = new JTextField();
        regPassField = new JPasswordField();
        JButton regBtn = new JButton("Register");

        regPanel.add(new JLabel("Choose Username:"));
        regPanel.add(regUserField);
        regPanel.add(new JLabel("Choose Password:"));
        regPanel.add(regPassField);
        regPanel.add(regBtn);

        // Add tabs to tabbed pane
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Register Student", regPanel);

        panel.add(tabbedPane, BorderLayout.CENTER);

        // Action Listeners
        loginBtn.addActionListener(e -> handleLogin());
        regBtn.addActionListener(e -> handleRegister());

        return panel;
    }

    // 2. Main Dashboard Panel (Submit & View Feedback)
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // Form for submitting feedback
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        courseField = new JTextField();
        topicField = new JTextField();
        lecturerField = new JTextField();
        feedbackArea = new JTextArea(3, 20);
        JButton submitBtn = new JButton("Submit Feedback");

        formPanel.add(new JLabel("Course Name:"));
        formPanel.add(courseField);
        formPanel.add(new JLabel("Topic:"));
        formPanel.add(topicField);
        formPanel.add(new JLabel("Lecturer Name:"));
        formPanel.add(lecturerField);
        formPanel.add(new JLabel("Feedback:"));
        formPanel.add(new JScrollPane(feedbackArea));
        formPanel.add(new JLabel(""));
        formPanel.add(submitBtn);

        // Table for viewing peer feedback
        tableModel = new DefaultTableModel(new String[]{"Student", "Course", "Topic", "Lecturer", "Feedback"}, 0);
        feedbackTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(feedbackTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Other students feedback "));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formPanel, tableScroll);
        splitPane.setDividerLocation(200);
        panel.add(splitPane, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> handleSubmitFeedback());

        return panel;
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

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all registration fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (dbOps.registerUser(user, pass)) {
                JOptionPane.showMessageDialog(this, "Registration successful! You can now switch to the Login tab.", "Success", JOptionPane.INFORMATION_MESSAGE);
                regUserField.setText("");
                regPassField.setText("");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Registration Error", JOptionPane.ERROR_MESSAGE);
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