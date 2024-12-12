import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a blockchain that manages a series of blocks containing transactions.
 * Provides functionality to add blocks, validate uniqueness of hashes, and manage blockchain metadata.
 *
 * @version 1.0
 * @author Charalampos Deligiannakis
 */
public class Blockchain {

    private String name; // Name of the blockchain
    private ArrayList<Block> blocks; // List of blocks in the blockchain
    private Set<String> hashes; // Set of unique block hashes to prevent duplication

    /**
     * Default constructor for the Blockchain.
     * Initializes the blockchain with a genesis block.
     *
     * @throws BlockException If there is an error creating the genesis block.
     * @throws TransactionException If there is an error with the transaction in the genesis block.
     */
    Blockchain() throws BlockException, TransactionException {
        this.blocks = new ArrayList<>();
        this.hashes = new HashSet<>();
        blocks.add(Block.createGenesisBlock());
    }

    /**
     * Constructor for the Blockchain with a name.
     * Initializes the blockchain with a genesis block and sets the name.
     *
     * @param name The name of the blockchain.
     * @throws BlockchainException If the provided name is invalid.
     * @throws BlockException If there is an error creating the genesis block.
     * @throws TransactionException If there is an error with the transaction in the genesis block.
     */
    Blockchain(String name) throws BlockchainException, BlockException, TransactionException {
        setName(name);
        this.blocks = new ArrayList<>();
        this.hashes = new HashSet<>();
        blocks.add(Block.createGenesisBlock());
    }

    /**
     * Sets the name of the blockchain.
     *
     * @param name The name to set for the blockchain.
     * @throws BlockchainException If the name contains invalid characters.
     */
    public void setName(String name) throws BlockchainException {
        if (name.matches("^[A-Za-zΑ-Ωα-ω0-9]+( ?[A-Za-zΑ-Ωα-ω0-9]*)")) {
            this.name = name;
        } else {
            // Throw an exception with an appropriate message and offending value
            throw new BlockchainException("Invalid receiver name.", name);
        }
    }

    /**
     * Retrieves the name of the blockchain.
     *
     * @return The name of the blockchain.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Creates and adds a new block to the blockchain.
     *
     * @param transaction The transaction data to include in the new block.
     * @throws BlockException If there is an error during block creation.
     */
    public void createBlock(Transaction transaction) throws BlockException {
        Block newBlock = new Block(blocks.size(), new Timestamp(System.currentTimeMillis()), transaction, blocks.get(blocks.size() - 1).getHash());
        String newHash;
        do {
            newHash = newBlock.calculateHash();
        } while (hashes.contains(newHash));
        newBlock.setHash(newHash, newBlock.getPreviousHash());

        blocks.add(newBlock);
        hashes.add(newHash);
    }

    /**
     * Creates and adds a new block to the blockchain with an option to show data.
     *
     * @param transaction The transaction data to include in the new block.
     * @param showData A flag indicating whether to show the data in the block.
     * @throws BlockException If there is an error during block creation.
     */
    public void createBlock(Transaction transaction, Boolean showData) throws BlockException {
        Block newBlock = new Block(blocks.size(), new Timestamp(System.currentTimeMillis()), transaction, blocks.get(blocks.size() - 1).getHash(), showData);
        String newHash;
        do {
            newHash = newBlock.calculateHash();
        } while (hashes.contains(newHash));
        newBlock.setHash(newHash, newBlock.getPreviousHash());

        blocks.add(newBlock);
        hashes.add(newHash);
    }

    /**
     * Retrieves the list of blocks in the blockchain.
     *
     * @return The list of blocks.
     */
    public ArrayList<Block> getBlocks() {
        return this.blocks;
    }

    /**
     * Returns a string representation of the blockchain.
     * Includes the name and the number of blocks.
     *
     * @return A string representation of the blockchain.
     */
    @Override
    public String toString() {
        return "Blockchain{" +
                "name=" + name +
                ", blocks=" + blocks.size() + " blocks" +
                '}';
    }

    /**
     * Returns a detailed string representation of the blockchain within a specific range of blocks.
     *
     * @param start The starting index of the blocks to include.
     * @param end The ending index of the blocks to include.
     * @return A string representation of the blockchain within the specified range.
     */
    public String toString(int start, int end) {
        StringBuilder output = new StringBuilder();
        output.append("BLOCKCHAIN ").append(name).append(":").append("\n");
        for (int i = start; i < end; i++) {
            output.append(blocks.get(i)).append("\n");
        }

        return output.toString();
    }
}