package org.example;

public class Electronics extends Product {
    protected String brand;
    protected int warrantyPeriod;

    // constructor
    public Electronics(String productID, String productName, int quantity, double price, String brand, int warrantyPeriod) {
        super(productID, productName, quantity, price);

        // Validate brand
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        this.brand = brand;

        // Validate warranty period
        if (warrantyPeriod < 0) {
            throw new IllegalArgumentException("Warranty period cannot be a negative");
        }
        this.warrantyPeriod = warrantyPeriod;
    }


    // ------------ Getters starts ------------------
    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    // ------------- Getters ends -------------------


    // ------------ Setters starts ------------------
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    // ------------- Setters ends -------------------


    // override the toString method
    @Override
    public String toString() {
        return "\n------- Electronics Details --------\n" +
                "Product ID      : " + productID + "\n" +
                "Product Name    : " + productName + "\n" +
                "Quantity        : " + quantity + "\n" +
                "Price           : " + price + "\n" +
                "Brand           : " + brand + "\n" +
                "Warranty Period : " + warrantyPeriod + " months\n";
    }

    @Override
    public String toRowString() {
        return String.format(
                "| %-15s | %-15s | %-15s | %-20s | %-10s | %-10s |",
                productID, productName, quantity, price, brand, warrantyPeriod);
    }

    @Override
    public String saveToString() {
        return "Electronics, " + productID + ", " + productName + ", " + quantity + ", " + price + ", " + brand + ", " + warrantyPeriod;
    }

    @Override
    public String getProductCategory() {
        return "Electronics";
    }

    @Override
    public String getInfo() {
        return brand + ", " + warrantyPeriod;
    }
}
