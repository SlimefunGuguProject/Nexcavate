package me.char321.nexcavate.research;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.items.ItemStacks;
import me.char321.nexcavate.items.Items;

public class Researches {
    public static Research RESEARCH_TABLE;
    public static Research RESEARCH_LAB;

    public static Research REDISCOVERY_PICKAXE;

    public static Research BASIC_ASSEMBLER;

    public static void init() {
        RESEARCH_TABLE = new Research(Nexcavate.key("RESEARCH_TABLE"), Items.RESEARCH_TABLE, ItemStacks.RESEARCH_TABLE, 0, 0, 0, 0);
        RESEARCH_TABLE.register();
        RESEARCH_LAB = new Research(Nexcavate.key("RESEARCH_LAB"), Items.RESEARCH_LAB, ItemStacks.RESEARCH_LAB, 1, 0, 100, 1);
        RESEARCH_LAB.register();

        REDISCOVERY_PICKAXE = new Research(Nexcavate.key("REDISCOVERY_PICKAXE"), Items.REDISCOVERY_PICKAXE, ItemStacks.REDISCOVERY_PICKAXE, 1, 1, 0, 1);
        REDISCOVERY_PICKAXE.register();

        BASIC_ASSEMBLER = new Research(Nexcavate.key("BASIC_ASSEMBLER"), Items.BASIC_ASSEMBLER, ItemStacks.BASIC_ASSEMBLER, 1, 2, 20, 1);
        BASIC_ASSEMBLER.register();
    }
}
