package exceptions;

/**
 * Custom exception class for handling errors related to models.Transaction Blocks.
 * This exception provides information about invalid data within a transaction block,
 * specifically which value caused the exception.
 *
 * @version 1.0
 * @author Charalampos Deligiannakis
 */
public class BlockException extends Exception {

    private Object offendingValue;

    /**
     * Constructs a new BlockException with the specified message.
     *
     * @param message The detail message that describes the error.
     */
    public BlockException(String message) {
        super(message);
    }

    /**
     * Constructs a new BlockException with the specified message and offending value.
     * The offending value provides additional context about the value that caused the exception.
     *
     * @param message The detail message that describes the error.
     * @param offendingValue The value that caused the exception (e.g., an invalid timestamp, hash, etc.).
     */
    public BlockException(String message, Object offendingValue) {
        super(message);
        this.offendingValue = offendingValue;
    }

    /**
     * Retrieves the value that caused the exception.
     * This could be the invalid transaction data, index, timestamp, etc.
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
        return "BlockException{" +
                "message=" + getMessage() +
                ", offendingValue=" + offendingValue +
                '}';
    }
}