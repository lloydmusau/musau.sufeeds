//Lloyd Musau, BBIT, 227326, 10th Nov 2026
package com.musau.sufeeds;
//child class
public class Student extends Academics {
    private String studentUsername;
    private String lecturerName;
    private String feedback;

    public Student(String studentUsername, String courseName, String topic, String lecturerName, String feedback) {
        super(courseName, topic); // Inheritance
        this.studentUsername = studentUsername;
        this.lecturerName = lecturerName;
        this.feedback = feedback;
    }
    public String getStudentUsername() {
        return studentUsername;
    }
    public String getLecturerName() {
        return lecturerName;
    }
    public String getFeedback() {
        return feedback;
    }
}