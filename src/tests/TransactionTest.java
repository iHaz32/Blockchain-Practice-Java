package tests;

import exceptions.*;
import models.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

/**
 * Unit tests for the Transaction class to ensure correct behavior and exception handling.
 * The tests cover valid and invalid transactions, checking sender/receiver names,
 * and ensuring that amounts are positive.
 * <p>
 * Version 1.0
 * Author: Charalampos Deligiannakis
 */
class TransactionTest {

    /**
     * Test for creating a valid Transaction with correct sender, receiver, and amount.
     * The test ensures the Transaction object is created successfully with no exceptions.
     */
    @Test
    public void testValidTransaction() {
        try {
            // Creating a valid transaction with sender, receiver, and a positive amount
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100)); // Valid
            // Assert that the transaction was created correctly
            assertNotNull(transaction);
            Assertions.assertEquals("Alice", transaction.getSender());
            Assertions.assertEquals("John Doe", transaction.getReceiver());
            Assertions.assertEquals(new BigDecimal(100), transaction.getAmount());
        } catch (TransactionException e) {
            fail("Transaction creation failed: " + e.getMessage());
        }
    }

    /**
     * Test for an invalid sender name (should throw TransactionException).
     * This test ensures that the sender name must meet the specified regex pattern.
     */
    @Test
    public void testInvalidSender() {
        try {
            // Attempt to create a transaction with an invalid sender name
            new Transaction("A123", "Bob Doe", new BigDecimal(100)); // Invalid sender
            fail("Transaction should have thrown exception for invalid sender.");
        } catch (TransactionException e) {
            // Assert that the correct error message is thrown and the offending value is correct
            Assertions.assertEquals("Invalid sender name.", e.getMessage());
            Assertions.assertEquals("A123", e.getOffendingValue());
        }
    }

    /**
     * Test for an invalid receiver name (should throw TransactionException).
     * This test ensures that the receiver name must meet the specified regex pattern.
     */
    @Test
    public void testInvalidReceiver() {
        try {
            // Attempt to create a transaction with an invalid receiver name
            new Transaction("Alice Doe", "Belling_ham", new BigDecimal(100)); // Invalid receiver
            fail("Transaction should have thrown exception for invalid receiver.");
        } catch (TransactionException e) {
            // Assert that the correct error message is thrown and the offending value is correct
            Assertions.assertEquals("Invalid receiver name.", e.getMessage());
            Assertions.assertEquals("Belling_ham", e.getOffendingValue());
        }
    }

    /**
     * Test for an invalid amount (should throw TransactionException).
     * This test ensures that the transaction amount must be greater than zero.
     */
    @Test
    public void testInvalidAmount() {
        try {
            // Attempt to create a transaction with a negative amount
            new Transaction("Alice Doe", "Bob Doe", new BigDecimal(-50)); // Invalid amount (negative)
            fail("Transaction should have thrown exception for invalid amount.");
        } catch (TransactionException e) {
            // Assert that the correct error message is thrown and the offending value is correct
            Assertions.assertEquals("Amount must be greater than zero.", e.getMessage());
            Assertions.assertEquals(new BigDecimal(-50), e.getOffendingValue());
        }
    }

    /**
     * Test for a zero amount (should throw TransactionException).
     * This test ensures that the transaction amount must be greater than zero.
     */
    @Test
    public void testZeroAmount() {
        try {
            // Attempt to create a transaction with a zero amount
            new Transaction("Alice Doe", "Bob Doe", new BigDecimal(0)); // Zero amount
            fail("Transaction should have thrown exception for zero amount.");
        } catch (TransactionException e) {
            // Assert that the correct error message is thrown and the offending value is correct
            Assertions.assertEquals("Amount must be greater than zero.", e.getMessage());
            Assertions.assertEquals(new BigDecimal(0), e.getOffendingValue());
        }
    }
}