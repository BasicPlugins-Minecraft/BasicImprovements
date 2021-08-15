package me.mobmaker.basicimprovements.utilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.Template;
import net.kyori.adventure.text.minimessage.markdown.DiscordFlavor;
import net.kyori.adventure.text.minimessage.transformation.TransformationType;

import java.util.ArrayList;
import java.util.List;

public enum Messages {
    WARP_START("warp.start", "<dark_purple>Warp started<gray>. Stay still for <dark_purple>five <gray>seconds, and do not take damage."),
    WARP_SUCCESS("warp.success", "You have been warped to <green><0><gray>."),
    WARP_NOTFOUND("warp.notfound", "<red>**HEY!** <gray>That warp doesn't exist. Make sure you spelled it right, or that it exists!"),
    WARP_UNNAMED("warp.set.unnamed", "<red>**HEY!** <gray>Make sure to name the warp!"),
    WARP_WRONGWORLD("warp.wrongworld", "<red>**HEY!** <gray>You need to be in the<green> <0><gray> to warp here."),
    WARP_SET_SUCCESS("warp.set.success", "<gray>You have set the location of <green><0><gray> to your current location."),
    WARP_DEL_SUCCESS("warp.del.success", "<gray>You have removed the warp<green> <0><gray>."),
    WARP_CANCEL_MOVE("warp.cancel.move", "<red>**HEY!** <gray>You moved! Warp cancelled."),
    WARP_CANCEL_DAMAGED("warp.cancel.damaged", "<red>**HEY!** <gray>You took damage! Warp cancelled."),
    WARP_CANCEL_DAMAGER("warp.cancel.damager", "<red>**HEY!** <gray>You damaged something! Warp cancelled."),
    BACK_SUCCESS("back.success", "<gray>You have been warped to<green> your previous location<gray>."),
    BACK_NOTFOUND("back.notfound", "<red>**HEY!** <gray> There's nowhere for you to return to!"),
    RTP_SUCCESS("rtp.success", "<gray>You are now at a new<green> random location<gray>."),
    RTP_COOLDOWN("rtp.cooldown", "<red>You can't run this command yet! You still have<gold> <0><red> minutes remaining!"),
    HOME_SUCCESS("home.success", "<gray>You have been warped to<green> your home<gray>."),
    HOME_NOTFOUND("home.notfound", "<red>**HEY!** <gray>Make sure to set your home first!"),
    HOME_SET_SUCCESS("home.set.success", "<gray>You have set the location of<green> your home<gray>."),
    STATISTIC_RETURN("statistic.return","<green><0> <gray><1> <2> <green><3> <gray><4>."),
    STATISTIC_NOTFOUND("statistic.notfound", "<red>**HEY!** <gray>This player does not have this statistic!"),
    GAMEMODE_INVALID("gamemode.invalid", "<red>**HEY!** <gray>That gamemode does not exist."),
    MESSAGE_PLAYER("message.player","<gold>*<0> <gray>whispers to you:*<white>"),
    PLUGIN_JOIN("pl.join","<gray>[<green>+<gray>] <dark_gray><0>"),
    PLUGIN_FIRSTJOIN("pl.firstjoin","<yellow>**WELCOME** <gray><0><yellow> to the server!"),
    PLUGIN_RELOAD("pl.reload", "<green>BasicImprovements <gray>has been reloaded!"),
    PLUGIN_PING("pl.ping", "<green><0> ping is <green><1> <gray>milliseconds."),
    PLUGIN_NOTPLAYER("pl.notplayer","<red>**HEY!** <gray>You are not a player!"),
    PLUGIN_INVALID_PLAYER("pl.badplayer", "<red>**HEY!** <gray>That player doesn't exist!"),
    PLUGIN_PREFIX("pl.prefix", "<dark_gray>[<gold>**Broadcast**<dark_gray>]<white>")
    ;
    private final String key;
    private final String defaultValue;
    //TODO: Configurable messages
    Messages(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public Component get() {
        return mm.parse(defaultValue);
        /*if (messages == null) color(defaultValue);
        String message = messages.getString(key);
        if (message == null || message.isEmpty())
        return color(message);*/
    }

    public Component get(Component... args) {
        List<Template> translate = new ArrayList<>();
        int argNum = 0;
        for (Component arg : args) {
            translate.add(Template.of(String.valueOf(argNum), arg));
            argNum++;
        }
        return mm.parse(defaultValue, translate);
    }

    public Component get(String... args) {
        List<Template> translate = new ArrayList<>();
        int argNum = 0;
        for (String arg : args) {
            translate.add(Template.of(String.valueOf(argNum), arg));
            argNum++;
        }
        return mm.parse(defaultValue, translate);
    }

    //private YamlConfiguration messages = null;

    /*protected void reloadLanguage() {
        File messagesFile = new File(BasicEnchants.getDataFolder(), "messages.yml");
        boolean isNew = !messagesFile.exists();
        boolean isTampered = false;

        if (isNew) {
            try {
                if (messagesFile.createNewFile()) {
                    YamlConfiguration configuration = YamlConfiguration.loadConfiguration(messagesFile);
                    for (Messages message : Messages.values()) {
                        configuration.set(message.key, message.defaultValue);
                    }
                    configuration.save(messagesFile);
                } else {
                    System.out.println("Unable to create messages, defaulting to preset.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Unable to create messages (see above error message), defaulting to preset.");
                return;
            }
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(messagesFile);
        for (Messages value : Messages.values()) {
            String string = config.getString(value.key);
            if (string == null) {
                isTampered = true;
                config.set(value.key, value.defaultValue);
            }
        }

        if (isTampered) {
            try {
                config.save(messagesFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }*/

    public static MiniMessage mm = MiniMessage.builder()
            .transformation(TransformationType.COLOR)
            .transformation(TransformationType.DECORATION)
            .markdownFlavor(DiscordFlavor.get())
            .markdown()
            .build();
}
