package smp.picnic.halloweenkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Halloween implements CommandExecutor, Listener {

	String worldName = "world";
	
	public void onImpact(Main Plugin) {
	}

	Map<String, Long> cooldowns = new HashMap<String, Long>();
    
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
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
				cooldowns.put(player.getName(), System.currentTimeMillis() + (500 * 1000));
			}
			
			int Amount_snowBat = 5;
			int Amount_pumpkinPie = 16;
			
			for (int i = 0; i < Amount_snowBat; i++) {
			player.getInventory().addItem(snowBat());
			}
			for (int i = 0; i < Amount_pumpkinPie; i++) {
			player.getInventory().addItem(pumpkinPie());
			}
			
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
	
	public ItemStack snowBat() {
		String itemLore = "Throw me!";
		ItemStack snowbat = new ItemStack(Material.SNOWBALL);
		ItemMeta snowbatMeta = snowbat.getItemMeta();
		List<String> snowbatLore = new ArrayList<String>();
		
		snowbatMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&6Event&8] &dBat Snowball"));
		snowbatLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + itemLore);
		snowbatMeta.setLore(snowbatLore);
		snowbat.setItemMeta(snowbatMeta);
		
		return snowbat;
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
	
	@EventHandler()
	public void onEat(PlayerItemConsumeEvent e) {
		if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&8[&6Event&8] &dPumpkin Pie!"))) {
			Player player = (Player) e.getPlayer();
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&o&5You're feeling lighter..."));
			int durationE1 = 20;
			int durationE2 = 20;
			PotionEffect E1 = new PotionEffect(PotionEffectType.INVISIBILITY, 20 * durationE1, 1); 
	        PotionEffect E2 = new PotionEffect(PotionEffectType.GLOWING, 20 * durationE2, 1);
			player.addPotionEffect(E1);
			player.addPotionEffect(E2);
			World world = (World) Bukkit.getWorld(worldName);
			world.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 5, 1);
		}
	}
	
	
	
	ArrayList<UUID> halloweenBalls = new ArrayList<UUID>();

	@EventHandler()
	public void onLaunch(ProjectileLaunchEvent e) {
		Player player = (Player) e.getEntity().getShooter();
		if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&8[&6Event&8] &dBat Snowball")))
				{
				            halloweenBalls.add(e.getEntity().getUniqueId());
				}
		
	}
	
	@EventHandler()
	public void onImpact(ProjectileHitEvent e) {
		if(halloweenBalls.contains(e.getEntity().getUniqueId())){
			BlockFace hitFace = (BlockFace) e.getHitBlockFace();
			Block relativeBlock = e.getHitBlock().getRelative(hitFace);
			Location loc = relativeBlock.getLocation();
			World world = (World) Bukkit.getWorld(worldName);
			world.playSound(loc, Sound.ENTITY_SPIDER_HURT, 5, 1);
			int deathTime = 3;
			
			Bat bats = (Bat) world.spawnEntity(loc, EntityType.BAT);
			
			bats.setCustomName(ChatColor.GOLD + "Happy Halloween!");
		
			Bukkit.getScheduler().runTaskLater(smp.picnic.halloweenkit.Main.getPlugin(), new Runnable() {
			 
				@Override
				public void run() {
					world.dropItem(bats.getLocation(), Halloweendiamond());
					world.playSound(bats.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 10, 1);
					world.spawnParticle(Particle.FIREWORKS_SPARK, bats.getLocation(), 5);
					bats.setHealth(0.0);
				}
		 
			}, 20*deathTime);
		}
			
			
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
