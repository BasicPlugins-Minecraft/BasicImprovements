package me.mobmaker.basicimprovements.utilities;

import org.bukkit.GameMode;

public class Aliases {

    public static GameMode gameModes(String name) {
        return switch (name) {
            case "survival", "0", "s" -> GameMode.SURVIVAL;
            case "creative", "1", "c" -> GameMode.CREATIVE;
            case "adventure", "2", "a" -> GameMode.ADVENTURE;
            case "spectator", "3", "sp", "spec" -> GameMode.SPECTATOR;
            default -> null;
        };
    }
}
