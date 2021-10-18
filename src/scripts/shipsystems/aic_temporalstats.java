package scripts.shipsystems;

import java.awt.Color;
import java.util.List;

import com.fs.starfarer.api.util.IntervalUtil;
import data.scripts.util.MagicLensFlare;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import org.dark.shaders.distortion.DistortionShader;
import org.dark.shaders.distortion.WaveDistortion;
import org.dark.shaders.light.LightShader;
import org.dark.shaders.light.StandardLight;
import org.lazywizard.lazylib.FastTrig;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lwjgl.util.vector.Vector2f;
//////////////////////
//Initially created by Cycerin and modified from Blackrock Driveyards, licenced under CC BY-NC
//////////////////////
public class aic_temporalstats extends BaseShipSystemScript {

    //Phase stuff
    public static final float SHIP_ALPHA_MULT = 0.25f;
    public static final float MAX_TIME_MULT = 5f;

    //DMG (for some reason smaller number = more DMG -thanks Cycerin)
    private static final float DAMAGE_MOD_VS_CAPITAL = 1.0f;
    private static final float DAMAGE_MOD_VS_CRUISER = 1.0f;
    private static final float DAMAGE_MOD_VS_DESTROYER = 1.0f;
    private static final float DAMAGE_MOD_VS_FIGHTER = 0.25f;
    private static final float DAMAGE_MOD_VS_FRIGATE = 1.0f;

    // Explosion effect constants
    private static final Color JITTER_COLOR = new Color(179, 59, 59, 255);
    private static final Color JITTER_UNDER_COLOR = new Color(179, 59, 95, 255);
    private static final Color AFTERIMAGE_COLOR = new Color(179, 59, 59, 50);
    private static final Color EXPLOSION_COLOR = new Color(160, 55, 64);
    private static final Color EMP_COLOR = new Color(112, 31, 204);
    private static final Color LENS_FLARE_OUTER_COLOR = new Color(150, 37, 25, 255);
    private static final Color LENS_FLARE_CORE_COLOR = new Color(255, 122, 122, 250);
    private static final float EXPLOSION_EMP_DAMAGE_AMOUNT = 1200f;
    private static final float EXPLOSION_DAMAGE_AMOUNT = 900f;
    private static final DamageType EXPLOSION_DAMAGE_TYPE = DamageType.HIGH_EXPLOSIVE;
    private static final float DISTORTION_BLAST_RADIUS = 750f;
    private static final float EXPLOSION_PUSH_RADIUS = 600f;
    private static final float EXPLOSION_VISUAL_RADIUS = 750f;
    private static final float FORCE_VS_ASTEROID = 50f;
    private static final float FORCE_VS_MISSILE = 50f;
    private static final float FORCE_VS_CAPITAL = 12f;
    private static final float FORCE_VS_CRUISER = 20f;
    private static final float FORCE_VS_DESTROYER = 30f;
    private static final float FORCE_VS_FIGHTER = 60f;
    private static final float FORCE_VS_FRIGATE = 40f;
    private static final float EXPLOSION_DAMAGE_VS_ALLIES_MODIFIER = .01f;
    private static final float EXPLOSION_EMP_VS_ALLIES_MODIFIER = .02f;
    private static final float EXPLOSION_FORCE_VS_ALLIES_MODIFIER = .2f;
    private static final String SOUND_ID = "aic_temporall_off";

    private boolean Explosions = true;

    // Local variables, don't touch these
    private StandardLight light;
    private WaveDistortion wave;
    private IntervalUtil interval;


    public static float getMaxTimeMult(MutableShipStatsAPI stats) {
        return 1f + (MAX_TIME_MULT - 1f) * stats.getDynamic().getValue(Stats.PHASE_TIME_BONUS_MULT);
    }

    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        ShipAPI ship = null;
        boolean player = false;
        if (stats.getEntity() instanceof ShipAPI) {
            ship = (ShipAPI) stats.getEntity();
            player = ship == Global.getCombatEngine().getPlayerShip();
            id = id + "_" + ship.getId();
        } else {
            return;
        }

        if (Global.getCombatEngine().isPaused() || !ship.isAlive()) {
            return;
        }

        float level = effectLevel;


        float levelForAlpha = level;

        ShipSystemAPI cloak = ship.getPhaseCloak();
        if (cloak == null) cloak = ship.getSystem();


