/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package electrohub;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Makhaga Mulweli
 * 26034761
 */
public class Products {

    Scanner sc = new Scanner(System.in);

    ArrayList<ReportData> products = new ArrayList<>();

    public void DisplayMenu() {

        System.out.println("BRIGHT FUTURE TECHNOLOGIES APPLICATION");
        System.out.println("************************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");

        String launch = sc.nextLine();

        if (!launch.equals("1")) {
            ExitApplication();
            return;
        }

        int option;

        do {

            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new product.");
            System.out.println("(2) Search for a product.");
            System.out.println("(3) Update a product.");
            System.out.println("(4) Delete a product.");
            System.out.println("(5) Print report.");
            System.out.println("(6) Exit Application.");

            System.out.print("Choose an option: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                sc.next();
            }

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                case 1:
                    CaptureProduct();
                    break;

                case 2:
                    SearchProduct();
                    break;

                case 3:
                    UpdateProduct();
                    break;

                case 4:
                    DeleteProduct();
                    break;

                case 5:
                    PrintReport();
                    break;

                case 6:
                    ExitApplication();
                    break;

                default:
                    System.out.println("Invalid option.");

            }

        } while (option != 6);

    }

    public void CaptureProduct() {

        System.out.println("\nCAPTURE A NEW PRODUCT");
        System.out.println("****************************");

        System.out.print("Enter the product code: ");
        String productCode = sc.nextLine();

        System.out.print("Enter the product name: ");
        String productName = sc.nextLine();

        int choice;

        do {

            System.out.println("Select the product category:");
            System.out.println("Desktop Computer - 1");
            System.out.println("Laptop - 2");
            System.out.println("Tablet - 3");
            System.out.println("Printer - 4");
            System.out.println("Gaming Console - 5");
            System.out.print("Product Category >> ");

            choice = readInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid Category. Please try again.");
            }

        } while (choice < 1 || choice > 5);

        String category;

        switch (choice) {

            case 1:
                category = "Desktop Computer";
                break;

            case 2:
                category = "Laptop";
                break;

            case 3:
                category = "Tablet";
                break;

            case 4:
                category = "Printer";
                break;

            default:
                category = "Gaming Console";
                break;
        }

        System.out.print("Indicate the product warranty. Enter (1) for 6 months or any other key for 2 years. ");

        int warrantyChoice = readInt();

        String warranty = (warrantyChoice == 1) ? "6 Months" : "2 Years";

        double price;
        do {
            System.out.print("Enter the price for " + productName + " >> ");
            price = readDouble();

            if (price < 0) {
                System.out.println("Price cannot be negative. Try again.");
            }
        } while (price < 0);

        int stock;
        do {
            System.out.print("Enter the stock level for " + productName + " >> ");
            stock = readInt();

            if (stock < 0) {
                System.out.println("Stock cannot be negative. Try again.");
            }
        } while (stock < 0);

        System.out.print("Enter the supplier for " + productName + " >> ");
        String supplier = sc.nextLine();

        SaveProduct(productCode, productName, category, warranty, price, stock, supplier);

        System.out.println("Product details has been saved successfully!!!");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");

    }

    public void SaveProduct(String productCode, String productName, String category,
            String warranty, double price, int stock, String supplier) {

        products.add(new ReportData(productCode, productName, category, warranty, price, stock, supplier));

    }

    public void SearchProduct() {

        System.out.print("Please enter the product code to search: ");
        String search = sc.nextLine();

        ReportData found = findProduct(search);

        if (found != null) {

            System.out.println("****************************************************************");
            System.out.println("PRODUCT SEARCH RESULTS");
            System.out.println("****************************************************************");
            System.out.println("PRODUCT CODE:\t\t" + found.getProductCode());
            System.out.println("PRODUCT NAME:\t\t" + found.getProductName());
            System.out.println("PRODUCT WARRANTY:\t" + found.getWarranty());
            System.out.println("PRODUCT CATEGORY:\t" + found.getCategory());
            System.out.printf("PRODUCT PRICE:\t\tR %.0f%n", found.getPrice());
            System.out.println("PRODUCT STOCK LEVELS:\t" + found.getStock());
            System.out.println("PRODUCT SUPPLIER:\t" + found.getSupplier());
            System.out.println("****************************************************************");

        } else {
            System.out.println("The product cannot be located. Invalid Product");
        }

        System.out.print("Enter (1) to launch menu or any other key to exit: ");

    }

    public void UpdateProduct() {

        System.out.print("Please enter the product code to update: ");
        String search = sc.nextLine();

        ReportData found = findProduct(search);

        if (found != null) {

            System.out.print("Update the warranty? (y) Yes, (n) No ");
            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("y")) {

                System.out.println("1. 6 Months");
                System.out.println("2. 2 Years");
                System.out.print("Choice: ");

                int choice = readInt();
                found.setWarranty(choice == 1 ? "6 Months" : "2 Years");

            }

            System.out.print("Update the product price? (y) Yes, (n) No ");
            answer = sc.nextLine();

            if (answer.equalsIgnoreCase("y")) {

                double newPrice;
                do {
                    System.out.print("Enter the new price for " + found.getProductName() + " >> ");
                    newPrice = readDouble();

                    if (newPrice < 0) {
                        System.out.println("Price cannot be negative. Try again.");
                    }
                } while (newPrice < 0);

                found.setPrice(newPrice);

            }

            System.out.print("Update the stock level? (y) Yes, (n) No ");
            answer = sc.nextLine();

            if (answer.equalsIgnoreCase("y")) {

                int newStock;
                do {
                    System.out.print("Enter the new stock level >> ");
                    newStock = readInt();

                    if (newStock < 0) {
                        System.out.println("Stock cannot be negative. Try again.");
                    }
                } while (newStock < 0);

                found.setStock(newStock);

            }

            System.out.println("Product details has been updated successfully!!!");

        } else {
            System.out.println("The product cannot be located. Invalid Product");
        }

        System.out.print("Enter (1) to launch menu or any other key to exit: ");

    }

    public void DeleteProduct() {

        System.out.print("Please enter the product code to delete: ");
        String search = sc.nextLine();

        ReportData found = findProduct(search);

        if (found != null) {

            System.out.print("Are you sure you want to delete this product? (y) Yes, (n) No ");
            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("y")) {

                products.remove(found);
                System.out.println("Product deleted successfully!!!");

            }

        } else {
            System.out.println("The product cannot be located. Invalid Product");
        }

        System.out.print("Enter (1) to launch menu or any other key to exit: ");

    }

    public void PrintReport() {

        double total = 0;

        System.out.println("PRODUCT REPORT");
        System.out.println("====================================================================");

        int i = 1;

        for (ReportData r : products) {

            System.out.println("PRODUCT " + i);
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("PRODUCT CODE >>\t\t" + r.getProductCode());
            System.out.println("PRODUCT NAME >>\t\t" + r.getProductName());
            System.out.println("PRODUCT CATEGORY >>\t" + r.getCategory());
            System.out.println("PRODUCT WARRANTY >>\t" + r.getWarranty());
            System.out.printf("PRODUCT PRICE >>\t%.1f%n", r.getPrice());
            System.out.println("PRODUCT LEVEL >>\t" + r.getStock());
            System.out.println("PRODUCT SUPPLIER >>\t" + r.getSupplier());
            System.out.println("--------------------------------------------------------------------------");

            total += r.getPrice();
            i++;

        }

        System.out.println("====================================================================");
        System.out.println("TOTAL PRODUCT COUNT: " + products.size());
        System.out.printf("TOTAL PRODUCT VALUE: R %.1f%n", total);

        if (!products.isEmpty()) {
            System.out.printf("AVERAGE PRODUCT VALUE: R %.0f%n", total / products.size());
        }

        System.out.println("====================================================================");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");

    }

    public void ExitApplication() {

        System.out.println("Thank you for using Bright Future Technologies Application.");

    }

    private ReportData findProduct(String code) {

        for (ReportData r : products) {
            if (r.getProductCode().equalsIgnoreCase(code)) {
                return r;
            }
        }

        return null;
    }

    private int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private double readDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Invalid input. Enter a number: ");
            sc.next();
        }
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

}