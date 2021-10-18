package scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

import java.util.HashSet;
import java.util.Set;

public class aic_hitargeting extends BaseHullMod {

	@Override
	public boolean isApplicableToShip(ShipAPI ship) {
		if (!ship.getVariant().hasHullMod("aic_hitec") && !ship.getVariant().hasHullMod("aic_lost_prot")) return false;
		if (ship.getVariant().hasHullMod("aic_himobility")) return false;
		return true;
	}
	public String getUnapplicableReason(ShipAPI ship) {

		if (!ship.getVariant().hasHullMod("aic_hitec") && !ship.getVariant().hasHullMod("aic_lost_prot")){
			return "Can only be installed on High-tech Union ships";
		}
		if (ship.getVariant().hasHullMod("aic_himobility")){
			return "Only one upgrade can be installed at a time";
		}
		return null;
	}
	private static final Set<String> BLOCKED_HULLMODS = new HashSet<>();
	static
	{
		// These hullmods will automatically be removed
		BLOCKED_HULLMODS.add("aic_himobility");
	}

	public static float PEN_BONUS = 10f;
	public static float PROJ_SPEED_BONUS = 25f;
	public static float MISSILE_BONUS = 10f;
	public static float SENSOR_BONUS = 30f;

	public static float FLUX_PENALTY = -10f;
	public static float DURA_PENALTY = -25f;
	public static float MALFUNCTION_PENALTY = -40f;



	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "ONLY";
		if (index == 1) return "" + Math.round((PEN_BONUS)) + "%";
		if (index == 2) return "" + Math.round((PROJ_SPEED_BONUS)) + "%";
		if (index == 3) return "" + Math.round((MISSILE_BONUS)) + "%";
		if (index == 4) return "" + Math.round((SENSOR_BONUS)) + "%";
		if (index == 5) return "" + Math.round((-1*(FLUX_PENALTY))) + "%";
		if (index == 6) return "" + Math.round((-1*(DURA_PENALTY))) + "%";
		if (index == 7) return "" + Math.round((-1*(MALFUNCTION_PENALTY))) + "%";

		return null;
	}


	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getHitStrengthBonus().modifyPercent("aic_hitargeting", PEN_BONUS);
		stats.getProjectileSpeedMult().modifyPercent("aic_hitargeting", PROJ_SPEED_BONUS);
		stats.getMissileMaxSpeedBonus().modifyPercent("aic_hitargeting", MISSILE_BONUS);
		stats.getMissileAccelerationBonus().modifyPercent("aic_hitargeting", MISSILE_BONUS*3);
		stats.getMissileMaxTurnRateBonus().modifyPercent("aic_hitargeting", MISSILE_BONUS);
		stats.getMissileTurnAccelerationBonus().modifyPercent("aic_hitargeting", MISSILE_BONUS*2);
		stats.getSensorStrength().modifyPercent("aic_hitargeting", SENSOR_BONUS);

		stats.getFluxCapacity().modifyPercent("aic_hitargeting", FLUX_PENALTY);
		stats.getWeaponHealthBonus().modifyPercent("aic_hitargeting", DURA_PENALTY);
		stats.getEngineHealthBonus().modifyPercent("aic_hitargeting", DURA_PENALTY);
		stats.getWeaponMalfunctionChance().modifyPercent("aic_hitargeting", MALFUNCTION_PENALTY);
		stats.getEngineMalfunctionChance().modifyPercent("aic_hitargeting", MALFUNCTION_PENALTY);


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
