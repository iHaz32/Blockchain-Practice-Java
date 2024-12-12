import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws BlockchainException, BlockException, TransactionException {
        Blockchain b = new Blockchain("my blockchain");

        Transaction transaction1 = new Transaction("Bob", "Alice", BigDecimal.valueOf(32.50));
        b.createBlock(transaction1, false);

        Transaction transaction2 = new Transaction("Mark", "John", BigDecimal.valueOf(1360));
        b.createBlock(transaction2);

        System.out.println(b.toString(0, b.getBlocks().size()));
    }
}