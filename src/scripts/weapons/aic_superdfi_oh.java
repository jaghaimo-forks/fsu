package scripts.weapons;


import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.OnHitEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.ApplyDamageResultAPI;

import org.lwjgl.util.vector.Vector2f;
import org.lazywizard.lazylib.MathUtils;
import java.awt.Color;


public class aic_superdfi_oh implements OnHitEffectPlugin {

    private static final float EXTRA_DMG_CHANCE = 1.0f;
    private static final float MAX_EXTRA_DMG = 50f;
    private static final float MIN_EXTRA_DMG = 100f;
    private static final Color EXPLOSION_COLOR = new Color(184, 247, 255,205);

	
    @Override
    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit,
            ApplyDamageResultAPI damageResult, CombatEngineAPI engine) {


        if (target instanceof ShipAPI && Math.random() <= EXTRA_DMG_CHANCE) {

            engine.applyDamage(target, point, MathUtils.getRandomNumberInRange(MIN_EXTRA_DMG, MAX_EXTRA_DMG),
                    DamageType.FRAGMENTATION, 0f, false,
                    false, projectile.getSource());

            Vector2f velc = new Vector2f(target.getVelocity());

            engine.spawnExplosion(point, velc, EXPLOSION_COLOR, 25f, 0.40f);
        }




		
		

    }

}