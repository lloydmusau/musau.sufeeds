package com.OOPbasic;

class scooter extends motorcycle {
    private int wheels;

    public scooter (String name, float price, int wheels) {
        super(name, price); //inherits name & price from class OOPbasics.motorcycle
        this.wheels = wheels;
    }
    public void showAttributes(){
        System.out.println("wheels: " + wheels);
        super.showDetails(); //inherits showDetails from class OOPbasics.motorcycle

    }
}


