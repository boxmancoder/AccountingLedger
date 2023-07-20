package org.yup.accountingledger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedger {

    private ReportsHandler reportsHandler;
    private static ArrayList<Transactions> transactions = new ArrayList<>();
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");

    public static void addTransaction(Transactions transaction) {
        transactions.add(transaction);
    }


    // This method reads and displays all transactions from my csv file when ran.
    public static void displayAllEntries() {
        System.out.println("Displaying all entries:");

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");

                    if (parts.length >= 5) {
                        String date = parts[0].trim();
                        String time = parts[1].trim();
                        String vendor = parts[2].trim();
                        String description = parts[3].trim();
                        double amount = Double.parseDouble(parts[4].trim());
                        String sign = (amount >= 0) ? "+" : "";

                        String formattedAmount = String.format("%.2f", amount);

                        System.out.println(date + " | " + time + " | " + vendor + " | " + description + " | " + sign + formattedAmount);
                    } else {
                        System.out.println("Invalid entry: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file.");
            e.printStackTrace();
        }
    }

    // This method reads and displays all deposit transactions only from my csv file when ran.
    public static void displayDepositEntries() {
        System.out.println("Displaying deposit entries:");

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");

                    if (parts.length >= 5) {
                        String date = parts[0].trim();
                        String time = parts[1].trim();
                        String vendor = parts[2].trim();
                        String description = parts[3].trim();
                        double amount = Double.parseDouble(parts[4].trim());

                        if (amount >= 0) {
                            String sign = "+";
                            String formattedAmount = String.format("%.2f", amount);

                            System.out.println(date + " | " + time + " | " + vendor + " | " + description + " | " + sign + formattedAmount);
                        }
                    } else {
                        System.out.println("Invalid entry: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file.");
            e.printStackTrace();
        }
    }

    //  // This method reads and displays all payment/withdrawal transactions from my csv file when ran.
    public static void displayPaymentEntries() {
        System.out.println("Displaying payment entries:");

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");

                    if (parts.length >= 5) {
                        String date = parts[0].trim();
                        String time = parts[1].trim();
                        String vendor = parts[2].trim();
                        String description = parts[3].trim();
                        double amount = Double.parseDouble(parts[4].trim());

                        if (amount < 0) {
                            String sign = "-";
                            String formattedAmount = String.format("%.2f", Math.abs(amount));

                            System.out.println(date + " | " + time + " | " + vendor + " | " + description + " | " + sign + formattedAmount);
                        }
                    } else {
                        System.out.println("Invalid entry: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file.");
            e.printStackTrace();
        }
    }


    // A menu created which gives users different options to choose from.
    public static void ledgerScreen(Scanner bankScanner) {
        boolean appRunning = true;
        System.out.println("Welcome to the Ledger Screen! What would you like to do? ");

        while (appRunning) {

            System.out.println("Please enter the appropriate character to proceed :) ");
            System.out.println("(A) <--- Display All Entries: ");
            System.out.println("(D) <--- Display Deposits only: ");
            System.out.println("(P) <--- Display only negative entries (or payments): ");
            System.out.println("(R) <--- Run pre-defined reports: ");
            System.out.println("(H) <--- Return to Home menu: ");
            String option = bankScanner.nextLine().toUpperCase();

            switch (option) {
                case "A" -> displayAllEntries();
                case "D" -> displayDepositEntries();
                case "P" -> displayPaymentEntries();
                case "R" -> runReportsScreen();
                case "H" -> {
                    // Exit
                    System.out.println("Returning to Home screen: ");
                    System.out.println("---------------------------------------------------");
                    appRunning = false;
                }
                default -> System.out.println("Please choose an appropriate character.");
            }
        }
     }

     // This method created my transactions.csv file. Where later I used to read from , call from and add to.
    public static void createTransactionsFile() {
        if(transactions.isEmpty()){
            System.out.println("Successfully added to Transactions! :) ");
               return; }
        try (FileWriter saveTransactions = new FileWriter("transactions.csv")){
            for (Transactions transaction : transactions) {
                LocalDate date = transaction.getDate();
                String dateString = dateFormatter.format(date);
                String vendor = transaction.getVendor();
                String description = transaction.getDescription();
                double amount = transaction.getAmount();
                String sign = (amount >= 0) ? "+" : "-";
                double absAmount = Math.abs(amount);
                saveTransactions.write(dateString + "|" + description + "|" + vendor + "|" + sign + absAmount + "\n");
            }
            System.out.println("transactions.csv file created successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while creating transactions.csv file: " + e.getMessage());
        }
    }

    // Another menu created which gives users access to different options.
    // This method is able to call the ReportsHandler class and their methods as well.
    public static void runReportsScreen() {
        ReportsHandler reportsHandler = new ReportsHandler(transactions);
        Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the Reports Menu! ");
            System.out.println("(1) ---> Month To Date");
            System.out.println("(2) ---> Previous Month");
            System.out.println("(3) ---> Year To Date");
            System.out.println("(4) ---> Previous Year");
            System.out.println("(5) ---> Search by Vendor");
            System.out.println("(B) ---> Return to the Ledger home screen");
            System.out.println("(H) ---> Return to the Home screen");
            System.out.println("Please chose an appropriate character: ");

            String option = scanner.nextLine().toUpperCase();

        switch (option) {
            case "1" -> reportsHandler.runMonthToDateReport2();
            case "2" -> reportsHandler.runPreviousMonthReport2();
            case "3" -> reportsHandler.runYearToDateReport2();
            case "4" -> reportsHandler.runPreviousYearReport2();
            case "5" -> reportsHandler.runVendorSearchReport2();
            case "B" ->  {
                System.out.println("Returning to the Ledger home screen.");
                System.out.println("---------------------------------------------------");
            }
            case "H" -> {
                System.out.println("Returning to the main Home screen.");
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
}



