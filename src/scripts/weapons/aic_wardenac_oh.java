package scripts.weapons;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.OnHitEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import org.lwjgl.util.vector.Vector2f;



public class aic_wardenac_oh implements OnHitEffectPlugin {

    private static final float EXTRA_DMG = 60f;
    private static final float NON_TARGET_MULTIPLIER = 0.25f;


	
    @Override
    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, CombatEngineAPI engine) {


        if (target instanceof ShipAPI && ((ShipAPI) target).isFighter()) {

            engine.applyDamage(target, point, EXTRA_DMG,
                    DamageType.FRAGMENTATION, 0f, false,
                    false, projectile.getSource());
		}
        else {
            engine.applyDamage(target, point, (EXTRA_DMG * NON_TARGET_MULTIPLIER),
                    DamageType.FRAGMENTATION, 0f, false,
                    false, projectile.getSource());

        }
    }

}