        if (state == State.IN || state == State.ACTIVE) {
            Explosions = true;
            ship.setPhased(true);
            levelForAlpha = level;
        }
        if (state == State.OUT) {
            if (level > 0.5f) {
                ship.setPhased(true);
            } else {
                ship.setPhased(false);
                //remove tidi
                stats.getTimeMult().unmodify("aic_temporalstats");
                Global.getCombatEngine().getTimeMult().unmodify("aic_temporalstats");
                stats.getMaxSpeed().unmodify("aic_temporalstats");
                stats.getAcceleration().unmodify("aic_temporalstats");
            }
            levelForAlpha = level;

        }


        ship.setExtraAlphaMult(1f - (1f - SHIP_ALPHA_MULT) * levelForAlpha);
        ship.setApplyExtraAlphaToEngines(true);

        float shipTimeMult = 1f + (getMaxTimeMult(stats) - 1f) * levelForAlpha;
        stats.getTimeMult().modifyMult(id, shipTimeMult);
        if (player) {
            Global.getCombatEngine().getTimeMult().modifyMult(id, 1f / shipTimeMult);
        } else {
            Global.getCombatEngine().getTimeMult().unmodify(id);
        }

        //extra fx
        ship.setJitter("aic_temporalstats", JITTER_COLOR, 2, 2, 4f);
        ship.setJitterUnder("aic_temporalstats", JITTER_UNDER_COLOR, 2, 2, 4f);
        ship.addAfterimage(AFTERIMAGE_COLOR, MathUtils.getRandomNumberInRange(40, -40), MathUtils.getRandomNumberInRange(40, -40), MathUtils.getRandomNumberInRange(40, -40), MathUtils.getRandomNumberInRange(40, -40), 1, 0.25f, 1.0f, 0.5f, false, false, false);


