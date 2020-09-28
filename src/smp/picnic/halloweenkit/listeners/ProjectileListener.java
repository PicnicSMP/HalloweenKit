package smp.picnic.halloweenkit.listeners;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import smp.picnic.halloweenkit.Main;
import smp.picnic.halloweenkit.SnowballBat;

public class ProjectileListener implements Listener {
	ArrayList<UUID> halloweenBalls = new ArrayList<UUID>();

	SnowballBat snowballbat;
	
	SnowballBat snowballbatInst = new SnowballBat(snowballbat);
	
	Main main;
	public ProjectileListener(Main instance) {
		this.main = instance;
	}

	@EventHandler()
	public void onLaunch(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
			Player player = (Player) e.getEntity().getShooter();
			if(player.getInventory().getItemInMainHand().isSimilar(snowballbatInst.snowBat()))
					{
					            halloweenBalls.add(e.getEntity().getUniqueId());
					}
		}
		
		
	}
	
	@EventHandler()
	public void onImpact(ProjectileHitEvent e) {
		if(halloweenBalls.contains(e.getEntity().getUniqueId())){
			if (!(e.getHitBlock() == null)) {
				BlockFace hitFace = (BlockFace) e.getHitBlockFace();
				Block relativeBlock = e.getHitBlock().getRelative(hitFace);
				Location loc = relativeBlock.getLocation();
				snowballbatInst.spawnBat(loc);
			}
			else if (!(e.getEntityType() == null)) {
				Location loc = e.getEntity().getLocation();
				snowballbatInst.spawnBat(loc);
			}
		}
	}
}
