package me.mobmaker.basicimprovements.stats;

import me.mobmaker.basicimprovements.utilities.Messages;
import me.mobmaker.basicimprovements.utilities.Pair;
import me.mobmaker.basicimprovements.utilities.Rounding;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.UUID;

public enum Statistics {
    //Minecraft leaderboard statistics
    animals_bred("bred animals", "times", 1),
    clean_armor("reset leather armor", "times", 1),
    clean_banner("reset a banner", "times", 1),
    open_barrel("opened a barrel","times",1),
    bell_ring("rung a bell", "times", 1),
    eat_cake_slice("eaten a slice of cake", "times", 1),
    fill_cauldron("filled a cauldron", "times", 1),
    open_chest("opened a chest", "times", 1),
    damage_absorbed("taken damage","hearts",10),
    damage_blocked_by_shield("blocked damage with a shield","hearts",10),
    damage_dealt("sent melee damage of","hearts",10),
    damage_dealt_absorbed("successfully hit damage worth", "hearts", 10),
    damage_dealt_resisted("had damage deflected worth", "hearts", 10),
    damage_resisted("resisted damage worth", "hearts", 10),
    damage_taken("taken damage worth", "hearts", 10),
    inspect_dispenser("interacted with dispensers","times",1),
    climb_one_cm("climbed up a block","blocks",100),
    crouch_one_cm("walked while crouching", "blocks", 100),
    fall_one_cm("fallen down", "blocks", 100),
    fly_one_cm("flown forward", "blocks", 100),
    sprint_one_cm("sprinted for","blocks", 100),
    swim_one_cm("swam for","blocks",100),
    walk_one_cm("walked for", "blocks", 100),
    walk_on_water_one_cm("bobbed on top of water for", "blocks", 100),
    walk_under_water_one_cm("walk in water for","blocks",100),
    boat_one_cm("traveled by boat for", "blocks", 100),
    aviate_one_cm("traveled using elytra for", "blocks", 100),
    horse_one_cm("traveled by horse for", "blocks", 100),
    minecart_one_cm("traveled by minecart for","blocks",100),
    pig_one_cm("traveled on a pig for","blocks",100),
    strider_one_cm("traveled on a strider for", "blocks", 100),
    inspect_dropper("interacted with droppers", "times", 1),
    open_enderchest("opened a enderchest","times",1),
    fish_caught("caught fish","times",1),
    leave_game("Left through 'save and quit'","times",1),
    inspect_hopper("interacted with a hopper","times",1),
    interact_with_anvil("interacted with an anvil","times",1),
    interact_with_beacon("interacted with a beacon","times",1),
    interact_with_blast_furnace("interacted with a blast furnace","times",1),
    interact_with_brewingstand("interacted with a brewing stand","times",1),
    interact_with_campfire("interacted with a campfire","times",1),
    interact_with_cartography_table("interacted with a cartography table","times",1),
    interact_with_crafting_table("interacted with a crafting table","times",1),
    interact_with_furnace("interacted with a furnace", "times", 1),
    interact_with_grindstone("interacted with a grindstone", "times", 1),
    interact_with_lectern("interacted with a lectern", "times", 1),
    interact_with_loom("interacted with a loom", "times", 1),
    interact_with_smithing_table("interacted with a smithing table", "times", 1),
    interact_with_smoker("interacted with a smoker", "times", 1),
    interact_with_stonecutter("interacted with a stonecutter", "times", 1),
    drop("dropped an item", "times", 1),
    enchant_item("enchanted an item", "times",1),
    jump("jumped","times",1),
    mob_kills("killed a mob", "times", 1),
    play_record("played a jukebox", "times",1),
    play_noteblock("played a noteblock", "times", 1),
    play_one_minute("played for", "seconds", 0),
    tune_noteblock("tune a noteblock", "times", 1),
    deaths("died", "times", 1),
    pot_flower("planted a flower in a flower pot", "times", 1),
    player_kills("killed a player", "times", 1),
    raid_trigger("started a raid", "times", 1),
    raid_win("won a raid", "times", 1),
    clean_shulker_box("reset a shulker box", "times", 1),
    open_shulker_box("opened a shulker box", "times",1),
    sneak_time("held down the sneak button", "seconds", 0),
    talked_to_villager("interacted with a villager", "times", 1),
    target_hit("hit a target", "times", 1),
    time_since_death("not died for", "seconds",0),
    time_since_rest("not slept for", "seconds", 0),
    sleep_in_bed("slept in a bed", "times", 1),
    traded_with_villager("traded with a villager", "times", 1),
    trigger_trapped_chest("opened a trapped chest", "times", 1),
    use_cauldron("filled a bottle from a cauldron", "times", 1);

    private final String title;
    private final String units;
    private final double divisor;

    Statistics(String title, String units, double divisor) {
        this.title = title;
        this.units = units;
        this.divisor = divisor;
    }

    public Component get(UUID uuid, CommandSender invoker) {
        Double finalStatistic;
        String theUnits;
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        long statistic = StatisticData.getPlayerStatistic(uuid, this.toString());
        if (divisor == 0) {
            Pair<Double, String> time = Rounding.timeRounding(statistic);
            finalStatistic = time.getKey();
            theUnits = time.getValue();
        } else {
            finalStatistic = Rounding.round(statistic/divisor, 2);
            theUnits = units;
        }
        if (invoker == player) {
            return Messages.STATISTIC_RETURN.get("You", "have", title, String.valueOf(finalStatistic), theUnits);
        } else {
            return Messages.STATISTIC_RETURN.get(
                    player.getName(), "has", title, String.valueOf(finalStatistic), theUnits);
        }
    }

}
// /statistic [player] animals_bred
//  [player] has [title] [amount/divisor] [units].