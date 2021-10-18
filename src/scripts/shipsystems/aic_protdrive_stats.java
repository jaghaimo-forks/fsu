package scripts.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.util.IntervalUtil;

import java.awt.*;

public class aic_protdrive_stats extends BaseShipSystemScript {

	public static final float SPEED_BONUS = 50f;
	public static final float SPEED_BONUS_FLAT = 30f;
	public static final float BALLISTIC_ROF_BONUS = 30f;
	public static final float BALLISTIC_FLUX_BONUS = -30f;
	public static final float ENERGY_DMG_BONUS = 30f;

	public static final float AGILITY_PENALTY = -50f;

	private static final Color JITTER_COLOR = new Color(179, 59, 59, 255);
	private static final Color AFTERIMAGE_COLOR = new Color(179, 59, 59, 50);

	IntervalUtil Interval = new IntervalUtil(0.5f, 0.5f);
	
	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		ShipAPI ship = null;
		if (stats.getEntity() instanceof ShipAPI) {
			ship = (ShipAPI) stats.getEntity();
		} else {
			return;
		}
		if (Global.getCombatEngine().isPaused() || !ship.isAlive()) {
			return;
		}

		stats.getMaxSpeed().modifyPercent("aic_protdrive_stats", SPEED_BONUS);
		stats.getMaxSpeed().modifyFlat("aic_protdrive_stats", SPEED_BONUS_FLAT);
		stats.getBallisticRoFMult().modifyPercent("aic_protdrive_stats", BALLISTIC_ROF_BONUS);
		stats.getBallisticWeaponFluxCostMod().modifyPercent("aic_protdrive_stats", BALLISTIC_FLUX_BONUS);
		stats.getEnergyWeaponDamageMult().modifyPercent("aic_protdrive_stats", ENERGY_DMG_BONUS);

		stats.getDeceleration().modifyPercent("aic_protdrive_stats", AGILITY_PENALTY);
		stats.getTurnAcceleration().modifyPercent("aic_protdrive_stats", AGILITY_PENALTY);
		stats.getMaxTurnRate().modifyPercent("aic_protdrive_stats", AGILITY_PENALTY);

		//FX

		ship.setJitterUnder(this, JITTER_COLOR, 10f * effectLevel, 8, 2f * effectLevel);
		ship.setJitterShields(false);
		Interval.advance(Global.getCombatEngine().getElapsedInLastFrame());
		if (Interval.intervalElapsed()) {
			ship.addAfterimage(AFTERIMAGE_COLOR, 0, 0 , 2f, -0.5f, 5f, 0.1f, 0f, 0.8f, true, false, false);
		}

		if (state == State.OUT) {
			unapply(stats, "aic_protdrive_stats");

			stats.getMaxSpeed().unmodify("aic_protdrive_stats");
			stats.getMaxSpeed().unmodify("aic_protdrive_stats");
			stats.getBallisticRoFMult().unmodify("aic_protdrive_stats");
			stats.getBallisticWeaponFluxCostMod().unmodify("aic_protdrive_stats");
			stats.getEnergyWeaponDamageMult().unmodify("aic_protdrive_stats");

			stats.getDeceleration().unmodify("aic_protdrive_stats");
			stats.getTurnAcceleration().unmodify("aic_protdrive_stats");
			stats.getMaxTurnRate().unmodify("aic_protdrive_stats");
		}

	}
	public void unapply(MutableShipStatsAPI stats, String id) {
		stats.getMaxSpeed().unmodify(id);
		stats.getMaxSpeed().unmodify(id);
		stats.getBallisticRoFMult().unmodify(id);
		stats.getBallisticWeaponFluxCostMod().unmodify(id);
		stats.getEnergyWeaponDamageMult().unmodify(id);

		stats.getDeceleration().unmodify(id);
		stats.getTurnAcceleration().unmodify(id);
		stats.getMaxTurnRate().unmodify(id);
	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		if (index == 0) {
			return new StatusData("All weapons improved", false);
		}
		if (index == 1) {
			return new StatusData("Top seed increased", false);
		}
		if (index == 2) {
			return new StatusData("Maneuverability reduced", true);
		}


		return null;
	}
}
