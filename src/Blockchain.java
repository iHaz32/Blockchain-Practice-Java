import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Blockchain {

    private String name;
    private ArrayList<Block> blocks;
    private Set<String> hashes;

    Blockchain() throws BlockException, TransactionException {
        this.blocks = new ArrayList<>();
        this.hashes = new HashSet<>();
        blocks.add(Block.createGenesisBlock());
    }

    Blockchain(String name) throws BlockchainException, BlockException, TransactionException {
        setName(name);
        this.blocks = new ArrayList<>();
        this.hashes = new HashSet<>();
        blocks.add(Block.createGenesisBlock());
    }

    public void setName(String name) throws BlockchainException {
        if (name.matches("^[A-Za-zΑ-Ωα-ω0-9]+( ?[A-Za-zΑ-Ωα-ω0-9]*)")) {
            this.name = name;
        } else {
            // Throw an exception with an appropriate message and offending value
            throw new BlockchainException("Invalid receiver name.", name);
        }
    }

    public String getName() {
        return this.name;
    }

    public void addBlock(Transaction transaction) throws BlockException {
        Block newBlock = new Block(blocks.size(), new Timestamp(System.currentTimeMillis()), transaction, blocks.getLast().getHash(), "0000000000000000000000000000000000000000000000000000000000000000");
        String newHash;
        do {
            newHash = newBlock.calculateHash();
        } while(hashes.contains(newHash));
        newBlock.setHash(newHash, newBlock.getPreviousHash());

        blocks.add(newBlock);
        hashes.add(newHash);
    }

    public ArrayList<Block> getBlocks() {
        return this.blocks;
    }

    @Override
    public String toString() {
        return "Blockchain{" +
                "name=" + name +
                ", blocks=" + blocks.size() + " blocks" +
                '}';
    }
}
