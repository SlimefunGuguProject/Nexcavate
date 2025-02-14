package me.char321.nexcavate.research.progress;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.research.Research;
import me.char321.nexcavate.research.Researches;
import me.char321.nexcavate.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerProgress {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeHierarchyAdapter(NamespacedKey.class, new NamespacedKeyAdapter())
            .registerTypeAdapter(Research.class, new ResearchAdapter())
            .create();

    private transient UUID player;
    @SerializedName("completed_tutorial")
    private boolean completedTutorial;
    private List<NamespacedKey> researches = new ArrayList<>();
    private ResearchProgress currentResearchProgress;

    public static PlayerProgress load(File f, UUID player) {
        try {
            PlayerProgress res = GSON.fromJson(new BufferedReader(new FileReader(f)), PlayerProgress.class);
            res.player = player;
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return new PlayerProgress(player);
        }
    }

    public static PlayerProgress get(Player player) {
        return get(player.getUniqueId());
    }

    public static PlayerProgress get(UUID player) {
        return Nexcavate.instance().getProgressManager().get(player);
    }

    public PlayerProgress(UUID player) {
        this.player = player;

        //unlocked by default
        researches.add(Researches.RESEARCH_TABLE.getKey());
    }

    public List<NamespacedKey> getResearches() {
        return researches;
    }

    public void research(Research research) {
        research(research.getKey());
    }

    public void research(NamespacedKey research) {
        if (!researches.contains(research)) {
            researches.add(research);
        }
    }

    public void setCompletedTutorial(boolean completedTutorial) {
        this.completedTutorial = completedTutorial;
    }

    public void save() throws IOException {
        String json = GSON.toJson(this);
        File progressFolder = new File(Nexcavate.instance().getDataFolder(), "/progress");
        File f = new File(progressFolder, player +".json");
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            if (!f.createNewFile()) {
                throw new IOException("Could not create file " + f.getPath());
            }
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f));
        bufferedWriter.write(json);
        bufferedWriter.close();
    }

    public boolean isResearched(Research research) {
        return isResearched(research.getKey());
    }

    public boolean isResearched(NamespacedKey research) {
        return researches.contains(research);
    }

    public ResearchProgress getCurrentResearchProgress() {
        return currentResearchProgress;
    }

    public Research getCurrentResearch() {
        return currentResearchProgress == null ? null : currentResearchProgress.getResearch();
    }

    public void beginResearch(Research research) {
        if (currentResearchProgress != null) {
            return;
        }

        currentResearchProgress = new ResearchProgress(research, System.currentTimeMillis());
        Bukkit.getScheduler().runTaskLaterAsynchronously(Nexcavate.instance(), () -> Bukkit.getScheduler().runTask(Nexcavate.instance(), this::completeCurrentResearch), research.getTime() * 20L * 60L);
    }

    public void completeCurrentResearch() {
        if (currentResearchProgress != null) {
            researches.add(currentResearchProgress.research.getKey());
            currentResearchProgress = null;

            notifyPlayer();
        }
    }

    private void notifyPlayer() {
        Player p = Bukkit.getPlayer(this.player);
        if (p != null) {
            p.sendMessage(Utils.color("&a您当前的研究已经完成！请打开Slimefun指南或研究站查看配方"));
        }
    }

    public void tryCompleteCurrentResearch() {
        if (currentResearchProgress == null) {
            return;
        }
        if (System.currentTimeMillis() > currentResearchProgress.startTime + (currentResearchProgress.research.getTime() * 60L * 1000L)) {
            completeCurrentResearch();
        }
    }

    public boolean hasCompletedTutorial() {
        return completedTutorial;
    }

    public static class ResearchProgress {
        private final Research research;
        private final long startTime;

        public ResearchProgress(Research research, long startTime) {
            this.research = research;
            this.startTime = startTime;
        }

        public Research getResearch() {
            return research;
        }

        public long getStartTime() {
            return startTime;
        }

        public String currentProgress() {
            double time = (System.currentTimeMillis() - startTime) / 60000.0;
            return String.format("%.2f", time) + " / " + research.getTime() + " minutes (" + String.format("%.2f", time / research.getTime() * 100.0) +"%)";
        }
    }
}
