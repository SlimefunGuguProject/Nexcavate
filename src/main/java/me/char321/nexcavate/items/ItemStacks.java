package me.char321.nexcavate.items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemStacks {
    public static final ItemStack RESEARCH_TABLE = new CustomItemStack(Material.CARTOGRAPHY_TABLE, "&eResearch Table");
    public static final ItemStack RESEARCH_LAB = new CustomItemStack(Material.FLETCHING_TABLE, "&eResearch Lab");

    public static final ItemStack REDISCOVERY_PICKAXE = new SlimefunItemStack("NE_REDISCOVERY_PICKAXE", Material.WOODEN_PICKAXE, "&eRediscovery Pickaxe", im -> {
        im.setLore(List.of(ChatColor.GRAY + "A basic tool for extracting ancient parts."));
        im.addEnchant(Enchantment.DURABILITY, 3, false);
    });

    public static final ItemStack ANCIENT_PART = new SlimefunItemStack("NE_ANCIENT_PART", Material.NETHER_BRICK, "&eAncient Part", "&7A relic of the ancient civilization.");
}
