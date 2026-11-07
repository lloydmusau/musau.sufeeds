package com.OOPbasic;

public class Account {
    public String accountNumber;
    private double balance;

    public Account(String accountNo , double balance) {
        this.accountNumber = accountNo;
        this.balance = balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;}

    public double getBalance() {
        return balance;
    }
    public void showDetails() {
        System.out.println("OOPbasics.Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}


