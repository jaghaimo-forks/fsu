package data.shipsystems.scripts;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;

public class aic_amodeStats extends BaseShipSystemScript {

	public static final float ROF_BONUS = .20f;
	public static final float FLUX_BONUS = 1.4f;

	
	
	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		
		float mult = 1f + ROF_BONUS * effectLevel;
		stats.getBallisticRoFMult().modifyMult(id, mult);
		stats.getEnergyRoFMult().modifyMult(id, mult);
		stats.getMissileRoFMult().modifyMult(id, mult);
		stats.getFluxDissipation().modifyMult(id, +FLUX_BONUS);
	
	
	}
	public void unapply(MutableShipStatsAPI stats, String id) {
		stats.getBallisticRoFMult().unmodify(id);
		stats.getBallisticWeaponFluxCostMod().unmodify(id);
		stats.getFluxDissipation().unmodify(id);
	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		float mult = 1f + ROF_BONUS * effectLevel;
		float bonusPercent = (int) ((mult - 1f) * 100f);
		if (index == 0) {
			return new StatusData("weapon rate of fire +20%", false);
		}
		if (index == 1) {
			return new StatusData("flux dissipatoin +40%", false);
		}
		return null;
	}
}
