package com.interfaces;
public class Employee implements payable{
    private String name;
    private float grossSal;

    public Employee(String name, float grossSal){
        this.name = name;
        this.grossSal=grossSal;
    }
    public String getName() {
        return name;
    }
    @Override
    public float getPayment() {
        float netSalary=(float)(this.grossSal*0.7);
        return netSalary;
    }
}
