package io.github.mrbeezwax.animalappetite;

import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;

import static io.github.mrbeezwax.animalappetite.AnimalAppetite.DIET_MAP;

public class FeedEventListener implements Listener {
    private static final HashMap<Animals, Integer> FED_ANIMALS_MAP = new HashMap<>();
    private final int BREEDING_REQUIREMENT;

    public FeedEventListener(AnimalAppetite plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        BREEDING_REQUIREMENT = plugin.getConfig().getInt("breeding-requirement");
    }

    @EventHandler
    public void onEntityFeed(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        Entity e = event.getRightClicked();
        Material hand = p.getInventory().getItemInMainHand().getType();
        if (event.getHand() == EquipmentSlot.HAND && e instanceof Animals) {
            Animals a = (Animals) e;
            if (!a.canBreed() || !DIET_MAP.containsKey(e.getType())) return;
            if (DIET_MAP.get(e.getType()).contains(hand)) {
                if (FED_ANIMALS_MAP.containsKey(a)) {
                    if (FED_ANIMALS_MAP.get(a) != BREEDING_REQUIREMENT - 1) FED_ANIMALS_MAP.put(a, FED_ANIMALS_MAP.get(a) + 1);
                    else return;
                } else FED_ANIMALS_MAP.put(a, 1);
                event.setCancelled(true);
                p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                p.updateInventory();
            }
        }
    }

    public static void removeFedAnimal(Animals a) {
        FED_ANIMALS_MAP.remove(a);
    }
}
