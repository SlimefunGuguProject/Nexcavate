package me.char321.nexcavate.items.structure.piece;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * logical or of its parameters
 */
public class UnionStructurePiece implements StructurePiece {
    private final ItemStack display;
    private final List<StructurePiece> subpieces;

    public UnionStructurePiece(ItemStack display, StructurePiece... subpieces) {
        this.display = display;
        this.subpieces = Arrays.asList(subpieces);
    }

    @Override
    public boolean isValid(Block b) {
        for (StructurePiece subpiece : subpieces) {
            if (subpiece.isValid(b)) return true;
        }
        return false;
    }

    @Override
    public ItemStack getDisplay() {
        return display;
    }
}
