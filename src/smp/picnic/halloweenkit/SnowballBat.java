package smp.picnic.halloweenkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class SnowballBat {

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
	
	public void spawnBat(Location loc) {
		World world = loc.getWorld();
		world.playSound(loc, Sound.ENTITY_SPIDER_HURT, 5, 1);
		int deathTime = 3;
		
		Bat bats = (Bat) world.spawnEntity(loc, EntityType.BAT);
		
		bats.setCustomName(ChatColor.GOLD + "Happy Halloween!");
	
		Bukkit.getScheduler().runTaskLater((Plugin) main, new Runnable() {
		 
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
