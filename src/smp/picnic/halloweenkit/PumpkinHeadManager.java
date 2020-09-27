/**
 * PumpkinHeadManager, for encapsulating the details of which zombies should be pumpkin-ified and what they drop 
 */
package smp.picnic.halloweenkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


/**
 * @author David
 */
public class PumpkinHeadManager {
	
	public static int CONVERSION_RATE = 3;
	public static int DROP_RATE_MIN = 0;
	public static int DROP_RATE_MAX = 2;
	
	public static int BAT_RATE_MIN = 3;
	public static int BAT_RATE_MAX = 7;
	
	private Halloween halloweenInst;
	
	public PumpkinHeadManager (Halloween halloweenInst) {
		this.halloweenInst = halloweenInst;
	}
	
	public boolean attemptConversion(LivingEntity zombie) {
		if(randomSelection()) {
			convertZombie( zombie );
			return true;
		}
		return false;
	}
	
	public boolean hasJackOHat(EntityDeathEvent event) {
		List<ItemStack> drops = event.getDrops();
		boolean found = false;
		int i = drops.size();
		while(!found && i > 0) {
			ItemStack item = drops.get(--i);
			found = (item.hasItemMeta() && item.getItemMeta().getDisplayName().contains("Jack'O'Hat"));
		}
		return found;
	}
	
	public void setCustomDrops(EntityDeathEvent event) {
		List<ItemStack> drops = event.getDrops();
		Random rand = new Random();
		
		// Set PumpkinPie Drop Count, offset by MIN drop rate.
		int randomInt = Math.max(rand.nextInt(PumpkinHeadManager.DROP_RATE_MAX + 1), PumpkinHeadManager.DROP_RATE_MIN);
		if(randomInt != 0) {
			ItemStack pies  = this.halloweenInst.pumpkinPie();
			pies.setAmount(randomInt);
			drops.add(pies);
		}
		
		Location loc = event.getEntity().getLocation();
		randomInt = Math.max(rand.nextInt(PumpkinHeadManager.BAT_RATE_MAX + 1), PumpkinHeadManager.BAT_RATE_MIN);
		while(--randomInt > 0) {
			this.halloweenInst.spawnBat(loc);
		}
	}
	
	private void convertZombie (LivingEntity zombie) {
		EntityEquipment zombiesStuff = zombie.getEquipment();
		zombiesStuff.clear();
		
		zombiesStuff.setHelmet(getJackOHat());
		zombiesStuff.setHelmetDropChance(1.0F);
	}
	
	//	Generate the halloween Jack'O'Hat for out Zombies
	private ItemStack getJackOHat() {
		ItemStack jackOHat = new ItemStack(Material.JACK_O_LANTERN);
		ItemMeta jackOHatMeta = jackOHat.getItemMeta();
		List<String> jackOHatLore = new ArrayList<String>();
		
		jackOHatLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Jack'O'Hat");
		jackOHatMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8[&6Event&8] &dJack'O'Hat"));
		jackOHatMeta.setLore(jackOHatLore);
		jackOHat.setItemMeta(jackOHatMeta);
		
		return jackOHat;
	}
	
	//	1 in 3 Chance of converting zombie to pumpkin-head
	private boolean randomSelection () {
		Random rand = new Random();
		int randomInt = rand.nextInt(PumpkinHeadManager.CONVERSION_RATE);
		return randomInt == 0;
	}

	

}
