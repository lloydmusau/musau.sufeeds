//Lloyd Musau, BBIT, 227326, 10th Nov 2026
package com.musau.sufeeds;
//handles how the db operates with the application
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class databaseOperations {

    // Register a new user
    public boolean registerUser(String username, String password) throws SQLException {
        // Insert the new account into the dedicated 'students' table
        String insertQuery = "INSERT INTO tbl_students (username, password) VALUES (?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            insertStmt.setString(1, username);
            insertStmt.setString(2, password);

            int rowsAffected = insertStmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            // If the username already exists, MySQL will throw a duplicate entry error
            if (ex.getMessage().toLowerCase().contains("duplicate")) {
                throw new SQLException("Username exist");
            }
            throw ex; // Throw any other SQL error normally
        }
    }

    // Authenticate user login
    public boolean loginUser(String username, String password) throws SQLException {
        // Query the dedicated 'students' table where usernames and passwords are stored
        String query = "SELECT * FROM tbl_students WHERE username = ? AND password = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                // If a matching record is found, login is successful
                return rs.next();
            }
        }
    }

    // Add a new student feedback entry
    public void addClassLog(Student student) throws SQLException {
        // Insert ONLY feedback details into tbl_studentdetails (no password column needed here)
        String query = "INSERT INTO tbl_studentdetails (username, course_name, topic, lecturer_name, feedback) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getStudentUsername());
            stmt.setString(2, student.getCourseName());
            stmt.setString(3, student.getTopic());
            stmt.setString(4, student.getLecturerName());
            stmt.setString(5, student.getFeedback());

            stmt.executeUpdate();
        }
    }

    // Get feedback from all students EXCEPT the currently logged-in user
    public List<Student> getOtherFeedback(String currentUsername) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT username, course_name, topic, lecturer_name, feedback FROM tbl_studentdetails WHERE username != ? AND feedback IS NOT NULL AND feedback != ''";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, currentUsername);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(
                            rs.getString("username"),
                            rs.getString("course_name"),
                            rs.getString("topic"),
                            rs.getString("lecturer_name"),
                            rs.getString("feedback")
                    ));
                }
            }
        }
        return list;
    }
}