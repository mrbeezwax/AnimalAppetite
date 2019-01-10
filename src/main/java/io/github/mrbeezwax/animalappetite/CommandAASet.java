package io.github.mrbeezwax.animalappetite;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CommandAASet implements CommandExecutor {
    private final AnimalAppetite plugin;

    public CommandAASet(AnimalAppetite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("aaset") && args.length == 1) {
            try {
                int newReq = Integer.parseInt(args[0]);
                if (newReq < 1) {
                    sender.sendMessage("Please type in a positive number");
                } else {
                    plugin.getConfig().set("breeding-requirement", newReq);
                    sender.sendMessage("Number of Food required for breeding set to: " + newReq);
                    sender.sendMessage("Please reload the server");
                    plugin.saveConfig();
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("Incorrect syntax. Please type a number");
            }
        }
        return true;
    }
}
