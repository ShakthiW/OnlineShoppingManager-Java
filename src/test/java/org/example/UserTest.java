package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class UserTest extends TestCase {
    @Test
    void createUser_ValidCredentials_UserCreated() {
        // Arrange
        String userName = "john_doe";
        String password = "Password123";

        // Act
        User user = new User(userName, password);

        // Assert
        assertNotNull(user);
        assertEquals(userName, user.getUserName());
        assertEquals(password, user.getPassword());
        assertEquals(0, user.getPurchaseCount());
    }

    @Test
    void validatePassword_ValidPassword_ReturnsTrue() {
        // Arrange
        String validPassword = "ValidPassword123";

        // Act
        boolean isValid = User.validatePassword(validPassword);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void validatePassword_InvalidPassword_ReturnsFalse() {
        // Arrange
        String invalidPassword = "invalid";

        // Act
        boolean isValid = User.validatePassword(invalidPassword);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void setPassword_ValidPassword_PasswordSet() {
        // Arrange
        User user = new User("john_doe", "OldPassword");

        // Act
        user.setPassword("NewValidPassword123");

        // Assert
        assertEquals("NewValidPassword123", user.getPassword());
    }

    @Test
    void setPassword_InvalidPassword_ExceptionThrown() {
        // Arrange
        User user = new User("john_doe", "OldPassword");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("invalid"));
    }

    @Test
    void incrementPurchaseCount_PurchaseCountIncremented() {
        // Arrange
        User user = new User("john_doe", "Password123");

        // Act
        user.incrementPurchaseCount();

        // Assert
        assertEquals(1, user.getPurchaseCount());
    }

    @Test
    void validateCredentials_ValidCredentials_ReturnsTrue() {
        // Arrange
        User user = new User("john_doe", "Password123");
        String enteredUsername = "john_doe";
        String enteredPassword = "Password123";

        // Act
        boolean isValid = user.validateCredentials(enteredUsername, enteredPassword);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void validateCredentials_InvalidCredentials_ReturnsFalse() {
        // Arrange
        User user = new User("john_doe", "Password123");
        String enteredUsername = "john_doe";
        String enteredPassword = "InvalidPassword";

        // Act
        boolean isValid = user.validateCredentials(enteredUsername, enteredPassword);

        // Assert
        assertFalse(isValid);
    }
}