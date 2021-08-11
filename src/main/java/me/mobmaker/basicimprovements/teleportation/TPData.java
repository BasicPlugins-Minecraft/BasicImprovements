package me.mobmaker.basicimprovements.teleportation;

import me.mobmaker.basicimprovements.BasicImprovements;
import org.bukkit.NamespacedKey;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class TPData {

    public static NamespacedKey lastRTP;

    public static HashMap<UUID, BukkitTask> warpTasks = new HashMap<>();

    public static void init(BasicImprovements pl) {
        lastRTP = new NamespacedKey(pl, "lastrtp");
    }

}
