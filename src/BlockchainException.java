/**
 * Custom exception class for handling errors related to Blockchain.
 * This exception provides information about invalid data within a blockchain,
 * specifically which value caused the exception.
 *
 * @version 1.0
 * @author Charalampos Deligiannakis
 */
public class BlockchainException extends Exception {

    private Object offendingValue;

    /**
     * Constructs a new BlockchainException with the specified message.
     *
     * @param message The detail message that describes the error.
     */
    public BlockchainException(String message) {
        super(message);
    }

    /**
     * Constructs a new BlockchainException with the specified message and offending value.
     * The offending value provides additional context about the value that caused the exception.
     *
     * @param message The detail message that describes the error.
     * @param offendingValue The value that caused the exception.
     */
    public BlockchainException(String message, Object offendingValue) {
        super(message);
        this.offendingValue = offendingValue;
    }

    /**
     * Retrieves the value that caused the exception.
     *
     * @return The offending value associated with the exception.
     */
    public Object getOffendingValue() {
        return offendingValue;
    }

    /**
     * Returns a string representation of the exception, including the message and the offending value.
     *
     * @return A string representation of the exception.
     */
    @Override
    public String toString() {
        return "BlockchainException{" +
                "message=" + getMessage() +
                ", offendingValue=" + offendingValue +
                '}';
    }
}