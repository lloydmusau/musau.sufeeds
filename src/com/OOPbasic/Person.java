package com.OOPbasic;

//Declaring variables
public class Person {
    private String name;
    private String gender;
    private int dob;
    private int currentYear;
    private int age;

//constructor 1
    public Person(String name){
        this.name=name;
    }
//constructor 2
    public Person(String name, String gender, int dob,int currentYear) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.currentYear = currentYear;
        this.age = currentYear-dob;
    }
//Methods
    public void Person(String name){
        this.name = name;
        System.out.println("name: "+this.name);
    }

    public void person(String name, String gender, int dob, int currentYear) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.currentYear = currentYear;
        this.age = currentYear-dob;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setDob(int dob) {
        this.dob = dob;
    }
    public String getName(){
        return this.name;
    }
    public String getGender(){
        return this.gender;
    }
    public int getDob(){
        return this.dob;
    }
    public int getAge(int dob,int currentYear){
        return this.age;
    }
    public void printDetails(){
        System.out.println("Name: "+this.name);
        System.out.println("Gender: "+this.gender);
        System.out.println("Current age: "+this.age);
    }
}
