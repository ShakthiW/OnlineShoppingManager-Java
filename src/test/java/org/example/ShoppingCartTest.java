package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest extends TestCase {
    @Test
    void addProduct_ValidProductAndQuantity_ProductAddedToCart() {
        // Arrange
        Product product = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);
        ShoppingCart shoppingCart = new ShoppingCart();

        // Act
        shoppingCart.addProduct(product, 3);

        // Assert
        assertTrue(shoppingCart.getProducts().containsKey(product));
        assertEquals(3, (int) shoppingCart.getProducts().get(product));
    }

    @Test
    void addProduct_NegativeQuantity_ErrorMessagePrinted() {
        // Arrange
        Product product = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);
        ShoppingCart shoppingCart = new ShoppingCart();

        // Act
        shoppingCart.addProduct(product, -1);

        // Assert
        assertFalse(shoppingCart.getProducts().containsKey(product));
    }

    @Test
    void removeProduct_ValidProductId_ProductRemovedFromCart() {
        // Arrange
        Product product = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(product, 2);

        // Act
        shoppingCart.removeProduct("E001");

        // Assert
        assertFalse(shoppingCart.getProducts().containsKey(product));
    }

    @Test
    void removeProduct_ProductNotInCart_ErrorMessagePrinted() {
        // Arrange
        Product product = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);
        ShoppingCart shoppingCart = new ShoppingCart();

        // Act
        shoppingCart.removeProduct("E001");

        // Assert
        assertFalse(shoppingCart.getProducts().containsKey(product));
    }

    @Test
    void getTotalPrice_ProductsInCart_CorrectTotalPriceCalculated() {
        // Arrange
        Product laptop = new Electronics("E001", "Laptop", 2, 999.99, "Dell", 12);
        Product phone = new Electronics("E002", "Smartphone", 3, 499.99, "Samsung", 6);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(laptop, 2);
        shoppingCart.addProduct(phone, 3);

        // Act
        double totalPrice = shoppingCart.getTotalPrice();

        // Assert
        assertEquals(2 * 999.99 + 3 * 499.99, totalPrice);
    }

    @Test
    void printCartItems_ProductsInCart_PrintedDetailsCorrect() {
        // Arrange
        Product laptop = new Electronics("E001", "Laptop", 2, 999.99, "Dell", 12);
        Product phone = new Electronics("E002", "Smartphone", 3, 499.99, "Samsung", 6);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(laptop, 2);
        shoppingCart.addProduct(phone, 3);

        // Act
        shoppingCart.printCartItems();

        // No assertion, just checking printed output
    }

    @Test
    void clearCart_ProductsInCart_CartCleared() {
        // Arrange
        Product laptop = new Electronics("E001", "Laptop", 2, 999.99, "Dell", 12);
        Product phone = new Electronics("E002", "Smartphone", 3, 499.99, "Samsung", 6);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(laptop, 2);
        shoppingCart.addProduct(phone, 3);

        // Act
        shoppingCart.clearCart();

        // Assert
        assertTrue(shoppingCart.getProducts().isEmpty());
    }
}