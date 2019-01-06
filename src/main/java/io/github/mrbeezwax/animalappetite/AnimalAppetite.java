package io.github.mrbeezwax.animalappetite;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public final class AnimalAppetite extends JavaPlugin {
    public static final HashMap<EntityType, ArrayList<Material>> DIET_MAP = new HashMap<>();

    @Override
    public void onEnable() {
        // Config Set-Up
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
            getLogger().info("Config file created");
        } else {
            getLogger().info("Config file found, loading...");
        }

        // Initializing Commands
        getCommand("aaset").setExecutor(new CommandSet(this));

        // Plugin startup logic
        System.out.println("Plugin Enabled");
        new BreedEventListener(this);
        new FeedEventListener(this);

        // Scoreboard Set-Up
        Scoreboard scoreboard = this.getServer().getScoreboardManager().getMainScoreboard();
        scoreboard.registerNewTeam("infertile");
        scoreboard.getTeam("infertile").setColor(ChatColor.DARK_RED);

        // HashMap Initialization
        DIET_MAP.put(EntityType.HORSE,
                new ArrayList<>(Arrays.asList(Material.GOLDEN_APPLE, Material.GOLDEN_CARROT)));
        DIET_MAP.put(EntityType.CHICKEN,
                new ArrayList<>(Arrays.asList(Material.BEETROOT_SEEDS, Material.MELON_SEEDS, Material.PUMPKIN_SEEDS, Material.WHEAT_SEEDS)));
        DIET_MAP.put(EntityType.COW,
                new ArrayList<>(Collections.singletonList(Material.WHEAT)));
        DIET_MAP.put(EntityType.MUSHROOM_COW,
                new ArrayList<>(Collections.singletonList(Material.WHEAT)));
        DIET_MAP.put(EntityType.OCELOT,
                new ArrayList<>(Arrays.asList(Material.PUFFERFISH, Material.SALMON, Material.COD, Material.TROPICAL_FISH)));
        DIET_MAP.put(EntityType.PIG,
                new ArrayList<>(Arrays.asList(Material.CARROT, Material.POTATO, Material.BEETROOT)));
        DIET_MAP.put(EntityType.RABBIT,
                new ArrayList<>(Arrays.asList(Material.DANDELION, Material.CARROT, Material.GOLDEN_CARROT)));
        DIET_MAP.put(EntityType.SHEEP,
                new ArrayList<>(Collections.singletonList(Material.WHEAT)));
        DIET_MAP.put(EntityType.WOLF,
                new ArrayList<>(Arrays.asList(Material.COOKED_BEEF, Material.COOKED_CHICKEN, Material.COOKED_MUTTON,
                        Material.COOKED_PORKCHOP, Material.COOKED_RABBIT, Material.BEEF, Material.CHICKEN,
                        Material.MUTTON, Material.PORKCHOP, Material.ROTTEN_FLESH, Material.RABBIT)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Plugin Disabled");
        this.getServer().getScoreboardManager().getMainScoreboard().getTeam("infertile").unregister();
    }
}
