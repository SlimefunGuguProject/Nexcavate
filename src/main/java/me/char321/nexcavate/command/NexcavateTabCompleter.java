package me.char321.nexcavate.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class NexcavateTabCompleter implements TabCompleter {
    private final NexcavateCommand basecmd;

    public NexcavateTabCompleter(NexcavateCommand cmd) {
        this.basecmd = cmd;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> list = new LinkedList<>();
            for (SubCommand subcmd : basecmd.getSubCommands()) {
                if (subcmd.getCommandName().contains(args[0].toLowerCase(Locale.ROOT))) {
                    list.add(subcmd.getCommandName());
                }
            }
            return list;
        } else if (args.length > 1) {
            for (SubCommand subcmd : basecmd.getSubCommands()) {
                if (subcmd.getCommandName().equals(args[0])) {
                    return subcmd.onTabComplete(sender, command, alias, args);
                }
            }
        }
        return Collections.emptyList();
    }
}
