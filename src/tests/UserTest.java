package tests;

import exceptions.UserException;
import models.User;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

/**
 * Unit tests for the User class.
 * These tests ensure that the User constructor, setters, and getters work as expected,
 * and that appropriate exceptions are thrown when invalid data is provided.
 *
 * @version 1.0
 * Author: Charalampos Deligiannakis
 */
class UserTest {

    /**
     * Test for creating a valid User object.
     * Ensures all fields are set correctly when valid parameters are provided.
     */
    @Test
    public void testValidUserCreation() {
        try {
            User user = new User("valid_user123", "secureToken123", new BigDecimal("1000.50"));

            // Assert that the User object is not null and all values match expectations
            assertNotNull(user);
            assertEquals("valid_user123", user.getUsername());
            assertEquals("secureToken123", user.getAuthToken());
            assertEquals(new BigDecimal("1000.50"), user.getBalance());
        } catch (UserException e) {
            fail("User creation failed: " + e.getMessage());
        }
    }

    /**
     * Test for creating a User object with an invalid username.
     * Ensures that a UserException is thrown when the username contains invalid characters.
     */
    @Test
    public void testInvalidUsername() {
        try {
            new User("invalid username!", "secureToken123", new BigDecimal("1000.50"));
            fail("User should have thrown exception for invalid username.");
        } catch (UserException e) {
            assertEquals("Invalid username. Only letters, numbers, and underscores are allowed.", e.getMessage());
        }
    }

    /**
     * Test for creating a User object with a null or empty authentication token.
     * Ensures that a UserException is thrown.
     */
    @Test
    public void testInvalidAuthToken() {
        try {
            new User("valid_user123", "", new BigDecimal("1000.50"));
            fail("User should have thrown exception for empty authentication token.");
        } catch (UserException e) {
            assertEquals("Authentication token cannot be null or empty.", e.getMessage());
        }

        try {
            new User("valid_user123", null, new BigDecimal("1000.50"));
            fail("User should have thrown exception for null authentication token.");
        } catch (UserException e) {
            assertEquals("Authentication token cannot be null or empty.", e.getMessage());
        }
    }

    /**
     * Test for creating a User object with a negative balance.
     * Ensures that a UserException is thrown.
     */
    @Test
    public void testInvalidBalance() {
        try {
            new User("valid_user123", "secureToken123", new BigDecimal("-100.00"));
            fail("User should have thrown exception for negative balance.");
        } catch (UserException e) {
            assertEquals("Balance must be greater than or equal to zero.", e.getMessage());
        }
    }

    /**
     * Test the setter methods for valid and invalid values.
     * Ensures that setters update the state correctly for valid values, and throw exceptions for invalid inputs.
     */
    @Test
    public void testSetters() {
        try {
            User user = new User("valid_user123", "secureToken123", new BigDecimal("1000.50"));

            // Test valid username
            user.setUsername("new_user123");
            assertEquals("new_user123", user.getUsername());

            // Test invalid username
            try {
                user.setUsername("invalid username!");
                fail("Setter should have thrown exception for invalid username.");
            } catch (UserException e) {
                assertEquals("Invalid username. Only letters, numbers, and underscores are allowed.", e.getMessage());
            }

            // Test valid authentication token
            user.setAuthToken("newSecureToken");
            assertEquals("newSecureToken", user.getAuthToken());

            // Test invalid authentication token
            try {
                user.setAuthToken("");
                fail("Setter should have thrown exception for empty authentication token.");
            } catch (UserException e) {
                assertEquals("Authentication token cannot be null or empty.", e.getMessage());
            }

            // Test valid balance
            user.setBalance(new BigDecimal("500.00"));
            assertEquals(new BigDecimal("500.00"), user.getBalance());

            // Test invalid balance
            try {
                user.setBalance(new BigDecimal("-200.00"));
                fail("Setter should have thrown exception for negative balance.");
            } catch (UserException e) {
                assertEquals("Balance must be greater than or equal to zero.", e.getMessage());
            }
        } catch (UserException e) {
            fail("Setter test initialization failed: " + e.getMessage());
        }
    }
}