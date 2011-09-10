package org.frantictools.franticgod;

import java.util.Calendar;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class FranticGodPlayerListener extends PlayerListener {

	public static FranticGod plugin;
	private HashMap<String, String> spamMap = new HashMap<String, String>();
	private HashMap<String, Long> timeMap = new HashMap<String, Long>();

	public FranticGodPlayerListener(FranticGod instance) {
		plugin = instance;
	}

	public void onPlayerChat(PlayerChatEvent event) {
		Player sender = event.getPlayer();
		String msg = event.getMessage();
		String name = sender.getName();
		
		try {
			if (spamMap.get(name).equalsIgnoreCase(msg) && (Calendar.getInstance().getTimeInMillis() - timeMap.get(name)) < 1000.0) {
				sender.kickPlayer("We don't appreciate spam here..");
				plugin.getServer().broadcastMessage(ChatColor.RED + name + " tried to spam us.");
				return;
			} else if ((Calendar.getInstance().getTimeInMillis() - timeMap.get(name)) < 300.0) {
				sender.kickPlayer("We don't appreciate spam here.");
				plugin.getServer().broadcastMessage(ChatColor.RED + name + " tried to spam us.");
				return;
			}
		} catch (Exception tits) {
			// Open void
		}
		
		if (msg.matches("([A-Z]| |[1-9]|\\.|!|\\?)*") && msg.length() > 5 && !sender.hasPermission("franticgod.immune")) {
			sender.kickPlayer("Caps Lock is cruise control for cool amirite?");
			return;
		}
	

		spamMap.put(name, msg);
		timeMap.put(name, Calendar.getInstance().getTimeInMillis());

		if (!sender.hasPermission("franticgod.immune")) {
			for (int i = 0; i < plugin.regexList.size(); i++) {
				if (msg.matches(plugin.regexList.get(i))) {
					GodResponder responder = new GodResponder(plugin.replList.get(i), plugin);
					Thread thread = new Thread(responder);
					thread.start();
				}

			}


		}
	}

}

