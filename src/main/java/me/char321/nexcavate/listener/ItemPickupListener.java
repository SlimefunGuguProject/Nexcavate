package me.char321.nexcavate.listener;

import me.char321.nexcavate.Nexcavate;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class ItemPickupListener implements Listener {

    public ItemPickupListener() {
        Bukkit.getPluginManager().registerEvents(this, Nexcavate.instance());
    }

    @EventHandler
    public void onEntityPickup(EntityPickupItemEvent e) {
        if (e.getItem().hasMetadata("ItemHolder")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHopperPickup(InventoryPickupItemEvent e) {
        if (e.getItem().hasMetadata("ItemHolder")) {
            e.setCancelled(true);
        }
    }
}