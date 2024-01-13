package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WestminsterShoppingManagerTest extends TestCase {
    @Test
    public void testAddProduct() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Product product = new Electronics("E123", "Laptop", 10, 999.99, "BrandX", 12);
        manager.addProduct(product);
        assertEquals(1, manager.getTotalProducts());
    }

    @Test
    public void testUpdateProductQuantity() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Product product = new Electronics("E123", "Laptop", 10, 999.99, "BrandX", 12);
        manager.addProduct(product);
        assertTrue(manager.updateProductQuantity("E123", 5));
        assertEquals(15, manager.getProductByID("E123").getQuantity());
    }

    @Test
    public void testRemoveProductFromSystem() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Product product = new Electronics("E123", "Laptop", 10, 999.99, "BrandX", 12);
        manager.addProduct(product);
        manager.removeProductFromSystem("E123");
        assertNull(manager.getProductByID("E123"));
    }

    @Test
    public void testLoadAndSaveProducts() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Product product1 = new Electronics("E123", "Laptop", 10, 999.99, "BrandX", 12);
        Product product2 = new Clothing("C456", "Shirt", 20, 29.99, "M", "Blue");

        // Add products to the system
        manager.addProduct(product1);
        manager.addProduct(product2);

        // Save products to a file
        manager.saveProducts();

        // Clear the system and load products from the file
        manager = new WestminsterShoppingManager();
        manager.loadProducts();

        // Check if the loaded products match the original products
        assertEquals(2, manager.getTotalProducts());
        assertEquals(product1.getProductID(), manager.getProductByID("E123").getProductID());
        assertEquals(product2.getProductID(), manager.getProductByID("C456").getProductID());
    }

    @Test
    public void testPrintAllProducts() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Product product1 = new Electronics("E123", "Laptop", 10, 999.99, "BrandX", 12);
        Product product2 = new Clothing("C456", "Shirt", 20, 29.99, "M", "Blue");

        // Add products to the system
        manager.addProduct(product1);
        manager.addProduct(product2);

        // Capture the output of printAllProducts
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Print all products
        manager.printAllProducts("E");

        // Restore the standard output
        System.setOut(System.out);

        // Verify the printed output
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("E123"));
        assertTrue(printedOutput.contains("Laptop"));
        assertTrue(printedOutput.contains("BrandX"));
        assertFalse(printedOutput.contains("C456"));
    }
}