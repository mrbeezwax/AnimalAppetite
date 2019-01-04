package io.github.mrbeezwax.animalappetite;

import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.HashMap;

public class FeedEventListener implements Listener {
    private HashMap<Animals, Integer> fedAnimals = new HashMap<>();
    private final int BREEDING_LIMIT = 3;

    public FeedEventListener(AnimalAppetite plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityFeed(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        Entity e = event.getRightClicked();
        if (e instanceof Animals) {
            Animals a = (Animals) e;
            if (!a.canBreed()) return;
            if (p.getInventory().getItemInMainHand().getType() == Material.CARROT) {
                if (fedAnimals.containsKey(e)) {
                    fedAnimals.put(a, fedAnimals.get(a) + 1);
                    if (fedAnimals.get(a) == BREEDING_LIMIT) {
                        fedAnimals.remove(a);
                        return;
                    }
                } else {
                    fedAnimals.put(a, 1);
                }
                event.setCancelled(true);
                p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                p.updateInventory();
            }
        }
    }
}
