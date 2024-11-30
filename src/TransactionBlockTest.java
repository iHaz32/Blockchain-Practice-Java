import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

class TransactionBlockTest {

    // Test for valid TransactionBlock
    @Test
    public void testValidTransactionBlock() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100)); // Valid transaction
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            TransactionBlock block = new TransactionBlock(1, timestamp, transaction, "previousHashExample", "hashExample");

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

    // Test for invalid index (negative index)
    @Test
    public void testInvalidIndex() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new TransactionBlock(-1, timestamp, transaction, "previousHashExample", "hashExample"); // Invalid index
            fail("TransactionBlock should have thrown exception for negative index.");
        } catch (TransactionBlockException e) {
            assertEquals("Index cannot be negative.", e.getMessage());
            assertEquals(-1, e.getOffendingValue());
        } catch (TransactionException e) {
            fail("TransactionBlock creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    // Test for invalid timestamp (null)
    @Test
    public void testInvalidTimestampNull() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            new TransactionBlock(1, null, transaction, "previousHashExample", "hashExample"); // Null timestamp
            fail("TransactionBlock should have thrown exception for null timestamp.");
        } catch (TransactionBlockException e) {
            assertEquals("Timestamp cannot be null.", e.getMessage());
            assertNull(e.getOffendingValue());
        } catch (TransactionException e) {
            fail("TransactionBlock creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    // Test for invalid timestamp (future timestamp)
    @Test
    public void testInvalidTimestampFuture() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp futureTimestamp = new Timestamp(System.currentTimeMillis() + 100000);
            new TransactionBlock(1, futureTimestamp, transaction, "previousHashExample", "hashExample"); // Future timestamp
            fail("TransactionBlock should have thrown exception for future timestamp.");
        } catch (TransactionBlockException e) {
            assertEquals("Timestamp cannot be in the future.", e.getMessage());
            assertEquals("TransactionBlockException", e.getClass().getSimpleName());
        } catch (TransactionException e) {
            fail("TransactionBlock creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    // Test for invalid transaction data (null)
    @Test
    public void testInvalidTransactionData() {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new TransactionBlock(1, timestamp, null, "previousHashExample", "hashExample"); // Null transaction data
            fail("TransactionBlock should have thrown exception for null transaction data.");
        } catch (TransactionBlockException e) {
            assertEquals("Transaction data cannot be null.", e.getMessage());
            assertNull(e.getOffendingValue());
        }
    }

    // Test for invalid previous hash (null or empty)
    @Test
    public void testInvalidPreviousHash() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new TransactionBlock(1, timestamp, transaction, "", "hashExample"); // Empty previous hash
            fail("TransactionBlock should have thrown exception for empty previous hash.");
        } catch (TransactionBlockException e) {
            assertEquals("Previous hash cannot be null or empty.", e.getMessage());
            assertEquals("", e.getOffendingValue());
        } catch (TransactionException e) {
            fail("TransactionBlock creation failed due to invalid Transaction: " + e.getMessage());
        }
    }

    // Test for invalid hash (null or empty)
    @Test
    public void testInvalidHash() {
        try {
            Transaction transaction = new Transaction("Alice", "John Doe", new BigDecimal(100));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            new TransactionBlock(1, timestamp, transaction, "previousHashExample", ""); // Empty hash
            fail("TransactionBlock should have thrown exception for empty hash.");
        } catch (TransactionBlockException e) {
            assertEquals("Hash cannot be null or empty.", e.getMessage());
            assertEquals("", e.getOffendingValue());
        } catch (TransactionException e) {
            fail("TransactionBlock creation failed due to invalid Transaction: " + e.getMessage());
        }
    }
}