package org.yup.accountingledger;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HomeScreenApp {
    public static void main(String[] args) {


        Scanner bankScanner = new Scanner(System.in);

        boolean appRunning = true;


        while (appRunning) {
            System.out.println("Hello! Welcome to the Big Balla Bank, what would you like to do today? :^) ");
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
                //    Transactions.add(deposit);
                    System.out.println("Your Deposit was successful!");

                    // Write transaction to file
                    try {
                        FileWriter writer = new FileWriter("transactions.csv", true);
                        writer.write(deposit + "\n");
                        writer.close();
                    } catch (IOException e) {
                        //   System.out.println("An error occurred while writing to file.");
                        e.printStackTrace();
                    }

                    break;
                case "P":
                    // User makes a payment
                    Transactions payment = Transactions.payment(bankScanner);
                  //  Transactions.add(payment);
                    System.out.println("Your Payment was successful!");

                    try {
                        FileWriter writer = new FileWriter("transactions.csv", true);
                        writer.write(payment + "\n");
                        writer.close();
                    } catch (IOException e) {
                    //    System.out.println("An error occurred while writing to file.");
                        e.printStackTrace();
                    }

                    break;
                    case "L":
                    // This displays the Ledger screen
                    AccountingLedger.ledgerScreen(bankScanner);

                    break;
                case "X":
                    // Exit
                    System.out.println("Exiting application. Thanks for balling with Big Balla Bank. Goodbye :) ");
                    appRunning = false;
                    break;
                default:
                    System.out.println("Please choose an appropriate character.");
            }
        }

    }
}

