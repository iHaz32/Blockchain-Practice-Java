import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Unit tests for the TransactionBlock class.
 * These tests ensure that the TransactionBlock constructor and methods work as expected,
 * and that appropriate exceptions are thrown when invalid data is provided.
 *
 * @version 1.0
 * @author Charalampos Deligiannakis
 */
class TransactionBlockTest {

    /**
     * Test for creating a valid TransactionBlock.
     * Verifies that a valid TransactionBlock can be created and that all values are correctly set.
     */
    @Test
    public void testValidTransactionBlock() {
        try {
            // Create a valid Transaction object
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Create a valid TransactionBlock
            TransactionBlock block = new TransactionBlock(1, timestamp, transaction, "previousHashExample", "hashExample");

            // Assert that the TransactionBlock is not null and its properties match the expected values
            assertNotNull(block);
            assertEquals(1, block.getIndex());
            assertEquals(timestamp, block.getTimestamp());
            assertEquals(transaction, block.getData());
            assertEquals("previousHashExample", block.getPreviousHash());
            assertEquals("hashExample", block.getHash());
        } catch (TransactionBlockException | TransactionException e) {
            fail("TransactionBlock creation failed: " + e.getMessage());
        }
    }

    /**
     * Test for an invalid TransactionBlock with a negative index.
     * Verifies that an exception is thrown if the index is negative.
     */
    @Test
    public void testInvalidIndex() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new TransactionBlock(-1, timestamp, transaction, "previousHashExample", "hashExample"); // Invalid index
            fail("TransactionBlock should have thrown exception for negative index.");
        } catch (TransactionBlockException e) {
            // Assert that the exception message and offending value match expectations
            assertEquals("Index cannot be negative.", e.getMessage());
            assertEquals(-1, e.getOffendingValue());
        } catch (TransactionException e) {
            fail("TransactionBlock creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    /**
     * Test for an invalid TransactionBlock with a null timestamp.
     * Verifies that an exception is thrown if the timestamp is null.
     */
    @Test
    public void testInvalidTimestampNull() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            new TransactionBlock(1, null, transaction, "previousHashExample", "hashExample"); // Null timestamp
            fail("TransactionBlock should have thrown exception for null timestamp.");
        } catch (TransactionBlockException e) {
            // Assert that the exception message and offending value match expectations
            assertEquals("Timestamp cannot be null.", e.getMessage());
            assertNull(e.getOffendingValue());
        } catch (TransactionException e) {
            fail("TransactionBlock creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    /**
     * Test for an invalid TransactionBlock with a future timestamp.
     * Verifies that an exception is thrown if the timestamp is set in the future.
     */
    @Test
    public void testInvalidTimestampFuture() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp futureTimestamp = new Timestamp(System.currentTimeMillis() + 100000);
            new TransactionBlock(1, futureTimestamp, transaction, "previousHashExample", "hashExample"); // Future timestamp
            fail("TransactionBlock should have thrown exception for future timestamp.");
        } catch (TransactionBlockException e) {
            // Assert that the exception message matches expectations
            assertEquals("Timestamp cannot be in the future.", e.getMessage());
            assertEquals("TransactionBlockException", e.getClass().getSimpleName());
        } catch (TransactionException e) {
            fail("TransactionBlock creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    /**
     * Test for an invalid TransactionBlock with null transaction data.
     * Verifies that an exception is thrown if the transaction data is null.
     */
    @Test
    public void testInvalidTransactionData() {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new TransactionBlock(1, timestamp, null, "previousHashExample", "hashExample"); // Null transaction data
            fail("TransactionBlock should have thrown exception for null transaction data.");
        } catch (TransactionBlockException e) {
            // Assert that the exception message and offending value match expectations
            assertEquals("Transaction data cannot be null.", e.getMessage());
            assertNull(e.getOffendingValue());
        }
    }

    /**
     * Test for an invalid TransactionBlock with an empty previous hash.
     * Verifies that an exception is thrown if the previous hash is empty or null.
     */
    @Test
    public void testInvalidPreviousHash() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new TransactionBlock(1, timestamp, transaction, "", "hashExample"); // Empty previous hash
            fail("TransactionBlock should have thrown exception for empty previous hash.");
        } catch (TransactionBlockException e) {
            // Assert that the exception message and offending value match expectations
            assertEquals("Previous hash cannot be null or empty.", e.getMessage());
            assertEquals("", e.getOffendingValue());
        } catch (TransactionException e) {
            fail("TransactionBlock creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    /**
     * Test for an invalid TransactionBlock with an empty hash.
     * Verifies that an exception is thrown if the hash is empty or null.
     */
    @Test
    public void testInvalidHash() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new TransactionBlock(1, timestamp, transaction, "previousHashExample", ""); // Empty hash
            fail("TransactionBlock should have thrown exception for empty hash.");
        } catch (TransactionBlockException e) {
            // Assert that the exception message and offending value match expectations
            assertEquals("Hash cannot be null or empty.", e.getMessage());
            assertEquals("", e.getOffendingValue());
        } catch (TransactionException e) {
            fail("TransactionBlock creation failed due to invalid Transaction: " + e.getMessage());
        }
    }
}