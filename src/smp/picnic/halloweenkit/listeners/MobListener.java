/**
 * 
 */
package smp.picnic.halloweenkit.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import smp.picnic.halloweenkit.HalloweenKit;
import smp.picnic.halloweenkit.PumpkinHeadManager;

/**
 * @author David
 *
 */
public class MobListener implements Listener {
	
	private PumpkinHeadManager pumpkinHeadManagerInst;
	
	public MobListener (HalloweenKit plugin) {
		pumpkinHeadManagerInst = new PumpkinHeadManager(plugin);
	}
		
	@EventHandler()
	public void onCreatureSpawn (CreatureSpawnEvent event) {
		//	Check the entity type, and confirm its a zombie.
		EntityType spawnedEntityType = event.getEntityType();
		if(spawnedEntityType == EntityType.ZOMBIE) {
			pumpkinHeadManagerInst.attemptConversion(event.getEntity());
		}
	}
	
	@EventHandler()
	public void onEntityDeath (EntityDeathEvent event) {
		//	Check the entity type, and confirm its a zombie.
		EntityType deadEntityType = event.getEntityType();
		if(deadEntityType == EntityType.ZOMBIE) {
			if(pumpkinHeadManagerInst.isJackOHat(event)){
				pumpkinHeadManagerInst.setCustomDrops(event);
			}
		}
	}
}
