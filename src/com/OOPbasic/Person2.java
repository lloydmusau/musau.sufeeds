package com.OOPbasic;

public abstract class Person2 {
   private String name;
    private String gender;
   private String dateOfBirth;
    private int age;

    Person2(String name, String gender, String dateOfBirth, int age) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public final int getAge() {
        return age;
    }

    public abstract double processLoan();
}
