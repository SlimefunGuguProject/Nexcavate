package me.char321.nexcavate.items.structure;

import me.char321.nexcavate.items.NEItem;
import org.bukkit.inventory.ItemStack;

/**
 * slimefunitem requiring a 3d structure
 */
public class NEStructure extends NEItem {
    private final Structure structure;

    public NEStructure(ItemStack item, String id, Structure structure) {
        super(item, id);
        this.structure = structure;
    }

    public Structure getStructure() {
        return structure;
    }
}
