package me.char321.nexcavate.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.items.stations.ResearchStation;
import me.char321.nexcavate.items.structure.Structure;
import me.char321.nexcavate.items.structure.piece.StructurePiece;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import static me.char321.nexcavate.items.structure.piece.StructurePiece.mat;
import static me.char321.nexcavate.items.structure.piece.StructurePiece.u;
import static org.bukkit.Material.AIR;
import static org.bukkit.Material.BASALT;
import static org.bukkit.Material.BOOKSHELF;
import static org.bukkit.Material.CARTOGRAPHY_TABLE;
import static org.bukkit.Material.END_ROD;
import static org.bukkit.Material.FLETCHING_TABLE;
import static org.bukkit.Material.LANTERN;
import static org.bukkit.Material.NETHERITE_BLOCK;
import static org.bukkit.Material.SEA_LANTERN;
import static org.bukkit.Material.SMOOTH_QUARTZ;
import static org.bukkit.Material.SMOOTH_QUARTZ_SLAB;
import static org.bukkit.Material.SMOOTH_QUARTZ_STAIRS;
import static org.bukkit.Material.SOUL_LANTERN;
import static org.bukkit.Material.WHITE_STAINED_GLASS;
import static org.bukkit.Material.WHITE_STAINED_GLASS_PANE;

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

    public static void init() {
        StructurePiece lantern = u(new CustomItemStack(LANTERN, "Lantern/Soul Lantern"), mat(LANTERN), mat(SOUL_LANTERN));
        RESEARCH_TABLE = new ResearchStation(new CustomItemStack(CARTOGRAPHY_TABLE, "&eResearch Table"), "NE_RESEARCH_TABLE", new Structure(new StructurePiece[][][]{
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

        RESEARCH_LAB = new ResearchStation(new CustomItemStack(FLETCHING_TABLE, "&eResearch Lab"), "NE_RESEARCH_LAB", new Structure(new StructurePiece[][][]{
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
    }

}
