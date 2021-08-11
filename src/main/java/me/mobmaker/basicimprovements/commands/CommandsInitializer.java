package me.mobmaker.basicimprovements.commands;

import me.mobmaker.basicimprovements.BasicImprovements;

public class CommandsInitializer {

    public static void init(BasicImprovements pl) {
        pl.getCommand("ping").setExecutor(new PingCommand());
        pl.getCommand("broadcast").setExecutor(new BroadcastCommand());
        pl.getCommand("randomtp").setExecutor(new RandomTPCommand());
        pl.getCommand("statistic").setExecutor(new StatisticCommand());
        pl.getCommand("playtime").setExecutor(new StatisticCommand());
        pl.getCommand("gamemode").setExecutor(new GamemodeCommand());
    }

}
