package scripts.hullmods;

import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class aic_focused_shield extends BaseHullMod {

	public static final float ENRAGE_PPT_PENALTY = -100f;
	public static final float ENRAGE_SPEED_BONUS = 35f;
	public static final float ENRAGE_SPEED_BONUS_FLAT = 20f;
	public static final float ENRAGE_BALLISTIC_ROF_BONUS = 35f;
	public static final float ENRAGE_BALLISTIC_FLUX_BONUS = -35f;
	public static final float ENRAGE_PROJ_SPEED_BONUS = 40f;
	public static final float ENRAGE_ENERGY_DMG_BONUS = 35f;
	public static final float ENRAGE_FLUX_MULTI_BONUS = 40f;

	private static final Color JITTER_COLOR = new Color(179, 59, 59, 255);
	private static final Color JITTER_UNDER_COLOR = new Color(179, 59, 95, 255);

	public static String MOD_ICON = "graphics/icons/hullsys/fortress_shield.png";
	public static String MOD_BUFFID = "aic_focused_shield";
	public static String MOD_NAME = "Adaptive Shield Projector";

	private static final String SOUND_ID = "aic_prot_warning";

	private IntervalUtil interval = new IntervalUtil(3.0f, 3.0f);

	private static boolean DoOnce = true;
	private static boolean DoOnce2 = true;

	private static final Set<String> BLOCKED_HULLMODS = new HashSet<>();
	static {

		// These hullmods will automatically be removed
		BLOCKED_HULLMODS.add("extendedshieldemitter");
		BLOCKED_HULLMODS.add("frontemitter");
		BLOCKED_HULLMODS.add("swp_shieldbypass");

	}

	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + 270;

		if (index == 1) return "" + 0;

		if (index == 2) return "PREVENTS";

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
		//apply math
		final float SIZE_BONUS = Math.round(360 + -((270) * (FluxRatio)));
		ship.getShield().setArc(SIZE_BONUS);
		//make shield color change with flux too
		final Color SHIELD_COLOR = new Color(255, Math.round(251 + -((250) * (FluxRatio))), Math.round(251 + -((250) * (FluxRatio))), 255);
		ship.getShield().setInnerColor(SHIELD_COLOR);
		//tooltip stuff
		float SIZE_BONUS_TT = Math.round(SIZE_BONUS);

		if (ship == Global.getCombatEngine().getPlayerShip()) {

			Global.getCombatEngine().maintainStatusForPlayerShip(MOD_BUFFID, MOD_ICON, MOD_NAME, "Shield size increased to " + (int) SIZE_BONUS_TT, false);
		}

		//Boss Enters 2nd Phase
		float HullRatio = (ship.getHitpoints() / ship.getMaxHitpoints());
		if (HullRatio <= 0.30 && !ship.getHullSize().equals(HullSize.FIGHTER)) {

			final CombatEngineAPI engine = Global.getCombatEngine();

			ship.getMutableStats().getPeakCRDuration().modifyPercent("aic_focused_shield", ENRAGE_PPT_PENALTY);

			ship.getMutableStats().getMaxSpeed().modifyPercent("aic_focused_shield", ENRAGE_SPEED_BONUS);
			ship.getMutableStats().getMaxSpeed().modifyFlat("aic_focused_shield", ENRAGE_SPEED_BONUS_FLAT);
			ship.getMutableStats().getBallisticRoFMult().modifyPercent("aic_focused_shield", ENRAGE_BALLISTIC_ROF_BONUS);
			ship.getMutableStats().getBallisticWeaponFluxCostMod().modifyPercent("aic_focused_shield", ENRAGE_BALLISTIC_FLUX_BONUS);
			ship.getMutableStats().getProjectileSpeedMult().modifyPercent("aic_focused_shield", ENRAGE_PROJ_SPEED_BONUS);
			ship.getMutableStats().getEnergyWeaponDamageMult().modifyPercent("aic_focused_shield", ENRAGE_ENERGY_DMG_BONUS);
			ship.getMutableStats().getFluxDissipation().modifyPercent("aic_focused_shield", ENRAGE_FLUX_MULTI_BONUS);

			//FX

			ship.setJitter("aic_focused_shield", JITTER_COLOR, 1, 1, 1f);
			ship.setJitterUnder("aic_focused_shield", JITTER_UNDER_COLOR, 1, 1, 1f);

			Color color = new Color(185, 100, 255, 255);
			Color color2 = new Color(110, 102, 117, 100);
			ship.getEngineController().fadeToOtherColor(this, color, color2, 1f, 0.8f);
			ship.getEngineController().extendFlame(this, 0.25f, 0.25f, 0.25f);


			if ((DoOnce)) {
				Vector2f loc = new Vector2f(ship.getLocation());
				Color color3 = new Color(255, 94, 31, 255);
				engine.addFloatingText(loc, "FINAL OVERDRIVE", 32.0f, color3, ship, 0.5f, 1.0f);
				Global.getSoundPlayer().playSound(SOUND_ID, 0.8f, 0.8f, ship.getLocation(), ship.getVelocity());

				//phase
		//		interval.setInterval(3.0f, 3.0f);
		//		ship.setPhased(true);
		//		ship.setCollisionClass(CollisionClass.NONE);
		//		ship.blockCommandForOneFrame(ShipCommand.USE_SYSTEM);
		//		ship.blockCommandForOneFrame(ShipCommand.VENT_FLUX);
		//		ship.setExtraAlphaMult(0.25f);
				ship.setJitter("aic_focused_shield", JITTER_COLOR, 4, 10, 10f);
				ship.setJitterUnder("aic_focused_shield", JITTER_UNDER_COLOR, 4, 10, 10f);
				DoOnce = false;
			}

		//	interval.advance(engine.getElapsedInLastFrame());
		//	if (interval.intervalElapsed() && (DoOnce2)){
		//		ship.setPhased(false);
		//		ship.setCollisionClass(CollisionClass.SHIP);
		//		ship.setExtraAlphaMult(1f);
		//		DoOnce2 = false;
		//	}

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