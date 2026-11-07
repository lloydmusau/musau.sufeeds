package com.interfaces_quiz;
public class Course implements Span{
    private String courseName;
    private String courseCode;
    private double totalHours;

    public Course(String courseName, String courseCode, double totalHours) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.totalHours = totalHours;
    }

    @Override
    public double getNumberOfcourses(){
        return (totalHours/(365/24));
    }
    public void showDetails(){
        System.out.println("Course Name: "+courseName);
        System.out.println("Course Code: "+courseCode);
        System.out.println("Number of courses: "+getNumberOfcourses());
    }

}
