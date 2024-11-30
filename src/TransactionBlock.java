import java.sql.Timestamp;

public class TransactionBlock {
    private int index;
    private Timestamp timestamp;
    private Transaction data;
    private String previousHash;
    private String hash;

    public TransactionBlock(int index, Timestamp timestamp, Transaction data, String previousHash, String hash) throws TransactionBlockException {
        setIndex(index);
        setTimestamp(timestamp);
        setData(data);
        setPreviousHash(previousHash);
        setHash(hash);
    }

    public void setIndex(int index) throws TransactionBlockException {
        if (index < 0) {
            throw new TransactionBlockException("Index cannot be negative.", index);
        }
        this.index = index;
    }

    public void setTimestamp(Timestamp timestamp) throws TransactionBlockException {
        if (timestamp == null) {
            throw new TransactionBlockException("Timestamp cannot be null.", timestamp);
        }
        if (timestamp.after(new Timestamp(System.currentTimeMillis()))) {
            throw new TransactionBlockException("Timestamp cannot be in the future.", timestamp);
        }
        this.timestamp = timestamp;
    }

    public void setData(Transaction data) throws TransactionBlockException {
        if (data == null) {
            throw new TransactionBlockException("Transaction data cannot be null.", data);
        }
        this.data = data;
    }

    public void setPreviousHash(String previousHash) throws TransactionBlockException {
        if (previousHash == null || previousHash.isEmpty()) {
            throw new TransactionBlockException("Previous hash cannot be null or empty.", previousHash);
        }
        // Optionally validate hash format (e.g., 64 hex characters for SHA-256)
        this.previousHash = previousHash;
    }

    public void setHash(String hash) throws TransactionBlockException {
        if (hash == null || hash.isEmpty()) {
            throw new TransactionBlockException("Hash cannot be null or empty.", hash);
        }
        // Optionally validate hash format (e.g., 64 hex characters for SHA-256)
        this.hash = hash;
    }

    public int getIndex() {
        return this.index;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public Transaction getData() {
        return this.data;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    public String getHash() {
        return this.hash;
    }

    @Override
    public String toString() {
        return "Transaction Block{" +
                "index='" + index + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", data='" + data + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", hash=" + hash +
                '}';
    }
}