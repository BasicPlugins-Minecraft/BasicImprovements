package me.mobmaker.basicimprovements.commands;

import me.mobmaker.basicimprovements.utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player t = Bukkit.getServer().getPlayer(args[0]);
        if (t == null) {
            sender.sendMessage(Messages.PLUGIN_INVALID_PLAYER.get());
            return true;
        }
        StringBuilder msg = new StringBuilder();
        for (String arg : args) {
            if (!arg.equals(args[0])) {
                msg.append(" ").append(arg);
            }
        }
        t.sendMessage(Messages.MESSAGE_PLAYER.get(sender.getName()).append(Messages.mm.parse(msg.toString())));
        return true;
    }
}
