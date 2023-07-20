package org.yup.accountingledger;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.yup.accountingledger.AccountingLedger.displayAllEntries;

public class HomeScreenApp {


    // Displays a menu with different options for the user.
    // This method also consist of calling other classes and their methods as well.
    public static void homeScreenMenu(){
        Scanner bankScanner = new Scanner(System.in);

        boolean appRunning = true;

        while (appRunning) {
            System.out.println("Hello! Welcome to the Big Baller Bank, what would you like to do today? :^) ");
            System.out.println("Please type the appropriate character to proceed :) ");
            System.out.println("(D) ---> Add Deposit");
            System.out.println("(P) ---> Make a Payment");
            System.out.println("(L) ---> Display the Ledger screen");
            System.out.println("(X) --->  Exit");
            String option = bankScanner.nextLine().toUpperCase();

            switch (option) {
                case "D" -> {
                    // User makes a deposit
                    Transactions deposit = Transactions.deposit(bankScanner);
                    Transactions.add(deposit);
                    AccountingLedger.createTransactionsFile();
                    System.out.println("Your Deposit was successful!");
                }
                case "P" -> {
                    // User makes a payment
                    Transactions payment = Transactions.payment(bankScanner);
                      Transactions.add(payment);
                      AccountingLedger.createTransactionsFile();
                     System.out.println("Your Payment was successful!");
                }
                case "L" ->
                    // This displays the Ledger screen
                        AccountingLedger.ledgerScreen(bankScanner);
                case "X" -> {
                    // Exit
                    System.out.println("Exiting application. Thanks for balling with the Big Baller Bank. Goodbye :) ");
                    appRunning = false;
                }
                default -> System.out.println("Please choose an appropriate character.");
            }
        }
      }
    }

