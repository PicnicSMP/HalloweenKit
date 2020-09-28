package smp.picnic.halloweenkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import smp.picnic.halloweenkit.listeners.PlayerListener;

public class HorseConverter {
	
	Halloween halloweenInst;
	
	public HorseConverter(Halloween instance) {
		this.halloweenInst = instance;
	}
	
	PlayerListener playerlistenerInst;
	
	public HorseConverter(PlayerListener instance) {
		this.playerlistenerInst = instance;
	}
	
	
	
	public ItemStack getHorseBone() {
		String itemLore = "Right Click me on a horse";
		ItemStack HorseBone = new ItemStack(Material.BONE);
		ItemMeta HorseBoneMeta = HorseBone.getItemMeta();
		
		List<String> HorseBoneLore = new ArrayList<String>();
		
		HorseBoneMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&6Event&8] &8&lCursed Bone"));
		HorseBoneLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + itemLore );
		HorseBoneMeta.setLore(HorseBoneLore);
		HorseBone.setItemMeta(HorseBoneMeta);

		return HorseBone;
	}
	
	public ItemStack getHorseFlesh() {
		String itemLore = "Right Click me on a horse";
		ItemStack HorseFlesh = new ItemStack(Material.ROTTEN_FLESH);
		ItemMeta HorseFleshMeta = HorseFlesh.getItemMeta();
		
		List<String> HorseFleshLore = new ArrayList<String>();
		
		HorseFleshMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&6Event&8] &8&lCursed Flesh"));
		HorseFleshLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + itemLore );
		HorseFleshMeta.setLore(HorseFleshLore);
		HorseFlesh.setItemMeta(HorseFleshMeta);

		return HorseFlesh;
	}
	
	public void convertToSkeletonHorse(Entity horse, Player player, World world){
		
			Location loc = horse.getLocation();
			horse.remove();																								//Remove old horse
			SkeletonHorse newHorse = (SkeletonHorse) world.spawnEntity(loc, EntityType.SKELETON_HORSE);					//Spawn Skeleton horse at location of old horse
			if (horse.getCustomName() != null) { newHorse.setCustomName(horse.getCustomName()); }						//Check if the old horse had a name, if it did, put it on the new one
			
			world.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 5, 1);
			world.spawnParticle(Particle.FLAME, loc, 10);
			newHorse.setOwner(player);																					//Make the player owner of the horse
			newHorse.setTamed(true);																					//Make the horse tamed
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&oThis horse looks different.."));
			
	}
	
	
	public void convertToZombieHorse(Entity horse, Player player, World world){
		Location loc = horse.getLocation();
		horse.remove();
		ZombieHorse newHorse = (ZombieHorse) world.spawnEntity(loc, EntityType.ZOMBIE_HORSE);
	
		if (horse.getCustomName() != null) { newHorse.setCustomName(horse.getCustomName()); }
		world.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 5, 1);
		world.spawnParticle(Particle.FLAME, loc, 10);
		newHorse.setOwner(player);
		newHorse.setTamed(true);
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&oThis horse looks different.."));
	}
	
	
	
	
	
	
	
	
}
