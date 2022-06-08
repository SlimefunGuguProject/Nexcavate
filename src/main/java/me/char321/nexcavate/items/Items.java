package me.char321.nexcavate.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.items.assemblers.Assembler;
import me.char321.nexcavate.items.stations.ResearchStation;
import me.char321.nexcavate.slimefun.NEAssembly;
import me.char321.nexcavate.slimefun.NEItem;
import me.char321.nexcavate.structure.Structure;
import me.char321.nexcavate.structure.piece.AnyStructurePiece;
import me.char321.nexcavate.structure.piece.StructurePiece;
import me.char321.nexcavate.items.tools.RediscoveryPickaxe;
import me.char321.nexcavate.items.tools.loot.LootTables;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import static me.char321.nexcavate.structure.piece.StructurePiece.mat;
import static me.char321.nexcavate.structure.piece.StructurePiece.u;
import static org.bukkit.Material.*;

public class Items {
    public static final ItemGroup DUMMY_GROUP = new ItemGroup(Nexcavate.key("dummy"), new ItemStack(Material.BARRIER)) {
        @Override
        public boolean isHidden(@Nonnull Player p) {
            return true;
        }
    };
    public static final RecipeType DUMMY_TYPE = new RecipeType(Nexcavate.key("invalid"), new CustomItemStack(
            Material.BARRIER,
            "&l&4You're viewing this recipe wrong!",
            "&7View the recipe through a Nexcavate research station."));

    public static ResearchStation RESEARCH_LAB;
    public static ResearchStation RESEARCH_TABLE;

    public static RediscoveryPickaxe REDISCOVERY_PICKAXE;

    public static Assembler BASIC_ASSEMBLER;

    public static NEAssembly ITEM_HOLDER;

    public static NEItem ANCIENT_PART;

    public static void init() {
        StructurePiece lantern = u(new CustomItemStack(LANTERN, "Lantern/Soul Lantern"), mat(LANTERN), mat(SOUL_LANTERN));
        RESEARCH_TABLE = new ResearchStation(ItemStacks.RESEARCH_TABLE, "NE_RESEARCH_TABLE", new Structure(new StructurePiece[][][]{
                {
                        {mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ)},
                        {mat(SMOOTH_QUARTZ), mat(NETHERITE_BLOCK), mat(SMOOTH_QUARTZ)},
                        {mat(SMOOTH_QUARTZ_STAIRS), mat(SMOOTH_QUARTZ_STAIRS), mat(SMOOTH_QUARTZ_STAIRS)}
                }, {
                        {mat(BOOKSHELF), mat(CARTOGRAPHY_TABLE), mat(BOOKSHELF)},
                        {mat(END_ROD), mat(AIR), mat(END_ROD)},
                        {mat(AIR), mat(AIR), mat(AIR)}
                }, {
                        {lantern, mat(AIR), lantern},
                        {mat(SMOOTH_QUARTZ_SLAB), mat(AIR), mat(SMOOTH_QUARTZ_SLAB)},
                        {mat(AIR), mat(AIR), mat(AIR)}
                }
        }, new int[]{1, 0, 1}), 1);
        RESEARCH_TABLE.register();

