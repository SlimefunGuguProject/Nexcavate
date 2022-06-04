package me.char321.nexcavate.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.items.structure.ResearchTable;
import me.char321.nexcavate.items.structure.Structure;
import me.char321.nexcavate.items.structure.piece.StructurePiece;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import static me.char321.nexcavate.items.structure.piece.StructurePiece.*;
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
            "&7View the recipe through the Nexcavate entry in the Slimefun guide."));

    public static ResearchTable RESEARCH_TABLE;

    public static void init() {
        StructurePiece lantern = u(new CustomItemStack(LANTERN, "Lantern/Soul Lantern"), lit(LANTERN), lit(SOUL_LANTERN));
        RESEARCH_TABLE = new ResearchTable(new CustomItemStack(CARTOGRAPHY_TABLE), "NE_RESEARCH_TABLE", new Structure(new StructurePiece[][][]{
                {
                        {lit(SMOOTH_QUARTZ), lit(SMOOTH_QUARTZ), lit(SMOOTH_QUARTZ)},
                        {lit(SMOOTH_QUARTZ), lit(NETHERITE_BLOCK), lit(SMOOTH_QUARTZ)},
                        {lit(SMOOTH_QUARTZ_STAIRS), lit(SMOOTH_QUARTZ_STAIRS), lit(SMOOTH_QUARTZ_STAIRS)}
                },
                {
                        {lit(BOOKSHELF), lit(CARTOGRAPHY_TABLE), lit(BOOKSHELF)},
                        {lit(END_ROD), lit(AIR), lit(END_ROD)},
                        {lit(AIR), lit(AIR), lit(AIR)}
                },
                {
                        {lantern, lit(AIR), lantern},
                        {lit(SMOOTH_QUARTZ_SLAB), lit(AIR), lit(SMOOTH_QUARTZ_SLAB)},
                        {lit(AIR), lit(AIR), lit(AIR)}
                }
        }, new int[]{1, 0, 1}), 0);
        RESEARCH_TABLE.register(Nexcavate.instance());

    }

}
