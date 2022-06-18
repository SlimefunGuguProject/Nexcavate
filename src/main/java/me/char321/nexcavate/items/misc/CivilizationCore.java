package me.char321.nexcavate.items.misc;

import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.char321.nexcavate.slimefun.NEAssembly;
import me.char321.nexcavate.structure.Structure;
import me.char321.nexcavate.util.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class CivilizationCore extends NEAssembly {
    public CivilizationCore(ItemStack item, String id, Structure assembly) {
        super(item, id, assembly);
    }

    @Override
    public void preRegister() {
        super.preRegister();

        addItemHandler(new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                playCutscene(e.getPlayer());
            }
        });
    }

    private void playCutscene(Player player) {
        player.playSound(player, Sound.BLOCK_END_PORTAL_SPAWN, 1, 1);
        Utils.runWithDelay(80L,
                () -> {
                    player.sendMessage(Utils.color("&d你放置了一个文明和谐"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&o什么？是谁把我从沉睡中唤醒？"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 1, 0.5f);
                    player.sendMessage(Utils.color("&a&o等等...那是什么东西?"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&o上面写着&e" + player.getName() + ""));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&o他们重建了...我们的文明。那些是我们的建筑工吗?"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 1, 0.5f);
                    player.sendMessage(Utils.color("&a&o他们是怎么得到建造它的零件的?"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&o他们一定是从另一个世界穿越而来"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 1, 0.5f);
                    player.sendMessage(Utils.color("&a&o看到了吗？我告诉过你有人会来救我们的！我就知道！"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&o是啊，当然。自从那次大规模灭绝以来，只花了大概179000年。"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 1, 0.5f);
                    player.sendMessage(Utils.color("&a&o嗯，你还是应该感激 &e" + player.getName() + "&a&o救了我们"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&o当然了。我很高兴我们的文明在经历了几千年后还能继续存在。"));
                }, () -> {

                }, () -> {
                    player.playSound(player, Sound.MUSIC_DISC_BLOCKS, 1, 1f);
                    player.sendMessage(Utils.color("&e&l终章"));
                }, () -> {
                    player.sendMessage(Utils.color("&e&l感谢游玩!"));
                }
        );
    }
}
