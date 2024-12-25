package xyz.devcmb.InteractiveChallenges;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.devcmb.InteractiveChallenges.challenges.ChallengeRegistry;
import xyz.devcmb.InteractiveChallenges.util.Database;

import java.util.logging.Logger;

public final class InteractiveChallenges extends JavaPlugin {
    private static InteractiveChallenges plugin;
    public static Logger LOGGER;
    public static boolean Debug = true;

    public static InteractiveChallenges getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        LOGGER = getLogger();
        saveDefaultConfig();

        Database.connect();
        ChallengeRegistry.registerChallenges();

        LOGGER.info("InteractiveChallenges has awakened!");
    }

    @Override
    public void onDisable() {
        Database.disconnect();
        LOGGER.info("InteractiveChallenges has been murdered.");
    }
}
