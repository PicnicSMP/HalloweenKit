package smp.picnic.halloweenkit.commands;


import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import smp.picnic.halloweenkit.HalloweenKit;
import smp.picnic.halloweenkit.HorseConverter;
import smp.picnic.halloweenkit.SnowballBat;

public class Halloween implements CommandExecutor {

	public static int AMOUNT_SNOWBAT = 4;
	public static int AMOUNT_PUMPKINPIE = 16;
	
	
	String worldName = "world";
	
	HalloweenKit plugin;
	public Halloween(HalloweenKit plugin) {
		this.plugin = plugin;
	}
	
	SnowballBat snowballbatInst = new SnowballBat(this.plugin);
	
	HorseConverter horseconverterInst = new HorseConverter(this.plugin);

    
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		PersistentDataContainer playerdata = player.getPersistentDataContainer();
		
		
		
		
		if (label.equalsIgnoreCase("halloween")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Please join the server to use this command");
				return true;
			}
			
			
			
			if (!(player.hasPermission("halloween.use"))){
				player.sendMessage(ChatColor.RED + "You do not have permission!");
				return true;
			}
			
			if (player.getInventory().firstEmpty() == -1) {
				player.sendMessage(ChatColor.RED + "Your inventory is full!");
				return true;
			}
			
			if (( playerdata.has(new NamespacedKey(plugin, "cooldown"), PersistentDataType.INTEGER)) && (player.hasPermission("halloween.use.nocooldown"))) {
				playerdata.remove(new NamespacedKey(plugin, "cooldown"));
			}
			
			if (playerdata.has(new NamespacedKey(plugin, "cooldown"), PersistentDataType.INTEGER)) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Come back next year !"));
					return true;
				}
			
			
			if (!(player.hasPermission("halloween.use.nocooldown"))){
				playerdata.set(new NamespacedKey(plugin, "cooldown"), PersistentDataType.INTEGER ,  1);
				
				
				
				
				
			}
			
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Happy Halloween!");
			
			ItemStack snowballBats = snowballbatInst.snowBat();
			snowballBats.setAmount(AMOUNT_SNOWBAT);
			player.getInventory().addItem(snowballBats);
			
			ItemStack pumpkinPies = plugin.pumpkinPie();
			pumpkinPies.setAmount(AMOUNT_PUMPKINPIE);
			player.getInventory().addItem(pumpkinPies);
			
			player.getInventory().addItem(horseconverterInst.getHorseBone());
			player.getInventory().addItem(horseconverterInst.getHorseFlesh());
			
			return true;
		}

		return false;
	}


	

	

	
	
	

	
	
	
	
	
}
