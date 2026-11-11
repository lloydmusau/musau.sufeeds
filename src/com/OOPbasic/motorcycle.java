package com.OOPbasic;

public class motorcycle {
    //attributes
    private String name;
    private float price;

//constructor1
    public motorcycle (String name) {
        this.name = name;
    }
//constructor2
    public motorcycle (String name, float price) {
        this.name = name;
        this.price = price;
    }

//methods
    public void setPrice( float price) {
        this.price = price;

    }
    public float getPrice() {
        return price;
    }
    public void showDetails() {
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
    }
}
