package io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain;

import java.util.UUID;

public class Transaction {
    private final long Id;
    private final long timestamp;
    private final UUID senderUUID;
    private final String senderName;
    private final UUID receiverUUID;
    private final String receiverName;
    private final long amount;
    private final long fee;

    private long blockId;

    public Transaction(long Id, UUID senderUUID, String senderName, UUID receiverUUID, String receiverName, long amount, long fee) {
        this.Id = Id;
        this.timestamp = System.currentTimeMillis();
        this.senderUUID = senderUUID;
        this.senderName = senderName;
        this.receiverUUID = receiverUUID;
        this.receiverName = receiverName;
        this.amount = amount;
        this.fee = fee;
        this.blockId = 0;
    }

    public long getId() {
        return Id;
    }

    public long getBlockId() {
        return blockId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public UUID getSenderUUID() {
        return senderUUID;
    }

    public String getSenderName() {
        return senderName;
    }

    public UUID getReceiverUUID() {
        return receiverUUID;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public long getAmount() {
        return amount;
    }

    public long getFee() {
        return fee;
    }

    public void setBlockId(long blockId) {
        this.blockId = blockId;
    }
}