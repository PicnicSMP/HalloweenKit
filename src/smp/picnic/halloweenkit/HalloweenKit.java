package smp.picnic.halloweenkit;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/*
	Local Imports
*/
import smp.picnic.halloweenkit.listeners.MobListener;
import smp.picnic.halloweenkit.listeners.PlayerListener;
import smp.picnic.halloweenkit.listeners.ProjectileListener;
import smp.picnic.halloweenkit.commands.Halloween;

public class HalloweenKit extends JavaPlugin implements Listener {
	
	
	
	@Override
	public void onEnable() {
		
		this.getCommand("halloween").setExecutor(new Halloween(this));
		
		Bukkit.getServer().getPluginManager().registerEvents(new ProjectileListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MobListener(this), this);
				
	}
	
	
	

	@Override
	public void onDisable() {
		
		
	}

	
	
	public void loadConfig() {
	}
	

	
	public ItemStack pumpkinPie() {
		String itemLore = "Halloween Snack";
		ItemStack pumpkinpie = new ItemStack(Material.PUMPKIN_PIE);
		ItemMeta pumpkinpieMeta = pumpkinpie.getItemMeta();
		List<String> pumpkinpieLore = new ArrayList<String>();
		
				
		pumpkinpieLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + itemLore);
		pumpkinpieMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&6Event&8] &dPumpkin Pie!"));
		pumpkinpieMeta.setLore(pumpkinpieLore);
		pumpkinpie.setItemMeta(pumpkinpieMeta);
		
		return  pumpkinpie;
						
	}
	
	public ItemStack Halloweendiamond() {
		String itemLore = "Happy Halloween!";
		ItemStack diamond = new ItemStack(Material.DIAMOND);
		ItemMeta diamondmeta = diamond.getItemMeta();
		List<String> diamondlore = new ArrayList<String>();
		
		diamondmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&6Event&8] &dHalloween Diamond"));
		diamondlore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + itemLore);
		diamondmeta.setLore(diamondlore);
		diamond.setItemMeta(diamondmeta);
		
		return diamond;
	}


}

