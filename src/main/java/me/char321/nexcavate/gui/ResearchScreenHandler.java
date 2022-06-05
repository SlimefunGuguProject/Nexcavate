package me.char321.nexcavate.gui;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.research.Research;
import me.char321.nexcavate.research.progress.PlayerProgress;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResearchScreenHandler implements NEGUIInventoryHolder {
    private final UUID player;
    private final Inventory inventory;
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
        if (research.getTier() > currentTier) {
            return new CustomItemStack(Material.BARRIER, "&4&lLocked", "&7A &f" + Nexcavate.instance().getRegistry().getResearchStation(research.getTier()).getItemName() + "&7 is required to research this item.");
        }

        ItemStack res = research.getDisplay();
        PlayerProgress playerProgress = Nexcavate.instance().getProgressManager().get(player);
        if (research.equals(playerProgress.getCurrentResearch())) {
            PlayerProgress.ResearchProgress progress = playerProgress.getCurrentResearchProgress();
            res = res.clone();
            res.setType(Material.YELLOW_STAINED_GLASS);
            ItemMeta im = res.getItemMeta();
            ArrayList<String> lore = im.hasLore() ? new ArrayList<>(im.getLore()) : new ArrayList<>();
            lore.add("");
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Research progress: &7" + progress.currentProgress()));
            im.setLore(lore);
            res.setItemMeta(im);
        } else if (!playerProgress.isResearched(research)) {
            res = res.clone();
            res.setType(Material.GRAY_STAINED_GLASS);
            ItemMeta im = res.getItemMeta();
            ArrayList<String> lore = im.hasLore() ? new ArrayList<>(im.getLore()) : new ArrayList<>();
            lore.add("");
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Research cost: &f" + research.getCost() + " Ancient Parts"));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Research time: &f" + research.getTime() + " minute(s)"));
            im.setLore(lore);
            res.setItemMeta(im);
        }
        return res;
    }

    @Override
    public void click(InventoryClickEvent e) {
        refresh(currentTier);
        Research research = researchSlots.get(e.getRawSlot());
        if (research == null) {
            return;
        }

        PlayerProgress playerProgress = Nexcavate.instance().getProgressManager().get(player);
        if (playerProgress.isResearched(research)) {
            //TODO show recipe
        } else if (currentTier >= research.getTier()) {
            if (playerProgress.getCurrentResearchProgress() == null) {
                playerProgress.beginResearch(research);
            } else {
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are already researching &f" + playerProgress.getCurrentResearch().getName() + "&c!"));
            }
            refresh(currentTier);
        }
    }
}
