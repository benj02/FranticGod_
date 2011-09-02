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
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Event;

@SuppressWarnings("unused")
public class FranticGod extends JavaPlugin {
	public static final double version = 1.0;
	public static final String godPrefix = ChatColor.DARK_RED + "[" + ChatColor.RED + "God" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + " ";
	private final FranticGodPlayerListener playerListener = new FranticGodPlayerListener(this);
	public Logger log = Logger.getLogger("minecraft");

//	public static final String[] regexArray = new String[] {
//			"(?i).*?(Why).*?(Do).*?(have).*?(register|registration).*?",  						//When are we registering
//			"(?i).*?(How).*?(Do).*?(register|registration).*?",
//			"(?i).*?(When|Why).*?(Bukkit).*?(update|updating|updated).*?",						//When are we updating
//			"(?i).*?(When|Why).*?(1\\.8).*?",							 						//When are we going to 1.8
//			"(?i).*?(Using|uses|used).*?(Fly *(hack|mod)).*?",									//x is using flyhack
//			"^(?=.*?\bfly ?(hack|mod)\b)((?=.*?\bcan\b)|(?=.*?\buse\b)|(?=.*?\ballow(ed)?\b)).*$",		//can i use a flyhack
//			"(?i).*?(lag|lagging).*?",															//lag
//			"(?i).*?avo(lition)*.*?",															//team avo
//			"(?i)t/.*?",																		//fail command
//			"(?i).*?(didn'?t|did ?not).*?(e-?mail).*?",											//didnt get email
//			"(?i).*?(can).*?(build).*?",														//can i build
//			"^[a-z]",																			//all caps
//			"(?i).*?(How|can).*?(private|lock|protect).*?",							//cprivate
//			"(?i).*?(i('m)?).*?(bored).*?",											//bored
//			"(?i).*?(can).*?(spawn).*?(item(s?)|thing(s?)).*?",					//can spawn items
//			"(?i).*?(how).*?(set).*?(home|spawn).*?",							//how to set home
//			"(?i).*?(lost).*?(home|house).*?",									//lost my home
//			"(?i).*?(x-?ray).*?",												//x-ray
//			"(?i).*?(can).*?(make).*?(day).*?",									//can make it day
//	}; 

	public ArrayList<String> regexList = new ArrayList<String>(); 
	public ArrayList<String> replList = new ArrayList<String>(); 
	
//	static String mainDirectory = "plugins/FranticGod"; 
//	static File regexList = new File(mainDirectory + File.separator + "regexList.cfg");
//	static File replList = new File(mainDirectory + File.separator + "replList.cfg"); 

	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
		
		getCommand("godsays").setExecutor(new GodSaysCommand(this));       // Registering commands:

		//loadFile();
		// I'm too lazy to make files work, so i'm hard-coding regexs. 
		log.info("FranticGod v: " + version + " is enabled :D");
	}

	private void loadFile() {
		try {
			FileReader file = new FileReader("godList.txt");
			BufferedReader br = new BufferedReader(file);
			
			String[] temp = br.readLine().split(";");
			regexList.add(temp[0]);
			replList.add(temp[1]);
		    
			br.close();
			file.close();
		} catch (Exception e) {
			log.info("Error" + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		log.info("FranticGod disabled D: ");

	}


}