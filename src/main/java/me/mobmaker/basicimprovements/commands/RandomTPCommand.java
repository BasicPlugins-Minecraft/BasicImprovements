package me.mobmaker.basicimprovements.commands;

import me.mobmaker.basicimprovements.BasicImprovements;
import me.mobmaker.basicimprovements.teleportation.TPData;
import me.mobmaker.basicimprovements.teleportation.TPRunnable;
import me.mobmaker.basicimprovements.utilities.Messages;
import me.mobmaker.basicimprovements.utilities.Rounding;
import net.kyori.adventure.text.Component;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class RandomTPCommand implements CommandExecutor {

    private final BukkitScheduler scheduler = getServer().getScheduler();
    private final double cooldown = 21600000;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            double timestamp = System.currentTimeMillis();
            PersistentDataContainer pdc = p.getPersistentDataContainer();
            if (pdc.has(TPData.lastRTP, PersistentDataType.DOUBLE)) {
                double lastRTP = pdc.get(TPData.lastRTP, PersistentDataType.DOUBLE);
                //TODO: configurable rtp cooldown
                if (lastRTP >= (timestamp - cooldown)) {
                    double remaining = Rounding.round((timestamp + cooldown - lastRTP)/60000,2);
                    p.sendMessage(Messages.RTP_COOLDOWN.get(Component.text(remaining)));
                    return true;
                }
            }
            Random rand = new Random();
            World world = getServer().getWorld("world");
            if (p.getWorld() != world) {
                p.sendMessage(Messages.WARP_WRONGWORLD.get(Component.text("Overworld")));
                return true;
            }
            if (args.length == 0) {
                int safe = 0;
                while (true) {
                    //TODO: configurable bounds
                    int rtpX = rand.nextInt(10000 + 10000) - 10000;
                    int rtpZ = rand.nextInt(10000 + 10000) - 10000;
                    Location loc = new Location(world, rtpX, 63, rtpZ, 0, 0);
                    Chunk chunk = loc.getChunk();
                    chunk.load();
                    for (int i = 64; i <= 256; i++) {
                        loc.setY(i);
                        if (loc.getBlock().isEmpty()) {
                            safe ++;
                            if (safe == 6) {
                                loc.setY(i-7);
                                if (!loc.getBlock().isEmpty()) {
                                    if (!loc.getBlock().isLiquid()) {
                                        loc.setY(i-5);
                                        //TODO: Configurable delay (universal for all TP'ing)
                                        BukkitTask task = new TPRunnable(loc, p, Messages.RTP_SUCCESS)
                                                .runTaskLater(BasicImprovements.instance(), 1000);
                                        TPData.warpTasks.put(p.getUniqueId(), task);
                                        return true;
                                    } else { safe = 0; }
                                } else { safe = 0; }
                            }
                        } else { safe = 0; }
                    }
                }
            }
        }

        return false;
    }
}
