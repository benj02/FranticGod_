package org.frantictools.franticgod;

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
		plugin.log.info("oncommand called");
		if (cmd.getName().equalsIgnoreCase("godsays")) {
			if (args != null) {
				String msg = FranticGod.godPrefix;
				for (String x : args)
					msg += x + ' ';
				plugin.getServer().broadcastMessage(msg);
				return true;
			}
		}
		
		return false;
	}

}
