package me.char321.nexcavate.items.tools.loot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;

public class ProbabilityLootTable implements LootTable {
    private final ItemStack output;
    private final Map<Material, Float> chances;

    public ProbabilityLootTable(ItemStack output, EnumMap<Material, Float> chances) {
        this.output = output;
        this.chances = chances;
    }


    public Map<Material, Float> getChances() {
        return chances;
    }

    @Override
    public ItemStack poll(Material type) {
        Float chance = chances.get(type);
        if (chance != null && Math.random() < chance) {
            return output;
        }
        return null;
    }
}
