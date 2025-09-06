package io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain;

import java.util.UUID;

public record Transaction(
        long Id,
        long blockId,
        long timestamp,
        UUID senderUUID,
        String senderName,
        UUID receiverUUID,
        String receiverName,
        long amount,
        long fee
) {
}