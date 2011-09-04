package org.frantictools.franticgod;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Properties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Event;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

@SuppressWarnings("unused")
public class FranticGod extends JavaPlugin {
	public static final double version = 1.0;
	public static final String godPrefix = ChatColor.DARK_RED + "[" + ChatColor.RED + "God" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + " ";
	private final FranticGodPlayerListener playerListener = new FranticGodPlayerListener(this);
	public Logger log = Logger.getLogger("minecraft");
	
	public static PermissionHandler permissionHandler;
	
	public static HashMap<String, Boolean> godBanMap = new HashMap<String, Boolean>(); 

	public ArrayList<String> regexList = new ArrayList<String>(); 
	public ArrayList<String> replList = new ArrayList<String>(); 
	
//	static String mainDirectory = "plugins/FranticGod"; 
//	static File regexList = new File(mainDirectory + File.separator + "regexList.cfg");
//	static File replList = new File(mainDirectory + File.separator + "replList.cfg"); 

	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Monitor, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
		
		
		getCommand("godsays").setExecutor(new GodSaysCommand(this));       // Registering commands:
		//getCommand("godban").setExecutor(new GodBanCommand(this));

		loadFile();

		log.info("FranticGod v: " + version + " is enabled :D");
	}

	private void loadFile() {
		try {
			FileReader file = new FileReader(new File("godList.txt"));
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
	
	private void setupPermissions() {
		if (permissionHandler != null)
			return;
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

		if (permissionsPlugin == null) {
		    log.info("Permission system not detected, defaulting to OP");
		    return;
		}

		permissionHandler = ((Permissions) permissionsPlugin).getHandler();
		log.info("Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName());
	}
	
	public void godMessage(String msg) {
		getServer().broadcastMessage(godPrefix + msg);
	}

	@Override
	public void onDisable() {
		log.info("FranticGod disabled D: ");

	}


}