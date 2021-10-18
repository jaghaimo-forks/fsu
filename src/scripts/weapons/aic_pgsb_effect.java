package data.scripts.weapons;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.util.IntervalUtil;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;
import com.fs.starfarer.api.combat.BeamAPI;
import com.fs.starfarer.api.combat.BeamEffectPlugin;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.ShipAPI;
//////////////////////
//Initially created by Tartiflette and modified from Scy, Used under Licence
//////////////////////
public class aic_pgsb_effect implements BeamEffectPlugin {

    private boolean hasFired=false;
    private float timer = 0;
    private IntervalUtil fireInterval = new IntervalUtil(0.01f, 0.01f);
    private boolean wasZero = true;

    @Override
    public void advance(float amount, CombatEngineAPI engine, BeamAPI beam) {


        CombatEntityAPI target = beam.getDamageTarget();
        if (target instanceof ShipAPI && beam.getBrightness() >= 1f) {
            float dur = beam.getDamage().getDpsDuration();
            // needed because when the ship is in fast-time, dpsDuration will not be reset every frame as it should be
            if (!wasZero) dur = 0;
            wasZero = beam.getDamage().getDpsDuration() <= 0;
            fireInterval.advance(dur);
        }


	 if (engine.isPaused()) {
            return;
        }

        if(beam.getBrightness()==1) {
            Vector2f start = beam.getFrom();
            Vector2f end = beam.getTo();

            if (MathUtils.getDistanceSquared(start, end)==0){
                return;
            }

            timer+=10*amount;
            if (timer>=2f){
                timer=0;
                hasFired=false;
            }

            if (!hasFired){
                hasFired=true;

				//play sound (to avoid limitations with the way weapon sounds are handled)
                Global.getSoundPlayer().playSound("aic_pgsb_fire", 1f, 0.5f, start, beam.getSource().getVelocity());

			}
        if(beam.getWeapon().getChargeLevel()<1)
        {
            hasFired=false;
            timer = 0;
        }
    }
}
}