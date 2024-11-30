import java.math.BigDecimal;

public class Transaction {
    private String sender;
    private String receiver;
    private BigDecimal amount;

    public Transaction(String sender, String receiver, BigDecimal amount) throws TransactionException {
        setSender(sender);
        setReceiver(receiver);
        setAmount(amount);
    }

    public void setSender(String sender) throws TransactionException {
        if (sender.matches("^[A-Za-zΑ-Ωα-ω]+( ?[A-Za-zΑ-Ωα-ω]*){5,35}$")) {
            this.sender = sender;
        } else {
            throw new TransactionException("Invalid sender name.", sender);
        }
    }

    public void setReceiver(String receiver) throws TransactionException {
        if (receiver.matches("^[A-Za-zΑ-Ωα-ω]+( ?[A-Za-zΑ-Ωα-ω]*){5,35}$")) {
            this.receiver = receiver;
        } else {
            throw new TransactionException("Invalid receiver name.", receiver);
        }
    }

    public void setAmount(BigDecimal amount) throws TransactionException {
        if (amount.compareTo(BigDecimal.ZERO)> 0) {
            this.amount = amount;
        } else {
            throw new TransactionException("Amount must be greater than zero.", amount);
        }
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                '}';
    }
}