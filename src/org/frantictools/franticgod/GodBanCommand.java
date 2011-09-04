package org.frantictools.franticgod;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodBanCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private FranticGod plugin;
	public GodBanCommand(FranticGod plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("godban")) {
			if (sender.hasPermission("franticgod.godban")) {
				FranticGod.godBanMap.put(((Player) sender).getName(), !(FranticGod.godBanMap.get(((Player) sender).getName())));
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
				return true;
			}
		}
		return false;
	}
}
