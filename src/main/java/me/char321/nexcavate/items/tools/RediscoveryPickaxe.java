package me.char321.nexcavate.items.tools;

import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import me.char321.nexcavate.items.NEItem;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class RediscoveryPickaxe extends NEItem {
    private final Map<Material, Float> partChances = new EnumMap<>(Material.class);

    public RediscoveryPickaxe(ItemStack item, String id) {
        super(item, id);
        addItemHandler((ToolUseHandler) (e, tool, fortune, drops) -> {

        });
    }
}
