package scripts.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.ShipAPI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;

import static org.dark.graphics.util.ShipColors.clamp255;

//////////////////////
//Initially created by DarkRevenant and modified from Ship and Weapon Pack & Underworld, Used under Licence
//////////////////////
public class aic_util {

    /* LazyLib 2.4b revert */
    public static List<ShipAPI> getShipsWithinRange(Vector2f location, float range) {
        List<ShipAPI> ships = new ArrayList<>();

        for (ShipAPI tmp : Global.getCombatEngine().getShips()) {
            if (tmp.isShuttlePod()) {
                continue;
            }

            if (MathUtils.isWithinRange(tmp, location, range)) {
                ships.add(tmp);
            }
        }

        return ships;
    }
    public static int clamp255(int x) {
        return Math.max(0, Math.min(255, x));
    }
    public static Color colorJitter(Color color, float amount) {
        return new Color(clamp255((int) (color.getRed() + (int) (((float) Math.random() - 0.5f) * amount))),
                clamp255((int) (color.getGreen() + (int) (((float) Math.random() - 0.5f) * amount))),
                clamp255((int) (color.getBlue() + (int) (((float) Math.random() - 0.5f) * amount))),
                color.getAlpha());
    }

}	
