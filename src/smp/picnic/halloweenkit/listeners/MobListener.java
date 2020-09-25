/**
 * 
 */
package smp.picnic.halloweenkit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import smp.picnic.halloweenkit.PumpkinHeadManager;

/**
 * @author David
 *
 */
public class MobListener implements Listener {
	private PumpkinHeadManager pumpkinHeadManager;
	public MobListener () {
		Bukkit.getLogger().info("HalloweenKit:: Setting up Mob Listeners");
		pumpkinHeadManager = new PumpkinHeadManager();
	}
	
	@EventHandler()
	public void onCreatureSpawn (CreatureSpawnEvent event) {
		//	Check the entity type, and confirm its a zombie.
		EntityType spawnedEntityType = event.getEntityType();
		if(spawnedEntityType == EntityType.ZOMBIE) {
			Bukkit.getLogger().info("HalloweenKit:: A Zombie has spawned, shall we Pumpkin it?");
			if(pumpkinHeadManager.attemptConversion(event.getEntity())) {
				Bukkit.getLogger().info("HalloweenKit:: Converting Zombie?");
			}
		}
	}
	
	@EventHandler()
	public void onEntityDeath (EntityDeathEvent event) {
		//	Check the entity type, and confirm its a zombie.
		EntityType deadEntityType = event.getEntityType();
		if(deadEntityType == EntityType.ZOMBIE) {
			Bukkit.getLogger().info("HalloweenKit:: A Zombie has been killed, was it a Pumpkin head?");
		}
	}
}
