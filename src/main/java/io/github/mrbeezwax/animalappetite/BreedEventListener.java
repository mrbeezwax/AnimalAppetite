package io.github.mrbeezwax.animalappetite;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class BreedEventListener implements Listener {
    public BreedEventListener(AnimalAppetite plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event) {
        System.out.println("Breed Event");
        Bukkit.broadcastMessage("Breed Event");
        Bukkit.broadcastMessage("Bred with: " + event.getBredWith().toString());
        Bukkit.broadcastMessage("Bred by: " + event.getBreeder().getName());
        Bukkit.broadcastMessage("Bred to: " + event.getEntity().getName());

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team infertile = scoreboard.registerNewTeam("infertile");
        infertile.setColor(ChatColor.DARK_RED);
        infertile.addEntry(event.getMother().getUniqueId().toString());
        event.getMother().setGlowing(true);
//        event.getFather().setGlowing(true);
    }
}
