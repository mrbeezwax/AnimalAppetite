package io.github.mrbeezwax.animalappetite;

import org.bukkit.Bukkit;
import org.bukkit.entity.Animals;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.scoreboard.Team;

import java.util.Timer;
import java.util.TimerTask;

public class BreedEventListener implements Listener {
    public BreedEventListener(AnimalAppetite plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event) {
        Team infertile = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("infertile");
        infertile.addEntry(event.getMother().getUniqueId().toString());
        infertile.addEntry(event.getFather().getUniqueId().toString());
        event.getMother().setGlowing(true);
        event.getFather().setGlowing(true);
        Animals dad = (Animals) event.getFather();
        Animals mom = (Animals) event.getMother();
        FeedEventListener.removeFedAnimal(dad);
        FeedEventListener.removeFedAnimal(mom);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                event.getFather().setGlowing(false);
                event.getMother().setGlowing(false);
                infertile.removeEntry(event.getMother().getUniqueId().toString());
                infertile.removeEntry(event.getFather().getUniqueId().toString());
            }
        }, 360000);
    }
}
