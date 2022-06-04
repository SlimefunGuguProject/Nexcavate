package me.char321.nexcavate.items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.inventory.ItemStack;

public class NEItem extends SlimefunItem {
    public NEItem(ItemStack item, String id) {
        super(Items.DUMMY_GROUP, item, id, Items.DUMMY_TYPE, new ItemStack[9]);
    }

    public NEItem(ItemStack item, String id, ItemStack[] recipe) {
        super(Items.DUMMY_GROUP, item, id, Items.DUMMY_TYPE, recipe);
    }
}
