//Lloyd Musau, BBIT, 227326, 10th-17th Nov 2026
package com.musau.sufeeds;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SufeedsGUI extends JFrame {

    // database object
    private databaseOperations dbOps;
    private String loggedInUser = null;

    // for switching between screens
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // login screen
    private JTextField loginUserField;
    private JPasswordField loginPassField;

    // register screen
    private JTextField regUserField;
    private JPasswordField regPassField;
    private JTextField regUnitsField;

    // dashboard screen
    private JTextField courseField, lecturerField;
    private JComboBox<String> unitDropdown;
    private JTextArea feedbackArea;
    private JTable feedbackTable;
    private DefaultTableModel tableModel;
    private JLabel welcomeLabel;

    // constructor
    public SufeedsGUI() {
        dbOps = new databaseOperations();
        setTitle("SU feeds");
        setSize(550, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // add all the screens
        mainPanel.add(createWelcomePanel(), "WELCOME");
        mainPanel.add(createLoginPanel(), "LOGIN");
        mainPanel.add(createRegisterPanel(), "REGISTER");
        mainPanel.add(createDashboardPanel(), "DASHBOARD");

        // copyright at the bottom
        JLabel footerLabel = new JLabel("© 2026 Lloyd Musau Mulinge. All Rights Reserved.", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 11));

        add(mainPanel, BorderLayout.CENTER);
        add(footerLabel, BorderLayout.SOUTH);

        // show welcome screen first
        cardLayout.show(mainPanel, "WELCOME");
    }

    // welcome screen
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));

        JLabel titleLabel = new JLabel("Welcome to Sufeeds!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel subtitleLabel = new JLabel("Please choose an option to continue:", SwingConstants.CENTER);

        JButton navLoginBtn = new JButton("Log In");
        JButton navRegisterBtn = new JButton("Create Account");

        panel.add(titleLabel);
        panel.add(subtitleLabel);
        panel.add(navLoginBtn);
        panel.add(navRegisterBtn);

        // button clicks
        navLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "LOGIN"));
        navRegisterBtn.addActionListener(e -> cardLayout.show(mainPanel, "REGISTER"));

        return panel;
    }

    // login screen
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel headerLabel = new JLabel("Log In to Your Account", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(headerLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 5, 5));
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

        loginBtn.addActionListener(e -> HandleLogin());
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "WELCOME"));

        return panel;
    }

    // register screen
    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel headerLabel = new JLabel("Create a New Account", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(headerLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 1, 5, 5));

        regUserField = new JTextField();
        regPassField = new JPasswordField();
        regUnitsField = new JTextField();

        JButton regBtn = new JButton("Add Account");
        JButton backBtn = new JButton("Back to Welcome Screen");

        formPanel.add(new JLabel("Choose Username:"));
        formPanel.add(regUserField);
        formPanel.add(new JLabel("Choose Password:"));
        formPanel.add(regPassField);
        formPanel.add(new JLabel("Enter Units (comma-separated e.g., OOP, Calculus, Finance):"));
        formPanel.add(regUnitsField);
        formPanel.add(regBtn);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        regBtn.addActionListener(e -> handleRegister());
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "WELCOME"));

        return panel;
    }

    // dashboard screen
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // top part with welcome label and logout button
        JPanel topPanel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JButton logoutBtn = new JButton("Log Out");
        logoutBtn.addActionListener(e -> handleLogout());
        topPanel.add(welcomeLabel, BorderLayout.WEST);
        topPanel.add(logoutBtn, BorderLayout.EAST);

        // form for feedback
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        courseField = new JTextField();
        unitDropdown = new JComboBox<>();
        lecturerField = new JTextField();
        feedbackArea = new JTextArea(3, 20);
        JButton submitBtn = new JButton("Submit Feedback");

        formPanel.add(new JLabel("Course Name:"));
        formPanel.add(courseField);
        formPanel.add(new JLabel("Enter your Units:"));
        formPanel.add(unitDropdown);
        formPanel.add(new JLabel("Lecturer Name:"));
        formPanel.add(lecturerField);
        formPanel.add(new JLabel("Your Feedback:"));
        formPanel.add(new JScrollPane(feedbackArea));
        formPanel.add(new JLabel(""));
        formPanel.add(submitBtn);

        // table to show other students feedback
        tableModel = new DefaultTableModel(new String[]{"Student", "Course", "Unit", "Lecturer", "Feedback"}, 0);
        feedbackTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(feedbackTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Other students feedback"));

        // put form on top and table in the middle
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(tableScroll, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> handleSubmitFeedback());

        return panel;
    }

    private void handleLogout() {
        // ask first
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            loggedInUser = null;

            // clear everything
            courseField.setText("");
            lecturerField.setText("");
            feedbackArea.setText("");
            tableModel.setRowCount(0);

            // go back to welcome screen
            cardLayout.show(mainPanel, "WELCOME");
        }
    }

    private void HandleLogin() {
        String user = loginUserField.getText().trim();
        String pass = new String(loginPassField.getPassword()).trim();

        // check if empty
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
        String units = regUnitsField.getText().trim();

        // check if empty
        if (user.isEmpty() || pass.isEmpty() || units.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all registration fields, including units.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (dbOps.registerUser(user, pass, units)) {
                JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                regUserField.setText("");
                regPassField.setText("");
                regUnitsField.setText("");
                cardLayout.show(mainPanel, "LOGIN");
            }
        } catch (SQLException ex) {
            String errorMsg = ex.getMessage().toLowerCase();
            if (errorMsg.contains("duplicate") || errorMsg.contains("already exists")) {
                JOptionPane.showMessageDialog(this, "Username exist", "Registration Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleSubmitFeedback() {
        String course = courseField.getText().trim();
        String lecturer = lecturerField.getText().trim();
        String feedback = feedbackArea.getText().trim();

        // get the unit that was picked
        String unit = "";
        if (unitDropdown.getSelectedItem() != null) {
            unit = unitDropdown.getSelectedItem().toString();
        }

        // check if empty
        if (course.isEmpty() || unit.isEmpty() || lecturer.isEmpty() || feedback.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please complete all feedback fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Student newLog = new Student(loggedInUser, course, unit, lecturer, feedback);
            dbOps.addClassLog(newLog);
            JOptionPane.showMessageDialog(this, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            courseField.setText("");
            lecturerField.setText("");
            feedbackArea.setText("");
            loadFeedbackTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving feedback: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFeedbackTable() {
        // remove old rows first
        tableModel.setRowCount(0);

        try {
            // put the student's units in the dropdown
            String[] studentUnits = dbOps.getStudentUnits(loggedInUser);
            unitDropdown.removeAllItems();
            for (String u : studentUnits) {
                unitDropdown.addItem(u.trim());
            }

            // get feedback from other students and add to table
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
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SufeedsGUI().setVisible(true));
    }

}