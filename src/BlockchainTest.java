import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Unit tests for the Blockchain class.
 * These tests ensure that the Blockchain methods work as expected,
 * and that appropriate exceptions are thrown when invalid data is provided.
 *
 * @version 1.0
 * @author Charalampos Deligiannakis
 */
class BlockchainTest {

    /**
     * Test for creating a valid Blockchain with default constructor.
     */
    @Test
    public void testValidBlockchainDefaultConstructor() {
        try {
            Blockchain blockchain = new Blockchain();
            assertNotNull(blockchain);
            assertEquals(1, blockchain.getBlocks().size());
            assertEquals("Genesis", blockchain.getBlocks().get(0).getData().getSender());
        } catch (Exception e) {
            fail("Blockchain creation failed: " + e.getMessage());
        }
    }

    /**
     * Test for creating a valid Blockchain with a name.
     */
    @Test
    public void testValidBlockchainWithName() {
        try {
            Blockchain blockchain = new Blockchain("TestChain");
            assertNotNull(blockchain);
            assertEquals("TestChain", blockchain.getName());
            assertEquals(1, blockchain.getBlocks().size());
            assertEquals("Genesis", blockchain.getBlocks().get(0).getData().getSender());
        } catch (Exception e) {
            fail("Blockchain creation with name failed: " + e.getMessage());
        }
    }

    /**
     * Test for setting a valid name in the Blockchain.
     */
    @Test
    public void testSetValidName() {
        try {
            Blockchain blockchain = new Blockchain();
            blockchain.setName("NewName");
            assertEquals("NewName", blockchain.getName());
        } catch (Exception e) {
            fail("Setting a valid name failed: " + e.getMessage());
        }
    }

    /**
     * Test for setting an invalid name in the Blockchain.
     */
    @Test
    public void testSetInvalidName() {
        try {
            Blockchain blockchain = new Blockchain();
            blockchain.setName("!Invalid Name@#");
            fail("Blockchain should have thrown exception for invalid name.");
        } catch (BlockchainException e) {
            assertEquals("Invalid receiver name.", e.getMessage());
            assertEquals("!Invalid Name@#", e.getOffendingValue());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test for adding a valid block to the Blockchain.
     */
    @Test
    public void testAddValidBlock() {
        try {
            Blockchain blockchain = new Blockchain();
            Transaction transaction = new Transaction("Alice", "Bob", new BigDecimal(50));
            blockchain.createBlock(transaction);
            assertEquals(2, blockchain.getBlocks().size());
            Block lastBlock = blockchain.getBlocks().get(1);
            assertEquals(transaction, lastBlock.getData());
        } catch (Exception e) {
            fail("Adding a valid block failed: " + e.getMessage());
        }
    }

    /**
     * Test for adding an invalid block (e.g., null transaction).
     */
    @Test
    public void testAddInvalidBlock() {
        try {
            Blockchain blockchain = new Blockchain();
            blockchain.createBlock(null);
            fail("Blockchain should have thrown exception for null transaction.");
        } catch (BlockException e) {
            assertEquals("Transaction data cannot be null.", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test for Blockchain toString method.
     */
    @Test
    public void testBlockchainToString() {
        try {
            Blockchain blockchain = new Blockchain("TestChain");
            String expected = "Blockchain{name=TestChain, blocks=1 blocks}";
            assertEquals(expected, blockchain.toString());
        } catch (Exception e) {
            fail("Blockchain toString failed: " + e.getMessage());
        }
    }

    /**
     * Test for Blockchain toString with block range.
     */
    @Test
    public void testBlockchainToStringWithRange() {
        try {
            Blockchain blockchain = new Blockchain("TestChain");
            Transaction transaction = new Transaction("Alice", "Bob", new BigDecimal(50));
            blockchain.createBlock(transaction);

            String result = blockchain.toString(0, blockchain.getBlocks().size());
            assertTrue(result.contains("BLOCKCHAIN TestChain:"));
            assertTrue(result.contains("Transaction{sender='Alice', receiver='Bob', amount=50}"));
        } catch (Exception e) {
            fail("Blockchain toString with range failed: " + e.getMessage());
        }
    }

    /**
     * Test for detecting duplicate hashes in the Blockchain.
     */
    @Test
    public void testDuplicateHashDetection() {
        try {
            Blockchain blockchain = new Blockchain();
            Transaction transaction = new Transaction("Alice", "Bob", new BigDecimal(50));
            blockchain.createBlock(transaction);

            // Attempt to add a block with a duplicate hash (manipulated test scenario)
            Block duplicateBlock = blockchain.getBlocks().get(1);
            blockchain.createBlock(transaction); // Should calculate a new unique hash

            List<Block> blocks = blockchain.getBlocks();
            assertEquals(3, blocks.size());
            assertNotEquals(blocks.get(1).getHash(), blocks.get(2).getHash());
        } catch (Exception e) {
            fail("Duplicate hash detection failed: " + e.getMessage());
        }
    }
}