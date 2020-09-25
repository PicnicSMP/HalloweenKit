/**
 * 
 */
package smp.picnic.halloweenkit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * @author David
 *
 */
public class MobListener implements Listener {
	
	public MobListener () {
		Bukkit.getLogger().info("HalloweenKit:: Setting up Mob Listeners");
	}
	
	@EventHandler()
	public void onCreatureSpawn (CreatureSpawnEvent event) {
		//	Check the entity type, and confirm its a zombie.
		EntityType spawnedEntityType = event.getEntityType();
		if(spawnedEntityType == EntityType.ZOMBIE) {
			Bukkit.getLogger().info("HalloweenKit:: A Zombie has spawned, shall we Pumpkin it?");
		}
	}
}
