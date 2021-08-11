package me.mobmaker.basicimprovements.teleportation;

import me.mobmaker.basicimprovements.utilities.Messages;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TPRunnable extends BukkitRunnable {

    private final Location loc;
    private final Player p;
    private final Messages msg;

    public TPRunnable(Location loca, Player pl, Messages key) {
        loc = loca;
        p = pl;
        msg = key;
    }

    @Override
    public void run() {
        if (p.getWorld() != loc.getWorld()) {
            p.sendMessage(Messages.WARP_WRONGWORLD.get(loc.getWorld().getName()));
            return;
        }
        p.teleport(loc);
        TPData.warpTasks.remove(p.getUniqueId());
        p.sendMessage(msg.get());
    }
}
