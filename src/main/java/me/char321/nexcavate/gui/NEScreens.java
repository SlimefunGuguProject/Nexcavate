package me.char321.nexcavate.gui;

import java.util.UUID;

public class NEScreens {
    public final ResearchScreenHandler researchScreen;

    public NEScreens(UUID p) {
        researchScreen = new ResearchScreenHandler(p);
    }
}
