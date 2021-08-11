package me.mobmaker.basicimprovements.commands;

import me.mobmaker.basicimprovements.utilities.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            Player entityPlayer;
            Component entityName;
            int ping;
            if (args.length == 0) {
                entityPlayer = player;
                entityName = Component.text("Your");
            } else if (args.length == 1) {
                entityPlayer = Bukkit.getPlayer(args[0]);
                if (entityPlayer == null) return false;
                entityName = entityPlayer.displayName();
            } else {
                return false;
            }
            ping = entityPlayer.getPing();
            sender.sendMessage(Messages.PLUGIN_PING.get(entityName, Component.text(ping)));
            return true;
        }
        return false;
    }
}
