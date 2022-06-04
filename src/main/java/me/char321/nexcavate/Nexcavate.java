package me.char321.nexcavate;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.char321.nexcavate.gui.NEGUI;
import me.char321.nexcavate.items.Items;
import me.char321.nexcavate.slimefun.NEItemGroup;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public final class Nexcavate extends JavaPlugin implements SlimefunAddon {
    private static Nexcavate instance;

    public Nexcavate() {
        instance = this;
    }

    public static Nexcavate instance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        NEGUI.init();
        Items.init();
        NEItemGroup.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/qwertyuioplkjhgfd/Nexcavate/issues";
    }

    @Nonnull
    public static NamespacedKey key(@Nonnull String key) {
        return new NamespacedKey(instance, key);
    }

    public static void info(String msg) {
        instance.getLogger().info(msg);
    }
}
