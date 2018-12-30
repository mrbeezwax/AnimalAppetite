package io.github.mrbeezwax.animalappetite;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class BreedEventListener implements Listener {
    public BreedEventListener(AnimalAppetite plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event) {
        System.out.println("Breed Event");
        event.getBreeder().sendMessage("Breed Event");
    }
}
