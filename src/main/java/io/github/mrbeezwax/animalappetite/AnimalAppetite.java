package io.github.mrbeezwax.animalappetite;

import org.bukkit.plugin.java.JavaPlugin;

public final class AnimalAppetite extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin Enabled");
        new BreedEventListener(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
