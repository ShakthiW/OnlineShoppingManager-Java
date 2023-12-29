package org.example;

import java.util.List;

public interface ShoppingManager {
    // Add a new product to the system
    void addProduct(Product product);

    // Remove a product from the system
    void removeProductFromSystem(String productId);

    // Get a list of all products in the system
    List<Product> getAllProducts();

    // Get product by ID
    Product getProductByID(String productID);


    // Get the total number of products in the system
    int getTotalProducts();

    // Print details of all products in the system
    void printAllProducts(String productType);

    // save the data to a file
    void saveProducts();

    void loadProducts();
}
