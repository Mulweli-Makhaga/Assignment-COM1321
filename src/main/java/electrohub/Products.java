/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package electrohub;

/**
 *
 * @author Makhaga Mulweli
 * 26034761
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Products {

    Scanner sc = new Scanner(System.in);

    ArrayList<ReportData> products = new ArrayList<>();

    public void DisplayMenu() {

        int option;

        do {

            System.out.println("************************************");
            System.out.println("ELECTRO HUB");
            System.out.println("************************************");

            System.out.println("1. Capture Product");
            System.out.println("2. Search Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Print Report");
            System.out.println("6. Exit");

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

        System.out.println("\nCAPTURE PRODUCT");

        System.out.print("Enter Product Code: ");
        String productCode = sc.nextLine();

        System.out.print("Enter Product Name: ");
        String productName = sc.nextLine();

        int choice;

        do {

            System.out.println("\nSelect Product Category");
            System.out.println("1. Desktop Computer");
            System.out.println("2. Laptop");
            System.out.println("3. Tablet");
            System.out.println("4. Printer");
            System.out.println("5. Gaming Console");
            System.out.print("Choice: ");

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

        System.out.println("\nWarranty");
        System.out.println("1. 6 Months");
        System.out.println("2. 2 Years");
        System.out.print("Choice: ");

        int warrantyChoice = readInt();

        String warranty = (warrantyChoice == 1) ? "6 Months" : "2 Years";

        double price;
        do {
            System.out.print("Enter Product Price: ");
            price = readDouble();

            if (price < 0) {
                System.out.println("Price cannot be negative. Try again.");
            }
        } while (price < 0);

        int stock;
        do {
            System.out.print("Enter Stock Level: ");
            stock = readInt();

            if (stock < 0) {
                System.out.println("Stock cannot be negative. Try again.");
            }
        } while (stock < 0);

        System.out.print("Enter Supplier: ");
        String supplier = sc.nextLine();

        products.add(new ReportData(productCode, productName, category, warranty, price, stock, supplier));

        System.out.println("\nProduct successfully captured!\n");

    }

    public void SearchProduct() {

        System.out.print("Enter Product Code to Search: ");
        String search = sc.nextLine();

        ReportData found = findProduct(search);

        if (found != null) {

            System.out.println("\nPRODUCT FOUND");
            printProduct(found);

        } else {
            System.out.println("Product not found.");
        }

    }

    public void UpdateProduct() {

        System.out.print("Enter Product Code to Update: ");
        String search = sc.nextLine();

        ReportData found = findProduct(search);

        if (found != null) {

            System.out.println("Product Found!");

            System.out.print("Update Warranty? (Y/N): ");
            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("Y")) {

                System.out.println("1. 6 Months");
                System.out.println("2. 2 Years");
                System.out.print("Choice: ");

                int choice = readInt();
                found.setWarranty(choice == 1 ? "6 Months" : "2 Years");

            }

            System.out.print("Update Price? (Y/N): ");
            answer = sc.nextLine();

            if (answer.equalsIgnoreCase("Y")) {

                double newPrice;
                do {
                    System.out.print("Enter New Price: ");
                    newPrice = readDouble();

                    if (newPrice < 0) {
                        System.out.println("Price cannot be negative. Try again.");
                    }
                } while (newPrice < 0);

                found.setPrice(newPrice);

            }

            System.out.print("Update Stock? (Y/N): ");
            answer = sc.nextLine();

            if (answer.equalsIgnoreCase("Y")) {

                int newStock;
                do {
                    System.out.print("Enter New Stock: ");
                    newStock = readInt();

                    if (newStock < 0) {
                        System.out.println("Stock cannot be negative. Try again.");
                    }
                } while (newStock < 0);

                found.setStock(newStock);

            }

            System.out.println("Product Updated Successfully!");

        } else {
            System.out.println("Product not found.");
        }

    }

    public void DeleteProduct() {

        System.out.print("Enter Product Code to Delete: ");
        String search = sc.nextLine();

        ReportData found = findProduct(search);

        if (found != null) {

            System.out.print("Are you sure? (Y/N): ");
            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("Y")) {

                products.remove(found);
                System.out.println("Product Deleted Successfully!");

            }

        } else {
            System.out.println("Product not found.");
        }

    }

    public void PrintReport() {

        double total = 0;

        System.out.println("\n========== PRODUCT REPORT ==========");

        for (ReportData r : products) {

            System.out.println("--------------------------------");
            printProduct(r);

            total += r.getPrice();

        }

        System.out.println("--------------------------------");
        System.out.println("Total Products : " + products.size());
        System.out.printf("Total Value    : R%.2f%n", total);

        if (!products.isEmpty()) {
            System.out.printf("Average Value  : R%.2f%n", total / products.size());
        }
    }

    public void ExitApplication() {

        System.out.println("Thank you for using Electro Hub.");

    }

    private ReportData findProduct(String code) {

        for (ReportData r : products) {
            if (r.getProductCode().equalsIgnoreCase(code)) {
                return r;
            }
        }

        return null;
    }

    private void printProduct(ReportData r) {
        System.out.println("Code      : " + r.getProductCode());
        System.out.println("Name      : " + r.getProductName());
        System.out.println("Category  : " + r.getCategory());
        System.out.println("Warranty  : " + r.getWarranty());
        System.out.printf("Price     : R%.2f%n", r.getPrice());
        System.out.println("Stock     : " + r.getStock());
        System.out.println("Supplier  : " + r.getSupplier());
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
    

