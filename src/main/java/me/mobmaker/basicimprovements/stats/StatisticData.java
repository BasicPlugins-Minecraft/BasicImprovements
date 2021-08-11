package me.mobmaker.basicimprovements.stats;

import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.UUID;

public class StatisticData {

    public static long getPlayerStatistic(UUID player, String stat) {
        File worldFolder = new File(Bukkit.getServer().getWorlds().get(0).getWorldFolder(), "stats"); //obtain stats folder of World
        File playerStatistics = new File(worldFolder, player + ".json"); //obtain player's stats JSON
        if (playerStatistics.exists()) { //check if file exists
            JSONParser parser = new JSONParser(); //Create new parser object
            JSONObject jsonObject = null; //create new object, make it null to prevent errors in case something goes wrong

            try {
                jsonObject = (JSONObject) parser.parse(new FileReader(playerStatistics)); //Use the parser to read the player's stats JSON
            } catch (Exception e) {
                e.printStackTrace();
                return 0L;
            }

            JSONObject primary = null; //null the first Object, to start the trail.
            JSONObject secondary; //generate the second Object, to prevent issues.
            try {
                primary = (JSONObject) jsonObject.get("stats"); //use the first Object to get the stats
                secondary = (JSONObject) primary.get("minecraft:custom"); //use the second object to get the 'custom' data
                for (Object o : secondary.entrySet()) {
                    o.getClass(); //cache the classes in memory
                }
                String third = secondary.get("minecraft:" + stat).toString(); //get the requested statistic of the player
                return Long.parseLong(third); //return the requested statistic
            } catch (Exception e) {
                return 0L;
            }
        }
        return 0L;
    }
}
