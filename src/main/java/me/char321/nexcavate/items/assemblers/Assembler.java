package me.char321.nexcavate.items.assemblers;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.slimefun.NEAssembly;
import me.char321.nexcavate.slimefun.NEStructure;
import me.char321.nexcavate.structure.Structure;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Assembler extends NEStructure implements Listener {
    public Assembler(ItemStack item, String id, Structure structure) {
        super(item, id, structure);
        Bukkit.getPluginManager().registerEvents(this, Nexcavate.instance());
    }

    @EventHandler
    public void interactListener(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null && structure.getCenterPiece().isValid(e.getClickedBlock())) {
            int orientation = structure.orientation(e.getClickedBlock().getLocation());
            if (orientation != -1) {
                e.setCancelled(true);
                handleClick(e.getPlayer(), orientation, e.getClickedBlock());
            }
        }
    }

    private void handleClick(Player player, int orientation, Block center) {
        if (!tryAssemble(orientation, center)) {
            player.sendMessage(ChatColor.RED + "The recipe in the assembler could not be recognized.");
        }
    }

    public boolean tryAssemble(int orientation, Block center) {
        Location centerPos = center.getLocation();

        /*
          assume assembler structures wrap the ingredients by 1 block
          ex. where # is assembler, o is ingredient, and imagine it's in 3d
          #####
          #ooo#
          #ooo#
          #ooo#
          #####

          ######
          #oooo#
          #oooo#
          #oooo#
          #oooo#
          ######
         */
        //assemblies must have center 0,0,0
        Location absoluteOrigin;
        List<Location> origins = new ArrayList<>();
        switch (orientation) { //TODO broken
            case 0 -> {
                //to you
                absoluteOrigin = centerPos.clone().subtract(structure.getCenter()[0], structure.getCenter()[1], structure.getCenter()[2]);
            }
            case 1 -> {
                absoluteOrigin = centerPos.clone().subtract(structure.getCenter()[0], structure.getCenter()[1], structure.getCenter()[2]);
                //basically rotate about the absolute center
                absoluteOrigin.subtract(structure.size-1, 0, 0);
            }
            case 2 -> {
                absoluteOrigin = centerPos.clone().subtract(structure.getCenter()[0], structure.getCenter()[1], structure.getCenter()[2]);
                //basically rotate about the absolute center
                absoluteOrigin.subtract(structure.size-1, 0, structure.size-1);
            }
            case 3 -> {
                absoluteOrigin = centerPos.clone().subtract(structure.getCenter()[0], structure.getCenter()[1], structure.getCenter()[2]);
                //basically rotate about the absolute center
                absoluteOrigin.subtract(0, 0, structure.size-1);
            }
            default -> throw new IllegalArgumentException("expected orientation 0-3, got " + orientation);
        }
        //front left
        origins.add(absoluteOrigin.clone().add(1, 1, 1));
        //front right
        origins.add(absoluteOrigin.clone().add(structure.size-2, 1, 1));
        //back right
        origins.add(absoluteOrigin.clone().add(structure.size-2, 1, structure.size-2));
        //back left
        origins.add(absoluteOrigin.clone().add(1, 1, structure.size-2));

        Map<Structure, NEAssembly> assemblies = Nexcavate.instance().getRegistry().getAssemblies();
        for (Map.Entry<Structure, NEAssembly> entry : assemblies.entrySet()) {
            Structure recipe = entry.getKey();
            if (recipe.size != this.structure.size - 2) {
                continue;
            }

            for(int i = 0; i < 4; i++) {
                Location origin = origins.get(i);
                if (recipe.validateOrientation(origin, i)) {
                    assemble(entry.getValue(), origin);
                    return true;
                }
            }
        }
        return false;
    }

    private void assemble(NEAssembly assembly, Location destination) {
        info("wowwee you assembnled a " + assembly.getItemName());
    }
}
