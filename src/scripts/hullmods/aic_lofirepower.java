package scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

import java.util.HashSet;
import java.util.Set;

public class aic_lofirepower extends BaseHullMod {

	@Override
	public boolean isApplicableToShip(ShipAPI ship) {
		if (!ship.getVariant().hasHullMod("aic_lotec") && !ship.getVariant().hasHullMod("aic_lux") && !ship.getVariant().hasHullMod("aic_rm")) return false;
		if (ship.getVariant().hasHullMod("aic_lodurability")) return false;
		return true;
	}
	public String getUnapplicableReason(ShipAPI ship) {

		if (!ship.getVariant().hasHullMod("aic_lotec") && !ship.getVariant().hasHullMod("aic_lux") && !ship.getVariant().hasHullMod("aic_rm")) {
			return "Can only be installed on Low-tech Union ships";
		}
		if (ship.getVariant().hasHullMod("aic_lodurability")){
			return "Only one upgrade can be installed at a time";
		}
		return null;
	}
	private static final Set<String> BLOCKED_HULLMODS = new HashSet<>();
	static
	{
		// These hullmods will automatically be removed
		BLOCKED_HULLMODS.add("aic_lodurability");
	}

	public static float RANGE_BONUS = 8f;
	public static float ROF_BONUS = 10f;
	public static float BONUS_DMG = 25f;

	public static float SPEED_PENALTY_FLAT = -5f;
	public static float SPEED_PENALTY_MULT = -10f;




	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "ONLY";
		if (index == 1) return "" + Math.round((RANGE_BONUS)) + "%";
		if (index == 2) return "" + Math.round((ROF_BONUS)) + "%";
		if (index == 3) return "" + Math.round((BONUS_DMG)) + "%";
		if (index == 4) return "" + Math.round((-1*(SPEED_PENALTY_FLAT)));
		if (index == 5) return "" + Math.round((-1*(SPEED_PENALTY_MULT))) + "%";

		return null;
	}


	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getBallisticWeaponRangeBonus().modifyPercent("aic_lofirepower", RANGE_BONUS);
		stats.getBallisticRoFMult().modifyPercent("aic_lofirepower", ROF_BONUS);
		stats.getMissileRoFMult().modifyPercent("aic_lofirepower", ROF_BONUS);
		stats.getDamageToFighters().modifyPercent("aic_lofirepower", BONUS_DMG);
		stats.getDamageToMissiles().modifyPercent("aic_lofirepower", BONUS_DMG);

		stats.getMaxSpeed().modifyFlat("aic_himobility", SPEED_PENALTY_FLAT);
		stats.getMaxSpeed().modifyPercent("aic_himobility", SPEED_PENALTY_MULT);

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