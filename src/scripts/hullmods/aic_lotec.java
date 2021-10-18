package scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class aic_lotec extends BaseHullMod {

	@Override
	public boolean isApplicableToShip(ShipAPI ship) {
		return true;
	}

	public String getUnapplicableReason(ShipAPI ship) {
		return null;
	}

	public static float WEP_DUR_BONUS = 25f;
	public static float FRAG_DMG = -10f;

	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + Math.round((WEP_DUR_BONUS)) + "%";
		if (index == 1) return "" + Math.round((-1*(FRAG_DMG))) + "%";

		return null;
	}


	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getWeaponHealthBonus().modifyPercent("aic_lotec", WEP_DUR_BONUS);
		stats.getFragmentationDamageTakenMult().modifyPercent("aic_lotec", FRAG_DMG);
	}

	@Override
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id){
	}


}