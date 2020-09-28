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
import smp.picnic.halloweenkit.Main;
import smp.picnic.halloweenkit.PumpkinHeadManager;

/**
 * @author David
 *
 */
public class MobListener implements Listener {
	Halloween halloween;
	PumpkinHeadManager pumpkinHeadManagerInst = new PumpkinHeadManager(halloween);
	
	public MobListener (PumpkinHeadManager instance) {
		pumpkinHeadManagerInst = instance;
	}
	
	Main main;
	public MobListener(Main instance) {
		this.main = instance;
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
			if(pumpkinHeadManagerInst.hasJackOHat(event)){
				pumpkinHeadManagerInst.setCustomDrops(event);
			}
		}
	}
}
