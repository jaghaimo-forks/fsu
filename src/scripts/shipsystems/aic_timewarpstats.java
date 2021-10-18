package scripts.shipsystems;

import java.awt.Color;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.util.IntervalUtil;
import data.scripts.util.MagicRender;
import org.dark.shaders.distortion.DistortionShader;
import org.dark.shaders.distortion.WaveDistortion;
import org.lazywizard.lazylib.FastTrig;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;
//////////////////////
//Parts of this script initially created by Nia Tahl and modified from Tahlan Shipworks, licenced under CC BY-NC-SA
//////////////////////
public class aic_timewarpstats extends BaseShipSystemScript {

    //Time stuff
    public static final float MAX_TIME_MULT = 20f;

    private static final Color JITTER_COLOR = new Color(59, 129, 179, 255);
    private static final Color JITTER_UNDER_COLOR = new Color(59, 121, 179, 255);
    private static final Color AFTERIMAGE_COLOR = new Color(59, 141, 179, 150);

    // Local variables, don't touch these
    private IntervalUtil interval = new IntervalUtil(0.75f, 0.75f);
    private WaveDistortion wave;


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

        if (state == State.OUT) {
            //remove tidi
            stats.getTimeMult().unmodify("aic_timewarpstats");
            Global.getCombatEngine().getTimeMult().unmodify("aic_timewarpstats");
            stats.getMaxSpeed().unmodify("aic_timewarpstats");
            stats.getAcceleration().unmodify("aic_timewarpstats");
        }

        ship.setApplyExtraAlphaToEngines(true);

        float shipTimeMult = 1f + (getMaxTimeMult(stats) - 1f);
        stats.getTimeMult().modifyMult(id, shipTimeMult);
        if (player) {
            Global.getCombatEngine().getTimeMult().modifyMult(id, 1f / shipTimeMult);
        } else {
            Global.getCombatEngine().getTimeMult().unmodify(id);
        }

        stats.getMaxSpeed().modifyFlat(id, 100f);
        stats.getAcceleration().modifyFlat(id, 200f);

        //extra fx
        ship.setJitter("aic_timewarpstats", JITTER_COLOR, 2, 2, 4f);
        ship.setJitterUnder("aic_timewarpstats", JITTER_UNDER_COLOR, 2, 10, 4f);
        ship.setJitterShields(false);

        CombatEngineAPI engine = Global.getCombatEngine();

        Vector2f loc = new Vector2f(ship.getLocation());
        loc.x -= 8f * FastTrig.cos(ship.getFacing() * Math.PI / 180f);
        loc.y -= 8f * FastTrig.sin(ship.getFacing() * Math.PI / 180f);

        //distortion fx
        wave = new WaveDistortion();
        wave.setLocation(loc);
        wave.setSize(40);
        wave.setIntensity(40 * 0.10f);
        wave.fadeInSize(1.2f);
        wave.fadeOutIntensity(0.9f);
        wave.setSize(40 * 0.35f);
        DistortionShader.addDistortion(wave);

        interval.advance(engine.getElapsedInLastFrame());

        if (interval.intervalElapsed()) {
            // Sprite offset fuckery - Don't you love trigonometry?
            SpriteAPI sprite = ship.getSpriteAPI();
            float offsetX = sprite.getWidth() / 2 - sprite.getCenterX();
            float offsetY = sprite.getHeight() / 2 - sprite.getCenterY();

            float trueOffsetX = (float) FastTrig.cos(Math.toRadians(ship.getFacing() - 90f)) * offsetX - (float) FastTrig.sin(Math.toRadians(ship.getFacing() - 90f)) * offsetY;
            float trueOffsetY = (float) FastTrig.sin(Math.toRadians(ship.getFacing() - 90f)) * offsetX + (float) FastTrig.cos(Math.toRadians(ship.getFacing() - 90f)) * offsetY;

            Vector2f trueLocation = new Vector2f(ship.getLocation().getX() + trueOffsetX, ship.getLocation().getY() + trueOffsetY);

            MagicRender.battlespace(
                    Global.getSettings().getSprite(ship.getHullSpec().getSpriteName()),
                    MathUtils.getRandomPointInCircle(trueLocation,MathUtils.getRandomNumberInRange(0f,20f)),
                    new Vector2f(0, 0),
                    new Vector2f(ship.getSpriteAPI().getWidth(), ship.getSpriteAPI().getHeight()),
                    new Vector2f(0, 0),
                    ship.getFacing() - 90f,
                    0f,
                    AFTERIMAGE_COLOR,
                    true,
                    0.1f,
                    0.1f,
                    1f,
                    CombatEngineLayers.BELOW_SHIPS_LAYER);
        }

        if (state == State.OUT) {

            //remove tidi
            stats.getTimeMult().unmodify(id);
            Global.getCombatEngine().getTimeMult().unmodify(id);
            stats.getMaxSpeed().unmodify(id);
            stats.getAcceleration().unmodify(id);

        }
    }

    public void unapply(MutableShipStatsAPI stats, String id) {
        stats.getMaxSpeed().unmodify(id);
        stats.getAcceleration().unmodify(id);
        //remove tidi again just to be safe
        stats.getTimeMult().unmodify(id);
        Global.getCombatEngine().getTimeMult().unmodify(id);
    }

    public StatusData getStatusData(int index, State state, float effectLevel) {
        return null;
    }
}























