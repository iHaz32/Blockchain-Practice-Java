public class TransactionBlockException extends Exception {

    private Object offendingValue;

    public TransactionBlockException(String message) {
        super(message);
    }

    public TransactionBlockException(String message, Object offendingValue) {
        super(message);
        this.offendingValue = offendingValue;
    }

    public Object getOffendingValue() {
        return offendingValue;
    }

    @Override
    public String toString() {
        return "TransactionException{" +
                "message=" + getMessage() +
                ", offendingValue=" + offendingValue +
                '}';
    }
}