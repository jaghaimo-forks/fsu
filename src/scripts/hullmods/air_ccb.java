package scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;


public class air_ccb extends BaseHullMod {


    public static final float HULL_REDUCTION = -20f;
    public static final float HULL_REPAIR = 25f;


    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {

        stats.getHullDamageTakenMult().modifyPercent(id, HULL_REDUCTION);
        stats.getRepairRatePercentPerDay().modifyPercent(id, HULL_REPAIR);
    }

    @Override
    public String getDescriptionParam(int index, HullSize hullSize) {

        if (index == 0) {
            return "" + (int) (-1 * HULL_REDUCTION) + "%";
        }
        if (index == 1) {
            return "" + (int) HULL_REPAIR + "%";
        }

        return null;
    }

    @Override //All you need is this to be honest. The framework will do everything on its own.
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
                if (ship.getVariant().hasHullMod("CHM_commission")) {
                    ship.getVariant().removeMod("CHM_commission");
                }
				// This is to remove the unnecessary dummy hull mod. Unless the player want it... but nah!
    }
}
