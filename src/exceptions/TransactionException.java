package exceptions;

/**
 * Custom exception class used for handling errors related to transactions.
 * This exception captures the error message and an optional "offending value"
 * (the value that caused the exception). It provides mechanisms for retrieving
 * both pieces of information.
 * <p>
 * Version 1.0
 * Author: Charalampos Deligiannakis
 */
public class TransactionException extends Exception {

    // The value that caused the exception (can be any object type)
    private Object offendingValue;

    /**
     * Constructor to create a new TransactionException with just an error message.
     *
     * @param message The error message explaining what went wrong.
     */
    public TransactionException(String message) {
        super(message);
    }

    /**
     * Constructor to create a new TransactionException with an error message
     * and an offending value (the value that caused the issue).
     *
     * @param message         The error message explaining what went wrong.
     * @param offendingValue  The value that caused the exception.
     */
    public TransactionException(String message, Object offendingValue) {
        super(message);
        this.offendingValue = offendingValue;
    }

    /**
     * Returns the value that caused the exception.
     *
     * @return The offending value (could be any object).
     */
    public Object getOffendingValue() {
        return offendingValue;
    }

    /**
     * Overrides the default toString() method to provide more detailed exception
     * information, including the message and offending value.
     *
     * @return A string representation of the exception, including the message and offending value.
     */
    @Override
    public String toString() {
        return "TransactionException{" +
                "message=" + getMessage() +
                ", offendingValue=" + offendingValue +
                '}';
    }
}