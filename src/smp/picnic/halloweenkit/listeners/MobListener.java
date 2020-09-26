/**
 * 
 */
package smp.picnic.halloweenkit.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import smp.picnic.halloweenkit.Halloween;
import smp.picnic.halloweenkit.PumpkinHeadManager;

/**
 * @author David
 *
 */
public class MobListener implements Listener {
	private PumpkinHeadManager pumpkinHeadManager;
	public MobListener (Halloween halloweenInst) {
		pumpkinHeadManager = new PumpkinHeadManager(halloweenInst);
	}
	
	@EventHandler()
	public void onCreatureSpawn (CreatureSpawnEvent event) {
		//	Check the entity type, and confirm its a zombie.
		EntityType spawnedEntityType = event.getEntityType();
		if(spawnedEntityType == EntityType.ZOMBIE) {
			pumpkinHeadManager.attemptConversion(event.getEntity());
		}
	}
	
	@EventHandler()
	public void onEntityDeath (EntityDeathEvent event) {
		//	Check the entity type, and confirm its a zombie.
		EntityType deadEntityType = event.getEntityType();
		if(deadEntityType == EntityType.ZOMBIE) {
			if(pumpkinHeadManager.hasJackOHat(event)){
				pumpkinHeadManager.setCustomDrops(event);
			}
		}
	}
}
