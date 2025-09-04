package io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain;

import java.util.List;

public record Block(
        long index,
        long timestamp,
        List<Transaction> transactions,
        String merkleRoot,
        String previousHash,
        String hash
) {
}
