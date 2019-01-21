package io.github.mrbeezwax.animalappetite;

import org.bukkit.Bukkit;
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
        if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam("infertile").hasEntry(e.getUniqueId().toString())) {
            event.setCancelled(true);
            return;
        }
        Material hand = p.getInventory().getItemInMainHand().getType();
        if (BREEDING_REQUIREMENT == 1) return;
        if (event.getHand() == EquipmentSlot.HAND && e instanceof Animals) {
            Animals a = (Animals) e;
            if (!a.canBreed() || !DIET_MAP.containsKey(e.getType())) return;
            if (DIET_MAP.get(e.getType()).contains(hand)) {
                if (FED_ANIMALS_MAP.containsKey(a)) {
                    if (FED_ANIMALS_MAP.get(a) == BREEDING_REQUIREMENT) p.sendMessage("Animal is not ready to breed yet");
                    FED_ANIMALS_MAP.put(a, FED_ANIMALS_MAP.get(a) + 1);
                } else {
                    FED_ANIMALS_MAP.put(a, 1);
                }
                p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                if (FED_ANIMALS_MAP.get(a) == BREEDING_REQUIREMENT) return;
                event.setCancelled(true);
            }
        }
    }

    public static void removeFedAnimal(Animals a) {
        FED_ANIMALS_MAP.remove(a);
    }
}
