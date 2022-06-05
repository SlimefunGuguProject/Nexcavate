package me.char321.nexcavate.research;

import me.char321.nexcavate.Nexcavate;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Research {
    private final NamespacedKey key;
    private final ItemStack display;
    private final int tier;
    private final int category;
    private final int cost;
    private final int time;

    /**
     *
     * @param key
     * @param display
     * @param tier
     * @param category
     * @param cost
     * @param time in minutes
     */
    public Research(NamespacedKey key, ItemStack display, int tier, int category, int cost, int time) {
        this.key = key;
        this.display = display;
        this.tier = tier;
        this.category = category;
        this.cost = cost;
        this.time = time;
    }

    public ItemStack getDisplay() {
        return display;
    }

    /**
     *
     * @return the minimum tier required to show this research in a table
     */
    public int getTier() {
        return tier;
    }

    public void register() {
        Nexcavate.instance().getRegistry().getResearches().add(this);
    }

    public int getCategory() {
        return category;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public int getCost() {
        return cost;
    }

    public int getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Research research = (Research) o;
        return key.equals(research.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public String getName() {
        return display.getItemMeta().getDisplayName();
    }
}
