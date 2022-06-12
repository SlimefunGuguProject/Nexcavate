package me.char321.nexcavate.items.machines;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.char321.nexcavate.items.ItemStacks;
import me.char321.nexcavate.slimefun.NEAssembly;
import me.char321.nexcavate.structure.Structure;
import me.char321.nexcavate.util.Utils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class PartExtractor extends NEAssembly implements MachineProcessHolder<CraftingOperation>, EnergyNetComponent {
    private static final int[] BORDER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
    private static final int[] BORDER_IN = { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
    private static final int[] BORDER_OUT = { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
    private static final int[] INPUT_SLOTS = {19, 20};
    private static final int[] OUTPUT_SLOTS = {24, 25};
    private static final String PROGRESS_PATH = "nexcavate.progress";

    private MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);
    private int capacity;
    private int consumption;
    private int period;

    public PartExtractor(ItemStack item, String id, Structure assembly, int capacity, int consumption, int period) {
        super(item, id, assembly);

        this.capacity = capacity;
        this.consumption = consumption;
        this.period = period;
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(new BlockPlaceHandler(true) {
            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                if (!e.getBlock().getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                    e.getPlayer().sendMessage(ChatColor.RED + "This machine won't work here. Try somewhere in the Nether perhaps?");
                    e.setCancelled(true);
                    Utils.removeBlock(e.getBlock(), true);
                }
            }
        }, new SimpleBlockBreakHandler() {
            @Override
            public void onBlockBreak(@Nonnull Block b) {
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), INPUT_SLOTS);
                    inv.dropItems(b.getLocation(), OUTPUT_SLOTS);
                }
            }
        }, new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                BlockMenu inv = BlockStorage.getInventory(b);
                CraftingOperation currentOperation = processor.getOperation(b);

                if (data.contains(PROGRESS_PATH)) {
                    int value = Integer.parseInt((String) data.getValue(PROGRESS_PATH));
                    if (value == -1) {
                        if (consumeItem(inv, new ItemStack(Material.BLACKSTONE))) {
                            data.setValue(PROGRESS_PATH, "0");
                        }
                    } else if (getCharge(b.getLocation()) >= consumption) {
                        removeCharge(b.getLocation(), consumption);
                        int newvalue = value + 1;
                        if (newvalue >= period) {
                            data.setValue(PROGRESS_PATH, "-1");
                            inv.pushItem(ItemStacks.ANCIENT_PART.clone(), OUTPUT_SLOTS);
                        } else {
                            data.setValue(PROGRESS_PATH, String.valueOf(newvalue));
                        }
                    }

                    updateProgress(inv, b, value);
                } else {
                    data.setValue(PROGRESS_PATH, "-1");
                }
            }
        });
    }

    private boolean consumeItem(BlockMenu inv, ItemStack itemStack) {
        int count = itemStack.getAmount();
        for (int inputSlot : INPUT_SLOTS) {
            ItemStack slotItem = inv.getItemInSlot(inputSlot);
            if (SlimefunUtils.isItemSimilar(slotItem, itemStack, true, false)) {
                int remove = Math.min(slotItem.getAmount(), count);
                slotItem.setAmount(slotItem.getAmount() - remove);
                count -= remove;
                if (count == 0) {
                    return true;
                }
            }
        }
        inv.pushItem(new CustomItemStack(itemStack, itemStack.getAmount() - count));
        return false;
    }

    private void updateProgress(BlockMenu inv, Block b, int value) {
        if (value == -1) {
            inv.addItem(22, ItemStacks.Menu.BLACK);
        } else {
            ChestMenuUtils.updateProgressbar(inv, 22, period - value, period, new ItemStack(Material.FLINT_AND_STEEL));
        }
    }

    @Override
    public void postRegister() {
        super.postRegister();

        new BlockMenuPreset(getId(), getItemName()) {

            @Override
            public void init() {
                for (int i : BORDER) {
                    addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
                }

                for (int i : BORDER_IN) {
                    addItem(i, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
                }

                for (int i : BORDER_OUT) {
                    addItem(i, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
                }

                addItem(22, ItemStacks.Menu.BLACK, ChestMenuUtils.getEmptyClickHandler());
            }

            @Override
            public boolean canOpen(@Nonnull Block b, @Nonnull Player p) {
                return true;
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow.equals(ItemTransportFlow.WITHDRAW)) {
                    return OUTPUT_SLOTS;
                } else {
                    return INPUT_SLOTS;
                }
            }
        };
    }

    @Nonnull
    @Override
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }


    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
