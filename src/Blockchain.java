import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Blockchain {

    private ArrayList<Block> blocks;
    private Set<String> hashes;

    Blockchain() {
        this.blocks = new ArrayList<>();
        this.hashes = new HashSet<>();
    }
}
