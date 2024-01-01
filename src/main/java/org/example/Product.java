package org.example;

public abstract class Product {
    protected String productID;
    protected String productName;
    protected  int quantity;
    protected double price;


    // Constructor for the product class'
    public Product(String productID, String productName, int quantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // ------------- Getters starts -------------------
    public abstract String getProductCategory();

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    // ------------- Getters ends -------------------



    // ------------ Setters starts ------------------
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    // ------------ Setters ends ------------------

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    // to string abstract method
    public abstract String toString();

    public abstract String toRowString();

    public abstract String saveToString();
}
