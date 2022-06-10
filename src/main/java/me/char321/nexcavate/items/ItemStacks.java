package me.char321.nexcavate.items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemStacks {
    public static final ItemStack RESEARCH_TABLE = new CustomItemStack(Material.CARTOGRAPHY_TABLE, "&eResearch Table", "&7Allows you to research basic items.");
    public static final ItemStack RESEARCH_LAB = new CustomItemStack(Material.FLETCHING_TABLE, "&eResearch Lab", "&7Allows you to research advanced items.");

    public static final ItemStack REDISCOVERY_PICKAXE = new SlimefunItemStack("NE_REDISCOVERY_PICKAXE", Material.WOODEN_PICKAXE, "&eRediscovery Pickaxe", im -> {
        im.setLore(List.of(ChatColor.GRAY + "A basic tool for extracting ancient parts."));
        im.addEnchant(Enchantment.DURABILITY, 2, false);
        im.addEnchant(Enchantment.DIG_SPEED, 1, false);
    });

    public static final ItemStack BLACKSTONE_CAST = new SlimefunItemStack("NE_BLACKSTONE_CAST", Material.CAULDRON, "&eBlackstone Cast", "&7Automatically extracts lava and infinitely casts it", "&7into blackstone when given power. Must be placed", "&7above a lava source in the Nether.", "", LoreBuilder.powerPerSecond(24));

    public static final ItemStack ITEM_HOLDER = new SlimefunItemStack("NE_ITEM_HOLDER", Material.CYAN_STAINED_GLASS, "&fItem Holder", "&7Holds an item.", "&7Used for using items as ingredients in assemblers.");

    public static final ItemStack BASIC_ASSEMBLER = new CustomItemStack(Material.CHISELED_QUARTZ_BLOCK, "&eBasic Assembler", "&7Assembles small 3x3x3 items.");

    public static final ItemStack ANCIENT_PART = new SlimefunItemStack("NE_ANCIENT_PART", Material.NETHER_BRICK, "&eAncient Part", "&7A relic of the ancient civilization.");

    public static class Menu {
        public static final ItemStack BLACK = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ");
    }
}
