package com.interfaces;

public class product implements payable{
    private String name;
    private float price;
    private int quantity;

    public product(String name,float price,int quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }
    public String getName() {
        return name;
    }
    @Override
    public float getPayment(){
        float totalPrice=this.quantity*this.price;
        return totalPrice;
    }
}
