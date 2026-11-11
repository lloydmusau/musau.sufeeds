package com.OOPbasic;

public class studentperson2 extends Person2 {
    int numberOfCourses;

    studentperson2(String name, String gender, String dateOfBirth, int age, int numberOfCourses) {
        super(name, gender, dateOfBirth, age);
        this.numberOfCourses = numberOfCourses;
    }
    @Override
    public double processLoan() {
        if (getAge() < 10) return 1000;
        else if (getAge() <= 18) return 10000;
        else if (getAge() <= 24) return 100000;
        else return 0;
    }
}

