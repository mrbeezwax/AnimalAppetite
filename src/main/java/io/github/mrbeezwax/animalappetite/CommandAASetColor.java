package io.github.mrbeezwax.animalappetite;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashSet;

public class CommandAASetColor implements CommandExecutor {
    private final AnimalAppetite plugin;
    private static final String[] GLOW_COLOR_VALUES = new String[] { "AQUA", "BLACK", "BLUE", "DARK_AQUA", "DARK_BLUE", "DARK_GRAY", "DARK_GREEN", "DARK_PURPLE", "DARK_RED",
            "GOLD", "GRAY", "GREEN", "LIGHT_PURPLE", "RED", "WHITE", "YELLOW" };
    private static final HashSet<String> GLOW_COLORS = new HashSet<>(Arrays.asList(GLOW_COLOR_VALUES));

    public CommandAASetColor(AnimalAppetite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("aasetcolor")) {
            String glowColor = args[0].toUpperCase();
            if (GLOW_COLORS.contains(glowColor)) {
                plugin.getConfig().set("glow-color", glowColor);
                sender.sendMessage("Glow Color set to: " + glowColor);
                sender.sendMessage("Please reload the server");
                plugin.saveConfig();
            } else {
                sender.sendMessage("Please type in a correct glow color. Available Colors: AQUA, BLACK, BLUE, DARK_AQUA, " +
                        "DARK_BLUE, DARK_GRAY, DARK_GREEN, DARK_PURPLE, DARK_RED, GOLD, GRAY, GREEN, LIGHT_PURPLE, RED, WHITE, YELLOW");
            }
        }
        return true;
    }
}
