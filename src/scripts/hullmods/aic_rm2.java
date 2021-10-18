package scripts.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.FluxTrackerAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;


public class aic_rm2 extends BaseHullMod {

	public static final float ROF_BONUS = 40f;
	public static final float PROJ_PENALTY = -25f;

	public static String MOD_ICON = "graphics/icons/hullsys/ammo_feeder.png";
	public static String MOD_BUFFID = "aic_rm2";
	public static String MOD_NAME = "Flux Ammofeeders";

	public String getDescriptionParam(int index, HullSize hullSize) {

		if (index == 0) return "" + Math.round(ROF_BONUS) + "%";

		if (index == 1) return "" + 0;

		if (index == 2) return "" + Math.round((-1) * PROJ_PENALTY) + "%";

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
		float RofBonus =((ROF_BONUS) - ((ROF_BONUS) * (FluxRatio)));
		//apply math
		ship.getMutableStats().getBallisticRoFMult().modifyPercent("aic_rm2", RofBonus);
		ship.getMutableStats().getProjectileSpeedMult().modifyPercent("aic_rm2", PROJ_PENALTY);
		//tooltip stuff
		float ROF_BONUS_TT = Math.round(RofBonus);

		if (ship == Global.getCombatEngine().getPlayerShip()) {

		Global.getCombatEngine().maintainStatusForPlayerShip(MOD_BUFFID, MOD_ICON, MOD_NAME, "Ballistics rof increased by "+(int) ROF_BONUS_TT + "%", false);

		}

	}



	@Override
	public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

	}
}