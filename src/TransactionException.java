public class TransactionException extends Exception {
    private Object offendingValue;

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Object offendingValue) {
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