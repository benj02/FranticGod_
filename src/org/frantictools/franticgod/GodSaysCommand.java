package org.frantictools.franticgod;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GodSaysCommand implements CommandExecutor {
	private final FranticGod plugin;
	
	public GodSaysCommand(FranticGod plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("godsays")) {
			if (sender.hasPermission("franticgod.godsays") || sender.isOp()) {
				if (args != null) {
					String msg = "";
					for (String x : args)
						msg += x + ' ';
					plugin.godMessage(msg);
					return true;
				} else {
					return false;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "I'm sorry Dave, but I can't let you do that.");
			}
		}
		
		return false;
	}

}