        RESEARCH_LAB = new ResearchStation(ItemStacks.RESEARCH_LAB, "NE_RESEARCH_LAB", new Structure(new StructurePiece[][][]{
                {
                        {mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ)},
                        {mat(SMOOTH_QUARTZ), mat(NETHERITE_BLOCK), mat(NETHERITE_BLOCK), mat(SMOOTH_QUARTZ)},
                        {mat(SMOOTH_QUARTZ), mat(NETHERITE_BLOCK), mat(NETHERITE_BLOCK), mat(SMOOTH_QUARTZ)},
                        {mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ)},
                }, {
                        {mat(SMOOTH_QUARTZ), mat(FLETCHING_TABLE), mat(BASALT), mat(SMOOTH_QUARTZ)}, //TODO analysis core
                        {mat(WHITE_STAINED_GLASS_PANE), mat(AIR), mat(AIR), mat(WHITE_STAINED_GLASS_PANE)},
                        {mat(WHITE_STAINED_GLASS_PANE), mat(AIR), mat(AIR), mat(WHITE_STAINED_GLASS_PANE)},
                        {mat(WHITE_STAINED_GLASS_PANE), mat(AIR), mat(AIR), mat(WHITE_STAINED_GLASS_PANE)},
                }, {
                        {mat(SMOOTH_QUARTZ), mat(BASALT), mat(BASALT), mat(SMOOTH_QUARTZ)},
                        {mat(WHITE_STAINED_GLASS_PANE), mat(AIR), mat(AIR), mat(WHITE_STAINED_GLASS_PANE)},
                        {mat(WHITE_STAINED_GLASS_PANE), mat(AIR), mat(AIR), mat(WHITE_STAINED_GLASS_PANE)},
                        {mat(WHITE_STAINED_GLASS_PANE), mat(AIR), mat(AIR), mat(WHITE_STAINED_GLASS_PANE)},
                }, {
                        {mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ), mat(SMOOTH_QUARTZ)},
                        {mat(WHITE_STAINED_GLASS), mat(SEA_LANTERN), mat(SEA_LANTERN), mat(WHITE_STAINED_GLASS)},
                        {mat(WHITE_STAINED_GLASS), mat(SEA_LANTERN), mat(SEA_LANTERN), mat(WHITE_STAINED_GLASS)},
                        {mat(WHITE_STAINED_GLASS), mat(WHITE_STAINED_GLASS), mat(WHITE_STAINED_GLASS), mat(WHITE_STAINED_GLASS)},
                }
        }, new int[]{1, 0, 1}), 2);
        RESEARCH_LAB.register();

        //research facility smithing

        ItemStack pbb = new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS);
        ItemStack stick = new ItemStack(Material.STICK);
        REDISCOVERY_PICKAXE = new RediscoveryPickaxe(ItemStacks.REDISCOVERY_PICKAXE, "NE_REDISCOVERY_PICKAXE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                pbb, SlimefunItems.PICKAXE_OF_THE_SEEKER, pbb,
                null, stick, null,
                null, stick, null
        }, LootTables.REDISCOVERY_PICKAXE);
        REDISCOVERY_PICKAXE.register();

        StructurePiece any = new AnyStructurePiece();
        StructurePiece extension = u(new CustomItemStack(POLISHED_BLACKSTONE_BRICKS, "Assembler Extension", "&7Any Assembler extension, or Polished Blackstone Bricks."), mat(POLISHED_BLACKSTONE_BRICKS));
        BASIC_ASSEMBLER = new Assembler(ItemStacks.BASIC_ASSEMBLER, "NE_BASIC_ASSEMBLER", new Structure(new StructurePiece[][][]{
                {
                        {extension, extension, extension, extension, extension},
                        {extension, mat(CHISELED_POLISHED_BLACKSTONE), mat(CHISELED_POLISHED_BLACKSTONE), mat(CHISELED_POLISHED_BLACKSTONE), extension},
                        {extension, mat(CHISELED_POLISHED_BLACKSTONE), mat(NETHERITE_BLOCK), mat(CHISELED_POLISHED_BLACKSTONE), extension},
                        {extension, mat(CHISELED_POLISHED_BLACKSTONE), mat(CHISELED_POLISHED_BLACKSTONE), mat(CHISELED_POLISHED_BLACKSTONE), extension},
                        {extension, extension, mat(CHISELED_QUARTZ_BLOCK), extension, extension}
                }, {
                        {mat(BLACKSTONE), mat(GRAY_STAINED_GLASS), mat(GRAY_STAINED_GLASS), mat(GRAY_STAINED_GLASS), mat(BLACKSTONE)},
                        {mat(GRAY_STAINED_GLASS), any, any, any, mat(GRAY_STAINED_GLASS)},
                        {mat(GRAY_STAINED_GLASS), any, any, any, mat(GRAY_STAINED_GLASS)},
                        {mat(GRAY_STAINED_GLASS), any, any, any, mat(GRAY_STAINED_GLASS)},
                        {mat(BLACKSTONE), mat(AIR), mat(AIR), mat(AIR), mat(BLACKSTONE)},
                }, {
                        {mat(BLACKSTONE), mat(GRAY_STAINED_GLASS), mat(GRAY_STAINED_GLASS), mat(GRAY_STAINED_GLASS), mat(BLACKSTONE)},
                        {mat(GRAY_STAINED_GLASS), any, any, any, mat(GRAY_STAINED_GLASS)},
                        {mat(GRAY_STAINED_GLASS), any, any, any, mat(GRAY_STAINED_GLASS)},
                        {mat(GRAY_STAINED_GLASS), any, any, any, mat(GRAY_STAINED_GLASS)},
                        {mat(BLACKSTONE), mat(AIR), mat(AIR), mat(AIR), mat(BLACKSTONE)},
                }, {
                        {mat(BLACKSTONE), mat(GRAY_STAINED_GLASS), mat(GRAY_STAINED_GLASS), mat(GRAY_STAINED_GLASS), mat(BLACKSTONE)},
                        {mat(GRAY_STAINED_GLASS), any, any, any, mat(GRAY_STAINED_GLASS)},
                        {mat(GRAY_STAINED_GLASS), any, any, any, mat(GRAY_STAINED_GLASS)},
                        {mat(GRAY_STAINED_GLASS), any, any, any, mat(GRAY_STAINED_GLASS)},
                        {mat(BLACKSTONE), mat(AIR), mat(AIR), mat(AIR), mat(BLACKSTONE)},
                }, {
                        {mat(POLISHED_BLACKSTONE_BRICK_SLAB), mat(POLISHED_BLACKSTONE_BRICK_STAIRS), mat(POLISHED_BLACKSTONE_BRICK_STAIRS), mat(POLISHED_BLACKSTONE_BRICK_STAIRS), mat(POLISHED_BLACKSTONE_BRICK_SLAB)},
                        {mat(POLISHED_BLACKSTONE_BRICK_STAIRS), mat(CHISELED_POLISHED_BLACKSTONE), mat(CHISELED_POLISHED_BLACKSTONE), mat(CHISELED_POLISHED_BLACKSTONE), mat(POLISHED_BLACKSTONE_BRICK_STAIRS)},
                        {mat(POLISHED_BLACKSTONE_BRICK_STAIRS), mat(CHISELED_POLISHED_BLACKSTONE), mat(NETHERITE_BLOCK), mat(CHISELED_POLISHED_BLACKSTONE), mat(POLISHED_BLACKSTONE_BRICK_STAIRS)},
                        {mat(POLISHED_BLACKSTONE_BRICK_STAIRS), mat(CHISELED_POLISHED_BLACKSTONE), mat(CHISELED_POLISHED_BLACKSTONE), mat(CHISELED_POLISHED_BLACKSTONE), mat(POLISHED_BLACKSTONE_BRICK_STAIRS)},
                        {mat(POLISHED_BLACKSTONE_BRICK_SLAB), mat(POLISHED_BLACKSTONE_BRICK_STAIRS), mat(POLISHED_BLACKSTONE_BRICK_STAIRS), mat(POLISHED_BLACKSTONE_BRICK_STAIRS), mat(POLISHED_BLACKSTONE_BRICK_SLAB)},
                }

        }, new int[]{0, 4, 2}));
        BASIC_ASSEMBLER.register();

        ITEM_HOLDER = new NEAssembly(ItemStacks.ITEM_HOLDER, "NE_ITEM_HOLDER", new Structure(new StructurePiece[][][]{
                {
                        {mat(AIR), mat(AIR), mat(AIR)},
                        {mat(AIR), mat(CYAN_STAINED_GLASS), mat(AIR)},
                        {mat(AIR), mat(AIR), mat(AIR)}
                }, {
                        {mat(AIR), mat(CYAN_STAINED_GLASS), mat(AIR)},
                        {mat(CYAN_STAINED_GLASS), mat(AIR), mat(CYAN_STAINED_GLASS)},
                        {mat(AIR), mat(CYAN_STAINED_GLASS), mat(AIR)},
                }, {
                        {mat(AIR), mat(AIR), mat(AIR)},
                        {mat(AIR), mat(CYAN_STAINED_GLASS), mat(AIR)},
                        {mat(AIR), mat(AIR), mat(AIR)}
                },
        }, new int[]{0, 0, 0}));
        ITEM_HOLDER.register();

        ANCIENT_PART = new NEItem(ItemStacks.ANCIENT_PART, "NE_ANCIENT_PART");
        ANCIENT_PART.register();
    }

}
