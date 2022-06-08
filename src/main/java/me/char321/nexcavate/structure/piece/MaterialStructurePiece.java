package me.char321.nexcavate.structure.piece;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class MaterialStructurePiece implements StructurePiece {
    private final Material material;

    public MaterialStructurePiece(Material material) {
        this.material = material;
    }

    @Override
    public boolean isValid(Block b) {
        return b.getType().equals(this.material);
    }

    @Override
    public ItemStack getDisplay() {
        return new ItemStack(material);
    }
}
