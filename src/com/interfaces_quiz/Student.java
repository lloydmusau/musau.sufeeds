package com.interfaces_quiz;

public class Student implements Span {
    private String name;
    private double numberOfcourses;

    public Student(String name, double numberOfcourses) {
        this.name = name;
        this.numberOfcourses = numberOfcourses;
    }
    public String getName() {
        return name;
    }
    @Override
    public double getNumberOfcourses() {
        return numberOfcourses*3/12;
    }
    public void showDetails() {
        System.out.println("Name: " + name);
        System.out.println("Student span is: " + getNumberOfcourses()+" years");
    }
}



