package me.char321.nexcavate.slimefun;

import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import me.char321.nexcavate.Nexcavate;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NexcavateItemGroup extends FlexItemGroup {
    public static void init() {
        //add research item
        new NexcavateItemGroup().register(Nexcavate.instance());
    }

    protected NexcavateItemGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @Override
    public boolean isVisible(Player p, PlayerProfile profile, SlimefunGuideMode layout) {
        return true;
    }

    @Override
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode layout) {
        System.out.println("yo im feinberg");

    }
}
