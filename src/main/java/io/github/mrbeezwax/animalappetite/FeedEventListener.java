package io.github.mrbeezwax.animalappetite;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.HashMap;

public class FeedEventListener implements Listener {
//    private HashMap<Entity, Integer> fedEntities = new HashMap<>();
//    private final int BREEDING_LIMIT = 3;

    public FeedEventListener(AnimalAppetite plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityFeed(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        Entity e = event.getRightClicked();
        Animals a = (Animals) e;

        if (a.canBreed()) Bukkit.broadcastMessage("Can Breed");
        else Bukkit.broadcastMessage("Cannot Breed");

//        if (event.getRightClicked() instanceof Animals) {
//            if (p.getInventory().getItemInMainHand().getType() == Material.CARROT) {
//                if (fedEntities.containsKey(e)) {
//                    fedEntities.put(e, fedEntities.get(e) + 1);
//                    if (fedEntities.get(event.getRightClicked()) == BREEDING_LIMIT) {
//                        fedEntities.remove(e);
//                        return;
//                    }
//                } else {
//                    fedEntities.put(e, 1);
//                }
//
//                event.setCancelled(true);
//                p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
//                p.updateInventory();
//            }
//        }
    }
}
