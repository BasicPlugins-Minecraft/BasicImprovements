package me.mobmaker.basicimprovements.teleportation;

import me.mobmaker.basicimprovements.utilities.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataType;

public class TPCancellationEvents implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getPersistentDataContainer().has(TPData.lastRTP, PersistentDataType.DOUBLE)) {
            TPData.warpTasks.get(p.getUniqueId()).cancel();
            TPData.warpTasks.remove(p.getUniqueId());
            p.sendMessage(Messages.WARP_CANCEL_MOVE.get());
        }
    }

    @EventHandler
    public void onPlayerDoDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (p.getPersistentDataContainer().has(TPData.lastRTP, PersistentDataType.DOUBLE)) {
                TPData.warpTasks.get(p.getUniqueId()).cancel();
                TPData.warpTasks.remove(p.getUniqueId());
                p.sendMessage(Messages.WARP_CANCEL_DAMAGER.get());
            }
        }
    }

    @EventHandler
    public void onPlayerDamaged(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (p.getPersistentDataContainer().has(TPData.lastRTP, PersistentDataType.DOUBLE)) {
                TPData.warpTasks.get(p.getUniqueId()).cancel();
                TPData.warpTasks.remove(p.getUniqueId());
                p.sendMessage(Messages.WARP_CANCEL_DAMAGED.get());
            }
        }
    }
}
