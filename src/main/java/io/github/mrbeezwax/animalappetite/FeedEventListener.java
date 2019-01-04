package io.github.mrbeezwax.animalappetite;

import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.HashMap;

import static io.github.mrbeezwax.animalappetite.AnimalAppetite.DIET_MAP;

public class FeedEventListener implements Listener {
    private HashMap<Animals, Integer> fedAnimals = new HashMap<>();
    private final int BREEDING_REQUIREMENT = 3;

    public FeedEventListener(AnimalAppetite plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityFeed(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        Entity e = event.getRightClicked();
        Material hand = p.getInventory().getItemInMainHand().getType();

        if (e instanceof Animals) {
            Animals a = (Animals) e;
            if (!a.canBreed() || !DIET_MAP.containsKey(e.getType())) return;
            if (DIET_MAP.get(e.getType()).contains(hand)) {
                if (fedAnimals.containsKey(e)) {
                    fedAnimals.put(a, fedAnimals.get(a) + 1);
                    if (fedAnimals.get(a) == BREEDING_REQUIREMENT) {
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
