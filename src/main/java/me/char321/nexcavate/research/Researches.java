package me.char321.nexcavate.research;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.items.ItemStacks;
import me.char321.nexcavate.items.Items;

public class Researches {
    public static Research RESEARCH_TABLE;
    public static Research RESEARCH_LAB;

    public static Research PART_DRILL;
    public static Research REDISCOVERY_PICKAXE;

    public static Research BASIC_ASSEMBLER;
    public static Research ADVANCED_ASSEMBLER;

    public static Research BLACKSTONE_CAST;
    public static Research PART_EXTRACTOR;
    public static Research PART_EXTRACTOR_2;

    public static Research ITEM_HOLDER;
    public static Research UPGRADE_CORE;

    public static Research CIVILIZATION_CORE;

    public static void init() {
        RESEARCH_TABLE = new Research(Nexcavate.key("research_table"), Items.RESEARCH_TABLE, ItemStacks.RESEARCH_TABLE, 0, 0, 0, 0);
        RESEARCH_TABLE.register();
        RESEARCH_LAB = new Research(Nexcavate.key("research_lab"), Items.RESEARCH_LAB, ItemStacks.RESEARCH_LAB, 1, 0, 100, 1);
        RESEARCH_LAB.register();

        REDISCOVERY_PICKAXE = new Research(Nexcavate.key("rediscovery_pickaxe"), Items.REDISCOVERY_PICKAXE, ItemStacks.REDISCOVERY_PICKAXE, 1, 1, 0, 1);
        REDISCOVERY_PICKAXE.register();
        PART_DRILL = new Research(Nexcavate.key("part_drill"), Items.PART_DRILL, ItemStacks.PART_DRILL, 1, 1, 50, 1);
        PART_DRILL.register();

        BASIC_ASSEMBLER = new Research(Nexcavate.key("basic_assembler"), Items.BASIC_ASSEMBLER, ItemStacks.BASIC_ASSEMBLER, 1, 2, 20, 1);
        BASIC_ASSEMBLER.register();
        ADVANCED_ASSEMBLER = new Research(Nexcavate.key("advanced_assembler"), Items.ADVANCED_ASSEMBLER, ItemStacks.ADVANCED_ASSEMBLER, 2, 2, 200, 1);
        ADVANCED_ASSEMBLER.register();

        BLACKSTONE_CAST = new Research(Nexcavate.key("blackstone_cast"), Items.BLACKSTONE_CAST, ItemStacks.BLACKSTONE_CAST, 1, 3, 15, 1);
        BLACKSTONE_CAST.register();
        PART_EXTRACTOR = new Research(Nexcavate.key("part_extractor"), Items.PART_EXTRACTOR, ItemStacks.PART_EXTRACTOR, 1, 3, 15, 1);
        PART_EXTRACTOR.register();
        PART_EXTRACTOR_2 = new Research(Nexcavate.key("part_extractor_2"), Items.PART_EXTRACTOR_2, ItemStacks.PART_EXTRACTOR_2, 2, 3, 70, 1);
        PART_EXTRACTOR_2.register();

        ITEM_HOLDER = new Research(Nexcavate.key("item_holder"), Items.ITEM_HOLDER, ItemStacks.ITEM_HOLDER, 1, 4, 5, 1);
        ITEM_HOLDER.register();
        UPGRADE_CORE = new Research(Nexcavate.key("upgrade_core"), Items.UPGRADE_CORE, ItemStacks.UPGRADE_CORE, 1, 4, 30, 1);
        UPGRADE_CORE.register();

        CIVILIZATION_CORE = new Research(Nexcavate.key("civilization_core"), Items.CIVILIZATION_CORE, ItemStacks.CIVILIZATION_CORE, 2, 5, 1000, 5);
        CIVILIZATION_CORE.register();
    }
}
