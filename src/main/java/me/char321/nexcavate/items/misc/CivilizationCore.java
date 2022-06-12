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
                    player.sendMessage(Utils.color("&dYou place down the Civilization Core."));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&oWhat? Who has awaken me from my slumber?"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 1, 0.5f);
                    player.sendMessage(Utils.color("&a&oWait...what is that thing?"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&oIt says &e" + player.getName() + " &9&o above."));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&oAnd they've built...our civilization. Are those our assemblers?"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 1, 0.5f);
                    player.sendMessage(Utils.color("&a&oHow did they even get the parts to build that?"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&oThey must be from the Overworld."));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 1, 0.5f);
                    player.sendMessage(Utils.color("&a&oSee? I TOLD you someone was gonna come along to save us! I knew it!"));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&oYeah, sure. It only took like, 179,000 years since &eThe Great Eradication."));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 1, 0.5f);
                    player.sendMessage(Utils.color("&a&oWell, you should still be grateful that &e" + player.getName() + "&a&o saved us."));
                }, () -> {
                    player.playSound(player, Sound.ENTITY_PIGLIN_RETREAT, 1, 0.5f);
                    player.sendMessage(Utils.color("&9&oOf course. I'm glad that our civilization can come alive after all these millennia."));
                }, () -> {

                }, () -> {
                    player.playSound(player, Sound.MUSIC_DISC_BLOCKS, 1, 1f);
                    player.sendMessage(Utils.color("&e&lThe End"));
                }, () -> {
                    player.sendMessage(Utils.color("&e&lThanks for playing!"));
                }
        );
    }
}
