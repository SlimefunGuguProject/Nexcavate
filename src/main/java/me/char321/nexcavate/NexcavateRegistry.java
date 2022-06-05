package me.char321.nexcavate;

import me.char321.nexcavate.items.stations.ResearchStation;
import me.char321.nexcavate.research.Research;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NexcavateRegistry {
    private Map<Integer, ResearchStation> researchStations = new HashMap<>();
    private List<Research> researches = new ArrayList<>();

    public Map<Integer, ResearchStation> getResearchStations() {
        return researchStations;
    }

    public ResearchStation getResearchStation(int tier) {
        return researchStations.get(tier);
    }

    public List<Research> getResearches() {
        return researches;
    }
}
