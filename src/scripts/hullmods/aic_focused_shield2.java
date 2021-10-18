package scripts.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.FluxTrackerAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;


public class aic_focused_shield2 extends BaseHullMod {


	public static final float RESISTANCE_BONUS = 0.50f;

	public static String MOD_ICON = "graphics/icons/hullsys/quantum_disruptor.png";
	public static String MOD_BUFFID = "aic_focused_shield2";
	public static String MOD_NAME = "Hardening Shield Projector";


	public String getDescriptionParam(int index, HullSize hullSize) {

		if (index == 0) return "" + Math.round(100f * RESISTANCE_BONUS) + "%";

		if (index == 1) return "" + 0;

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
		float ResBonus = 100f * - ((RESISTANCE_BONUS) * (FluxRatio));

		ship.getMutableStats().getShieldDamageTakenMult().modifyPercent("aic_focused_shield2", ResBonus);
		//tooltip stuff
		float RESISTANCE_BONUS_TT = Math.round(-1 * ResBonus);

		if (ship == Global.getCombatEngine().getPlayerShip()) {

		Global.getCombatEngine().maintainStatusForPlayerShip(MOD_BUFFID, MOD_ICON, MOD_NAME, "Shield damage taken reduced by "+(int) RESISTANCE_BONUS_TT + "%", false);

		}


	}



	@Override
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

	}
}