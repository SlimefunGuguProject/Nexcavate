package me.char321.nexcavate.items.stations;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.gui.NEGUI;
import me.char321.nexcavate.items.structure.NEStructure;
import me.char321.nexcavate.items.structure.Structure;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ResearchStation extends NEStructure implements Listener {
    private final int tier;

    public ResearchStation(ItemStack item, String id, Structure structure, int tier) {
        super(item, id, structure);
        this.tier = tier;
        Bukkit.getPluginManager().registerEvents(this, Nexcavate.instance());
    }

    @EventHandler
    public void interactListener(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null && structure.getCenterPiece().isValid(e.getClickedBlock())) {
            if (structure.validate(e.getClickedBlock().getLocation())) {
                e.setCancelled(true);
                NEGUI.openResearchScreen(e.getPlayer(), tier);
            }
        }
    }

    @Override
    public void postRegister() {
        Nexcavate.instance().getRegistry().getResearchStations().put(tier, this);
    }
}
