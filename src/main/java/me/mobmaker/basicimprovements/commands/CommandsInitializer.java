package me.mobmaker.basicimprovements.commands;

import me.mobmaker.basicimprovements.BasicImprovements;

public class CommandsInitializer {

    public static void init(BasicImprovements pl) {
        pl.getCommand("ping").setExecutor(new PingCommand());
        pl.getCommand("broadcast").setExecutor(new BroadcastCommand());
        pl.getCommand("randomtp").setExecutor(new RandomTPCommand());
        pl.getCommand("statistic").setExecutor(new StatisticCommand());
        pl.getCommand("statistic").setTabCompleter(new StatisticCommand());
        pl.getCommand("playtime").setExecutor(new PlaytimeCommand());
        pl.getCommand("gamemode").setExecutor(new GamemodeCommand());
        pl.getCommand("message").setExecutor(new MessageCommand());
        pl.getCommand("help").setExecutor(new HelpCommand());
    }

}
