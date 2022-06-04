package me.char321.nexcavate.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.UUID;

public class ResearchScreenHandler implements NEGUIInventoryHolder {
    private final UUID player;
    private final Inventory inventory;

    public ResearchScreenHandler(UUID player) {
        this.player = player;
        this.inventory = Bukkit.createInventory(this, 54, "Nexcavate Research");
    }

    @Override
    @Nonnull
    public Inventory getInventory() {
        return inventory;
    }

    public void refresh(int tier) {
        inventory.setItem(0, new ItemStack(Material.BLACKSTONE));
    }

    @Override
    public void click(InventoryClickEvent e) {

    }
}
