package io.github.mrbeezwax.animalappetite;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.util.*;

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
        getCommand("aaset").setExecutor(new CommandAASet(this));
        getCommand("aasetcolor").setExecutor(new CommandAASetColor(this));
        getCommand("aasetcolor").setTabCompleter(new AASetColorTabCompleter());

        // Plugin startup logic
        System.out.println("Plugin Enabled");
        ChatColor GLOW_COLOR = ChatColor.DARK_RED;
        try {
            GLOW_COLOR = ChatColor.valueOf(getConfig().getString("glow-color"));
            getLogger().info("Glow Color Found. Setting to " + GLOW_COLOR.name());
        } catch (IllegalArgumentException e) {
            getLogger().info("Color not found. Falling back to default color DARK_RED");
        }
        new BreedEventListener(this);
        new FeedEventListener(this);

        // Scoreboard Set-Up
        Scoreboard scoreboard = this.getServer().getScoreboardManager().getMainScoreboard();
        if (scoreboard.getTeam("infertile") == null) scoreboard.registerNewTeam("infertile");
        scoreboard.getTeam("infertile").setColor(GLOW_COLOR);

        // Diet Map Initialization
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
    }
}
