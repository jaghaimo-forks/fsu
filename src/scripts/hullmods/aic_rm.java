package scripts.hullmods;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.FluxTrackerAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import java.util.HashSet;
import java.util.Set;

public class aic_rm extends BaseHullMod {

	public static String MOD_ICON = "graphics/icons/hullsys/damper_field.png";
	public static String MOD_BUFFID = "aic_rm";
	public static String MOD_NAME = "Revolutionary Modifications";

	private static Map mag = new HashMap();

	static {
		mag.put(HullSize.FIGHTER, -35f);
		mag.put(HullSize.FRIGATE, -35f);
		mag.put(HullSize.DESTROYER, -30f);
		mag.put(HullSize.CRUISER, -25f);
		mag.put(HullSize.CAPITAL_SHIP, -20f);
	}
	private static Map man = new HashMap();

	static {
		man.put(HullSize.FIGHTER, -50f);
		man.put(HullSize.FRIGATE, -50f);
		man.put(HullSize.DESTROYER, -40f);
		man.put(HullSize.CRUISER, -35f);
		man.put(HullSize.CAPITAL_SHIP, -25f);
	}

	private static final Color JITTER_COLOR = new Color(93, 59, 179, 55);
	private static final Color JITTER_UNDER_COLOR = new Color(93, 59, 179, 190);




	private static final Set<String> BLOCKED_HULLMODS = new HashSet<>();
	static {

		// These hullmods will automatically be removed
		BLOCKED_HULLMODS.add("frontshield");

	}
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getDeceleration().modifyPercent(id, (Float) man.get(hullSize));
		stats.getTurnAcceleration().modifyPercent(id, (Float) man.get(hullSize));
		stats.getMaxTurnRate().modifyPercent(id, (Float) man.get(hullSize));
	}

	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + -1*((Float) mag.get(HullSize.FRIGATE)).intValue() + "%";
		if (index == 1) return "" + -1*((Float) mag.get(HullSize.DESTROYER)).intValue() + "%";
		if (index == 2) return "" + -1*((Float) mag.get(HullSize.CRUISER)).intValue() + "%";
		if (index == 3) return "" + -1*((Float) mag.get(HullSize.CAPITAL_SHIP)).intValue() + "%";

		if (index == 4) return "" + 0;

		if (index == 5) return "" + -1*((Float) man.get(HullSize.FRIGATE)).intValue() + "%";
		if (index == 6) return "" + -1*((Float) man.get(HullSize.DESTROYER)).intValue() + "%";
		if (index == 7) return "" + -1*((Float) man.get(HullSize.CRUISER)).intValue() + "%";
		if (index == 8) return "" + -1*((Float) man.get(HullSize.CAPITAL_SHIP)).intValue() + "%";

		if (index == 9) return "PREVENTS";


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
		float JitterFx = 2 + - (2*(FluxRatio));
		float JitterFx2 = 10 + - (10*(FluxRatio));
		//apply math
		ship.getMutableStats().getArmorDamageTakenMult().modifyPercent("aic_rm", DrBonus);
		ship.getMutableStats().getHullDamageTakenMult().modifyPercent("aic_rm", DrBonus);

		//make jitter fx scale with flux too
		ship.setJitter("aic_rm", JITTER_COLOR, JitterFx, 1, 2.0f);
		ship.setJitterUnder("aic_rm", JITTER_UNDER_COLOR, JitterFx2, 10, 1.5f);
		//tooltip stuff
		float RESISTANCE_BONUS_TT = Math.round(-1f * DrBonus);

		if (ship == Global.getCombatEngine().getPlayerShip()) {

		Global.getCombatEngine().maintainStatusForPlayerShip(MOD_BUFFID, MOD_ICON, MOD_NAME, "Damage taken reduced by "+(int) RESISTANCE_BONUS_TT + "%", false);

		}

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