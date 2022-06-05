package me.char321.nexcavate.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NEGUIListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof NEGUIInventoryHolder) {
            e.setCancelled(true);
            ((NEGUIInventoryHolder)e.getInventory().getHolder()).click(e);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {

    }
}
