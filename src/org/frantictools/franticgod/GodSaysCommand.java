package org.frantictools.franticgod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodSaysCommand implements CommandExecutor {
	private final FranticGod plugin;
	
	public GodSaysCommand(FranticGod plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		plugin.log.info("oncommand called");
		if (commandLabel.equalsIgnoreCase("godsays")) {
			if (((Player) sender).hasPermission("franticgod.godsays") || sender.isOp()) {
				if (args != null) {
					String msg = "";
					for (String x : args)
						msg += x + ' ';
					plugin.godMessage(msg);
					return true;
				}
			}
		}
		
		return false;
	}

}
