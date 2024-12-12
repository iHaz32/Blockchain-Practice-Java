import java.math.BigDecimal;
import java.sql.Timestamp;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * Represents a block in a transaction blockchain.
 * A Block contains information about a transaction, a timestamp, and hash values,
 * and links to the previous block in the chain.
 * This class validates the integrity of a block by ensuring that the index is non-negative,
 * the timestamp is not in the future, and the transaction data and hashes are valid.
 *
 * @version 1.0
 * @author Charalampos Deligiannakis
 */
public class Block {

    private int index;
    private Timestamp timestamp;
    private Transaction data;
    private String previousHash;
    private String hash;
    private boolean showData;

    /**
     * Constructs a new Block with the specified parameters.
     * This constructor validates all inputs before setting the values.
     *
     * @param index The index of the block in the blockchain.
     * @param timestamp The timestamp when the block is created.
     * @param data The transaction data associated with this block.
     * @param previousHash The hash of the previous block in the blockchain.
     * @throws BlockException If any of the input parameters are invalid.
     */
    public Block(int index, Timestamp timestamp, Transaction data, String previousHash) throws BlockException {
        setIndex(index);
        setTimestamp(timestamp);
        setData(data);
        setPreviousHash(previousHash);
        setHash(calculateHash(), previousHash);
        this.showData = true;
    }

    /**
     * Constructs a new Block with the specified parameters.
     * This constructor validates all inputs before setting the values.
     *
     * @param index The index of the block in the blockchain.
     * @param timestamp The timestamp when the block is created.
     * @param data The transaction data associated with this block.
     * @param previousHash The hash of the previous block in the blockchain.
     * @param showData The block data may be private
     * @throws BlockException If any of the input parameters are invalid.
     */
    public Block(int index, Timestamp timestamp, Transaction data, String previousHash, Boolean showData) throws BlockException {
        setIndex(index);
        setTimestamp(timestamp);
        setData(data);
        setPreviousHash(previousHash);
        setHash(calculateHash(), previousHash);
        this.showData = showData;
    }

    /**
     * Sets the index for this block.
     *
     * @param index The index of the block.
     * @throws BlockException If the index is negative.
     */
    public void setIndex(int index) throws BlockException {
        if (index < 0) {
            throw new BlockException("Index cannot be negative.", index);
        }
        this.index = index;
    }

    /**
     * Sets the timestamp for this block.
     * The timestamp must not be null and must not be in the future.
     *
     * @param timestamp The timestamp of the block.
     * @throws BlockException If the timestamp is null or in the future.
     */
    public void setTimestamp(Timestamp timestamp) throws BlockException {
        if (timestamp == null) {
            throw new BlockException("Timestamp cannot be null.", timestamp);
        }
        long now = System.currentTimeMillis();
        if (timestamp.getTime() > now + 10000) { // Allow a 10-second window for clock differences
            throw new BlockException("Timestamp cannot be in the future.", timestamp);
        }
        this.timestamp = timestamp;
    }

    /**
     * Sets the transaction data for this block.
     * The transaction data must not be null.
     *
     * @param data The transaction data associated with the block.
     * @throws BlockException If the transaction data is null.
     */
    public void setData(Transaction data) throws BlockException {
        if (data == null) {
            throw new BlockException("Transaction data cannot be null.", data);
        }
        this.data = data;
    }

    /**
     * Sets the previous hash for this block.
     * The previous hash must not be null or empty.
     *
     * @param previousHash The hash of the previous block.
     * @throws BlockException If the previous hash is null or empty.
     */
    public void setPreviousHash(String previousHash) throws BlockException {
        if (previousHash == null || previousHash.isEmpty()) {
            throw new BlockException("Previous hash cannot be null or empty.", previousHash);
        }
        if (!previousHash.matches("[a-fA-F0-9]{64}")) { // SHA-256 hash format
            throw new BlockException("Invalid hash format.", hash);
        }
        this.previousHash = previousHash;
    }

    /**
     * Sets the hash for this block.
     * The hash must not be null or empty or same with previousHash.
     *
     * @param hash The hash of the current block.
     * @param previousHash The previous hash of the current block.
     * @throws BlockException If the hash is null or empty.
     */
    public void setHash(String hash, String previousHash) throws BlockException {
        if (hash == null || hash.isEmpty()) {
            throw new BlockException("Hash cannot be null or empty.", hash);
        }
        if (hash.equals(previousHash)) {
            throw new BlockException("Hash cannot be same with previous hash.", hash);
        }
        this.hash = hash;
    }

    /**
     * Returns the index of this block.
     *
     * @return The index of the block.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Returns the timestamp of this block.
     *
     * @return The timestamp of the block.
     */
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    /**
     * Returns the transaction data associated with this block.
     *
     * @return The transaction data.
     */
    public Transaction getData() {
        return this.data;
    }

    /**
     * Returns the previous hash of this block.
     *
     * @return The previous hash of the block.
     */
    public String getPreviousHash() {
        return this.previousHash;
    }

    /**
     * Returns the hash of this block.
     *
     * @return The hash of the block.
     */
    public String getHash() {
        return this.hash;
    }

    /**
     * Calculates the SHA-256 hash of the block based on its properties.
     *
     * @return The calculated hash as a hexadecimal string.
     */
    public String calculateHash() {
        String input = index + timestamp.toString() + data.toString() + previousHash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error calculating hash", e);
        }
    }

    /**
     * Validates the current block's hash by recalculating it and comparing with the stored hash.
     *
     * @return True if the stored hash matches the calculated hash, false otherwise.
     */
    public boolean isHashValid() {
        return hash.equals(calculateHash());
    }

    /**
     * Creates the genesis block (the first block in the blockchain).
     *
     * @return A new block with predefined properties as the genesis block.
     * @throws BlockException If any block property fails validation.
     */
    public static Block createGenesisBlock() throws BlockException, TransactionException {
        Block genesisBlock = new Block(
                0, // Genesis block index is 0
                new Timestamp(System.currentTimeMillis()), // Current timestamp
                new Transaction("Genesis", "Genesis", BigDecimal.ONE), // Dummy transaction
                "0000000000000000000000000000000000000000000000000000000000000000" // No previous hash for the genesis block
        );
        // Calculate and set the actual hash
        String calculatedHash = genesisBlock.calculateHash();
        genesisBlock.setHash(calculatedHash, genesisBlock.getPreviousHash());

        return genesisBlock;
    }

    /**
     * Checks if this block is equal to another block by comparing all fields.
     *
     * @param other The block to compare with.
     * @return True if all fields match, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Block otherBlock)) return false;
        return index == otherBlock.index &&
                timestamp.equals(otherBlock.timestamp) &&
                data.equals(otherBlock.data) &&
                previousHash.equals(otherBlock.previousHash) &&
                hash.equals(otherBlock.hash);
    }

    /**
     * Returns a string representation of the Block object.
     *
     * @return A string representing the Block.
     */
    @Override
    public String toString() {
        return "Transaction Block{" +
                "index=" + index +
                ", timestamp=" + timestamp +
                ", data=" + (showData ? data : "hidden") +
                ", previousHash='" + previousHash + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}