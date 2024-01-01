package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
    // List to store products in the shopping cart
    private final HashMap<Product, Integer> cartItems;

    // constructor
    public ShoppingCart() {
        cartItems = new HashMap<>();
    }

    // Adds a product to the shopping cart
    public void addProduct(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            cartItems.put(product, cartItems.get(product) + quantity);
        } else {
            cartItems.put(product, quantity);
        }
        System.out.println("Product added to the shopping cart.");
    }

    // Removes a product from the shopping cart based on the provided product ID
    // Prints a success message if the product is found and removed, or a message if the product is not found
    public void removeProduct(String productId) {
        Iterator<Product> iterator = cartItems.keySet().iterator();  // Iterate through the product keys
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductID().equals(productId)) {
                iterator.remove();
                System.out.println("Product removed from the shopping cart.");
                return;
            }
        }
        System.out.println("Product not found in the shopping cart with ID: " + productId);
    }

    // Calculates and returns the total cost of all products in the shopping cart
    public double getTotalPrice() {
        double totalCost = 0.0;
        for (Product product : cartItems.keySet()) {
            int quantity = cartItems.get(product);
            totalCost += product.getPrice() * quantity;  // Calculate total cost based on price and quantity
        }
        return totalCost;
    }

    // Print details of all products in the shopping cart
    public void printCartItems() {
        System.out.println("Shopping Cart Items:");
        for (Product product : cartItems.keySet()) {  // Iterate through the product keys
            int quantity = cartItems.get(product);
            System.out.println(product.toString() + " (Quantity: " + quantity + ")");
        }
    }

    // Clears all products from the shopping cart
    public void clearCart() {
        cartItems.clear();
        System.out.println("Shopping cart cleared.");
    }

    public HashMap<Product, Integer> getProducts(){
        return cartItems;
    }
}
