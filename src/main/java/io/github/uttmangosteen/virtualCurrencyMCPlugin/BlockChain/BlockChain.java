package io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain;

import io.github.uttmangosteen.virtualCurrencyMCPlugin.Global;
import io.github.uttmangosteen.virtualCurrencyMCPlugin.MySQL;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {
    private final List<Block> chain;
    private final List<Transaction> pendingTransactions;
    private final int TRANSACTIONS_PER_BLOCK;
    private long nextTransactionIndex;
    private long startFromBlockIndex;

    public BlockChain(MySQL database){
        this.chain = new ArrayList<>();
        this.pendingTransactions = new ArrayList<>();
        this.TRANSACTIONS_PER_BLOCK = Global.config.getInt("blockchain.transactions_per_block", 16);
        this.nextTransactionIndex = 1;
        this.startFromBlockIndex = 0;
    }
}
