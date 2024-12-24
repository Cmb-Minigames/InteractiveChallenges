package xyz.devcmb.ic;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class InteractiveChallenges extends JavaPlugin {
    private static InteractiveChallenges plugin;
    public static Logger LOGGER;

    public static InteractiveChallenges getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        LOGGER = getLogger();
        saveDefaultConfig();

        LOGGER.info("InteractiveChallenges has awakened!");
    }

    @Override
    public void onDisable() {
        LOGGER.info("InteractiveChallenges has been murdered.");
    }
}
