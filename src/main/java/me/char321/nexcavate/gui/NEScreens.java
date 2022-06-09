package me.char321.nexcavate.gui;

import java.util.UUID;

public class NEScreens {
    public final ResearchScreenHandler researchScreen;
    public final StructureScreenHandler structureScreen3;
    public final StructureScreenHandler structureScreen4;
    public final StructureScreenHandler structureScreen5;
    public final StructureScreenHandler structureScreen6;

    public NEScreens(UUID p) {
        researchScreen = new ResearchScreenHandler(p);
        structureScreen3 = new StructureScreenHandler(3);
        structureScreen4 = new StructureScreenHandler(4);
        structureScreen5 = new StructureScreenHandler(5);
        structureScreen6 = new StructureScreenHandler(6);
    }
}
