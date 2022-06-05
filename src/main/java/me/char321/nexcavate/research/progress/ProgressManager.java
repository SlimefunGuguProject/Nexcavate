package me.char321.nexcavate.research.progress;

import me.char321.nexcavate.Nexcavate;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProgressManager {
    private final Map<UUID, PlayerProgress> progressMap = new HashMap<>();

    public Map<UUID, PlayerProgress> getProgressMap() {
        return progressMap;
    }

    public PlayerProgress get(Player p) {
        return get(p.getUniqueId());
    }

    public PlayerProgress get(UUID player) {
        return progressMap.computeIfAbsent(player, this::retrieve);
    }

    public PlayerProgress retrieve(UUID player) {
        File progressFolder = new File(Nexcavate.instance().getDataFolder(), "/progress");
        File f = new File(progressFolder, player +".json");
        if (!f.exists()) {
            return new PlayerProgress(player);
        }
        return PlayerProgress.load(f, player);
    }

    public void save() throws IOException {
        for (PlayerProgress playerProgress : progressMap.values()) {
            playerProgress.save();
        }
    }
}
