package com.OOPbasic;

public class q4 {
    public static void main(String[] args) {
        int sum=0;
        System.out.println("The odd numbers are: ");
        for(int x=1; x<=100;x+=2){
            System.out.println(x);
            sum+=x;
        }
        System.out.println("the sum is: "+sum);


    }
}