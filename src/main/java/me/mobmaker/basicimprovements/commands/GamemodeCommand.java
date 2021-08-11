package me.mobmaker.basicimprovements.commands;

import me.mobmaker.basicimprovements.utilities.Aliases;
import me.mobmaker.basicimprovements.utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GameMode mode = Aliases.gameModes(args[0]);
        if (args.length == 1) {
            if (sender instanceof Player p) {
                if (mode != null) {
                    p.setGameMode(mode);
                    return true;
                }
                p.sendMessage(Messages.GAMEMODE_INVALID.get());
                return true;
            }
        }
        if (args.length == 2) {
            Player t = Bukkit.getPlayer(args[1]);
            if (t == null) {
                sender.sendMessage(Messages.PLUGIN_INVALID_PLAYER.get());
                return true;
            }
            if (mode != null) {
                t.setGameMode(mode);
                return true;
            }
            sender.sendMessage(Messages.GAMEMODE_INVALID.get());
            return true;
        }
        return false;
    }
}
