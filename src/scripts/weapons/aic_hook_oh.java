package scripts.weapons;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lazywizard.lazylib.combat.entities.SimpleEntity;
import org.lwjgl.util.vector.Vector2f;
import scripts.util.aic_util;
//////////////////////
//Initially created by DarkRevenant and modified from Ship and Weapon Pack, Used under Licence
//////////////////////
public class aic_hook_oh implements OnHitEffectPlugin {

    private static final String SOUND_ID = "aic_hook_explo";

    private static final float AREA_EFFECT = 100f;
    private static final float AREA_EFFECT_INNER = 25f;

    private static final Color COLOR1 = new Color(25,155,146);
    private static final Color COLOR2 = new Color(255, 255, 255);

    private static final Vector2f ZERO = new Vector2f();


    public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, CombatEngineAPI engine) {

            if (target == null || point == null) {
                return;
            }
            float emp = projectile.getEmpAmount();
            float dam = projectile.getDamageAmount();

            List<ShipAPI> targets = aic_util.getShipsWithinRange(point, AREA_EFFECT);

            Iterator<ShipAPI> iter = targets.iterator();
            while (iter.hasNext()) {
                ShipAPI entity = iter.next();

                if (entity.getCollisionClass() == CollisionClass.NONE) {
                    iter.remove();
                    continue;
                }

                boolean remove = false;
                for (ShipAPI ship : targets) {
                    if (ship.getShield() != null && ship != entity) {
                        if (ship.getShield().isWithinArc(entity.getLocation()) && ship.getShield().isOn()) {
                            remove = true;
                            break;
                        }
                    }
                }

                if (remove) {
                    iter.remove();
                }
            }

            if (target instanceof ShipAPI) {
                ShipAPI ship = (ShipAPI) target;
                targets.remove(ship);

                engine.spawnEmpArc(projectile.getSource(), point, null, target, DamageType.ENERGY, 0.0f, emp, 100000f,
                        "tachyon_lance_emp_impact", 10f, COLOR1, COLOR2);
            }

            for (ShipAPI ship : targets) {
                float distance = MathUtils.getDistance(ship.getLocation(), point);
                float reduction = 1;
                if (distance > AREA_EFFECT_INNER + ship.getCollisionRadius()) {
                    reduction = (AREA_EFFECT - distance) / (AREA_EFFECT - AREA_EFFECT_INNER);
                }

                engine.spawnEmpArc(projectile.getSource(), point, null, ship, DamageType.ENERGY, dam * reduction, emp
                                * reduction, 100000f, "tachyon_lance_emp_impact",
                        10f, COLOR1, COLOR2);
            }

            for (int i = 0; i < 6; i++) {
                Vector2f location = new Vector2f(projectile.getLocation().x + (float) Math.random() * 50.0f + 25.0f,
                        projectile.getLocation().y);
                location = VectorUtils.rotateAroundPivot(location, projectile.getLocation(), (float) Math.random() * 360f,
                        location);
                engine.spawnEmpArc(projectile.getSource(), point, null, new SimpleEntity(location), DamageType.ENERGY, 0.0f,
                        0.0f, 100000f,
                        "tachyon_lance_emp_impact", 5f, COLOR1, COLOR2);
            }

            Global.getSoundPlayer().playSound("disabled_large", 0.25f, 0.4f, point, ZERO);

        Global.getSoundPlayer().playSound(SOUND_ID, 1f, 0.7f, target.getLocation(), target.getVelocity());

        if ((float) Math.random() > 0.50f && !shieldHit && target instanceof ShipAPI) {

            float emp2 = projectile.getDamageAmount() * 1.0f;
            float dam2 = projectile.getDamageAmount() * 1.0f;

            engine.spawnEmpArc(projectile.getSource(), point, target, target,
                    DamageType.ENERGY,
                    dam2,
                    emp2, // emp
                    100000f, // max range
                    "tachyon_lance_emp_impact",
                    5f, // thickness
                    new Color(25,155,146,255),
                    new Color(255,255,255,255)
            );
        


        }
    }

}