package scripts.shipsystems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.util.IntervalUtil;
import data.scripts.util.MagicRender;
import org.lazywizard.lazylib.FastTrig;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
//////////////////////
//Parts of this script initially created by Mshadowy & Nia Tahl and modified from Shadowyards & Tahlan Shipworks, both licenced under CC BY-NC-SA
//////////////////////
public class aic_slidestats extends BaseShipSystemScript {

    private static final Color AFTERIMAGE_COLOR = new Color(129, 112, 98, 200);
    private static final Color JITTER_UNDER_COLOR = new Color(59, 121, 179, 255);

    private IntervalUtil interval = new IntervalUtil(0.1f, 0.1f);


    @Override
    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        CombatEngineAPI engine = Global.getCombatEngine();

        ShipAPI ship = (ShipAPI) stats.getEntity();

        float amount = engine.getElapsedInLastFrame();
        if (engine.isPaused()) {
            return;
        }
        if (ship.isAlive()) {

            if (state == State.IN) {
                float speed = ship.getVelocity().length();
                if (speed <= 0.1f) {
                    ship.getVelocity().set(VectorUtils.getDirectionalVector(ship.getLocation(), ship.getVelocity()));
                }
                if (speed < 900f) {
                    ship.getVelocity().normalise();
                    ship.getVelocity().scale(speed + amount * 3600f);
                }

                ship.setCollisionClass(CollisionClass.FIGHTER);

                ship.setJitterUnder("aic_timewarpstats", JITTER_UNDER_COLOR, 2, 10, 4f);
                ship.setJitterShields(false);

            } else if (state == State.ACTIVE) {
                float speed = ship.getVelocity().length();
                if (speed < 900f) {
                    ship.getVelocity().normalise();
                    ship.getVelocity().scale(speed + amount * 3600f);
                }

                ship.setCollisionClass(CollisionClass.FIGHTER);

                ship.setJitterUnder("aic_timewarpstats", JITTER_UNDER_COLOR, 2, 10, 4f);
                ship.setJitterShields(false);

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

            } else {
                float speed = ship.getVelocity().length();
                if (speed > ship.getMutableStats().getMaxSpeed().getModifiedValue()) {
                    ship.getVelocity().normalise();
                    ship.getVelocity().scale(speed - amount * 3600f);
                }
                ship.setCollisionClass(CollisionClass.SHIP);
            }
        }
    }

    @Override
    public void unapply(MutableShipStatsAPI stats, String id) {
        ShipAPI ship = (ShipAPI) stats.getEntity();

    }

    @Override
    public StatusData getStatusData(int index, State state, float effectLevel) {
        return null;
    }
}








