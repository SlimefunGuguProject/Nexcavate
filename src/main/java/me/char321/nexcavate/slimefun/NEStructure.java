package me.char321.nexcavate.slimefun;

import me.char321.nexcavate.structure.Structure;
import org.bukkit.inventory.ItemStack;

/**
 * slimefunitem requiring a 3d structure
 */
public class NEStructure extends NEItem {
    protected final Structure structure;

    public NEStructure(ItemStack item, String id, Structure structure) {
        super(item, id);
        this.structure = structure;
    }

    public Structure getStructure() {
        return structure;
    }
}
