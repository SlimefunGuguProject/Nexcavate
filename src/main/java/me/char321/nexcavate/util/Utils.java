package me.char321.nexcavate.util;

import me.char321.nexcavate.Nexcavate;
import org.bukkit.Bukkit;

public class Utils {
    public static void runWithDelay(long period, Runnable... runnables) {
        long currentDelay = 0;
        for (Runnable runnable : runnables) {
            Bukkit.getScheduler().runTaskLater(Nexcavate.instance(), runnable, currentDelay);
            currentDelay += period;
        }
    }
}
