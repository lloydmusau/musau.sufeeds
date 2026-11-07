package com.OOPbasic;

class cylinder extends circle {
    private int height;

    public cylinder(int radius,int height){
        super(radius);
        this.height = height;
    }
    public float getArea(){
        return 44/7*(getRadius()*getRadius())+44/7*getRadius()*height;
    }
    public float getPerimeter(){
        return 4*getRadius()+2*height;
    }
    public void showDetails(){
        System.out.println("radius: "+getRadius());
        System.out.println("height: "+height);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());

    }
}
