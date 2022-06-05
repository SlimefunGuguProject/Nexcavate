package me.char321.nexcavate.research.progress;

import me.char321.nexcavate.Nexcavate;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.logging.Level;

public class ResearchUpdateTask {
    public static void init() {
        Bukkit.getScheduler().runTaskTimer(Nexcavate.instance(), () -> {
            for (PlayerProgress progress : Nexcavate.instance().getProgressManager().getProgressMap().values()) {
                progress.tryCompleteCurrentResearch();
            }
        }, 100, 100);
    }
}
