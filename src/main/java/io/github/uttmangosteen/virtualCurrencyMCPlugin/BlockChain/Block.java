package io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain;

import java.util.List;
import java.util.UUID;

public record Block(
        long Id,
        long nonce,
        UUID minerUUID,
        String minerName,
        long timestamp,
        String previousHash,
        String hash,
        List<Transaction> transactions
) {
}