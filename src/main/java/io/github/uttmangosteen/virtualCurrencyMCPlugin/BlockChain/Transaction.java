package io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain;

import java.util.UUID;

public record Transaction(
        long index,
        long timestamp,
        String sender,
        UUID senderUUID,
        String receiver,
        UUID receiverUUID,
        long amount
) {
}
