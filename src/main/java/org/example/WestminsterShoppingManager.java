package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    // List to store products in the system
    private static List<Product> productList;

    // Constructor to initialize the productList as an ArrayList
    public WestminsterShoppingManager() {
        productList = new ArrayList<>();
    }

    // Override the ShoppingManager interface methods

    // --------- Add products -------------
    private boolean validateProductSize() {
        return productList.size() < 50;
    }

    @Override
    public void addProduct(Product product) {
        if (validateProductSize()) {
            productList.add(product);
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Reached the products limit of 50! Try removing some products before adding");
        }
    }

    // ------------- update products quantity --------------------
    public boolean updateProductQuantity(String productId, int addedQuantity) {
        for (Product product : productList) {
            if (product.getProductID().equals(productId)) {
                int newQuantity = product.getQuantity() + addedQuantity;
                product.setQuantity(newQuantity);
                return true;  // Product found and updated successfully
            }
        }
        return false;  // Product not found
    }


    // ------------ remove products --------------------
    public void removeProductFromSystem(String productId) {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductID().equals(productId)) {
                iterator.remove();
                System.out.println("Product deleted successfully.");
                return;
            }
        }
        System.out.println("Product not found with ID: " + productId);
    }


    // -------------- get products list --------------------
    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }


    // --------------- search products by ID -------------------
    @Override
    public Product getProductByID(String productID) {
        for (Product product : productList) {
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;
    }


    // -------------- print all products --------------------
    @Override
    public void printAllProducts(String productType) {
        System.out.println("** List of Products **\n");

        String header;

        String headerElectric = String.format(
                "| %-15s | %-15s | %-15s | %-20s | %-10s | %-13s |",
                "productID", "productName", "quantity", "price", "brand", "warrantyPeriod");

        String headerClothing = String.format(
                "| %-15s | %-15s | %-15s | %-20s | %-10s | %-13s |",
                "productID", "productName", "quantity", "price", "size", "color");

        // Print header row with formatting
        if (productType.toUpperCase().startsWith("E")) {
            header = headerElectric;
        } else {
            header = headerClothing;
        }

        System.out.println("+-----------------+-----------------+-----------------+----------------------+------------+----------------+");
        System.out.println(header);
        System.out.println("+-----------------+-----------------+-----------------+----------------------+------------+----------------+");

        // Print each product row with formatting
        for (Product product : productList) {
            if (productType.toUpperCase().startsWith("E") && product instanceof Electronics) {
                System.out.println(product.toRowString());
                System.out.println("+-----------------+-----------------+-----------------+----------------------+------------+----------------+");
            } else if (productType.toUpperCase().startsWith("C") && product instanceof Clothing) {
                System.out.println(product.toRowString());
                System.out.println("+-----------------+-----------------+-----------------+----------------------+------------+----------------+");
            }
        }
    }



    // -------------- save all products --------------------
    @Override
    public void saveProducts() {
        try (FileWriter writer = new FileWriter("products.txt")) {
            for (Product product : productList) {
                writer.write(product.saveToString() + "\n");  // Write each product's details to the file
            }
            System.out.println("Products saved successfully to products.txt");
        } catch (IOException e) {
            System.err.println("Error saving products: " + e.getMessage());
        }
    }

    @Override
    public void loadProducts() {
        try (Scanner scanner = new Scanner(new File("products.txt"))) {
            while (scanner.hasNextLine()) {
                String productLine = scanner.nextLine();
                // Split the line into product attributes, trimming any leading/trailing whitespace
                String[] productData = productLine.split(",", -1); // Preserve trailing empty strings
                for (int i = 0; i < productData.length; i++) {
                    productData[i] = productData[i].trim(); // Trim each element
                }

                // Create the appropriate Product object based on the product type
                Product product;
                if (productData[0].startsWith("E")) {
                    product = new Electronics(productData[1], productData[2],
                            Integer.parseInt(productData[3]),
                            Double.parseDouble(productData[4]),
                            productData[5], Integer.parseInt(productData[6]));
                } else {
                    product = new Clothing(productData[1], productData[2],
                            Integer.parseInt(productData[3]),
                            Double.parseDouble(productData[4]),
                            productData[5], productData[6]);
                }

                productList.add(product);
            }
            System.out.println("Products loaded successfully from products.txt");
        } catch (IOException e) {
            if (e.getMessage().contains("No such file or directory")) {
                // File doesn't exist, print a custom message
                System.out.println("No saved products found. Starting with a fresh list.");
            } else {
                // Other error, print the original message
                System.err.println("Error loading products: " + e.getMessage());
            }
        }
    }


    // -------------- counter ----------------
    private int countElectronicProducts() {
        int count = 0;
        for (Product product : productList) {
            if (product.getProductID().startsWith("E")) {
                count++;
            }
        }
        return count;
    }

    private int countClothingProducts() {
        int count = 0;
        for (Product product : productList) {
            if (product.getProductID().startsWith("E")) {
                count++;
            }
        }
        return count;
    }


    // ---------------- get total number of products products ----------------
    public int getTotalProducts() {
        return productList.size();
    }
}