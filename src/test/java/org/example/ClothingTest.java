package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class ClothingTest extends TestCase {
    @Test
    public void testClothingConstructor() {
        // Valid input
        Clothing clothing = new Clothing("C123", "Shirt", 8, 29.99, "Large", "Blue");
        assertEquals("Large", clothing.getSize());
        assertEquals("Blue", clothing.getColor());

        // Invalid size (null)
        assertThrows(IllegalArgumentException.class, () -> {
            new Clothing("C124", "Jeans", 5, 49.99, null, "Black");
        });

        // Invalid color (empty)
        assertThrows(IllegalArgumentException.class, () -> {
            new Clothing("C125", "Dress", 3, 39.99, "Medium", "");
        });
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
    public void testToRowString() {
        Clothing clothing = new Clothing("C123", "Shirt", 8, 29.99, "Large", "Blue");
        String expected = "| C123           | Shirt          | 8              | 29.99                  | Large      | Blue      |";
        assertEquals(expected, clothing.toRowString());
    }

    @Test
    public void testSaveToString() {
        Clothing clothing = new Clothing("C123", "Shirt", 8, 29.99, "Large", "Blue");
        String expected = "Clothing, C123, Shirt, 8, 29.99, Blue, Large";
        assertEquals(expected, clothing.saveToString());
    }

}