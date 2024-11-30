import java.math.BigDecimal;

/**
 * This class represents a transaction between two parties, with a sender, receiver, and an amount.
 * It validates the sender and receiver names (ensuring they meet certain criteria) and ensures
 * the transaction amount is greater than zero. It also includes methods to retrieve the sender,
 * receiver, and amount, as well as a string representation of the transaction.
 * <p>
 * Version 1.0
 * Author: Charalampos Deligiannakis
 */
public class Transaction {

    // Sender's name in the transaction
    private String sender;

    // Receiver's name in the transaction
    private String receiver;

    // Amount of money involved in the transaction
    private BigDecimal amount;

    /**
     * Constructor that initializes a new Transaction with a sender, receiver, and amount.
     *
     * @param sender   The sender's name (must meet validation criteria).
     * @param receiver The receiver's name (must meet validation criteria).
     * @param amount   The amount of money involved in the transaction (must be greater than zero).
     * @throws TransactionException If the sender or receiver names are invalid, or the amount is not valid.
     */
    public Transaction(String sender, String receiver, BigDecimal amount) throws TransactionException {
        setSender(sender);
        setReceiver(receiver);
        setAmount(amount);
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
        if (amount == null || amount.compareTo(BigDecimal.ZERO) > 0) {
            this.amount = amount;
        } else {
            // Throw an exception with an appropriate message and offending value
            throw new TransactionException("Amount must be greater than zero.", amount);
        }
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
     * Returns a string representation of the transaction.
     * The string includes the sender, receiver, and amount.
     *
     * @return A string representation of the transaction.
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                '}';
    }
}