package me.char321.nexcavate.gui;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.gui.structurescreen.StructureScreenHandler;
import me.char321.nexcavate.items.structure.NEStructure;
import me.char321.nexcavate.items.structure.Structure;
import me.char321.nexcavate.research.Research;
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

    public static void openResearchScreen(Player p) {
        NEScreens neScreens = inventories.computeIfAbsent(p.getUniqueId(), NEScreens::new);
        ResearchScreenHandler handler = neScreens.researchScreen;
        handler.refresh();
        p.openInventory(handler.getInventory());
    }

    public static void openResearchScreen(Player p, int tier) {
        NEScreens neScreens = inventories.computeIfAbsent(p.getUniqueId(), NEScreens::new);
        ResearchScreenHandler handler = neScreens.researchScreen;
        handler.refresh(tier);
        p.openInventory(handler.getInventory());
    }

    public static void openStructure(Player p, Structure structure) {
        NEScreens neScreens = inventories.computeIfAbsent(p.getUniqueId(), NEScreens::new);
        StructureScreenHandler handler;
        switch (structure.size) {
            case 3:
                handler = neScreens.structureScreen3;
                break;
            case 4:
                handler = neScreens.structureScreen4;
                break;
            case 5:
                handler = neScreens.structureScreen5;
                break;
            case 6:
                handler = neScreens.structureScreen6;
                break;
            default:
                throw new IllegalArgumentException("Tried to open structure with invalid size!");
        }
        handler.setStructure(structure);
        p.openInventory(handler.getInventory());
    }

    public static void openRecipe(Player player, Research research) {
        if (research.getItem() instanceof NEStructure) {
            openStructure(player, ((NEStructure) research.getItem()).getStructure());
        }
    }
}
