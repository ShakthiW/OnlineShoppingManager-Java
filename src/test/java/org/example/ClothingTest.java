package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class ClothingTest extends TestCase {
    @Test
    void constructor_ValidParameters_ObjectCreatedSuccessfully() {
        // Arrange
        String productID = "C001";
        String productName = "T-Shirt";
        int quantity = 10;
        double price = 19.99;
        String size = "M";
        String color = "Blue";

        // Act
        Clothing clothing = new Clothing(productID, productName, quantity, price, size, color);

        // Assert
        assertEquals(productID, clothing.getProductID());
        assertEquals(productName, clothing.getProductName());
        assertEquals(quantity, clothing.getQuantity());
        assertEquals(price, clothing.getPrice());
        assertEquals(size, clothing.getSize());
        assertEquals(color, clothing.getColor());
    }

    @Test
    void constructor_NullSize_ThrowsIllegalArgumentException() {
        // Arrange
        String productID = "C001";
        String productName = "T-Shirt";
        int quantity = 10;
        double price = 19.99;
        String size = null;
        String color = "Blue";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Clothing(productID, productName, quantity, price, size, color));
    }

    @Test
    void constructor_EmptyColor_ThrowsIllegalArgumentException() {
        // Arrange
        String productID = "C001";
        String productName = "T-Shirt";
        int quantity = 10;
        double price = 19.99;
        String size = "M";
        String color = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Clothing(productID, productName, quantity, price, size, color));
    }

    @Test
    void toString_ValidObject_ReturnsFormattedString() {
        // Arrange
        Clothing clothing = new Clothing("C001", "T-Shirt", 10, 19.99, "M", "Blue");

        // Act
        String result = clothing.toString();

        // Assert
        assertTrue(result.contains("Product ID"));
        assertTrue(result.contains("Product Name"));
        assertTrue(result.contains("Quantity"));
        assertTrue(result.contains("Price"));
        assertTrue(result.contains("Size"));
        assertTrue(result.contains("Color"));
    }

    @Test
    void getProductCategory_ValidObject_ReturnsClothing() {
        // Arrange
        Clothing clothing = new Clothing("C001", "T-Shirt", 10, 19.99, "M", "Blue");

        // Act
        String category = clothing.getProductCategory();

        // Assert
        assertEquals("Clothing", category);
    }

    @Test
    void getInfo_ValidObject_ReturnsSizeAndColor() {
        // Arrange
        Clothing clothing = new Clothing("C001", "T-Shirt", 10, 19.99, "M", "Blue");

        // Act
        String info = clothing.getInfo();

        // Assert
        assertEquals("M, Blue", info);
    }

    @Test
    public void testClothingToString() {
        Clothing clothing = new Clothing("C123", "Shirt", 8, 29.99, "Large", "Blue");
        String expected = "\n------- Clothing Details --------\n" +
                "Product ID      : C123\n" +
                "Product Name    : Shirt\n" +
                "Quantity        : 8\n" +
                "Price           : 29.99\n" +
                "Size            : Large\n" +
                "Color           : Blue\n";
        assertEquals(expected, clothing.toString());
    }

    @Test
    public void testSaveToString() {
        Clothing clothing = new Clothing("C123", "Shirt", 8, 29.99, "Large", "Blue");
        String expected = "Clothing, C123, Shirt, 8, 29.99, Blue, Large";
        assertEquals(expected, clothing.saveToString());
    }

}