package me.char321.nexcavate.items.structure.piece;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;

public interface StructurePiece {
    boolean isValid(Block b);

    ItemStack getDisplay();

    EnumMap<Material, StructurePiece> literals = new EnumMap<>(Material.class);

    static StructurePiece lit(Material m) {
        return literals.computeIfAbsent(m, LiteralStructurePiece::new);
    }

    static StructurePiece u(ItemStack display, StructurePiece... pieces) {
        return new UnionStructurePiece(display, pieces);
    }
}
