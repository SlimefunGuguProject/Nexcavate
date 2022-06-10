package me.char321.nexcavate.gui;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.items.ItemStacks;
import me.char321.nexcavate.items.Items;
import me.char321.nexcavate.research.Research;
import me.char321.nexcavate.research.progress.PlayerProgress;
import me.char321.nexcavate.research.progress.ProgressManager;
import me.char321.nexcavate.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResearchScreenHandler implements NEGUIInventoryHolder {
    private final Inventory inventory;
    private final UUID player;
    private int currentTier;
    private final Map<Integer, Research> researchSlots = new HashMap<>();

    public ResearchScreenHandler(UUID player) {
        this.player = player;
        this.inventory = Bukkit.createInventory(this, 54, "Nexcavate Research");
    }

    @Override
    @Nonnull
    public Inventory getInventory() {
        return inventory;
    }

    public void refresh() {
        refresh(currentTier);
    }

    public void refresh(int tier) {
        currentTier = tier;
        int[] pos = new int[6];
        for (Research research : Nexcavate.instance().getRegistry().getResearches()) {
            ItemStack display = getDisplay(research);
            int row = research.getCategory();
            int slot = 9 * row + pos[row]++;
            inventory.setItem(slot, display);
            researchSlots.put(slot, research);
        }
    }

    private ItemStack getDisplay(Research research) {
        ItemStack res = research.getDisplay().clone();
        PlayerProgress playerProgress = PlayerProgress.get(player);
        if (research.equals(playerProgress.getCurrentResearch())) {
            PlayerProgress.ResearchProgress progress = playerProgress.getCurrentResearchProgress();
            res.setType(Material.YELLOW_STAINED_GLASS_PANE);
            ItemMeta im = Utils.appendLore(res, "", "&7Research progress: &7" + progress.currentProgress());
            im.getEnchants().forEach((enchantment, i) -> im.removeEnchant(enchantment));
            res.setItemMeta(im);
        } else if (!playerProgress.isResearched(research)) {
            if (research.getTier() > currentTier) {
                res = new CustomItemStack(Material.BARRIER, "&4&lLocked", "&7A &f" + Nexcavate.instance().getRegistry().getResearchStation(research.getTier()).getItemName(), "&7 is required to research this item.");
            } else {
                res.setType(Material.GRAY_STAINED_GLASS_PANE);
                ItemMeta im = Utils.appendLore(res, "", "&7Research cost: &f" + research.getCost() + " Ancient Parts", "&7Research time: &f" + research.getTime() + " minute(s)");
                im.getEnchants().forEach((enchantment, i) -> im.removeEnchant(enchantment));
                res.setItemMeta(im);
            }
        }
        return res;
    }

    @Override
    public void click(InventoryClickEvent e) {
        refresh();
        Research research = researchSlots.get(e.getRawSlot());
        if (research == null) {
            return;
        }

        Player player1 = (Player) e.getWhoClicked();
        PlayerProgress playerProgress = PlayerProgress.get(player);
        if (playerProgress.isResearched(research)) {
            NEGUI.openRecipe(player1, researchSlots.get(e.getRawSlot()));
        } else if (currentTier >= research.getTier()) {
            if (playerProgress.getCurrentResearchProgress() != null) {
                e.getWhoClicked().sendMessage(Utils.color("&cYou are already researching &f" + playerProgress.getCurrentResearch().getName() + "&c!"));
            } else if (!canAfford(player1, research)) {
                e.getWhoClicked().sendMessage(Utils.color("&cYou cannot afford this research! " + research.getCost() + " ancient parts are required."));
            } else {
                consumeParts(player1, research);
                playerProgress.beginResearch(research);
                player1.playSound(player1, Sound.BLOCK_END_PORTAL_SPAWN, 1, 1);
            }
            refresh();
        }
    }

    private void consumeParts(Player player, Research research) {
        int cost = research.getCost();

        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (SlimefunUtils.isItemSimilar(itemStack, ItemStacks.ANCIENT_PART, true, false)) {
                int amount = itemStack.getAmount();
                int toConsume = Math.min(amount, cost);
                itemStack.setAmount(amount - toConsume);
                cost -= toConsume;
                if (cost == 0) {
                    return;
                }
            }
        }
    }

    private boolean canAfford(Player player, Research research) {
        int count = 0;
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (SlimefunUtils.isItemSimilar(itemStack, ItemStacks.ANCIENT_PART, true, false)) {
                count += itemStack.getAmount();
            }
        }
        return count >= research.getCost();
    }
}
