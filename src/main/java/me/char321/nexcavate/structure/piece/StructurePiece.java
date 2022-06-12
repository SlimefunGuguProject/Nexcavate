package me.char321.nexcavate.structure.piece;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public interface StructurePiece {
    boolean isValid(Block b);

    ItemStack getDisplay();

    EnumMap<Material, StructurePiece> literals = new EnumMap<>(Material.class);

    static StructurePiece mat(Material m) {
        return literals.computeIfAbsent(m, MaterialStructurePiece::new);
    }

    static StructurePiece u(ItemStack display, StructurePiece... pieces) {
        return new UnionStructurePiece(display, pieces);
    }

    static StructurePiece wrap(ItemStack item) {
        return new ItemHolderStructurePiece(item);
    }

    Map<String, StructurePiece> sfpieces = new HashMap<>();
    static StructurePiece sf(String item) {
        return sfpieces.computeIfAbsent(item, SlimefunItemStructurePiece::new);
    }
}
