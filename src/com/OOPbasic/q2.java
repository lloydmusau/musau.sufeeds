package com.OOPbasic;

import java.util.Scanner;

public class q2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int x;
        System.out.println("Enter 1st interger");
        x=in.nextInt();
        int y;
        System.out.println("Enter 2nd interger ");
        y=in.nextInt();
        int z;
        System.out.println("Enter 3rd interger");
        z=in.nextInt();
        int sum;
        sum=y+z+x;
        System.out.println("The Sum is:"+sum);
        int av;
        av=sum/3;
        System.out.println("The average is : "+av);
        int prd;
        prd=x*y*z;
        System.out.println("The product is: "+prd);
    }

    }
