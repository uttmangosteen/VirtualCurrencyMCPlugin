package io.github.uttmangosteen.virtualCurrencyMCPlugin;

import io.github.uttmangosteen.virtualCurrencyMCPlugin.BlockChain.BlockChain;
import org.bukkit.configuration.file.FileConfiguration;

public class Global {
    public static boolean isRunning;
    public static FileConfiguration config = Main.plugin.getConfig();
    public static BlockChain blockChain;
    public static MySQL mysql;
}
