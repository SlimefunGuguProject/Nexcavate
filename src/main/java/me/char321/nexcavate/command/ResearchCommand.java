package me.char321.nexcavate.command;

import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.research.Research;
import me.char321.nexcavate.research.progress.PlayerProgress;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class ResearchCommand implements SubCommand {
    @Override
    public boolean onExecute(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        PlayerProgress playerProgress = Nexcavate.instance().getProgressManager().get(player);
        for (Research research : Nexcavate.instance().getRegistry().getResearches()) {
            playerProgress.research(research);
        }
        return true;
    }

    @Nonnull
    @Override
    public String getCommandName() {
        return "research";
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}
