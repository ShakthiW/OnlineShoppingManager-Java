package org.example;

public class Clothing extends Product {
    protected String size;
    protected String color;

    // Constructor
    public Clothing(String productID, String productName, int quantity, double price, String size, String color) {
        super(productID, productName, quantity, price);

        // Validate size
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Size cannot be empty");
        }
        this.size = size;

        // Validate color
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        this.color = color;
    }

    // ------------ Getters starts ------------------
    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }
    // ------------- Getters ends -------------------


    // ------------ Setters starts ------------------
    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }
    // ------------- Setters ends -------------------


    // override toString method for clothing class
    @Override
    public String toString() {
        return  "\n------- Clothing Details --------\n" +
                "Product ID      : " + productID + "\n" +
                "Product Name    : " + productName + "\n" +
                "Quantity        : " + quantity + "\n" +
                "Price           : " + price + "\n" +
                "Size            : " + size + "\n" +
                "Color           : " + color + "\n";
    }

    @Override
    public String toRowString() {
        return String.format(
                "| %-15s | %-15s | %-15s | %-20s | %-10s | %-10s |",
                productID, productName, quantity, price, size, color);
    }

    @Override
    public String saveToString() {
        return "Clothing, " + productID + ", " + productName + ", " + quantity + ", " + price + ", " + color + ", " + size;
    }

    @Override
    public String getProductCategory() {
        return "Clothing";
    }

    @Override
    public String getInfo() {
        return size + ", " + color;
    }
}
