package io.github.uttmangosteen.virtualCurrencyMCPlugin;

import io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain.BlockChain;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        MySQL mysql = new MySQL();
        Global.mysql = mysql;

        Objects.requireNonNull(getCommand("vc")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("vcop")).setExecutor(new Commands());

        if (!Global.isRunning) return;

        Global.blockChain = new BlockChain(mysql, getConfig().getInt("blockchain.transactions_per_block", 16));
        getLogger().info("正常に起動しました");
    }

    @Override
    public void onDisable() {
        Global.mysql.connectionClose();
        getLogger().info("停止しました");
    }
}
