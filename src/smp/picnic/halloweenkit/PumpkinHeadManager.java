/**
 * PumpkinHeadManager, for encapsulating the details of which zombies should be pumpkin-ified and what they drop 
 */
package smp.picnic.halloweenkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

/**
 * @author David
 */
public class PumpkinHeadManager {
	
	public static int CONVERSION_RATE = 3;
	public static int DROP_RATE_MIN = 0;
	public static int DROP_RATE_MAX = 2;
	
	public static int BAT_RATE_MIN = 3;
	public static int BAT_RATE_MAX = 7;
	
	private HalloweenKit plugin;
	
	private SnowballBat snowballbatInst;
	
	private NamespacedKey namespacedKey;
	
	public PumpkinHeadManager (HalloweenKit plugin) {
		this.plugin = plugin;
		this.snowballbatInst = new SnowballBat(plugin);
		this.namespacedKey = new NamespacedKey(plugin, "PumpkinHeadManager");
	}
	

	public boolean attemptConversion(LivingEntity zombie) {
		if(randomSelection()) {
			convertZombie( zombie );
			return true;
		}
		return false;
	}
	
	public boolean isJackOHat(EntityDeathEvent event) {
		PersistentDataContainer zData = event.getEntity().getPersistentDataContainer();
		return zData.has(this.namespacedKey, PersistentDataType.INTEGER);
	}
	
	public void setCustomDrops(EntityDeathEvent event) {
		
		List<ItemStack> drops = event.getDrops();
		Random rand = new Random();
		
		// Set PumpkinPie Drop Count, offset by MIN drop rate.
		int randomInt = Math.max(rand.nextInt(PumpkinHeadManager.DROP_RATE_MAX + 1), PumpkinHeadManager.DROP_RATE_MIN);
		if(randomInt != 0) {
			ItemStack pies  = this.plugin.pumpkinPie();
			pies.setAmount(randomInt);
			drops.add(pies);
		}
		
		Location loc = event.getEntity().getLocation();
		randomInt = Math.max(rand.nextInt(PumpkinHeadManager.BAT_RATE_MAX + 1), PumpkinHeadManager.BAT_RATE_MIN);
		
		while(--randomInt > 0) {
			snowballbatInst.spawnBat(loc);
		}
	}
	
	private void convertZombie (LivingEntity zombie) {
		PersistentDataContainer zData = zombie.getPersistentDataContainer();
		zData.set(this.namespacedKey, PersistentDataType.INTEGER, 1);
		
		EntityEquipment zombiesStuff = zombie.getEquipment();
		zombiesStuff.clear();
		
		zombiesStuff.setHelmet(getJackOHat());
		zombiesStuff.setHelmetDropChance(0.3F);
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
