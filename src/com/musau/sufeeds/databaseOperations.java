//Lloyd Musau, BBIT, 227326, 10th Nov 2026
package com.musau.sufeeds;
//handles how the db operates
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class databaseOperations {

    // Register a new user
    public boolean registerUser(String username, String password) throws SQLException {
        String sql = "INSERT INTO tbl_studentdetails (username, password) VALUES (?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Username already exists. Please choose another.");
        }
    }

    // Authenticate user login
    public boolean loginUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM tbl_studentdetails WHERE username = ? AND password = ? LIMIT 1";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Add a new student feedback entry
    public void addClassLog(Student studentLog) throws SQLException {
        String sql = "INSERT INTO tbl_studentdetails (username, password, course_name, topic, lecturer_name, feedback) " +
                "VALUES (?, (SELECT password FROM (SELECT password FROM tbl_studentdetails WHERE username = ? LIMIT 1) AS tmp), ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentLog.getStudentUsername());
            pstmt.setString(2, studentLog.getStudentUsername());
            pstmt.setString(3, studentLog.getCourseName());
            pstmt.setString(4, studentLog.getTopic());
            pstmt.setString(5, studentLog.getLecturerName());
            pstmt.setString(6, studentLog.getFeedback());
            pstmt.executeUpdate();
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