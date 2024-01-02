package org.example;

import GUI.Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManagerConsole {
    // Display Menu
    private static void displayMenu() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Please select an option:");
        System.out.println("1) Add new product");
        System.out.println("2) Delete existing product");
        System.out.println("3) Re-stock product");
        System.out.println("4) Print the list of products");
        System.out.println("5) Save to file");
        System.out.println("6) Open customer GUI");
        System.out.println("\n\t0) Quit");
        System.out.println("-------------------------------------------------");
    }

    // validate the input for the menu
    private static boolean validate(int choice){
        return choice <= -1 || choice >= 7;
    }

    // Add products to the system methods
    private static String productTypeValidation(Scanner input) {
        while (true) {
            System.out.println("What kind of a product are you trying to add?");
            System.out.println("Enter E for an electronic item \nEnter C for a clothing item");
            System.out.print("Enter your response here (E/C): ");
            String response = input.next();

            if (response.equalsIgnoreCase("E")) {
                return "electronic";
            } else if (response.equalsIgnoreCase("C")) {
                return "clothing";
            } else {
                System.out.println("Not a valid product type. Choose E or C!");
            }
        }
    }


    // ---------------------- get details ------------------------
    public static String getProductDetails(Scanner input, String message) {
        System.out.print("\n" + message + " ");
        return input.next();
    }

    public static int getProductDetailsInt(Scanner input, String message) {
        System.out.print("\n" + message + " ");
        return input.nextInt();
    }

    public static double getProductDetailsDouble(Scanner input, String message) {
        System.out.print("\n" + message + " ");
        return input.nextDouble();
    }
    // ----------------------------------------------------------------


    public static String validateProductID(Scanner input, String productType, List<Product> productList) {
        while (true) {
            String productID = getProductDetails(input, "Please Enter The ProductID:");

            // Validate format
            if (!productID.matches("[EC]{1}\\d{3}")) {
                System.out.println("Invalid product ID format. Please enter an ID starting with E or C (capitalized) followed by 3 numbers (e.g., E001, C237).");
                continue;
            }

            // Capitalize the first letter
            productID = productID.toUpperCase();

            // Check if the ID matches the product type
            char expectedPrefix = productType.toUpperCase().charAt(0);
            if (productID.charAt(0) != expectedPrefix) {
                System.out.println("Product ID prefix does not match the product type. Please use E for electronics and C for clothing.");
                continue;
            }

            // Check for duplicate IDs
            boolean idExists = false;
            for (Product product : productList) {
                if (product.getProductID().equals(productID)) {
                    System.out.println("Product ID already exists. Please choose a different ID.");
                    idExists = true;
                    break;
                }
            }

            if (!idExists) {
                return productID;  // Valid ID, return it
            }
        }
    }



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // load the content if there is any text file with system data
        shoppingManager.loadProducts();

        System.out.println("=======================================");  //Welcome msg
        System.out.println("|                                     |");
        System.out.println("|             Welcome to              |");
        System.out.println("|        Westminster Shopping!        |");
        System.out.println("|                                     |");
        System.out.println("=======================================");


        // Display menu
        displayMenu();

        // load products to the system
//        try {
//            shoppingManager.loadProducts();
//        } catch (Exception e) {
//            System.out.println("something happened to load products.... Error - " + e;
//        }


        boolean quit = false;

        while (!quit) {
            System.out.print("\nEnter Your Choice Here: ");
            int choice;
            try {
                choice = input.nextInt();
                input.nextLine(); // consume the leftover newline character

                if (validate(choice)) {
                    System.out.println("Please Enter a valid choice between 0 - 4");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please Enter Number...");
                input.nextLine(); // consume the leftover newline character
                continue;
            }

            switch (choice) {
                case 1:
                    // Add new product to the system
                    System.out.println("\n----- Add Products -----\n");

                    // get the product type
                    String productType = productTypeValidation(input);

                    // Get the inputs form the manager
                    String productID = validateProductID(input, productType, shoppingManager.getAllProducts()); // get + validate the productID

                    String productName = getProductDetails(input, "Please Enter The Product Name:");
                    int quantity = getProductDetailsInt(input, "How many "
                            + productName + "s are adding to the system:");
                    double price = getProductDetailsDouble(input, "How much is one " + productName + ":");

                    // add the products separately for clothing and electronics
                    if (productType.toUpperCase().startsWith("E")) {
                        String brand = getProductDetails(input, "What is the product brand:");
                        int warrantyPeriod = getProductDetailsInt(input, "How long is the warranty for one "
                                + productName + " (in months):");

                        // create an electronic product based on the details
                        Electronics electronicProduct = new Electronics(productID, productName, quantity, price, brand, warrantyPeriod);
                        shoppingManager.addProduct(electronicProduct);
                        System.out.println(quantity + " " + productName + "/s successfully added to the system" );

                    } else if (productID.toUpperCase().startsWith("C")) {
                        String size = getProductDetails(input, "What is the size of the " + productName + " :");
                        String color = getProductDetails(input, "What is the color of the " + productName + " :");

                        // create a clothing product based on the details
                        Clothing clothingProduct = new Clothing(productID, productName, quantity, price, size, color);
                        shoppingManager.addProduct(clothingProduct);
                        System.out.println(quantity + " " + productName + "/s successfully added to the system" );
                    }
                    break;

                case 2:
                    // Remove a product from the system
                    System.out.print("ProductID: ");
                    String productId = input.next();
                    shoppingManager.removeProductFromSystem(productId);
                    break;

                case 3:
                    // update existing product
//                    System.out.println("** Electronic products **\n");
                    shoppingManager.printAllProducts("Electronics");
//                    System.out.println("\n** Clothing products **\n");
                    shoppingManager.printAllProducts("Clothing");
                    String productIdToUpdate = getProductDetails(input, "Enter the product ID of the product you want to update the quantity:");

                    int addingAmount = getProductDetailsInt(input, "How many products are you going tp add:");

                    boolean productFound = shoppingManager.updateProductQuantity(productIdToUpdate, addingAmount);

                    if (productFound) {
                        System.out.println("Product quantity updated successfully.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    // Print a list of products
                    System.out.print("Which products you want to print? (Clothing ['C'], Electronics ['E']): ");
                    String productTypeToPrint = input.next();
                    shoppingManager.printAllProducts(productTypeToPrint);

                    break;

                case 5:
                    // Save products to a file
                    shoppingManager.saveProducts();
                    break;

                case 6:
                    // Open GUI
                    Main main = new Main();
                    break;

                case 0:
                    //quit the loop and the program
                    quit = true;
                    break;

                default:
                    System.out.println("Not a valid response. Try again...");
            }

            displayMenu();
        }
    }
}
