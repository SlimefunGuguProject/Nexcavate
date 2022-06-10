package me.char321.nexcavate.structure.piece;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.char321.nexcavate.items.misc.ItemHolder;
import me.char321.nexcavate.util.Utils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class ItemHolderStructurePiece implements StructurePiece {
    private ItemStack item;

    public ItemHolderStructurePiece(ItemStack item) {
        this.item = item;
    }

    @Override
    public boolean isValid(Block b) {
        SlimefunItem sfitem = BlockStorage.check(b);
        if (!(sfitem instanceof ItemHolder holder)) {
            return false;
        }

        ItemStack held = BlockStorage.getInventory(b).getItemInSlot(13);
        return SlimefunUtils.isItemSimilar(this.item, held, true, true);
    }

    @Override
    public ItemStack getDisplay() {
        ItemStack clone = item.clone();
        Utils.appendLore(clone, "", "&7Place an Item Holder at this position", "&7and put the item in it to assemble. ");
        return clone;
    }
}
