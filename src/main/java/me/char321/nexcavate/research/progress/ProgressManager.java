package me.char321.nexcavate.research.progress;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProgressManager {
    private final Map<UUID, PlayerProgress> progressMap = new HashMap<>();

    public PlayerProgress get(Player p) {
        return get(p.getUniqueId());
    }

    public PlayerProgress get(UUID p) {
        return progressMap.computeIfAbsent(p, PlayerProgress::new);
    }

    public void save() throws IOException {
        for (PlayerProgress playerProgress : progressMap.values()) {
            playerProgress.save();
        }
    }
}
