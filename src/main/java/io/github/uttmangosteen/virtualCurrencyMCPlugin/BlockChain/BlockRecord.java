package io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain;

public record BlockRecord(
        long id,
        long timestamp,
        String previousHash,
        long nonce,
        String merkleRoot,
        String hash
) {
}