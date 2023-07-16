package org.yup.accountingledger;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AccountingLedger {

    private static ArrayList<Transactions> transactions = new ArrayList<>();
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

    public static void addTransaction(Transactions transaction) {
        transactions.add(transaction);
    }

    public static void displayAllEntries() {
        System.out.println("Displaying all entries:");
        for (Transactions transaction : transactions) {
            System.out.println(transaction.getDate() + "|" + transaction.getTime() + "|" +
                    transaction.getDescription() + "|" + transaction.getVendor() + "|" +
                    transaction.getAmount());
        }
    }
    public static void displayDepositEntries() {
        System.out.println("Displaying deposit entries:");
        for (Transactions transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction.getDate() + "|" + transaction.getTime() + "|" +
                        transaction.getDescription() + "|" + transaction.getVendor() + "|" +
                        transaction.getAmount());
            }
        }
    }
    public static void displayPaymentEntries() {
        System.out.println("Displaying payment entries:");
        for (Transactions transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction.getDate() + "|" + transaction.getTime() + "|" +
                        transaction.getDescription() + "|" + transaction.getVendor() + "|" +
                        transaction.getAmount());
            }
        }
    }
    public static void runReports() {
        System.out.println("Running pre-defined reports:");
        // Implement the reports here
    }

    public static void ledgerScreen(Scanner bankScanner) {
        boolean appRunning = true;
        System.out.println("Welcome to the Ledger Screen! What would you like to do? ");

        while (appRunning) {

            System.out.println("Please enter the appropriate character to proceed :) ");
            System.out.println("(A) Display All Entries: ");
            System.out.println("(D) Display Deposits only: ");
            System.out.println("(P) Display only negative entries (or payments): ");
            System.out.println("(R) Run pre-defined reports: ");
            System.out.println("(H) Return to Home menu: ");
            String option = bankScanner.nextLine().toUpperCase();

            switch (option) {
                case "A":
                    displayAllEntries();
                    break;
                case "D":
                    displayDepositEntries();
                    break;
                case "P":
                    displayPaymentEntries();
                    break;
                case "R":
                    runReports();
                    break;

                case "H":
                    // Exit
                    System.out.println("Returning to Home screen: ");
                    System.out.println("---------------------------------------------------");
                    appRunning = false;
                    break;
                default:
                    System.out.println("Please choose an appropriate character.");
            }
        }
     }

    public static void createTransactionsFile() {
        try {
            FileWriter saveTransactions = new FileWriter("transactions.csv");
            for (Transactions transaction : transactions) {
                LocalDate date = transaction.getDate();
                String dateString = dateFormatter.format(date);
                String description = transaction.getDescription();
                String vendor = transaction.getVendor();
                double amount = transaction.getAmount();
                saveTransactions.write(dateString + "|" + description + "|" + vendor + "|" + amount + "\n");
            }
            saveTransactions.close();
            System.out.println("transactions.csv file created successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while creating transactions.csv file: " + e.getMessage());
        }
    }

}



