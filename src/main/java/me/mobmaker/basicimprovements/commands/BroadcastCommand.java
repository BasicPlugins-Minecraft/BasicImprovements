package me.mobmaker.basicimprovements.commands;

import me.mobmaker.basicimprovements.utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        StringBuilder broadStream = new StringBuilder();
        for (String arg : args) {
            broadStream.append(" ").append(arg);
        }
        Bukkit.broadcast(Messages.PLUGIN_PREFIX.get().append(Messages.mm.parse(broadStream.toString())));
        return true;
    }
}
