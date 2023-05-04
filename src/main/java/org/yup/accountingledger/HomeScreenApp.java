package org.yup.accountingledger;

import java.util.Scanner;

public class HomeScreenApp {
    public static void main(String[] args) {
        Scanner bankScanner = new Scanner(System.in);

        boolean appRunning = true;
        System.out.println("Hello! Welcome to Big Balla Bank, what would you like to do today? ");

        while (appRunning) {

            System.out.println("Please type the appropriate character to proceed :) ");
            System.out.println("(D) Add Deposit");
            System.out.println("(P) Make a Payment");
            System.out.println("(L) Display the Ledger screen");
            System.out.println("(X) Exit");
            String option = bankScanner.nextLine().toUpperCase();

            switch (option) {
                case "D":
                    // User makes a deposit
                    Transactions deposit = Transactions.deposit(bankScanner);
                    Transactions.add(deposit);
                    System.out.println("Your Deposit was successful!");

                    break;
                case "P":
                    // User makes a payment
                    Transactions payment = Transactions.payment(bankScanner);
                    Transactions.add(payment);
                    System.out.println("Your Payment was successful!");

                    break;
                    case "L":
                    // This displays or returns to the Ledger screen
                    AccountingLedger.ledgerScreen(bankScanner);
                    System.out.println("Hello! Welcome to Big Balla Bank, what would you like to do today? ");

                    break;
                case "X":
                    // Exit
                    System.out.println("Exiting application. Thanks for choosing Big Balla Bank for your banking needs. Goodbye :) ");
                    appRunning = false;
                    break;
                default:
                    System.out.println("Please choose an appropriate character.");
            }
        }

    }
}

