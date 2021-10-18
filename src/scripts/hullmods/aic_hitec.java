package scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class aic_hitec extends BaseHullMod {

	@Override
	public boolean isApplicableToShip(ShipAPI ship) {
		return true;
	}
	
	public String getUnapplicableReason(ShipAPI ship) {
		return null;
	}
	
	public static float ENG_DUR_BONUS = 25f;
	public static float ACC_BONUS = 10f;
	public static float FUEL_USE = -10f;


	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + Math.round((ENG_DUR_BONUS)) + "%";
		if (index == 1) return "" + Math.round((ACC_BONUS)) + "%";
		if (index == 2) return "" + Math.round((-1*(FUEL_USE))) + "%";

		return null;
	}
	
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getEngineHealthBonus().modifyPercent("aic_hitec", ENG_DUR_BONUS);
		stats.getAcceleration().modifyPercent("aic_hitec", ACC_BONUS);
		stats.getFuelUseMod().modifyPercent("aic_hitec", FUEL_USE);
	}

	@Override
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id){
	}


}
