package org.yup.accountingledger;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedger {

    private static ArrayList<Transactions> transactions = new ArrayList<>();

    public static void addTransaction(Transactions transaction) {
        transactions.add(transaction);
    }

    public static void displayAllEntries() {
        System.out.println("Displaying all entries:");
        for (Transactions transaction : transactions) {
            System.out.println(transaction.getDescription() + " " + transaction.getAmount());
        }
    }
    public static void displayDepositEntries() {
        System.out.println("Displaying deposit entries:");
        for (Transactions transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction.getDescription() + " " + transaction.getAmount());
            }
        }
    }
    public static void displayPaymentEntries() {
        System.out.println("Displaying payment entries:");
        for (Transactions transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction.getDescription() + " " + transaction.getAmount());
            }
        }
    }
    public static void runReports() {
        System.out.println("Running pre-defined reports:");
        // Implement your reports here
    }

    public static void ledgerScreen(Scanner bankScanner) {
        boolean ledgerScreen = true;
        System.out.println("Welcome to the Ledger Screen! What would you like to do? ");

        while (ledgerScreen) {

            System.out.println("Please type the appropriate character to proceed :) ");
            System.out.println("(A) Display All Entries: ");
            System.out.println("(D) Display only the entries that are deposits into the account: ");
            System.out.println("(P) Display only the negative entries (or payments): ");
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
                    ledgerScreen = false;
                    break;
                default:
                    System.out.println("Please choose an appropriate character.");
            }
        }
     }



}



