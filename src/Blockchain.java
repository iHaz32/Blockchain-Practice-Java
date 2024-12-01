import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Blockchain {

    private String name;
    private ArrayList<Block> blocks;
    private Set<String> hashes;

    Blockchain() {
        this.blocks = new ArrayList<>();
        this.hashes = new HashSet<>();
    }

    Blockchain(String name) throws BlockchainException {
        setName(name);
        this.blocks = new ArrayList<>();
        this.hashes = new HashSet<>();
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
}
