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

public class ProjectileListener implements Listener {
	ArrayList<UUID> halloweenBalls = new ArrayList<UUID>();

	@EventHandler()
	public void onLaunch(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
			Player player = (Player) e.getEntity().getShooter();
			if(player.getInventory().getItemInMainHand().isSimilar(snowBat()))
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
				spawnBat(loc);
			}
			else if (!(e.getEntityType() == null)) {
				Location loc = e.getEntity().getLocation();
				spawnBat(loc);
			}
		}
	}
}
