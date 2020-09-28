package smp.picnic.halloweenkit;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
/*
	Local Imports
*/
import smp.picnic.halloweenkit.listeners.MobListener;
import smp.picnic.halloweenkit.listeners.PlayerListener;
import smp.picnic.halloweenkit.listeners.ProjectileListener;

public class Main extends JavaPlugin implements Listener {

	
	@Override
	public void onEnable() {
		Halloween halloweenInst = new Halloween(this);
		this.getCommand("halloween").setExecutor(halloweenInst);

		Bukkit.getServer().getPluginManager().registerEvents(new ProjectileListener(halloweenInst), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(halloweenInst), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MobListener(halloweenInst), this);
		
				
	}
	
	
	

	@Override
	public void onDisable() {

	}



}

