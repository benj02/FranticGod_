package org.frantictools.franticgod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class FranticGod extends JavaPlugin {
	public static final double version = 1.0;
	public static final String godPrefix = ChatColor.DARK_RED + "[" + ChatColor.RED + "God" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + " ";
	private final FranticGodPlayerListener playerListener = new FranticGodPlayerListener(this);
	public Logger log = Logger.getLogger("minecraft");

	public static PermissionHandler permissionHandler;

	public ArrayList<String> regexList = new ArrayList<String>();
	public ArrayList<String> replList = new ArrayList<String>();

	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener,
				Event.Priority.Monitor, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener,
				Event.Priority.Normal, this);

		getCommand("godsays").setExecutor(new GodSaysCommand(this)); // Registering
																		// commands:

		loadFile();
		setupPermissions();

		log.info("FranticGod v: " + version + " is enabled :D");
	}

	private void loadFile() {
		try {
			FileReader file = new FileReader(new File("./plugins/godList.txt"));
			BufferedReader br = new BufferedReader(file);

			String tits;
			while ((tits = br.readLine()) != null) {
				String[] temp = tits.split(";");
				regexList.add(temp[0]);
				replList.add(temp[1]);
			}

			br.close();
			file.close();
		} catch (Exception e) {
			log.info("Error" + e.toString());
			e.printStackTrace();
		}
	}

	public void godMessage(String msg) {
		getServer().broadcastMessage(godPrefix + msg);
	}

	private void setupPermissions() {
		if (permissionHandler != null)
			return;
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

		if (permissionsPlugin == null) {
			log.info("Permission system not detected, defaulting to OP");
			return;
		}

		permissionHandler = ((Permissions) permissionsPlugin).getHandler();
		log.info("Found and will use plugin "
				+ ((Permissions) permissionsPlugin).getDescription().getFullName());
	}

	@Override
	public void onDisable() {
		log.info("FranticGod disabled D: ");

	}

}