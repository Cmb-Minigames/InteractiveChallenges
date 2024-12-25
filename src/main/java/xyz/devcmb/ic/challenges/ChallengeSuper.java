package xyz.devcmb.ic.challenges;

import org.bukkit.inventory.ItemStack;
import xyz.devcmb.ic.InteractiveChallenges;

public abstract class ChallengeSuper {
    private final String id;
    private final String name;
    private final String description;
    private final ItemStack item;
    protected int progress;
    protected boolean active;

    public ChallengeSuper(String id, String name, String description, ItemStack item) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.item = item;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemStack getItem(){
        return item;
    }

    public void activate(){
        if(isNotRegistered()){
            throw new ChallengeNotRegisteredException("Challenge " + id + " is not registered, so it could not be activated! You might not have registered it through InteractiveChallenges.challenges.ChallengeRegistry#registerChallenge()");
        }

        active = true;
    }

    public void deactivate(){
        if(isNotRegistered()){
            throw new ChallengeNotRegisteredException("Challenge " + id + " is not registered, so it could not be deactivated! You might not have registered it through InteractiveChallenges.challenges.ChallengeRegistry#registerChallenge()");
        }

        active = false;
    }

    protected boolean isNotRegistered(){
        return !ChallengeRegistry.isRegistered(id);
    }

    protected void increaseProgress(Integer amount){
        if(isNotRegistered()){
            throw new ChallengeNotRegisteredException("Challenge " + id + " is not registered, so its progress could not be increased! You might not have registered it through InteractiveChallenges.challenges.ChallengeRegistry#registerChallenge()");
        }

        if(InteractiveChallenges.Debug){
            InteractiveChallenges.LOGGER.info("Challenge " + id + " has increased its progress by " + amount + ".");
        }

        progress += amount;
    }
}
