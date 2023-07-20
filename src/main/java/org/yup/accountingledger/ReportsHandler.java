package org.yup.accountingledger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ReportsHandler {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private List<Transactions> transactions;

    public ReportsHandler(ArrayList<Transactions> transactions) {
    }

    public void runMonthToDateReport2() {
        runMonthToDateReport();
    }
    private void runMonthToDateReport() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        System.out.println("Month To Date Report:");

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");

                    if (parts.length >= 5) {
                        LocalDate transactionDate = LocalDate.parse(parts[0].trim());
                        int transactionMonth = transactionDate.getMonthValue();
                        int transactionYear = transactionDate.getYear();

                        if (transactionMonth == currentMonth && transactionYear == currentYear) {
                            String time = parts[1].trim();
                            String vendor = parts[2].trim();
                            String description = parts[3].trim();
                            double amount = Double.parseDouble(parts[4].trim());

                            String sign = (amount >= 0) ? "+" : "-";
                            String formattedAmount = String.format("%.2f", Math.abs(amount));

                            System.out.println(transactionDate + " | " + time + " | " + vendor + " | " + description + " | " + sign + formattedAmount);
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

    public void runPreviousMonthReport2(){
        runPreviousMonthReport();
    }
    private void runPreviousMonthReport() {
        System.out.println("Running Previous Month report...");

        LocalDate currentDate = LocalDate.now();

        LocalDate previousMonthDate = currentDate.minusMonths(1);
        int previousMonth = previousMonthDate.getMonthValue();
        int previousYear = previousMonthDate.getYear();

        boolean hasTransactions = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");

                    if (parts.length >= 5) {
                        LocalDate transactionDate = LocalDate.parse(parts[0].trim(), dateFormatter);
                        int transactionMonth = transactionDate.getMonthValue();
                        int transactionYear = transactionDate.getYear();

                        if (transactionMonth == previousMonth && transactionYear == previousYear) {
                            String time = parts[1].trim();
                            String vendor = parts[2].trim();
                            String description = parts[3].trim();
                            double amount = Double.parseDouble(parts[4].trim());
                            String sign = (amount >= 0) ? "+" : "";
                            String formattedAmount = String.format("%.2f", Math.abs(amount));

                            System.out.println(transactionDate + " | " + time + " | " + vendor + " | " + description + " | " + sign + formattedAmount);
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
        if (!hasTransactions) {
            System.out.println("Sorry, No transactions found for the previous month. :( ");
        }
    }


    public void runYearToDateReport2(){
        runYearToDateReport();
    }
    private void runYearToDateReport() {
        System.out.println("Running Year To Date report...");

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        double totalAmount = 0.0;
        boolean hasTransactions = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");

                    if (parts.length >= 5) {
                        LocalDate transactionDate = LocalDate.parse(parts[0].trim(), dateFormatter);
                        int transactionYear = transactionDate.getYear();

                        if (transactionYear == currentYear) {
                            double amount = Double.parseDouble(parts[4].trim());
                            totalAmount += amount;
                            hasTransactions = true;

                            String time = parts[1].trim();
                            String vendor = parts[2].trim();
                            String description = parts[3].trim();
                            String sign = (amount >= 0) ? "+" : "-";
                            String formattedAmount = String.format("%.2f", Math.abs(amount));

                            System.out.println(transactionDate + " | " + time + " | " + vendor + " | " + description + " | " + sign + formattedAmount);
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

        if (hasTransactions) {
            String formattedTotalAmount = String.format("%.2f", totalAmount);
            System.out.println("Total amount for the year: " + formattedTotalAmount);
        } else {
            System.out.println("No transactions found for the current year. :( ");
        }
    }


    public void runPreviousYearReport2(){
        runPreviousYearReport();
    }
    private void runPreviousYearReport() {
        System.out.println("Running Previous Year report...");

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int previousYear = currentYear - 1;
        double totalAmount = 0.0;
        boolean hasTransactions = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");

                    if (parts.length >= 5) {
                        LocalDate transactionDate = LocalDate.parse(parts[0].trim(), dateFormatter);
                        int transactionYear = transactionDate.getYear();

                        if (transactionYear == previousYear) {
                            double amount = Double.parseDouble(parts[4].trim());
                            totalAmount += amount;
                            hasTransactions = true;

                            String time = parts[1].trim();
                            String vendor = parts[2].trim();
                            String description = parts[3].trim();
                            String sign = (amount >= 0) ? "+" : "-";
                            String formattedAmount = String.format("%.2f", Math.abs(amount));

                            System.out.println(transactionDate + " | " + time + " | " + vendor + " | " + description + " | " + sign + formattedAmount);
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

        if (hasTransactions) {
            String formattedTotalAmount = String.format("%.2f", totalAmount);
            System.out.println("Total amount for the previous year: " + formattedTotalAmount);
        } else {
            System.out.println("No transactions found for the previous year. :( ");
        }
    }


    public void runVendorSearchReport2(){
        runVendorSearchReport();
    }
    private void runVendorSearchReport() {
        System.out.println("Running Vendor Search report...");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the vendor name: ");
        String vendorName = scanner.nextLine().trim();

        boolean hasTransactions = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");

                    if (parts.length >= 5) {
                        String vendor = parts[2].trim();

                        if (vendor.equalsIgnoreCase(vendorName)) {
                            double amount = Double.parseDouble(parts[4].trim());
                            hasTransactions = true;

                            String date = parts[0].trim();
                            String time = parts[1].trim();
                            String description = parts[3].trim();
                            String sign = (amount >= 0) ? "+" : "-";
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

        if (!hasTransactions) {
            System.out.println("No transactions found for the vendor: " + vendorName);
        }
    }

}
