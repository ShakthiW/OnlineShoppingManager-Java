package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest extends TestCase {
    @Test
    public void testAddProduct() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = new Electronics("E001", "Laptop", 10, 899.99, "HP", 12);
        shoppingCart.addProduct(product, 2);
        assertEquals(2, shoppingCart.getProducts().get(product).intValue());
    }

    @Test
    public void testRemoveProduct() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = new Electronics("E001", "Laptop", 10, 899.99, "HP", 12);
        shoppingCart.addProduct(product, 2);
        shoppingCart.removeProduct("E001");
        assertNull(shoppingCart.getProducts().get(product));
    }

    @Test
    public void testGetTotalPrice() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product1 = new Electronics("E001", "Laptop", 10, 899.99, "HP", 12);
        Product product2 = new Clothing("C001", "T-Shirt", 5, 19.99, "M", "Blue");
        shoppingCart.addProduct(product1, 2);
        shoppingCart.addProduct(product2, 3);
        assertEquals(2 * 899.99 + 3 * 19.99, shoppingCart.getTotalPrice());
    }

    @Test
    public void testClearCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = new Electronics("E001", "Laptop", 10, 899.99, "HP", 12);
        shoppingCart.addProduct(product, 2);
        shoppingCart.clearCart();
        assertTrue(shoppingCart.getProducts().isEmpty());
    }
}