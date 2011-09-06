package org.frantictools.franticgod;

public class GodResponder extends Thread {
	private String reply = "";
	private final FranticGod plugin;
	
	public GodResponder(String reply, FranticGod plugin) {
		this.reply = reply;
		this.plugin = plugin;
	}

	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		plugin.godMessage(reply);
	}

}
