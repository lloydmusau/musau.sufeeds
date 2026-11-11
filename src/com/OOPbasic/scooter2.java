package com.OOPbasic;

class scooter2 extends motorcycle {
    private int wheels;

    public scooter2 (String name, float price, int wheels) {
        super(name, price); //inherits name & price from class OOPbasics.motorcycle
        this.wheels = wheels;
    }
    public void showDetails() { //polymorphism
        System.out.println("wheels: " + wheels);

    }
}
