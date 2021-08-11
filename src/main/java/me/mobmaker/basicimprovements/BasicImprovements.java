package me.mobmaker.basicimprovements;

import me.mobmaker.basicimprovements.commands.CommandsInitializer;
import me.mobmaker.basicimprovements.teleportation.TPCancellationEvents;
import me.mobmaker.basicimprovements.teleportation.TPData;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class BasicImprovements extends JavaPlugin {

    public static boolean isEcon;
    private static Economy econ;
    private static BasicImprovements instance;

    @Override
    public void onEnable() {
        instance = this;
        if (!setupEconomy()) {
            this.getLogger().severe("Economy not found. Economy integration disabled!");
            isEcon = false;
        } else isEcon = true;
        TPData.init(instance);
        Bukkit.getPluginManager().registerEvents(new TPCancellationEvents(), instance);
        CommandsInitializer.init(instance);
        //this.saveDefaultConfig();
    }

    public static BasicImprovements instance() {
        return instance;
    }

    public static FileConfiguration config() {
        return instance().getConfig();
    }

    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public static Economy economy() {
        return econ;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
