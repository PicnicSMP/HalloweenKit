package smp.picnic.halloweenkit.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import smp.picnic.halloweenkit.Halloween;
import smp.picnic.halloweenkit.HorseConverter;

public class PlayerListener implements Listener {
	
	private Halloween halloweenInst;
	private HorseConverter horseConverter;
	public PlayerListener (Halloween halloweenInst) {
		this.halloweenInst = halloweenInst;
		this.horseConverter = new HorseConverter();
	}
	
	@EventHandler()
	public void onHorseClick(PlayerInteractEntityEvent e) {
		World world = e.getPlayer().getWorld();
		Player player = (Player) e.getPlayer();
		Entity horse = e.getRightClicked();
		
		if (e.getRightClicked().getType() == EntityType.HORSE) {
			
			if (player.getInventory().getItemInMainHand().isSimilar(this.horseConverter.getHorseBone())){ 			 								//Check the item in the players main hand and compare it with ItemStack HorseBone()
				if (player.getGameMode() != GameMode.CREATIVE) { 															//If the players gamemode is not creative remove one from the item stack
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() -1);
				}
				this.horseConverter.convertToSkeletonHorse(horse, player, world);
			}
				
			else if (player.getInventory().getItemInMainHand().isSimilar(this.horseConverter.getHorseFlesh())) {
				if (player.getGameMode() != GameMode.CREATIVE) {
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() -1);
				}
				
				this.horseConverter.convertToZombieHorse(horse, player, world);
				
			}
		}	
	}
	
	@EventHandler()
	public void onEat(PlayerItemConsumeEvent e) {
		if (e.getItem().isSimilar(this.halloweenInst.pumpkinPie())) {
			Player player = (Player) e.getPlayer();
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&o&5You're feeling lighter..."));
			int durationE1 = 20;
			int durationE2 = 20;
			PotionEffect E1 = new PotionEffect(PotionEffectType.INVISIBILITY, 20 * durationE1, 1); 
	        PotionEffect E2 = new PotionEffect(PotionEffectType.GLOWING, 20 * durationE2, 1);
			player.addPotionEffect(E1);
			player.addPotionEffect(E2);
			World world = e.getPlayer().getWorld();
			world.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 5, 1);
		}
	}
}
