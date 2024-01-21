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
        setQuantity(quantity); // Validate quantity during construction
        setPrice(price);
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
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
    // ------------ Setters ends ------------------

    public void decreaseQuantity(int quantity) {
        if (quantity >= 0 && quantity <= this.quantity) {
            this.quantity -= quantity;
        } else {
            throw new IllegalArgumentException("Invalid quantity to decrease");
        }
    }

    // to string abstract method
    public abstract String toString();

    public abstract String toRowString();

    public abstract String saveToString();

    public abstract String getInfo();
}
