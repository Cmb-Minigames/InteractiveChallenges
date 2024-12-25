package xyz.devcmb.InteractiveChallenges.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import xyz.devcmb.InteractiveChallenges.InteractiveChallenges;
import xyz.devcmb.InteractiveChallenges.commands.challenges.*;
import xyz.devcmb.InteractiveChallenges.commands.completions.UITabCompletion;
import xyz.devcmb.InteractiveChallenges.commands.debug.*;

import java.util.Objects;

public class CommandManager {
    public static void registerCommands() {
        registerSingleCommand("ui", new UICommand());
        registerSingleCommand("challenges", new ChallengesCommand());

        registerSingleTabCompletion("ui", new UITabCompletion());
    }

    /**
     * Registers a single command
     * @param command The command name
     * @param executor The command executor class
     */
    public static void registerSingleCommand(String command, CommandExecutor executor){
        InteractiveChallenges plugin = InteractiveChallenges.getPlugin();
        Objects.requireNonNull(plugin.getCommand(command)).setExecutor(executor);
    }

    /**
     * Registers a single tab completion
     * @param command The command name
     * @param completer The tab completer class
     */
    public static void registerSingleTabCompletion(String command, TabCompleter completer){
        InteractiveChallenges plugin = InteractiveChallenges.getPlugin();
        Objects.requireNonNull(plugin.getCommand(command)).setTabCompleter(completer);
    }
}
