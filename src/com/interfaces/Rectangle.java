package com.interfaces;
public class Rectangle implements Calculation{
    private float length;
    private float width;

    public Rectangle(float lenth, float width){
        this.length = lenth;
        this.width = width;
    }
    @Override
    public float getArea() {
        return length*width;
    }
    public float getPerimeter() {
        return 2*(length+width);
    }
    public void showDetails(){
        System.out.println("length: "+length);
        System.out.println("width: "+width);
        System.out.println("area: "+getArea());
        System.out.println("perimeter: "+getPerimeter());
    }
}
