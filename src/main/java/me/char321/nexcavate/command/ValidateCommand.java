package me.char321.nexcavate.command;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.char321.nexcavate.slimefun.NEAssembly;
import me.char321.nexcavate.slimefun.NEStructure;
import me.char321.nexcavate.structure.Structure;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidateCommand implements SubCommand {
    @Override
    public boolean onExecute(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You can only run this command as a player.");
            return false;
        }

        if (args.length < 2) {
            player.sendMessage("Usage: /nexcavate validate <structure> [<orientation>]");
            player.sendMessage("See the github page for more detailed instructions of how to use this command.");
            return false;
        }

        int orientation = -2;
        if (args.length == 3) {
            try {
                orientation = Integer.parseInt(args[2]);
            } catch (NumberFormatException x) {
                player.sendMessage("Usage: /nexcavate validate <structure> [<orientation>]");
            }
        }

        SlimefunItem sfitem = SlimefunItem.getById(args[1]);
        if (sfitem instanceof NEAssembly assembly) {
            validate(player, assembly.getStructure(), orientation);
            return true;
        } else if (sfitem instanceof NEStructure structure) {
            validate(player, structure.getStructure(), orientation);
            return true;
        } else {
            player.sendMessage("You did not enter a valid item with a structure.");
            return true;
        }
    }

    private void validate(Player player, Structure structure, int orientation) {
        if (orientation == -2) {
            orientation = getOrientation(player);
        }

        Block target = player.getTargetBlockExact(4, FluidCollisionMode.NEVER);
        if (target == null) {
            player.sendMessage("You are not looking at any solid block.");
        }
        String incorrect = structure.getIncorrect(target.getLocation(), orientation);
        if (incorrect != null) {
            player.sendMessage("Found incorrect block at " + incorrect);
        } else {
            player.sendMessage("Structure is valid!");
        }
    }

    private int getOrientation(Player player) {
        Vector v = player.getLocation().getDirection();
        double x = v.getX();
        double z = v.getZ();
        if (z > 0) {
            if (x > z) {
                return 1;
            } else if (x < -z) {
                return 3;
            } else {
                return 2;
            }
        } else {
            if (x > -z) {
                return 1;
            } else if (x < z) {
                return 3;
            } else {
                return 0;
            }
        }
    }

    @Nonnull
    @Override
    public String getCommandName() {
        return "validate";
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 2) {
            return Slimefun.getRegistry().getAllSlimefunItems().stream().map(SlimefunItem::getId).filter(name -> name.contains(args[1])).toList();
        } else if (args.length == 3) {
            return List.of("0", "1", "2", "3");
        }
        return Collections.emptyList();
    }
}
