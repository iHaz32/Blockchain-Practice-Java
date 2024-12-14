package models;

import exceptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * This class represents a transaction between two parties, with a sender, receiver, amount, and timestamp.
 * It validates the sender and receiver names (ensuring they meet certain criteria), ensures the transaction
 * amount is greater than zero, and provides methods to retrieve the sender, receiver, amount, and timestamp.
 * It also includes methods for setting and getting the timestamp, as well as a string representation of the transaction.
 * <p>
 * Version 1.1
 * Author: Charalampos Deligiannakis
 */
public class Transaction {

    // Sender's name in the transaction
    private String sender;

    // Receiver's name in the transaction
    private String receiver;

    // Amount of money involved in the transaction
    private BigDecimal amount;

    // Timestamp of when the transaction was created
    private Timestamp timestamp;

    /**
     * Constructor that initializes a new Transaction with a sender, receiver, amount, and timestamp.
     *
     * @param sender   The sender's name (must meet validation criteria).
     * @param receiver The receiver's name (must meet validation criteria).
     * @param amount   The amount of money involved in the transaction (must be greater than zero).
     * @throws TransactionException If the sender or receiver names are invalid, the amount is not valid, or the timestamp is null.
     */
    public Transaction(String sender, String receiver, BigDecimal amount) throws TransactionException {
        setSender(sender);
        setReceiver(receiver);
        setAmount(amount);
        setTimestamp(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Sets the sender's name. Validates that the name is between 5 and 35 characters and
     * only contains alphabetic characters and spaces.
     *
     * @param sender The sender's name.
     * @throws TransactionException If the sender's name is invalid.
     */
    public void setSender(String sender) throws TransactionException {
        // Regular expression allows alphabetic characters, including spaces, between 5 to 35 characters
        if (sender.matches("^[A-Za-zΑ-Ωα-ω]+( ?[A-Za-zΑ-Ωα-ω]*){5,35}$")) {
            this.sender = sender;
        } else {
            // Throw an exception with an appropriate message and offending value
            throw new TransactionException("Invalid sender name.", sender);
        }
    }

    /**
     * Sets the receiver's name. Validates that the name is between 5 and 35 characters and
     * only contains alphabetic characters and spaces.
     *
     * @param receiver The receiver's name.
     * @throws TransactionException If the receiver's name is invalid.
     */
    public void setReceiver(String receiver) throws TransactionException {
        // Regular expression allows alphabetic characters, including spaces, between 5 to 35 characters
        if (receiver.matches("^[A-Za-zΑ-Ωα-ω]+( ?[A-Za-zΑ-Ωα-ω]*){5,35}$")) {
            this.receiver = receiver;
        } else {
            // Throw an exception with an appropriate message and offending value
            throw new TransactionException("Invalid receiver name.", receiver);
        }
    }

    /**
     * Sets the transaction amount. Validates that the amount is greater than zero.
     *
     * @param amount The transaction amount.
     * @throws TransactionException If the amount is less than or equal to zero.
     */
    public void setAmount(BigDecimal amount) throws TransactionException {
        // Compare the amount with BigDecimal.ZERO to ensure it's positive and not null
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            this.amount = amount;
        } else {
            // Throw an exception with an appropriate message and offending value
            throw new TransactionException("Amount must be greater than zero.", amount);
        }
    }

    /**
     * Sets the timestamp for this transaction.
     * The timestamp must not be null and must not be in the future.
     *
     * @param timestamp The timestamp of the transaction.
     * @throws TransactionException If the timestamp is null or in the future.
     */
    public void setTimestamp(Timestamp timestamp) throws TransactionException {
        if (timestamp == null) {
            throw new TransactionException("Timestamp cannot be null.", timestamp);
        }
        long now = System.currentTimeMillis();
        if (timestamp.getTime() > now + 10000) { // Allow a 10-second window for clock differences
            throw new TransactionException("Timestamp cannot be in the future.", timestamp);
        }
        this.timestamp = timestamp;
    }

    /**
     * Returns the sender's name.
     *
     * @return The sender's name.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Returns the receiver's name.
     *
     * @return The receiver's name.
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Returns the transaction amount.
     *
     * @return The transaction amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Returns the timestamp of the transaction.
     *
     * @return The timestamp of the transaction.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Returns a string representation of the transaction.
     * The string includes the sender, receiver, amount, and timestamp.
     *
     * @return A string representation of the transaction.
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}