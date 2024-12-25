package xyz.devcmb.InteractiveChallenges.commands.challenges;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import xyz.devcmb.InteractiveChallenges.util.Colors;

public class ChallengesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage(Component.text("Only players can use this command").color(Colors.RED));
            return true;
        }

        Inventory inventory = Bukkit.createInventory(player, 27, Component.text("Challenges"));
        player.openInventory(inventory);

        return true;
    }
}
