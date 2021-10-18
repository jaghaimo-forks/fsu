package scripts.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import org.lazywizard.lazylib.CollisionUtils;
import org.lwjgl.util.vector.Vector2f;
import scripts.util.aic_util;

import java.awt.*;
//////////////////////
//Initially created by DarkRevenant and modified from Underworld, Used under Licence
//////////////////////
public class aic_lux extends BaseHullMod {

	private static final float SUPPLY_USE_MULT = 1.5f;
    private static final float CR_BONUS = 0.15f;
    private static final float CRE_RECOVERY_BONUS = 50f;

	private static final float MAX_SPARKLE_CHANCE_PER_SECOND_PER_CELL = 0.05f;
	private static final Color SPARK_COLOR = new Color(255, 196, 0, 175);
	private static final float SPARK_DURATION = 1.2f;
	private static final float SPARK_RADIUS = 6f;

	public static Vector2f getCellLocation(ShipAPI ship, float x, float y) {
		float xx = x - (ship.getArmorGrid().getGrid().length / 2f);
		float yy = y - (ship.getArmorGrid().getGrid()[0].length / 2f);
		float cellSize = ship.getArmorGrid().getCellSize();
		Vector2f cellLoc = new Vector2f();
		float theta = (float) (((ship.getFacing() - 90f) / 360f) * (Math.PI * 2.0));
		cellLoc.x = (float) (xx * Math.cos(theta) - yy * Math.sin(theta)) * cellSize + ship.getLocation().x;
		cellLoc.y = (float) (xx * Math.sin(theta) + yy * Math.cos(theta)) * cellSize + ship.getLocation().y;

		return cellLoc;
	}

	@Override
	public void advanceInCombat(ShipAPI ship, float amount) {
		CombatEngineAPI engine = Global.getCombatEngine();

		float fluxLevel = ship.getFluxTracker().getFluxLevel();

		ArmorGridAPI armorGrid = ship.getArmorGrid();
		Color color = new Color(SPARK_COLOR.getRed(), SPARK_COLOR.getGreen(), SPARK_COLOR.getBlue(), aic_util.clamp255((int) (SPARK_COLOR.getAlpha() * (1f - fluxLevel))));
		for (int x = 0; x < armorGrid.getGrid().length; x++) {
			for (int y = 0; y < armorGrid.getGrid()[0].length; y++) {
				float armorLevel = armorGrid.getArmorValue(x, y);
				if (armorLevel <= 0f) {
					continue;
				}

				float chance = amount * (1f - fluxLevel) * MAX_SPARKLE_CHANCE_PER_SECOND_PER_CELL * armorLevel / armorGrid.getMaxArmorInCell();
				if (Math.random() >= chance) {
					continue;
				}

				float cellSize = armorGrid.getCellSize();
				Vector2f cellLoc = getCellLocation(ship, x, y);
				cellLoc.x += cellSize * 0.1f - cellSize * (float) Math.random();
				cellLoc.y += cellSize * 0.1f - cellSize * (float) Math.random();
				if (CollisionUtils.isPointWithinBounds(cellLoc, ship)) {
					engine.addHitParticle(cellLoc, ship.getVelocity(), 0.5f * SPARK_RADIUS * (float) Math.random() + SPARK_RADIUS, 1f, SPARK_DURATION,
							aic_util.colorJitter(color, 50f));
				}
			}
		}
	}

	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getSuppliesPerMonth().modifyMult(id, SUPPLY_USE_MULT);
		stats.getMaxCombatReadiness().modifyFlat(id, CR_BONUS);
		stats.getBaseCRRecoveryRatePercentPerDay().modifyPercent(id, CRE_RECOVERY_BONUS);
	}
	
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int)((SUPPLY_USE_MULT - 1f) * 100f) + "%";
        if (index == 1) return "" + Math.round((CR_BONUS) * 100f) + "%";
        if (index == 2) return "" + Math.round(CRE_RECOVERY_BONUS) + "%";
		return null;
	}


}
