package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void createProduct_ValidParameters_ProductCreatedSuccessfully() {
        // Arrange
        String productId = "P123";
        String productName = "TestProduct";
        int quantity = 10;
        double price = 50.0;

        // Act
        Product product = new Clothing(productId, productName, quantity, price, "M", "Blue");

        // Assert
        assertNotNull(product);
        assertEquals(productId, product.getProductID());
        assertEquals(productName, product.getProductName());
        assertEquals(quantity, product.getQuantity());
        assertEquals(price, product.getPrice());
    }

    @Test
    void setProductID_ValidID_ProductIDSetSuccessfully() {
        // Arrange
        Product product = new Clothing("C123", "Shirt", 10, 20.0, "XL", "Blue");

        // Act
        product.setProductID("C456");

        // Assert
        assertEquals("C456", product.getProductID());
    }

    @Test
    void setProductName_ValidName_ProductNameSetSuccessfully() {
        // Arrange
        Product product = new Electronics("E789", "Laptop", 5, 1200.0, "Dell", 12);

        // Act
        product.setProductName("Desktop");

        // Assert
        assertEquals("Desktop", product.getProductName());
    }

    @Test
    void setQuantity_ValidQuantity_QuantitySetSuccessfully() {
        // Arrange
        Product product = new Clothing("C789", "Jeans", 15, 30.0, "M", "Black");

        // Act
        product.setQuantity(20);

        // Assert
        assertEquals(20, product.getQuantity());
    }

    @Test
    void setPrice_ValidPrice_PriceSetSuccessfully() {
        // Arrange
        Product product = new Electronics("E456", "Smartphone", 8, 500.0, "Samsung", 6);

        // Act
        product.setPrice(550.0);

        // Assert
        assertEquals(550.0, product.getPrice());
    }

    @Test
    void setQuantity_NegativeQuantity_ThrowsIllegalArgumentException() {
        // Arrange
        Product product = new Clothing("C456", "T-Shirt", 5, 15.0, "S", "White");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setQuantity(-3);
        });

        assertEquals("Quantity cannot be negative", exception.getMessage());
    }

    @Test
    void setPrice_NegativePrice_ThrowsIllegalArgumentException() {
        // Arrange
        Product product = new Electronics("E123", "Headphones", 12, 80.0, "Sony", 3);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setPrice(-20.0);
        });

        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    void decreaseQuantity_ValidQuantity_DecreasesQuantitySuccessfully() {
        // Arrange
        Product product = new Clothing("C789", "Sweater", 10, 25.0, "L", "Red");

        // Act
        product.decreaseQuantity(3);

        // Assert
        assertEquals(7, product.getQuantity());
    }

    @Test
    void decreaseQuantity_NegativeQuantity_ThrowsIllegalArgumentException() {
        // Arrange
        Product product = new Electronics("E789", "Tablet", 6, 200.0, "Apple", 12);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            product.decreaseQuantity(-2);
        });

        assertEquals("Invalid quantity to decrease", exception.getMessage());
    }

}