        //explosion on exit
        if (state == State.OUT) {

            //remove tidi
            stats.getTimeMult().unmodify(id);
            Global.getCombatEngine().getTimeMult().unmodify(id);

            ship.setPhased(false);
            ship.setExtraAlphaMult(1f);


            CombatEngineAPI engine = Global.getCombatEngine();

                stats.getMaxSpeed().unmodify(id);
                stats.getAcceleration().unmodify(id);

                if (Explosions) {

                    engine.spawnExplosion(ship.getLocation(), ship.getVelocity(), EXPLOSION_COLOR, EXPLOSION_VISUAL_RADIUS,
                            0.31f);
                    engine.spawnExplosion(ship.getLocation(), ship.getVelocity(), EXPLOSION_COLOR, EXPLOSION_VISUAL_RADIUS /
                            2f, 0.29f);

                    Vector2f loc = new Vector2f(ship.getLocation());
                    loc.x -= 8f * FastTrig.cos(ship.getFacing() * Math.PI / 180f);
                    loc.y -= 8f * FastTrig.sin(ship.getFacing() * Math.PI / 180f);

                    //lens flare fx
                    MagicLensFlare.createSmoothFlare(
                            engine,
                            ship,
                            loc,
                            125,
                            750,
                            0,
                            LENS_FLARE_OUTER_COLOR,
                            LENS_FLARE_CORE_COLOR
                    );

                    //light fx
                    light = new StandardLight();
                    light.setLocation(loc);
                    light.setIntensity(2.0f);
                    light.setSize(EXPLOSION_VISUAL_RADIUS * 3f);
                    light.setColor(EXPLOSION_COLOR);
                    light.fadeOut(3f);
                    LightShader.addLight(light);

                    //distortion fx
                    wave = new WaveDistortion();
                    wave.setLocation(loc);
                    wave.setSize(DISTORTION_BLAST_RADIUS);
                    wave.setIntensity(DISTORTION_BLAST_RADIUS * 0.10f);
                    wave.fadeInSize(1.2f);
                    wave.fadeOutIntensity(0.9f);
                    wave.setSize(DISTORTION_BLAST_RADIUS * 0.35f);
                    DistortionShader.addDistortion(wave);

                    Global.getSoundPlayer().playSound(SOUND_ID, 0.7f, 1.0f, ship.getLocation(), ship.getVelocity());

                    ShipAPI victim;
                    Vector2f dir;
                    float force, damage, emp, mod;
                    List<CombatEntityAPI> entities = CombatUtils.getEntitiesWithinRange(ship.getLocation(),
                            EXPLOSION_PUSH_RADIUS);
                    int size = entities.size();
                    int i = 0;
                    while (i < size) {
                        CombatEntityAPI tmp = entities.get(i);

                        mod = 1f - (MathUtils.getDistance(ship, tmp) / EXPLOSION_PUSH_RADIUS);
                        force = FORCE_VS_ASTEROID * mod;
                        damage = EXPLOSION_DAMAGE_AMOUNT * mod;
                        emp = EXPLOSION_EMP_DAMAGE_AMOUNT * mod;

                        //fuck missiles
                        if (tmp instanceof MissileAPI) {
                            force = FORCE_VS_MISSILE * mod;
                            engine.applyDamage(tmp, loc, 400, DamageType.FRAGMENTATION, 0, false, false, ship);
                        }
                        if (tmp instanceof ShipAPI) {
                            victim = (ShipAPI) tmp;

                            // Modify push strength and dmg based on ship class
                            if (victim.getHullSize() == ShipAPI.HullSize.FIGHTER) {
                                force = FORCE_VS_FIGHTER * mod;
                                damage /= DAMAGE_MOD_VS_FIGHTER;
                            } else if (victim.getHullSize() == ShipAPI.HullSize.FRIGATE) {
                                force = FORCE_VS_FRIGATE * mod;
                                damage /= DAMAGE_MOD_VS_FRIGATE;
                            } else if (victim.getHullSize() == ShipAPI.HullSize.DESTROYER) {
                                force = FORCE_VS_DESTROYER * mod;
                                damage /= DAMAGE_MOD_VS_DESTROYER;
                            } else if (victim.getHullSize() == ShipAPI.HullSize.CRUISER) {
                                force = FORCE_VS_CRUISER * mod;
                                damage /= DAMAGE_MOD_VS_CRUISER;
                            } else if (victim.getHullSize() == ShipAPI.HullSize.CAPITAL_SHIP) {
                                force = FORCE_VS_CAPITAL * mod;
                                damage /= DAMAGE_MOD_VS_CAPITAL;
                            }

                            if (victim.getOwner() == ship.getOwner()) {
                                damage *= EXPLOSION_DAMAGE_VS_ALLIES_MODIFIER;
                                emp *= EXPLOSION_EMP_VS_ALLIES_MODIFIER;
                                force *= EXPLOSION_FORCE_VS_ALLIES_MODIFIER;
                            }


                            //spawn emp arcs to unshielded targets
                            if ((victim.getShield() != null && victim.getShield().isOn() && victim.getShield().isWithinArc(
                                    ship.getLocation()))) {
                                victim.getFluxTracker().increaseFlux(damage * 1, true);
                            } else {
                                ShipAPI empTarget = victim;
                                for (int x = 0; x < 5; x++) {
                                    engine.spawnEmpArc(ship, MathUtils.getRandomPointInCircle(victim.getLocation(),
                                            victim.getCollisionRadius()),
                                            empTarget,
                                            empTarget, EXPLOSION_DAMAGE_TYPE, damage / 10, emp / 5,
                                            EXPLOSION_PUSH_RADIUS, null, 2f, EMP_COLOR,
                                            EMP_COLOR);
                                }
                            }
                        }

                        dir = VectorUtils.getDirectionalVector(ship.getLocation(), tmp.getLocation());
                        dir.scale(force);

                        Vector2f.add(tmp.getVelocity(), dir, tmp.getVelocity());
                        i++;
                    }
                    Explosions = false;
                }

        }


        //extra speed to go fast
        else {
            stats.getMaxSpeed().modifyFlat(id, 200f * effectLevel);
            stats.getAcceleration().modifyFlat(id, 2000f * effectLevel);
        }


    }

    public void unapply(MutableShipStatsAPI stats, String id) {
        ShipAPI ship;
        if (stats.getEntity() instanceof ShipAPI) {
            ship = (ShipAPI) stats.getEntity();
        } else {
            return;
        }
        stats.getMaxSpeed().unmodify("aic_temporalstats");
        stats.getAcceleration().unmodify("aic_temporalstats");

        //remove tidi again just to be safe
        stats.getTimeMult().unmodify("aic_temporalstats");
        Global.getCombatEngine().getTimeMult().unmodify("aic_temporalstats");

        //ship.setPhased(false);
        //ship.setExtraAlphaMult(1f);


    }

    public StatusData getStatusData(int index, State state, float effectLevel) {
        return null;
    }
}























