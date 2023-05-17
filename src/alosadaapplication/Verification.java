/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alosadaapplication;

/**
 *
 * @author L14Y09W07
 */
public class Verification {
    private String customerUsername;
    private String accountNumber;
    private double amount;
    private String typeoftransaction;
    private int status;

    public Verification() {
    }

    public Verification(String customerUsername, String accountNumber, double amount, String typeoftransaction, int status) {
        this.customerUsername = customerUsername;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.typeoftransaction = typeoftransaction;
        this.status = status;
    }

    public Verification(String customerUsername, String accountNumber, double amount, String typeoftransaction) {
        this.customerUsername = customerUsername;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.typeoftransaction = typeoftransaction;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTypeoftransaction() {
        return typeoftransaction;
    }

    public void setTypeoftransaction(String typeoftransaction) {
        this.typeoftransaction = typeoftransaction;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
