package xyz.devcmb.InteractiveChallenges.challenges;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakChallenge extends ChallengeSuper implements Challenge {
    public BlockBreakChallenge() {
        super(
                "blockbreaking",
                "Block Breaking",
                "Break the set amount of blocks to complete the challenge!",
                new ItemStack(Material.GRASS_BLOCK)
        );
    }

    @Override
    public void onRegister() {}

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(active) {
            increaseProgress(1);
        }
    }
}
