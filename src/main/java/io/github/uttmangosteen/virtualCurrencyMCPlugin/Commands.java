package io.github.uttmangosteen.virtualCurrencyMCPlugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (command.getName().equals("vc")) return vcCommand(sender, args);
        if (command.getName().equals("vcop")) return vcopCommand(sender, args);
        return true;
    }

    private static boolean vcCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission("vc.user")) return true;
        if (!(sender instanceof Player player)) {
            sender.sendMessage("プレイヤーのみ実行可能です");
            return true;
        }
        if (args.length == 0) return false;
        switch (args[0].toLowerCase()) {
            case "balance" -> {

                return true;
            }
            case "pay" -> {

                return true;
            }
            case "check" ->{

                return true;
            }
            default -> {return false;}
        }
    }

    private static boolean vcopCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission("vc.op")) return true;
        if (args.length == 0) return false;
        switch (args[0].toLowerCase()) {
            case "balance" -> {

                return true;
            }
            case "give" -> {

                return true;
            }
            case "check" -> {

                return true;
            }
            case "reload" -> {

                return true;
            }
            case "mine" -> {
                Player player = Bukkit.getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage("プレイヤーがオンラインではありません");
                    return true;
                }
                return true;
            }
            default -> {return false;}
        }
    }
}
