package me.mobmaker.basicimprovements.utilities;

import me.mobmaker.basicimprovements.BasicImprovements;
import me.mobmaker.basicimprovements.stats.Statistics;

import java.io.File;
import java.util.ArrayList;

public class Data {

    private static File helpFile;
    public static ArrayList<String> stats = new ArrayList<>();

    public static void loadSources(BasicImprovements pl) {
        pl.saveResource("help.yml", false);
        getStatisticStrings();
        getHelpFile(pl);
    }

    public static boolean reloadSources() {
        //TODO: load config.yml
        return true;
    }

    public static File helpYml() {
        return helpFile;
    }

     private static void getHelpFile(BasicImprovements pl) {
        helpFile = new File(pl.getDataFolder(), "help.yml");
        try {
            if (!helpFile.exists()) {
                helpFile.getParentFile().mkdirs();
                helpFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getStatisticStrings() {
        stats.clear();
        for (Statistics value : Statistics.values()) {
            stats.add(value.name());
        }
    }

}
