package me.Unweptpit.HalloweenPicnic;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin implements Listener {
	private static Plugin plugin;

	@Override
	public void onEnable() {

		PluginManager pm = getServer().getPluginManager();
		
		this.getCommand("halloween").setExecutor(new Halloween());
		Halloween halloweenListeners = new Halloween();
		
		pm.registerEvents(halloweenListeners, this);
		
		plugin = this;
	}
	
	
	

	@Override
	public void onDisable() {

	}

	public static Plugin getPlugin() {
	    return plugin;
	  }


}

