package me.char321.nexcavate.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public interface NEGUIInventoryHolder extends InventoryHolder {
    void click(InventoryClickEvent slot);
}
