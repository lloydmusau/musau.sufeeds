//Lloyd Musau, BBIT, 227326, 10th Nov 2026
package com.musau.sufeeds;

// this class handles the database work for the app
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class databaseOperations {

    // register a new user and save their units
    public boolean registerUser(String username, String password, String units) throws SQLException {
        String insertQuery = "INSERT INTO tbl_students (username, password, units) VALUES (?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            insertStmt.setString(1, username);
            insertStmt.setString(2, password);
            insertStmt.setString(3, units);

            int rowsAffected = insertStmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            // if the username is already taken
            if (ex.getMessage().toLowerCase().contains("duplicate")) {
                throw new SQLException("Username exist");
            }
            throw ex;
        }
    }

    // get the units of one student
    public String[] getStudentUnits(String username) throws SQLException {
        String query = "SELECT units FROM tbl_students WHERE username = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String unitsStr = rs.getString("units");
                if (unitsStr != null && !unitsStr.isEmpty()) {
                    // split the units at the commas
                    return unitsStr.split(",");
                }
            }
        }

        // if nothing was found
        return new String[0];
    }

    // check the username and password when logging in
    public boolean loginUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM tbl_students WHERE username = ? AND password = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            // if a row is found the login is correct
            return rs.next();
        }
    }

    // save a new feedback
    public void addClassLog(Student student) throws SQLException {
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

    // get feedback from all the other students (not the one logged in)
    public List<Student> getOtherFeedback(String currentUsername) throws SQLException {
        List<Student> list = new ArrayList<Student>();
        String sql = "SELECT username, course_name, topic, lecturer_name, feedback FROM tbl_studentdetails WHERE username != ? AND feedback IS NOT NULL AND feedback != ''";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, currentUsername);
            ResultSet rs = pstmt.executeQuery();

            // go through each row and add it to the list
            while (rs.next()) {
                Student s = new Student(
                        rs.getString("username"),
                        rs.getString("course_name"),
                        rs.getString("topic"),
                        rs.getString("lecturer_name"),
                        rs.getString("feedback")
                );
                list.add(s);
            }
        }

        return list;
    }
}