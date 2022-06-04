package me.char321.nexcavate.gui;

import me.char321.nexcavate.Nexcavate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NEGUI {
    private static final Map<UUID, NEScreens> inventories = new HashMap<>();

    public static void init() {
        Bukkit.getPluginManager().registerEvents(new NEGUIListener(), Nexcavate.instance());
    }

    public static void openResearchScreen(Player p, int tier) {
        NEScreens neScreens = inventories.computeIfAbsent(p.getUniqueId(), NEScreens::new);
        ResearchScreenHandler handler = neScreens.researchScreen;
        handler.refresh(tier);
        p.openInventory(handler.getInventory());
    }
}
