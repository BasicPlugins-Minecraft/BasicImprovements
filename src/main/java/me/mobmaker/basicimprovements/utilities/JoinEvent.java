package me.mobmaker.basicimprovements.utilities;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getFirstPlayed() > System.currentTimeMillis()-1000) {
            e.joinMessage(Messages.PLUGIN_FIRSTJOIN.get(p.displayName()));
            World w = Bukkit.getWorld("world");
            assert w != null;
            //TODO: configurable border resize
            w.getWorldBorder().setSize(w.getWorldBorder().getSize()+100, 30);
            Bukkit.getServer().broadcast(Messages.PLUGIN_PREFIX.get().append(
                    Messages.mm.parse(" The world border is now: <green>" + w.getWorldBorder().getSize()/2 +
                            "<white>, <green>-" + w.getWorldBorder().getSize()/2 + "<white>.")));
            //init stuff here
        } else {
            e.joinMessage(Messages.PLUGIN_JOIN.get(p.displayName()));
            //load stuff here
        }

    }
}
