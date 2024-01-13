package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class ElectronicsTest extends TestCase {
    @Test
    void constructor_ValidParameters_ObjectCreatedSuccessfully() {
        // Arrange
        String productID = "E001";
        String productName = "Laptop";
        int quantity = 5;
        double price = 999.99;
        String brand = "Dell";
        int warrantyPeriod = 12;

        // Act
        Electronics electronics = new Electronics(productID, productName, quantity, price, brand, warrantyPeriod);

        // Assert
        assertEquals(productID, electronics.getProductID());
        assertEquals(productName, electronics.getProductName());
        assertEquals(quantity, electronics.getQuantity());
        assertEquals(price, electronics.getPrice());
        assertEquals(brand, electronics.getBrand());
        assertEquals(warrantyPeriod, electronics.getWarrantyPeriod());
    }

    @Test
    void constructor_NullBrand_ThrowsIllegalArgumentException() {
        // Arrange
        String productID = "E001";
        String productName = "Laptop";
        int quantity = 5;
        double price = 999.99;
        String brand = null;
        int warrantyPeriod = 12;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Electronics(productID, productName, quantity, price, brand, warrantyPeriod));
    }

    @Test
    void constructor_NegativeWarrantyPeriod_ThrowsIllegalArgumentException() {
        // Arrange
        String productID = "E001";
        String productName = "Laptop";
        int quantity = 5;
        double price = 999.99;
        String brand = "Dell";
        int warrantyPeriod = -1;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Electronics(productID, productName, quantity, price, brand, warrantyPeriod));
    }

    @Test
    void toString_ValidObject_ReturnsFormattedString() {
        // Arrange
        Electronics electronics = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);

        // Act
        String result = electronics.toString();

        // Assert
        assertTrue(result.contains("Product ID"));
        assertTrue(result.contains("Product Name"));
        assertTrue(result.contains("Quantity"));
        assertTrue(result.contains("Price"));
        assertTrue(result.contains("Brand"));
        assertTrue(result.contains("Warranty Period"));
    }

    @Test
    void getProductCategory_ValidObject_ReturnsElectronics() {
        // Arrange
        Electronics electronics = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);

        // Act
        String category = electronics.getProductCategory();

        // Assert
        assertEquals("Electronics", category);
    }

    @Test
    void getInfo_ValidObject_ReturnsBrandAndWarrantyPeriod() {
        // Arrange
        Electronics electronics = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);

        // Act
        String info = electronics.getInfo();

        // Assert
        assertEquals("Dell, 12", info);
    }
    @Test
    public void testElectronicsToString() {
        Electronics electronics = new Electronics("E123", "Laptop", 10, 999.99, "Sony", 12);
        String expected = "\n------- Electronics Details --------\n" +
                "Product ID      : E123\n" +
                "Product Name    : Laptop\n" +
                "Quantity        : 10\n" +
                "Price           : 999.99\n" +
                "Brand           : Sony\n" +
                "Warranty Period : 12 months\n";
        assertEquals(expected, electronics.toString());
    }

    @Test
    public void testSaveToString() {
        Electronics electronics = new Electronics("E456", "Laptop", 5, 899.99, "HP", 12);
        String expected = "Electronics, E456, Laptop, 5, 899.99, HP, 12";
        assertEquals(expected, electronics.saveToString());
    }
}