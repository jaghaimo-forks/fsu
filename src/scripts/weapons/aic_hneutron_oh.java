package scripts.weapons;

import com.fs.starfarer.api.Global;
import data.scripts.util.MagicLensFlare;
import org.lwjgl.util.vector.Vector2f;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.OnHitEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;

import java.awt.*;


public class aic_hneutron_oh implements OnHitEffectPlugin {

    //private static final String SOUND_ID = "aic_hook_explo";

    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, CombatEngineAPI engine) {

        //Global.getSoundPlayer().playSound(SOUND_ID, 1f, 0.7f, target.getLocation(), target.getVelocity());

        if (target instanceof ShipAPI) {

            MagicLensFlare.createSmoothFlare(
                    engine,
                    projectile.getSource(),
                    point,
                    50,
                    300,
                    0,
                    new Color(150, 148, 25, 255),
                    new Color(255, 239, 122, 250)
            );

        }
    }

}