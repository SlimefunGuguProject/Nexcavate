package me.char321.nexcavate.items.stations;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.gui.NEGUI;
import me.char321.nexcavate.research.progress.PlayerProgress;
import me.char321.nexcavate.slimefun.NEStructure;
import me.char321.nexcavate.structure.Structure;
import me.char321.nexcavate.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
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
                handleClick(e.getPlayer());
            }
        }
    }

    public void handleClick(Player player) {
        if (!PlayerProgress.get(player).hasCompletedTutorial()) {
            playTutorial(player);
        } else {
            NEGUI.openResearchScreen(player, tier);
        }
    }

    private void playTutorial(Player player) {
        PlayerProgress.get(player).setCompletedTutorial(true);

        Utils.runWithDelay(80L,
        () -> {
            player.playSound(player, Sound.AMBIENT_WARPED_FOREST_MOOD, 1, 1);
            player.playSound(player, Sound.AMBIENT_SOUL_SAND_VALLEY_MOOD, 1, 1);
            player.playSound(player, Sound.AMBIENT_CRIMSON_FOREST_MOOD, 1, 1);
            player.playSound(player, Sound.AMBIENT_NETHER_WASTES_MOOD, 1, 1);
            player.sendMessage(Utils.color("&dAfter successfully constructing the &eResearch Table&d, you sense an immense energy radiating from the depths."));
        }, () -> {
            player.sendMessage(Utils.color("&dTo your surprise, a mysterious voice pierces the air."));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&oWe've been trapped in here for so long..."));
        }, () -> {
            player.sendMessage(Utils.color("&dYou grew confused. &f&oWhat? &r&dYou find yourself asking."));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&oPlease, help us. We need you to rebuild our once great civilization."));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&oI'm sure you've come across some of our &eBastion Remnants."));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&oThey were once homes of a highly advanced species."));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&oAble to achieve things now inconceivable."));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&oThey were left in ruins after The &kGreat Abcdefghi."));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&oThe species, along with all of its progress, was wiped off the face of the Nether."));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&oSome traces were still present &ein the walls &7&oof the remaining structures, but it was unrecoverable for the species."));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&oThe only way the species could relive its greatness was with a special tool, only craftable from materials that were inaccessible to them"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&oA &eRediscovery Pickaxe&7&o, which could extract &eancient parts &7&otrapped in blocks around Bastion Remnants."));
        }, () -> {
            player.sendMessage(Utils.color("&dBefore you could ask any questions, the voice had left."));
        }, () -> {
            NEGUI.openResearchScreen(player, tier);
        }
        );
    }

    @Override
    public void postRegister() {
        Nexcavate.instance().getRegistry().getResearchStations().put(tier, this);
    }
}
