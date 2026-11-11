package com.OOPbasic;

public class mainS2 {
    public static void main(String[] args) {
        motorcycle cycle1=new motorcycle("honda", 29000 );
        cycle1.showDetails();
        System.out.println("");

        scooter2 cycle2=new scooter2("suzuki", 28000, 2);
        cycle2.showDetails();//displays wheels only
    }
}
