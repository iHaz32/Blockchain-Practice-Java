public class Main {
    public static void main(String[] args) throws BlockchainException, BlockException, TransactionException {
        Blockchain b = new Blockchain("my blockchain");
        System.out.println(b.toString());
        System.out.println(b.getBlocks().getFirst());
    }
}