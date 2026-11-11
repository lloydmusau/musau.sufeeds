package com.interfaces;
public class main {
    public static void main(String[] args) {
        Employee obj1=new Employee("mary ",8400);
        String msg= obj1.getName() +"earns "+obj1.getPayment();
        System.out.println(msg);

        product obj2=new product("hat ", 200, 3);
        msg= obj2.getName() +"will cost "+obj2.getPayment();
        System.out.println(msg);
    }
}
