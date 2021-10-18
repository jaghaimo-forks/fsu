package scripts.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.FluxTrackerAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;

import java.awt.*;

public class aic_overrofstats extends BaseShipSystemScript {

	public static final float ROF_BONUS = 20f;
	public static final float FLUX_REDUCTION = -10f;
	public static final float TIME_DILATION = 1.20f;
	public static final float TIME_DILATION_BONUS = 0.8f;

	private static final Color JITTER_COLOR = new Color(255, 208, 0, 255);

	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		ShipAPI ship = null;
		boolean player = false;
		if (stats.getEntity() instanceof ShipAPI) {
			ship = (ShipAPI) stats.getEntity();
			player = ship == Global.getCombatEngine().getPlayerShip();
			id = id + "_" + ship.getId();
		} else {
			return;
		}
		if (Global.getCombatEngine().isPaused() || !ship.isAlive()) {
			return;
		}
		FluxTrackerAPI fluxTracker = ship.getFluxTracker();
		float Fluxed = ((fluxTracker.getCurrFlux() + 1) / fluxTracker.getMaxFlux());

		float RofMult = ROF_BONUS + (Fluxed * ROF_BONUS);
		float FluxMult = FLUX_REDUCTION + (Fluxed * FLUX_REDUCTION);
		float TimeMult = TIME_DILATION + (Fluxed * TIME_DILATION_BONUS);

		stats.getTimeMult().modifyMult(id, TimeMult);
		if (player) {
			Global.getCombatEngine().getTimeMult().modifyMult(id, 1f / TimeMult);
		} else {
			Global.getCombatEngine().getTimeMult().unmodify(id);
		}

		stats.getBallisticRoFMult().modifyPercent(id, RofMult);
		stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FluxMult);

		ship.setJitterUnder(this, JITTER_COLOR, 10f * effectLevel, 8, 2f * effectLevel);
		ship.setJitterShields(false);

		if (ship.getSystem().getState() == ShipSystemAPI.SystemState.OUT){
			Global.getCombatEngine().getTimeMult().unmodify(id);
			stats.getTimeMult().unmodify(id);
			stats.getBallisticRoFMult().unmodify(id);
			stats.getBallisticWeaponFluxCostMod().unmodify(id);
		}
	}

	public void unapply(MutableShipStatsAPI stats, String id) {
		Global.getCombatEngine().getTimeMult().unmodify(id);
		stats.getTimeMult().unmodify(id);
		stats.getBallisticRoFMult().unmodify(id);
		stats.getBallisticWeaponFluxCostMod().unmodify(id);
	}

	public StatusData getStatusData(int index, State state, float effectLevel) {

		if (index == 0) {
			return new StatusData("ballistic rof increased", false);
		}
		if (index == 1) {
			return new StatusData("ballistic flux use reduced", false);
		}
		if (index == 2) {
			return new StatusData("Time sped up", false);
		}
		return null;
	}
}




