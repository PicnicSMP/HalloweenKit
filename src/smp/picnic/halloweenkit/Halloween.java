package smp.picnic.halloweenkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Halloween implements CommandExecutor, Listener {

	String worldName = "world";
	private final smp.picnic.halloweenkit.Main main;


	public Halloween(smp.picnic.halloweenkit.Main main) {
		this.main = main;
	}

	Map<String, Long> cooldowns = new HashMap<String, Long>();
    
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int cooldown = 500;
		
		if (label.equalsIgnoreCase("halloween")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Please join the server to use this command");
				return true;
			}
			
			Player player = (Player) sender;
			
			if (!(player.hasPermission("halloween.use"))){
				player.sendMessage(ChatColor.RED + "You do not have permission!");
				return true;
			}
			
			if (player.getInventory().firstEmpty() == -1) {
				player.sendMessage(ChatColor.RED + "Your inventory is full!");
				return true;
			}
			
			if (cooldowns.containsKey(player.getName()) && (player.hasPermission("halloween.use.nocooldown"))) {
				cooldowns.remove(player.getName());
			}
			
			if (cooldowns.containsKey(player.getName())) {
				if (cooldowns.get(player.getName()) > System.currentTimeMillis()){
					long timeleft = (cooldowns.get(player.getName()) - System.currentTimeMillis())/1000;
					player.sendMessage(ChatColor.RED + "You can use this command again in " + timeleft + " seconds");
					return true;
				}
			}
			
			if (!(player.hasPermission("halloween.use.nocooldown"))){
				cooldowns.put(player.getName(), System.currentTimeMillis() + (cooldown * 1000));
			}
			
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Happy Halloween!");
			
			int Amount_snowBat = 5;
			int Amount_pumpkinPie = 16;
			
			for (int i = 0; i < Amount_snowBat; i++) {
			player.getInventory().addItem(snowBat());
			}
			for (int i = 0; i < Amount_pumpkinPie; i++) {
			player.getInventory().addItem(pumpkinPie());
			}
			
			player.getInventory().addItem(getHorseBone());
			player.getInventory().addItem(HorseFlesh());
			
			return true;
		}

		return false;
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
