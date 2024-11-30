import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

class TransactionTest {

    // Test for valid Transaction
    @Test
    public void testValidTransaction() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100)); // Valid
            assertNotNull(transaction);
            assertEquals("Alice", transaction.getSender());
            assertEquals("John Doe", transaction.getReceiver());
            assertEquals(new BigDecimal(100), transaction.getAmount());
        } catch (TransactionException e) {
            fail("Transaction creation failed: " + e.getMessage());
        }
    }

    // Test for invalid sender (should throw TransactionException)
    @Test
    public void testInvalidSender() {
        try {
            new Transaction("A123", "Bob Doe", new BigDecimal(100)); // Invalid sender
            fail("Transaction should have thrown exception for invalid sender.");
        } catch (TransactionException e) {
            assertEquals("Invalid sender name.", e.getMessage());
            assertEquals("A123", e.getOffendingValue());
        }
    }

    // Test for invalid receiver (should throw TransactionException)
    @Test
    public void testInvalidReceiver() {
        try {
            new Transaction("Alice Doe", "Belling_ham", new BigDecimal(100)); // Invalid receiver
            fail("Transaction should have thrown exception for invalid receiver.");
        } catch (TransactionException e) {
            assertEquals("Invalid receiver name.", e.getMessage());
            assertEquals("Belling_ham", e.getOffendingValue());
        }
    }

    // Test for invalid amount (should throw TransactionException)
    @Test
    public void testInvalidAmount() {
        try {
            new Transaction("Alice Doe", "Bob Doe", new BigDecimal(-50)); // Invalid amount (negative)
            fail("Transaction should have thrown exception for invalid amount.");
        } catch (TransactionException e) {
            assertEquals("Amount must be greater than zero.", e.getMessage());
            assertEquals(new BigDecimal(-50), e.getOffendingValue());
        }
    }

    // Test for zero amount (should throw TransactionException)
    @Test
    public void testZeroAmount() {
        try {
            new Transaction("Alice Doe", "Bob Doe", new BigDecimal(0)); // Zero amount
            fail("Transaction should have thrown exception for zero amount.");
        } catch (TransactionException e) {
            assertEquals("Amount must be greater than zero.", e.getMessage());
            assertEquals(new BigDecimal(0), e.getOffendingValue());
        }
    }
}
