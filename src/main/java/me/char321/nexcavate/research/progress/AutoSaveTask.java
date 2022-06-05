package me.char321.nexcavate.research.progress;

import me.char321.nexcavate.Nexcavate;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.logging.Level;

public class AutoSaveTask {
    public static void init() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(Nexcavate.instance(), () -> Nexcavate.instance().save(), 6000, 6000);
    }
}
