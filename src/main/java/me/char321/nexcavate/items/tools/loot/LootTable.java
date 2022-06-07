package me.char321.nexcavate.items.tools.loot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface LootTable {
    ItemStack poll(Material type);
}
