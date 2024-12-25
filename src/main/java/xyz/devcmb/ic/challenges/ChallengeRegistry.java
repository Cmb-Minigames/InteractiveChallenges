package xyz.devcmb.ic.challenges;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.devcmb.ic.InteractiveChallenges;
import xyz.devcmb.ic.util.Database;

import java.util.ArrayList;
import java.util.List;

public class ChallengeRegistry {
    public static List<Challenge> challenges = new ArrayList<>();
    public static void registerChallenges(){
        registerChallenge(InteractiveChallenges.getPlugin(), new BlockBreakChallenge());
    }

    public static void registerChallenge(JavaPlugin plugin, Challenge challenge){
        challenge.onRegister();
        challenges.add(challenge);
        Bukkit.getPluginManager().registerEvents(challenge, plugin);
        Database.insertChallenge(challenge);

        InteractiveChallenges.LOGGER.info(challenge.getName() + " has been registered successfully.");
    }

    public static boolean isRegistered(String id){
        for(Challenge challenge : challenges){
            if(challenge.getId().equals(id)){
                return true;
            }
        }

        return false;
    }
}
