package me.char321.nexcavate.command;

import me.char321.nexcavate.Nexcavate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class NexcavateCommand implements CommandExecutor {
    private final List<SubCommand> subcommands = new LinkedList<>();

    public NexcavateCommand(Nexcavate plugin) {
        subcommands.add(new ValidateCommand());
        subcommands.add(new ResearchCommand());

        plugin.getCommand("nexcavate").setTabCompleter(new NexcavateTabCompleter(this));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            for (SubCommand subcmd : subcommands) {
                if (args[0].equalsIgnoreCase(subcmd.getCommandName())) {
                    if (sender.hasPermission("nexcavate.command."+subcmd.getCommandName())) {
                        return subcmd.onExecute(sender, command, label, args);
                    } else {
                        sender.sendMessage("You do not have permission to use this command.");
                        return false;
                    }
                }
            }
            sender.sendMessage("Unknown subcommand! Available subcommands are: " + subcommands.stream().map(SubCommand::getCommandName).collect(Collectors.joining(", ")));
            return false;
        }
        sender.sendMessage("Nexcavate version " + Nexcavate.instance().getDescription().getVersion());
        return true;
    }

    public List<SubCommand> getSubCommands() {
        return subcommands;
    }

}
