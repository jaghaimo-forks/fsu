package scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

import java.util.HashSet;
import java.util.Set;

public class aic_himobility extends BaseHullMod {

	@Override
	public boolean isApplicableToShip(ShipAPI ship) {
		if (!ship.getVariant().hasHullMod("aic_hitec") && !ship.getVariant().hasHullMod("aic_lost_prot")) return false;
		if (ship.getVariant().hasHullMod("aic_hitargeting")) return false;
		return true;
	}
	public String getUnapplicableReason(ShipAPI ship) {

		if (!ship.getVariant().hasHullMod("aic_hitec") && !ship.getVariant().hasHullMod("aic_lost_prot")){
			return "Can only be installed on High-tech Union ships";
		}
		if (ship.getVariant().hasHullMod("aic_hitargeting")){
			return "Only one upgrade can be installed at a time";
		}
		return null;
	}
	private static final Set<String> BLOCKED_HULLMODS = new HashSet<>();
	static
	{
		// These hullmods will automatically be removed
		BLOCKED_HULLMODS.add("aic_hitargeting");
	}
	
	public static float SPEED_FLAT = 10f;
	public static float SPEED_MULT = 12f;
	public static float AGILITY_BONUS = 20f;

	public static float ARMOR_PENALTY = -20f;
	public static float HULL_PENALTY = -5f;
	public static float PPT_PENALTY = -25f;


	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "ONLY";
		if (index == 1) return "" + Math.round((SPEED_FLAT));
		if (index == 2) return "" + Math.round((SPEED_MULT)) + "%";
		if (index == 3) return "" + Math.round((AGILITY_BONUS)) + "%";
		if (index == 4) return "" + Math.round((-1*(ARMOR_PENALTY))) + "%";
		if (index == 5) return "" + Math.round((-1*(HULL_PENALTY))) + "%";
		if (index == 6) return "" + Math.round((-1*(PPT_PENALTY))) + "%";

		return null;
	}
	
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getMaxSpeed().modifyFlat("aic_himobility", SPEED_FLAT);
		stats.getMaxSpeed().modifyPercent("aic_himobility", SPEED_MULT);
		stats.getAcceleration().modifyPercent("aic_himobility", AGILITY_BONUS);
		stats.getTurnAcceleration().modifyPercent("aic_himobility", AGILITY_BONUS);
		stats.getMaxTurnRate().modifyPercent("aic_himobility", AGILITY_BONUS);
		stats.getDeceleration().modifyPercent("aic_himobility", AGILITY_BONUS);

		stats.getArmorBonus().modifyPercent("aic_himobility", ARMOR_PENALTY);
		stats.getHullBonus().modifyPercent("aic_himobility", HULL_PENALTY);
		stats.getPeakCRDuration().modifyPercent("aic_himobility", PPT_PENALTY);


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
