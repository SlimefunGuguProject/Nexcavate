package me.char321.nexcavate.items.assemblers;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.slimefun.NEAssembly;
import me.char321.nexcavate.slimefun.NEStructure;
import me.char321.nexcavate.structure.Structure;
import me.char321.nexcavate.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Assembler extends NEStructure implements Listener {
    private boolean netherRequired;

    public Assembler(ItemStack item, String id, Structure structure) {
        super(item, id, structure);
        Bukkit.getPluginManager().registerEvents(this, Nexcavate.instance());
    }

    public void setNetherRequired(boolean netherRequired) {
        this.netherRequired = netherRequired;
    }

    @EventHandler
    public void interactListener(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getHand() != EquipmentSlot.OFF_HAND && e.getClickedBlock() != null && structure.getCenterPiece().isValid(e.getClickedBlock())) {
            int orientation = structure.orientation(e.getClickedBlock().getLocation());
            if (orientation != -1) {
                e.setCancelled(true);
                handleClick(e.getPlayer(), orientation, e.getClickedBlock());
            }
        }
    }

    private void handleClick(Player player, int orientation, Block center) {
        if (netherRequired && !player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
            player.sendMessage(ChatColor.RED + "请将机器放置在下界中");
            return;
        }

        if (!tryAssemble(orientation, center)) {
            player.sendMessage(ChatColor.RED + "装配机中的配方无法被识别");
        }
    }

    public boolean tryAssemble(int assemblerOrientation, Block center) {
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
        switch (assemblerOrientation) {
            case 0 -> absoluteOrigin = centerPos.clone().subtract(structure.getCenter()[2], structure.getCenter()[0], structure.getCenter()[1]);
            case 1 -> {
                absoluteOrigin = centerPos.clone().subtract(-structure.getCenter()[1], structure.getCenter()[0], structure.getCenter()[2]);
                absoluteOrigin.subtract(structure.size-1, 0, 0);
            }
            case 2 -> {
                absoluteOrigin = centerPos.clone().subtract(-structure.getCenter()[2], structure.getCenter()[0], -structure.getCenter()[1]);
                absoluteOrigin.subtract(structure.size-1, 0, structure.size-1);
            }
            case 3 -> {
                absoluteOrigin = centerPos.clone().subtract(structure.getCenter()[1], structure.getCenter()[0], -structure.getCenter()[2]);
                absoluteOrigin.subtract(0, 0, structure.size-1);
            }
            default -> throw new IllegalArgumentException("expected orientation 0-3, got " + assemblerOrientation);
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

            for(int recipeOrientation = 0; recipeOrientation < 4; recipeOrientation++) {
                Location origin = origins.get(recipeOrientation);
                if (recipe.validateOrientation(origin, recipeOrientation)) {
                    beginAssemble(entry.getValue(), origin, box(origin, recipeOrientation, recipe.size));
                    return true;
                }
            }
        }
        return false;
    }

    private BoundingBox box(Location origin, int orientation, int size) {
        switch (orientation) {
            case 0 -> {
                return BoundingBox.of(origin, origin.clone().add(size-1, size-1, size-1));
            }
            case 1 -> {
                return BoundingBox.of(origin, origin.clone().add(-(size-1), size-1, size-1));
            }
            case 2 -> {
                return BoundingBox.of(origin, origin.clone().add(-(size-1), size-1, -(size-1)));
            }
            case 3 -> {
                return BoundingBox.of(origin, origin.clone().add(size-1, size-1, -(size-1)));
            }
        }
        return null;
    }

    private void beginAssemble(NEAssembly assembly, Location destination, BoundingBox ingredients) {
        for (Entity entity : destination.getWorld().getNearbyEntities(destination, 25, 25, 25)) {
            if (entity instanceof Player) {
                playAnimation(destination, ingredients);
                break;
            }
        }
        assemble(assembly, destination, ingredients);
    }

    private void playAnimation(Location destination, BoundingBox ingredients) {
        Set<Block> blocks = new HashSet<>();
        for (double y = ingredients.getMinY(); y <= ingredients.getMaxY(); y++) {
            for (double z = ingredients.getMinZ(); z <= ingredients.getMaxZ(); z++) {
                for (double x = ingredients.getMinX(); x <= ingredients.getMaxX(); x++) {
                    blocks.add(new Location(destination.getWorld(), x, y, z).getBlock());
                }
            }
        }

        List<FallingBlock> fallingBlocks = new ArrayList<>();
        for (Block b : blocks) {
            if (b.getType().equals(Material.AIR)) {
                continue;
            }
            FallingBlock block = b.getWorld().spawnFallingBlock(b.getLocation().add(0.5, 0, 0.5), b.getBlockData());
            block.setVelocity(new Vector(0, 0, 0));
            block.setGravity(false);
            block.setDropItem(false);
            block.setPersistent(true);
            block.setInvulnerable(true);
            fallingBlocks.add(block);
        }

        Vector center = ingredients.getCenter().add(new Vector(0.5, 0, 0.5));

        for (FallingBlock fallingBlock : fallingBlocks) {
            Location location = fallingBlock.getLocation();
            Vector v = new Vector(center.getX() - location.getX(), center.getY()-location.getY(), center.getZ()-location.getZ());
            if (v.lengthSquared() != 0) {
                v.multiply(0.035);
            }
            fallingBlock.setVelocity(v);
        }

        Bukkit.getScheduler().runTaskLater(Nexcavate.instance(), () -> {
            fallingBlocks.forEach(Entity::remove);
            destination.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, center.getX(), center.getY(), center.getZ(), 100, 0, 0, 0, 0.5);
        }, 50L);
    }

    private void assemble(NEAssembly assembly, Location destination, BoundingBox ingredients) {
        for (double y = ingredients.getMinY(); y <= ingredients.getMaxY(); y++) {
            for (double z = ingredients.getMinZ(); z <= ingredients.getMaxZ(); z++) {
                for (double x = ingredients.getMinX(); x <= ingredients.getMaxX(); x++) {
                    Block block = new Location(destination.getWorld(), x, y, z).getBlock();
                    Utils.removeBlock(block, true);
                }
            }
        }

        Bukkit.getScheduler().runTaskLater(Nexcavate.instance(), () -> {
            destination.getWorld().dropItem(ingredients.getCenter().toLocation(destination.getWorld()).add(0.5, 0, 0.5), assembly.getRecipeOutput().clone());
        }, 50L);
    }
}
