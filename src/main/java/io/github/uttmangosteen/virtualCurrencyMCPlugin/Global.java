package io.github.uttmangosteen.virtualCurrencyMCPlugin;

import org.bukkit.configuration.file.FileConfiguration;

public class Global {
    public static boolean isRunning;
    public static FileConfiguration config = Main.plugin.getConfig();


    public static MySQL mysql;
}
