package io.github.mrbeezwax.animalappetite;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class FeedEventListener implements Listener {
    public FeedEventListener(AnimalAppetite plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityFeed(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        if (event.getRightClicked() instanceof Animals) {
            if (p.getInventory().getItemInMainHand().getType() == Material.CARROT) {
                event.setCancelled(true);
                p.updateInventory();
                Bukkit.broadcastMessage("Feed Event");
            }
        }
    }
}
