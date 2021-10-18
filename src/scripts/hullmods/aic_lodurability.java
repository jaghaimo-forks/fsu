package scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

import java.util.HashSet;
import java.util.Set;

public class aic_lodurability extends BaseHullMod {

	@Override
	public boolean isApplicableToShip(ShipAPI ship) {
		if (!ship.getVariant().hasHullMod("aic_lotec") && !ship.getVariant().hasHullMod("aic_lux") && !ship.getVariant().hasHullMod("aic_rm")) return false;
		if (ship.getVariant().hasHullMod("aic_lofirepower")) return false;
		return true;
	}
	public String getUnapplicableReason(ShipAPI ship) {

		if (!ship.getVariant().hasHullMod("aic_lotec") && !ship.getVariant().hasHullMod("aic_lux") && !ship.getVariant().hasHullMod("aic_rm")) {
			return "Can only be installed on Low-tech Union ships";
		}
		if (ship.getVariant().hasHullMod("aic_lofirepower")){
			return "Only one upgrade can be installed at a time";
		}
		return null;
	}
	private static final Set<String> BLOCKED_HULLMODS = new HashSet<>();
	static
	{
		// These hullmods will automatically be removed
		BLOCKED_HULLMODS.add("aic_lofirepower");
	}

	public static float EXPLO_BONUS = 20f;
	public static float DURA_BONUS = 50f;
	public static float HULL_BONUS = 12f;
	public static float CREW_LOSS = -30f;

	public static float MANU_PENALTY = -30f;




	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "ONLY";
		if (index == 1) return "" + Math.round((EXPLO_BONUS)) + "%";
		if (index == 2) return "" + Math.round((-1*(CREW_LOSS))) + "%";
		if (index == 3) return "" + Math.round((DURA_BONUS)) + "%";
		if (index == 4) return "" + Math.round((HULL_BONUS)) + "%";

		if (index == 5) return "" + Math.round((-1*(MANU_PENALTY))) + "%";

		return null;
	}


	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getHighExplosiveDamageTakenMult().modifyPercent("aic_lodurability", EXPLO_BONUS);
		stats.getWeaponHealthBonus().modifyPercent("aic_lodurability", DURA_BONUS);
		stats.getEngineHealthBonus().modifyPercent("aic_lodurability", DURA_BONUS);
		stats.getHullBonus().modifyPercent("aic_lodurability", HULL_BONUS);
		stats.getCrewLossMult().modifyPercent("aic_lodurability", CREW_LOSS);

		stats.getAcceleration().modifyPercent("aic_himobility", MANU_PENALTY);
		stats.getTurnAcceleration().modifyPercent("aic_himobility", MANU_PENALTY);
		stats.getMaxTurnRate().modifyPercent("aic_himobility", MANU_PENALTY);
		stats.getDeceleration().modifyPercent("aic_himobility", MANU_PENALTY);

	}

	@Override
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

		for (String tmp : BLOCKED_HULLMODS) {
			if (ship.getVariant().getHullMods().contains(tmp)) {
				ship.getVariant().removeMod(tmp);
			}
		}
	}

}