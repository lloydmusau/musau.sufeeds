package com.OOPbasic;

import java.util.Scanner;

public class q3 {
    public static void main(String[] args){
        Scanner in= new Scanner(System.in);
        int x;
        System.out.println("Pick an number between 1-5: ");
        x=in.nextInt();

        if(x==2 || x==4) {
            System.out.println("Lucky guess!");
        }
        else if(x>5 || x<1){System.out.println("Your number is not between 1-5");

        }
        else{
            System.out.println("Better luck next time");
        }

    }
}
