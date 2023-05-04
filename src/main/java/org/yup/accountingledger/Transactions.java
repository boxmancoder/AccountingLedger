package org.yup.accountingledger;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Transactions {

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transactions(String description, String vendor, double amount) {

        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }

    public static void add(Transactions deposit) {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static Transactions deposit(Scanner bankScanner) {
        System.out.println("Enter customer name: ");
        String vendor = bankScanner.nextLine();

        System.out.println("Will you be depositing cash or check?: ");
        String description = bankScanner.nextLine();

        System.out.println("Enter the amount you wish to deposit: ");
        double amount = Double.parseDouble(bankScanner.nextLine());

        Transactions transactions = new Transactions(description, vendor, amount);
        return transactions;
    }

    public static Transactions payment(Scanner bankScanner) {
        System.out.println("Enter customer name: ");
        String vendor = bankScanner.nextLine();

        System.out.println("Enter payment description: ");
        String description = bankScanner.nextLine();

        System.out.println("Enter the amount you wish to pay: ");
        double amount = Double.parseDouble(bankScanner.nextLine());

        return new Transactions(description, vendor, amount);

    }

 }