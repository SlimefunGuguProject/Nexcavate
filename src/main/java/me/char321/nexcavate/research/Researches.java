package me.char321.nexcavate.research;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.items.Items;

public class Researches {
    public static Research RESEARCH_TABLE;
    public static Research RESEARCH_LAB;
    public static void init() {
        RESEARCH_TABLE = new Research(Nexcavate.key("RESEARCH_TABLE"), Items.RESEARCH_TABLE, Items.RESEARCH_TABLE.getItem(), 0, 0, 0, 0);
        RESEARCH_TABLE.register();
        RESEARCH_LAB = new Research(Nexcavate.key("RESEARCH_LAB"), Items.RESEARCH_LAB, Items.RESEARCH_LAB.getItem(), 1, 0, 1, 1);
        RESEARCH_LAB.register();
    }
}
