package io.github.mrbeezwax.animalappetite;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AASetColorTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(command.getName().equalsIgnoreCase("aasetcolor") && sender instanceof Player) {
            return new ArrayList<>(Arrays.asList(CommandAASetColor.GLOW_COLOR_VALUES));
        }
        return null;
    }
}
