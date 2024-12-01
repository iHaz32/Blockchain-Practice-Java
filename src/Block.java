import java.sql.Timestamp;

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

    /**
     * Constructs a new Block with the specified parameters.
     * This constructor validates all inputs before setting the values.
     *
     * @param index The index of the block in the blockchain.
     * @param timestamp The timestamp when the block is created.
     * @param data The transaction data associated with this block.
     * @param previousHash The hash of the previous block in the blockchain.
     * @param hash The hash of the current block.
     * @throws BlockException If any of the input parameters are invalid.
     */
    public Block(int index, Timestamp timestamp, Transaction data, String previousHash, String hash) throws BlockException {
        setIndex(index);
        setTimestamp(timestamp);
        setData(data);
        setPreviousHash(previousHash);
        setHash(hash, previousHash);
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
     * Returns a string representation of the Block object.
     *
     * @return A string representing the Block.
     */
    @Override
    public String toString() {
        return "Transaction Block{" +
                "index=" + index +
                ", timestamp=" + timestamp +
                ", data=" + data +
                ", previousHash='" + previousHash + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}