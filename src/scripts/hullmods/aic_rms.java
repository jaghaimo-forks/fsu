package scripts.hullmods;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.FluxTrackerAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import java.util.HashSet;
import java.util.Set;

public class aic_rms extends BaseHullMod {

	private static Map mag = new HashMap();

	static {
		mag.put(HullSize.FIGHTER, -25f);
		mag.put(HullSize.FRIGATE, -25f);
		mag.put(HullSize.DESTROYER, -25f);
		mag.put(HullSize.CRUISER, -25f);
		mag.put(HullSize.CAPITAL_SHIP, -25f);
	}
	private static final Color JITTER_COLOR = new Color(93, 59, 179, 75);
	private static final Color JITTER_UNDER_COLOR = new Color(93, 59, 179, 190);

	public static final float ROF_BONUS = 20f;
	public static final float PROJ_PENALTY = -25f;



	private static final Set<String> BLOCKED_HULLMODS = new HashSet<>();
	static {

		// These hullmods will automatically be removed
		BLOCKED_HULLMODS.add("frontshield");

	}

	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + ((Float) mag.get(HullSize.FRIGATE)).intValue() + "%";
		if (index == 1) return "" + ((Float) mag.get(HullSize.DESTROYER)).intValue() + "%";
		if (index == 2) return "" + ((Float) mag.get(HullSize.CRUISER)).intValue() + "%";
		if (index == 3) return "" + ((Float) mag.get(HullSize.CAPITAL_SHIP)).intValue() + "%";

		if (index == 4) return "" + ROF_BONUS + "%";

		if (index == 5) return "" + 0;

		if (index == 6) return "" + Math.round((-1) * PROJ_PENALTY) + "%";

		return null;
	}


	@Override
	public boolean isApplicableToShip(ShipAPI ship) {
		return false;
	}

	@Override
	public void advanceInCombat(ShipAPI ship, float amount) {
		if (Global.getCombatEngine().isPaused() || !ship.isAlive()) {
			return;
		}


		//do the math for the effects
		FluxTrackerAPI fluxTracker = ship.getFluxTracker();
		float FluxRatio = ((fluxTracker.getCurrFlux() + 1) / fluxTracker.getMaxFlux());
		float DrBonus = -100f + (100f + ((Float) mag.get(ship.getHullSize())) + ( -1 * (((Float) mag.get(ship.getHullSize())) * (FluxRatio))));
		float RofBonus =((ROF_BONUS) - ((ROF_BONUS) * (FluxRatio)));
		float JitterFx = 2 + - (2*(FluxRatio));
		float JitterFx2 = 10 + - (10*(FluxRatio));
		//apply math
		ship.getMutableStats().getArmorDamageTakenMult().modifyPercent("aic_rms", DrBonus);
		ship.getMutableStats().getHullDamageTakenMult().modifyPercent("aic_rms", DrBonus);
		ship.getMutableStats().getBallisticRoFMult().modifyPercent("aic_rms", RofBonus);
		ship.getMutableStats().getProjectileSpeedMult().modifyPercent("aic_rms", PROJ_PENALTY);


		//make jitter fx scale with flux too
		ship.setJitter("aic_rms", JITTER_COLOR, JitterFx, 1, 3.5f);
		ship.setJitterUnder("aic_rm", JITTER_UNDER_COLOR, JitterFx2, 10, 1.5f);


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