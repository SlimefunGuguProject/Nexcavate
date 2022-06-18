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
            player.sendMessage(Utils.color("&d成功构建 &e研究台&d 你感觉到一股巨大的能量从下界深处散发出来"));
        }, () -> {
            player.sendMessage(Utils.color("&d令你惊讶的是，一个神秘的声音穿透了空气"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&o我们被困在这里太久了..."));
        }, () -> {
            player.sendMessage(Utils.color("&d你变得困惑&f&o什么? &r&d你在自言自语"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&o请帮帮我们，我们需要你来重建我们曾经伟大的文明。"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&o我相信你已经见过我们的一些堡垒遗迹了"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&o它们曾经是高度发达文明的家园。"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&o能够实现现在不可想象的事情"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&o在&kGreat Abcdefghi&7&o后它们变成了废墟"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&o这个物种，连同它所有的文明，都被从下界中抹去了"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&o一些痕迹仍然存在于剩余结构的&e墙壁&7&o上，但对于物种来说是不可恢复的"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&o此文明重现其伟大的唯一方法是使用一种特殊的工具，但这种工具只能用他们无法获得的材料来制作"));
        }, () -> {
            player.playSound(player, Sound.ENTITY_ZOMBIFIED_PIGLIN_DEATH, 1, 0.5f);
            player.sendMessage(Utils.color("&7&o一个&e考古镐&7&o, 它可以提取猪灵堡垒周围方块中的古代零件"));
        }, () -> {
            player.sendMessage(Utils.color("&d你还没来得及问任何问题，那个声音已经离开了"));
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
