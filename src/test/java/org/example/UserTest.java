package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class UserTest extends TestCase {
    @Test
    public void testValidPassword() {
        assertTrue(User.validatePassword("Abcdef1"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(User.validatePassword("password")); // Doesn't contain uppercase and numeric
    }

    @Test
    public void testIncrementPurchaseCount() {
        User user = new User("testUser", "TestPassword");
        assertEquals(0, user.getPurchaseCount());
        user.incrementPurchaseCount();
        assertEquals(1, user.getPurchaseCount());
    }

    @Test
    public void testValidateCredentials() {
        User user = new User("testUser", "TestPassword");
        assertTrue(user.validateCredentials("testUser", "TestPassword"));
        assertFalse(user.validateCredentials("testUser", "WrongPassword"));
    }

    @Test
    public void testSetPassword() {
        User user = new User("testUser", "TestPassword");
        assertEquals("TestPassword", user.getPassword());
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("weak"));
    }
}