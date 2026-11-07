package com.OOPbasic;//OOPbasics.input
import java.util.Scanner;
public class input {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int x;
        System.out.println("Enter an interger");
        x=in.nextInt();
        System.out.println("You have entered:"+x);

        //If else
        if (x<1) {
            System.out.println("x is small");
        }else{
            System.out.println("x is large");}

        //for loop
        for(int a=0;a<4;a++){
            System.out.println("Hello");

        }
    }
}


