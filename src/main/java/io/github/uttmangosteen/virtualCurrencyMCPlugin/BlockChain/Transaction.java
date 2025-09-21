package io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain;

import java.util.UUID;

public record Transaction(
        long id,
        long timestamp,
        UUID senderUUID,
        String senderName,
        UUID receiverUUID,
        String receiverName,
        long amount,
        long fee,
        long blockId,
        String signature,
        String publicKey,
        boolean serverSigned //trueならサーバー署名,falseならプレイヤー署名
) {
}