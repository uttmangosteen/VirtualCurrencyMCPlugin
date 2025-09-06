package io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain;

import java.util.List;

public class Block {
    private final long id;
    private final long timestamp;
    private final String previousHash;
    private final long nonce;
    private final List<Transaction> transactions;
    private final String merkleRoot;
    private final String hash;

    public Block(long id, String previousHash, long nonce, List<Transaction> transactions) {
        this.id = id;
        this.timestamp = System.currentTimeMillis();
        this.previousHash = previousHash;
        this.nonce = nonce;
        this.transactions = List.copyOf(transactions);
        this.merkleRoot = "";//transactions→hash関数で計算
        this.hash = "";//関数で計算
    }

    public long getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getNonce() {
        return nonce;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getHash() {
        return hash;
    }
}