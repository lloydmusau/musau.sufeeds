package com.OOPbasic;

public class student extends Person {
    private String regNo;
    private String course;




    public student(String name, String gender, int dob, int currentYear, String regNo, String course) {
        super(name, gender, dob, currentYear);
        this.regNo = regNo;
        this.course = course;

    }

    public void setRegNo(String regNo) { this.regNo = regNo; }
    public void setCourse(String course) { this.course = course; }
    public String getRegNo() { return this.regNo; }
    public String getCourse() { return this.course; }




    public void printDetails() {
        super.printDetails();
        System.out.println("Registration No: " + this.regNo);
        System.out.println("Course: " + this.course);

    }

}
