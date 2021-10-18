package scripts.weapons;

import java.awt.Color;

import org.lwjgl.util.vector.Vector2f;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.OnHitEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;


public class aic_hook_ohb implements OnHitEffectPlugin {


    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target,
                      Vector2f point, boolean shieldHit, CombatEngineAPI engine) {


        if ((float) Math.random() > 0.75f && !shieldHit && target instanceof ShipAPI) {

            float emp = projectile.getDamageAmount()*0.5f;
            float dam = projectile.getDamageAmount()*0.5f;

            engine.spawnEmpArc(projectile.getSource(), point, target, target,
                    DamageType.HIGH_EXPLOSIVE,
                    dam,
                    emp, // emp
                    100000f, // max range
                    "tachyon_lance_emp_impact",
                    20f, // thickness
                    new Color(155, 142, 25,255),
                    new Color(255,255,255,255)
            );
        


        }
    }

}