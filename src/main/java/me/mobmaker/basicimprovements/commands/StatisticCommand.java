package me.mobmaker.basicimprovements.commands;

import me.mobmaker.basicimprovements.stats.Statistics;
import me.mobmaker.basicimprovements.utilities.Data;
import me.mobmaker.basicimprovements.utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StatisticCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player p) {
                p.sendMessage(Statistics.valueOf(args[0]).get(p.getUniqueId(), p));
                return true;
            } else return false;
        } else if (args.length == 2) {
            OfflinePlayer t = Bukkit.getOfflinePlayerIfCached(args[0]);
            if (t == null) {
                sender.sendMessage(Messages.PLUGIN_INVALID_PLAYER.get());
                return true;
            }
            sender.sendMessage(Statistics.valueOf(args[1]).get(t.getUniqueId(), sender));
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 2) {
            return Data.stats;
        }
        ArrayList<String> temp = new ArrayList<>(Data.stats);
        for (Player e : Bukkit.getOnlinePlayers()) {
            temp.add(e.getName());
        }
        return temp;
    }
}
