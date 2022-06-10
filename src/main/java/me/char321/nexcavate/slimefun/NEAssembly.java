package me.char321.nexcavate.slimefun;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.structure.Structure;
import org.bukkit.inventory.ItemStack;

public class NEAssembly extends NEItem {
    protected final Structure assembly;

    public NEAssembly(ItemStack item, String id, Structure assembly) {
        super(item, id);
        this.assembly = assembly;
    }

    public Structure getStructure() {
        return assembly;
    }

    @Override
    public void postRegister() {
        Nexcavate.instance().getRegistry().getAssemblies().put(assembly, this);
    }
}
