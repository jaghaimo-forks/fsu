package scripts.hullmods;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import java.awt.Color;
import java.util.Set;

import com.fs.starfarer.api.impl.campaign.ids.HullMods;
import com.fs.starfarer.api.util.Misc;

public class aic_fso extends BaseHullMod {
	private static Map speed = new HashMap();
	static {
		speed.put(HullSize.CAPITAL_SHIP, 30f);
	}

	private static final float PEAK_MULT = 0.20f;

	private static final float FLUX_DISSIPATION_MULT = 1.5f;


	private static final float RANGE_THRESHOLD = 700f;
	private static final float RANGE_MULT = 0.25f;

	private static final Set<String> BLOCKED_HULLMODS = new HashSet<>();
	static
	{
		// These hullmods will automatically be removed
		BLOCKED_HULLMODS.add("safetyoverrides");
		BLOCKED_HULLMODS.add("mhmods_hyperengineupgrade");
		BLOCKED_HULLMODS.add("loamt_floodedinjectors");
	}



	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getMaxSpeed().modifyFlat(id, (Float) speed.get(hullSize));
		stats.getAcceleration().modifyFlat(id, (Float) speed.get(hullSize) * 1.5f);
		stats.getDeceleration().modifyFlat(id, (Float) speed.get(hullSize) * 1.5f);

		stats.getFluxDissipation().modifyMult(id, FLUX_DISSIPATION_MULT);

		stats.getPeakCRDuration().modifyMult(id, PEAK_MULT);
		stats.getVentRateMult().modifyMult(id, 0f);

		stats.getWeaponRangeThreshold().modifyFlat(id, RANGE_THRESHOLD);
		stats.getWeaponRangeMultPastThreshold().modifyMult(id, RANGE_MULT);


	}

	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + ((Float) speed.get(HullSize.CAPITAL_SHIP)).intValue();

		if (index == 1) return Misc.getRoundedValue(FLUX_DISSIPATION_MULT);

		if (index == 2) return "5";

		if (index == 3) return Misc.getRoundedValue(RANGE_THRESHOLD);

		if (index == 4) return "ONLY";

		return null;
	}

	@Override
	public boolean isApplicableToShip(ShipAPI ship) {
		if (ship.getVariant().getHullSize() == HullSize.FIGHTER) return false;
		if (ship.getVariant().getHullSize() == HullSize.FRIGATE) return false;
		if (ship.getVariant().getHullSize() == HullSize.DESTROYER) return false;
		if (ship.getVariant().getHullSize() == HullSize.CRUISER) return false;
		if (ship.getVariant().hasHullMod(HullMods.CIVGRADE) && !ship.getVariant().hasHullMod(HullMods.MILITARIZED_SUBSYSTEMS)) return false;
		if (ship.getVariant().hasHullMod("safetyoverrides")) return false;
		if (ship.getVariant().hasHullMod("mhmods_hyperengineupgrade")) return false;
		if (ship.getVariant().hasHullMod("loamt_floodedinjectors")) return false;
		return true;
}


	public String getUnapplicableReason(ShipAPI ship) {
		if (ship.getVariant().getHullSize() == HullSize.FRIGATE) {
			return "Can not be installed on frigates";
		}
		if (ship.getVariant().getHullSize() == HullSize.DESTROYER) {
			return "Can not be installed on destroyers";
		}
		if (ship.getVariant().getHullSize() == HullSize.CRUISER) {
			return "Can not be installed on cruisers";
		}
		if (ship.getVariant().hasHullMod(HullMods.CIVGRADE) && !ship.getVariant().hasHullMod(HullMods.MILITARIZED_SUBSYSTEMS)) {
			return "Can not be installed on civilian ships";
		}
		if (ship.getVariant().hasHullMod("safetyoverrides")) {
			return "Can not be installed with safety overrides";
		}
		if (ship.getVariant().hasHullMod("mhmods_hyperengineupgrade")) {
			return "Can not be installed with hyper engine upgrade";
		}
		if (ship.getVariant().hasHullMod("loamt_floodedinjectors")) {
			return "Can not be installed with flooded injectors";
		}

		return null;
	}

	@Override
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id){
		for (String tmp : BLOCKED_HULLMODS) {
			if (ship.getVariant().getHullMods().contains(tmp)) {
				ship.getVariant().removeMod(tmp);
			}
		}
	}

	private Color color = new Color(185,100,255,255);

	@Override
	public void advanceInCombat(ShipAPI ship, float amount) {
		ship.getEngineController().fadeToOtherColor(this, color, null, 1f, 0.4f);
		ship.getEngineController().extendFlame(this, 0.25f, 0.25f, 0.25f);

	}



}