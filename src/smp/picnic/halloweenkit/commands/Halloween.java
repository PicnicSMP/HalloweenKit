package smp.picnic.halloweenkit.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
