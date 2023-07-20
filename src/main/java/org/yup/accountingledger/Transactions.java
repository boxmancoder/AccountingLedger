package org.yup.accountingledger;



import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Transactions {

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // The constructor for the Transactions class
    public Transactions(String description, String vendor, double amount) {

        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.date = LocalDate.now();
        this.time = LocalTime.now();

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

    // This method lets the user create a deposit once the app is running.
    // It also asks for various information from the user too.
    public static Transactions deposit(Scanner bankScanner) {
        System.out.println("Enter customer name: ");
        String vendor = bankScanner.nextLine();

        System.out.println("Enter deposit information: ");
        String description = bankScanner.nextLine();

        System.out.println("Enter deposit amount: ");
        double amount = Double.parseDouble(bankScanner.nextLine());

        return new Transactions(description, vendor, amount);
    }

    // This method lets the user create a payment once the app is running.
    // It also asks for various information from the user too.
    public static Transactions payment(Scanner bankScanner) {
        System.out.println("Enter customer name: ");
        String vendor = bankScanner.nextLine();

        System.out.println("Enter withdrawal information: ");
        String description = bankScanner.nextLine();

        System.out.println("Enter withdrawal amount: ");
        double amount = Double.parseDouble(bankScanner.nextLine());

        amount = -amount;
        return new Transactions(description, vendor, amount);

    }

    // This method adds all transactions to a file created called "transactions.csv"
    public static void add(Transactions transaction) {
        // Write transaction to file
        try(FileWriter writer = new FileWriter("transactions.csv", true)){
            writer.write(transaction.toCSVString() + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

    // The CSV string method contains the transaction details in a specific format.
    public String toCSVString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String sign = (amount >= 0) ? "+" : "-";
        double absAmount = Math.abs(amount);
        return String.format("%s | %s | %s | %s | %s%.2f", date, time.format(timeFormatter), vendor, description, sign, absAmount);
    }


 }