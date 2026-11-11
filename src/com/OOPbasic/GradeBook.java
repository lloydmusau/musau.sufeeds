package com.OOPbasic;

public class GradeBook {
    private String courseName;

    public GradeBook(String courseName) {
        this.courseName = courseName;}

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return courseName;
    }
    public void displaymessage() {
        System.out.println("OOPbasics.Welcome to "+courseName);
    }
}

