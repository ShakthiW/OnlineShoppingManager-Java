package GUI;

import org.example.Clothing;
import org.example.Electronics;
import org.example.Product;
import org.example.WestminsterShoppingManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingPage {
    ArrayList<Product> products;

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

                products.add(product);
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

    ShoppingPage(String userID) {
        products = new ArrayList<>();
        loadProducts();

        new GUI(products);
    }
}
