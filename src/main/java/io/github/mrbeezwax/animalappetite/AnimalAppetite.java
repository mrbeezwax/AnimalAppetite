package io.github.mrbeezwax.animalappetite;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

public final class AnimalAppetite extends JavaPlugin {
    public Scoreboard scoreboard = getServer().getScoreboardManager().getMainScoreboard();

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin Enabled");
        new BreedEventListener(this);

        // Scoreboard Set-Up
        scoreboard.registerNewTeam("infertile");
        scoreboard.getTeam("infertile").setColor(ChatColor.DARK_RED);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Plugin Disabled");
        this.getServer().getScoreboardManager().getMainScoreboard().getTeam("infertile").unregister();
    }
}
