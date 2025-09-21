package io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Block {
    private final long id;
    private final long timestamp;
    private final String previousHash;
    private final String merkleRoot;
    private final long nonce;
    private final List<Transaction> transactions;
    private final String hash;

    public Block(long id, String previousHash, long nonce, List<Transaction> transactions) {
        this.id = id;
        this.timestamp = System.currentTimeMillis();
        this.previousHash = previousHash;
        this.transactions = List.copyOf(transactions);
        this.merkleRoot = calculateMerkleRoot(transactions);
        this.nonce = nonce;
        this.hash = calculateHash();
    }

    private String calculateHash() {
        String data = id + "|" + timestamp + "|" + previousHash + "|" + merkleRoot + "|" + nonce;
        return sha256(data);
    }

    private static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String calculateMerkleRoot(List<Transaction> transactions) {
        if (transactions.isEmpty()) return "";

        List<String> hashes = new ArrayList<>();
        for (Transaction tx : transactions) {
            hashes.add(sha256(tx.toString())); // シンプルに toString ベース
        }

        while (hashes.size() > 1) {
            List<String> newHashes = new ArrayList<>();
            for (int i = 0; i < hashes.size(); i += 2) {
                if (i + 1 < hashes.size()) {
                    newHashes.add(sha256(hashes.get(i) + hashes.get(i + 1)));
                } else {
                    newHashes.add(sha256(hashes.get(i) + hashes.get(i))); // 奇数の時は複製
                }
            }
            hashes = newHashes;
        }
        return hashes.getFirst();
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

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public long getNonce() {
        return nonce;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getHash() {
        return hash;
    }
}