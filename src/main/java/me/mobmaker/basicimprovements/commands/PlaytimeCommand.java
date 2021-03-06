package me.mobmaker.basicimprovements.commands;

import me.mobmaker.basicimprovements.stats.Statistics;
import me.mobmaker.basicimprovements.utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaytimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player p) {
                p.sendMessage(Statistics.play_one_minute.get(p.getUniqueId(), p));
                return true;
            } else return false;
        } else if (args.length == 1) {
            OfflinePlayer t = Bukkit.getOfflinePlayerIfCached(args[0]);
            if (t == null) {
                sender.sendMessage(Messages.PLUGIN_INVALID_PLAYER.get());
                return true;
            }
            sender.sendMessage(Statistics.play_one_minute.get(t.getUniqueId(), sender));
            return true;
        }
        return false;
    }
}
