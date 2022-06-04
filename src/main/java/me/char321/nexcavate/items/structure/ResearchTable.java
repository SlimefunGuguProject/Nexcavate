package me.char321.nexcavate.items.structure;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.gui.NEGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ResearchTable extends NEStructure implements Listener {
    private final int tier;

    public ResearchTable(ItemStack item, String id, Structure structure, int tier) {
        super(item, id, structure);
        this.tier = tier;
        Bukkit.getPluginManager().registerEvents(this, Nexcavate.instance());
    }

    @EventHandler
    public void cartographyTableListener(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.CARTOGRAPHY_TABLE)) {
            if (getStructure().validate(e.getClickedBlock().getLocation())) {
                e.setCancelled(true);
                NEGUI.openResearchScreen(e.getPlayer(), tier);
            }
        }
    }
}
