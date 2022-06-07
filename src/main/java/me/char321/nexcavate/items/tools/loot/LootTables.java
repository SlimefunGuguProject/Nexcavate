package me.char321.nexcavate.items.tools.loot;

import me.char321.nexcavate.items.ItemStacks;
import org.bukkit.Material;

import java.util.EnumMap;

public class LootTables {
    public static final ProbabilityLootTable REDISCOVERY_PICKAXE;
    static {
        EnumMap<Material, Float> chances = new EnumMap<>(Material.class);
        chances.put(Material.NETHERRACK, 0.05f);
        chances.put(Material.WARPED_HYPHAE, 0.05f);
        chances.put(Material.CRIMSON_HYPHAE, 0.05f);
        chances.put(Material.BASALT, 0.05f);
        chances.put(Material.BLACKSTONE, 0.15f);
        chances.put(Material.BLACKSTONE_STAIRS, 0.3f);
        chances.put(Material.BLACKSTONE_SLAB, 0.3f);
        chances.put(Material.GILDED_BLACKSTONE, 0.3f);
        chances.put(Material.POLISHED_BLACKSTONE_BRICKS, 0.4f);
        chances.put(Material.POLISHED_BASALT, 0.4f);
        chances.put(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0.4f);
        chances.put(Material.POLISHED_BLACKSTONE_BRICK_STAIRS, 0.4f);
        chances.put(Material.CHISELED_POLISHED_BLACKSTONE, 0.5f);
        chances.put(Material.NETHER_BRICKS, 0.1f);
        chances.put(Material.NETHER_BRICK_FENCE, 0.1f);
        REDISCOVERY_PICKAXE = new ProbabilityLootTable(ItemStacks.ANCIENT_PART, chances);
    }
}
