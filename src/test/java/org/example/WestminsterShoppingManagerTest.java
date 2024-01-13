package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

public class WestminsterShoppingManagerTest extends TestCase {
    @Test
    void addProduct_ProductListNotFull_ProductAddedSuccessfully() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product product = new Electronics("E123", "Laptop", 5, 1200.0, "Dell", 12);

        // Act
        shoppingManager.addProduct(product);

        // Assert
        List<Product> productList = shoppingManager.getAllProducts();
        assertEquals(1, productList.size());
        assertEquals(product, productList.get(0));
    }

    @Test
    void addProduct_ProductListFull_LimitReached() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        for (int i = 0; i < 50; i++) {
            Product product = new Clothing("C" + i, "Shirt", 10, 20.0, "XL", "Blue");
            shoppingManager.addProduct(product);
        }

        // Act
        Product newProduct = new Electronics("E123", "Laptop", 5, 1200.0, "Dell", 12);
        shoppingManager.addProduct(newProduct);

        // Assert
        List<Product> productList = shoppingManager.getAllProducts();
        assertEquals(50, productList.size());
        assertFalse(productList.contains(newProduct));
    }

    @Test
    void updateProductQuantity_ProductFound_QuantityUpdatedSuccessfully() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product product = new Electronics("E123", "Laptop", 5, 1200.0, "Dell", 12);
        shoppingManager.addProduct(product);

        // Act
        boolean result = shoppingManager.updateProductQuantity("E123", 3);

        // Assert
        assertTrue(result);
        assertEquals(8, product.getQuantity());
    }

    @Test
    void updateProductQuantity_ProductNotFound_ReturnsFalse() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Act
        boolean result = shoppingManager.updateProductQuantity("E123", 3);

        // Assert
        assertFalse(result);
    }

    @Test
    void removeProductFromSystem_ProductFound_ProductRemovedSuccessfully() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product product = new Electronics("E123", "Laptop", 5, 1200.0, "Dell", 12);
        shoppingManager.addProduct(product);

        // Act
        shoppingManager.removeProductFromSystem("E123");

        // Assert
        List<Product> productList = shoppingManager.getAllProducts();
        assertEquals(0, productList.size());
    }

    @Test
    void removeProductFromSystem_ProductNotFound_NoChange() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Act
        shoppingManager.removeProductFromSystem("E123");

        // Assert
        List<Product> productList = shoppingManager.getAllProducts();
        assertEquals(0, productList.size());
    }

    @Test
    void getAllProducts_ReturnsCopyOfProductList() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product product = new Electronics("E123", "Laptop", 5, 1200.0, "Dell", 12);
        shoppingManager.addProduct(product);

        // Act
        List<Product> productList = shoppingManager.getAllProducts();

        // Assert
        assertEquals(1, productList.size());
        assertEquals(product, productList.get(0));
        // Modifying the returned list should not affect the internal list
        productList.clear();
        List<Product> updatedList = shoppingManager.getAllProducts();
        assertEquals(1, updatedList.size());
    }

    @Test
    void getProductByID_ProductFound_ReturnsProduct() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product product = new Electronics("E123", "Laptop", 5, 1200.0, "Dell", 12);
        shoppingManager.addProduct(product);

        // Act
        Product foundProduct = shoppingManager.getProductByID("E123");

        // Assert
        assertNotNull(foundProduct);
        assertEquals(product, foundProduct);
    }

    @Test
    void getProductByID_ProductNotFound_ReturnsNull() {
        // Arrange
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Act
        Product foundProduct = shoppingManager.getProductByID("E123");

        // Assert
        assertNull(foundProduct);
    }
}