package xyz.devcmb.ic.challenges;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public interface Challenge extends Listener {
    String getId();
    String getName();
    String getDescription();
    ItemStack getItem();

    void onRegister();
}
