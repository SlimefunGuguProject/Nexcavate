package me.char321.nexcavate.util;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import me.char321.nexcavate.Nexcavate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Utils {
    public static void runWithDelay(long period, Runnable... runnables) {
        long currentDelay = 0;
        for (Runnable runnable : runnables) {
            Bukkit.getScheduler().runTaskLater(Nexcavate.instance(), runnable, currentDelay);
            currentDelay += period;
        }
    }

    public static ItemStack getItem(String id) {
        if(id == null || id.equals("AIR") || id.equals("null")) {
            return null;
        }

        SlimefunItem item = SlimefunItem.getById(id);
        if(item != null) {
            return item.getItem();
        }

        Material material = Material.getMaterial(id);
        if(material != null) {
            return new ItemStack(material);
        }

        throw new IllegalArgumentException();
    }

}
