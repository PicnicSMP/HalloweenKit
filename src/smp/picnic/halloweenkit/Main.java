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
		Halloween halloweenInst = new Halloween(this);
		Bukkit.getServer().getPluginManager().registerEvents(halloweenInst, this);
		Bukkit.getServer().getPluginManager().registerEvents(new MobListener(halloweenInst), this);
		
		this.getCommand("halloween").setExecutor(halloweenInst);		
	}
	
	
	

	@Override
	public void onDisable() {

	}


}

