package smp.picnic.halloweenkit;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
/*
	Local Imports
*/
import smp.picnic.halloweenkit.listeners.MobListener;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new Halloween(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MobListener(), this);
		
		this.getCommand("halloween").setExecutor(new Halloween(this));		
	}
	
	
	

	@Override
	public void onDisable() {

	}


}

