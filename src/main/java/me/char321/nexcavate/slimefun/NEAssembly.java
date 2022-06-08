package me.char321.nexcavate.slimefun;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.structure.Structure;
import org.bukkit.inventory.ItemStack;

public class NEAssembly extends NEItem {
    protected final Structure structure;

    public NEAssembly(ItemStack item, String id, Structure structure) {
        super(item, id);
        this.structure = structure;
    }

    public Structure getStructure() {
        return structure;
    }

    @Override
    public void postRegister() {
        Nexcavate.instance().getRegistry().getAssemblies().put(structure, this);
    }
}
