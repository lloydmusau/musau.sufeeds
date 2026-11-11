package com.OOPbasic;

public class circle {
    //attributes
    private int radius;
    //constructors
    public circle(int radius) {
        this.radius = radius;
    }
//methods
    public void setRadius(int radius) {
        this.radius = radius;
    }
    public int getRadius() {
        return radius;
    }
    public float getArea() {
        return 22/7*(radius * radius);
    }
    public float getPerimeter() {
        return 44/7* (radius);
    }
    public void showDetails() {
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }
}
