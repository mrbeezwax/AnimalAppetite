package io.github.mrbeezwax.animalappetite;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CommandSet implements CommandExecutor {
    private final AnimalAppetite plugin;

    public CommandSet(AnimalAppetite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("aaset")) {
            try {
                plugin.getConfig().set("breeding-requirement", Integer.parseInt(args[0]));
                plugin.saveConfig();
            } catch (NumberFormatException e) {
                sender.sendMessage("Incorrect syntax. Please type a number");
            }
        }
        return true;
    }
}
