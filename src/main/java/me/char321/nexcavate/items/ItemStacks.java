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
    public static final ItemStack RESEARCH_TABLE = new CustomItemStack(Material.CARTOGRAPHY_TABLE, "&e研究台", "&7允许你研究基础物品");
    public static final ItemStack RESEARCH_LAB = new CustomItemStack(Material.FLETCHING_TABLE, "&e研究实验室", "&7允许你研究高级物品");

    public static final ItemStack REDISCOVERY_PICKAXE = new SlimefunItemStack("NE_REDISCOVERY_PICKAXE", Material.WOODEN_PICKAXE, "&e考古镐", im -> {
        im.setLore(List.of(ChatColor.GRAY + "提取古代零件的基础工具"));
        im.addEnchant(Enchantment.DURABILITY, 2, false);
        im.addEnchant(Enchantment.DIG_SPEED, 1, false);
    });
    public static final ItemStack PART_DRILL = new SlimefunItemStack("NE_PART_DRILL", Material.DIAMOND_PICKAXE, "&考古钻头", im -> {
        im.setLore(List.of(ChatColor.GRAY + "提取古代零件的高级工具"));
        im.addEnchant(Enchantment.DURABILITY, 3, false);
        im.addEnchant(Enchantment.DIG_SPEED, 5, false);
        im.addEnchant(Enchantment.MENDING, 1, false);
    });

    public static final ItemStack BLACKSTONE_CAST = new SlimefunItemStack("NE_BLACKSTONE_CAST", Material.CAULDRON, "&e黑石提取机", "&7自动提取岩浆", "&7通电时，可无限将其施放至黑石中", "&7必须放在下界的岩浆源上", "", LoreBuilder.powerPerSecond(24));
    public static final ItemStack PART_EXTRACTOR = new SlimefunItemStack("NE_PART_EXTRACTOR", Material.RED_NETHER_BRICKS, "&e零件提取机", "&7缓慢地从黑石中提取古代零件", "", "&7必须放置在下界中", "", LoreBuilder.powerPerSecond(12));
    public static final ItemStack PART_EXTRACTOR_2 = new SlimefunItemStack("NE_PART_EXTRACTOR_2", Material.CHISELED_NETHER_BRICKS, "&d高级零件提取机", "&7更快地从黑石中提取古代零件", "", "&7必须放置在下界中", "", LoreBuilder.powerPerSecond(24));

    public static final ItemStack ITEM_HOLDER = new SlimefunItemStack("NE_ITEM_HOLDER", Material.CYAN_STAINED_GLASS, "&f物品架", "&7搁置一个物品", "&7用于将物品用作装配机中的物品");

    public static final ItemStack BASIC_ASSEMBLER = new CustomItemStack(Material.CHISELED_QUARTZ_BLOCK, "&e基础装配机", "&7装配小范围3x3x3内的物品");
    public static final ItemStack ADVANCED_ASSEMBLER = new CustomItemStack(Material.QUARTZ_PILLAR, "&d高级装配机", "&7装配复杂的4x4x4内的物品", "仅在下界工作");

    public static final ItemStack ANCIENT_PART = new SlimefunItemStack("NE_ANCIENT_PART", Material.NETHER_BRICK, "&e古代零件", "&7古代文明的遗迹");
    public static final ItemStack UPGRADE_CORE = new SlimefunItemStack("NE_UPGRADE_CORE", Material.TARGET, "&e古代升级核心", "&7可用于升级机器");

    public static final ItemStack CIVILIZATION_CORE = new SlimefunItemStack("NE_CIVILIZATION_CORE", Material.CONDUIT, "&b文明核心", "&d拥有远古下界文明的元素", "&d用于扭转 &kasdfg hijklmn &d的局面");

    public static class Menu {
        public static final ItemStack BLACK = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ");
    }
}
