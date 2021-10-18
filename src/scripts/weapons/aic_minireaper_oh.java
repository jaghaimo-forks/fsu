package scripts.weapons;

import com.fs.starfarer.api.Global;
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


public class aic_minireaper_oh implements OnHitEffectPlugin {

    private static final float EXTRA_DMG_CHANCE = 1.0f;
    private static final float MAX_EXTRA_DMG = 400f;
    private static final float MIN_EXTRA_DMG = 200f;
    private static final Color EXPLOSION_COLOR = new Color(227, 57, 27,205);
	private static final String SOUND_ID = "aic_ssms_explo";
	
    @Override
    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit,
            ApplyDamageResultAPI damageResult, CombatEngineAPI engine) {


        if (target instanceof ShipAPI && !shieldHit && Math.random() <= EXTRA_DMG_CHANCE) {

            engine.applyDamage(target, point, MathUtils.getRandomNumberInRange(MIN_EXTRA_DMG, MAX_EXTRA_DMG),
                    DamageType.FRAGMENTATION, 0f, false,
                    false, projectile.getSource());
		
		Vector2f velc = new Vector2f(target.getVelocity());

		engine.spawnExplosion(point, velc, EXPLOSION_COLOR, 250f, 0.40f);
		Global.getSoundPlayer().playSound(SOUND_ID, 0.5f, 2.0f, target.getLocation(), target.getVelocity());
		
		
		}
    }

}