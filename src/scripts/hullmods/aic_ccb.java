package scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;


public class aic_ccb extends BaseHullMod {



    public static final float FLUX_REDUCTION = -4f;


    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {

        stats.getBallisticWeaponFluxCostMod().modifyPercent(id, FLUX_REDUCTION);
        stats.getMissileWeaponFluxCostMod().modifyPercent(id, FLUX_REDUCTION);
        stats.getEnergyWeaponFluxCostMod().modifyPercent(id, FLUX_REDUCTION);

    }

    @Override
    public String getDescriptionParam(int index, HullSize hullSize) {

        if (index == 0) {
            return "" + -1*((int) FLUX_REDUCTION) + "%";
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
