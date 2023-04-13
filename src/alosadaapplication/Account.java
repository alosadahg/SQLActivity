/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alosadaapplication;

/**
 *
 * @author L14Y09W07
 */
public class Account {
    private String accountNumber;
    private double balance;
    private String username;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Account() {
        setBalance(1000);
    }

    public Account(String accountNumber, double balance, String username) {
        setAccountNumber(accountNumber);
        setBalance(balance);
        setUsername(username);
    }
    
    
    
}
