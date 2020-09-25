/**
 * PumpkinHeadManager, for encapsulating the details of which zombies should be pumpkin-ified and what they drop 
 */
package smp.picnic.halloweenkit;

import java.util.Random;

import org.bukkit.entity.LivingEntity;

/**
 * @author David
 */
public class PumpkinHeadManager {
	
	public static int CONVERSION_RATE = 3;
	
	public boolean attemptConversion(LivingEntity zombie) {
		if(randomSelection()) {
			return true;
		}
		return false;
	}
	
	//	1 in 3 Chance of converting zombie to pumpkin-head
	private boolean randomSelection () {
		Random rand = new Random();
		int randomInt = rand.nextInt(PumpkinHeadManager.CONVERSION_RATE);
		return randomInt == 0;
	}

}
