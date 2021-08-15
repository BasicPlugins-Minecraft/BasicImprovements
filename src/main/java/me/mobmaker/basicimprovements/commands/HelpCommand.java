package me.mobmaker.basicimprovements.commands;

import me.mobmaker.basicimprovements.utilities.Data;
import me.mobmaker.basicimprovements.utilities.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(Data.helpYml());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        while (scanner.hasNextLine()) {
            sender.sendMessage((Messages.mm.parse(scanner.nextLine())));
        }
        return true;
        //TODO: read from help.yml or make it if it doesn't exist

    }
}
