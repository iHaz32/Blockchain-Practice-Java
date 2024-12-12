import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Unit tests for the Block class.
 * These tests ensure that the Block constructor and methods work as expected,
 * and that appropriate exceptions are thrown when invalid data is provided.
 *
 * @version 1.0
 * @author Charalampos Deligiannakis
 */
class BlockTest {

    /**
     * Test for creating a valid Block.
     * Verifies that a valid Block can be created and that all values are correctly set.
     */
    @Test
    public void testValidTransactionBlock() {
        try {
            // Create a valid Transaction object
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Create a valid Block
            Block block = new Block(1, timestamp, transaction, "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824", false);

            // Assert that the Block is not null and its properties match the expected values
            assertNotNull(block);
            assertEquals(1, block.getIndex());
            assertEquals(timestamp, block.getTimestamp());
            assertEquals(transaction, block.getData());
            assertEquals("2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824", block.getPreviousHash());
        } catch (BlockException | TransactionException e) {
            fail("Block creation failed: " + e.getMessage());
        }
    }

    /**
     * Test for an invalid Block with a negative index.
     * Verifies that an exception is thrown if the index is negative.
     */
    @Test
    public void testInvalidIndex() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new Block(-1, timestamp, transaction, "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824"); // Invalid index
            fail("Block should have thrown exception for negative index.");
        } catch (BlockException e) {
            // Assert that the exception message and offending value match expectations
            assertEquals("Index cannot be negative.", e.getMessage());
            assertEquals(-1, e.getOffendingValue());
        } catch (TransactionException e) {
            fail("Block creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    /**
     * Test for an invalid Block with a null timestamp.
     * Verifies that an exception is thrown if the timestamp is null.
     */
    @Test
    public void testInvalidTimestampNull() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            new Block(1, null, transaction, "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824"); // Null timestamp
            fail("Block should have thrown exception for null timestamp.");
        } catch (BlockException e) {
            // Assert that the exception message and offending value match expectations
            assertEquals("Timestamp cannot be null.", e.getMessage());
            assertNull(e.getOffendingValue());
        } catch (TransactionException e) {
            fail("Block creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    /**
     * Test for an invalid Block with a future timestamp.
     * Verifies that an exception is thrown if the timestamp is set in the future.
     */
    @Test
    public void testInvalidTimestampFuture() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp futureTimestamp = new Timestamp(System.currentTimeMillis() + 100000);
            new Block(1, futureTimestamp, transaction, "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824"); // Future timestamp
            fail("Block should have thrown exception for future timestamp.");
        } catch (BlockException e) {
            // Assert that the exception message matches expectations
            assertEquals("Timestamp cannot be in the future.", e.getMessage());
            assertEquals("BlockException", e.getClass().getSimpleName());
        } catch (TransactionException e) {
            fail("Block creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    /**
     * Test for an invalid Block with null transaction data.
     * Verifies that an exception is thrown if the transaction data is null.
     */
    @Test
    public void testInvalidTransactionData() {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new Block(1, timestamp, null, "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824"); // Null transaction data
            fail("Block should have thrown exception for null transaction data.");
        } catch (BlockException e) {
            // Assert that the exception message and offending value match expectations
            assertEquals("Transaction data cannot be null.", e.getMessage());
            assertNull(e.getOffendingValue());
        }
    }

    /**
     * Test for an invalid Block with an empty previous hash.
     * Verifies that an exception is thrown if the previous hash is empty or null.
     */
    @Test
    public void testInvalidPreviousHash() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new Block(1, timestamp, transaction, "i_am_not_a_hash!!"); // Empty previous hash
            fail("Block should have thrown exception for empty previous hash.");
        } catch (BlockException e) {
            // Assert that the exception message and offending value match expectations
            assertEquals("Invalid hash format.", e.getMessage());
            assertEquals(null, e.getOffendingValue());
        } catch (TransactionException e) {
            fail("Block creation failed due to invalid Transaction: " + e.getMessage());
        }
    }
}