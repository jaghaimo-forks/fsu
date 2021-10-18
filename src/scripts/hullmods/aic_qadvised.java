package scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.impl.campaign.ids.Stats;

public class aic_qadvised extends BaseHullMod {

	private static final float WEAPON_MALFUNCTION_PROB = 0.03f;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		float effect = stats.getDynamic().getValue(Stats.DMOD_EFFECT_MULT);
		
		stats.getCriticalMalfunctionChance().modifyFlat(id, 0.5f * effect);
		stats.getWeaponMalfunctionChance().modifyFlat(id, WEAPON_MALFUNCTION_PROB * effect);
	}
	
	public String getDescriptionParam(int index, HullSize hullSize) {
		return null;
	}


}
