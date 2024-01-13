package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class ElectronicsTest extends TestCase {
    @Test
    public void testElectronicsConstructor() {
        // Valid input
        Electronics electronics = new Electronics("E123", "Laptop", 10, 999.99, "Sony", 12);
        assertEquals("Sony", electronics.getBrand());
        assertEquals(12, electronics.getWarrantyPeriod());

        // Invalid brand (null)
        assertThrows(IllegalArgumentException.class, () -> {
            new Electronics("E124", "Phone", 5, 499.99, null, 6);
        });

        // Invalid warranty period (negative)
        assertThrows(IllegalArgumentException.class, () -> {
            new Electronics("E125", "Camera", 3, 299.99, "Canon", -1);
        });
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
    public void testToRowString() {
        Electronics electronics = new Electronics("E456", "Laptop", 5, 899.99, "HP", 12);
        String expected = "| E456           | Laptop         | 5              | 899.99                 | HP         | 12        |";
        assertEquals(expected, electronics.toRowString());
    }

    @Test
    public void testSaveToString() {
        Electronics electronics = new Electronics("E456", "Laptop", 5, 899.99, "HP", 12);
        String expected = "Electronics, E456, Laptop, 5, 899.99, HP, 12";
        assertEquals(expected, electronics.saveToString());
    }
}