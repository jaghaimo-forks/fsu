package scripts.campaign;

import com.fs.starfarer.api.campaign.OrbitAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.impl.campaign.DerelictShipEntityPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Entities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.procgen.DefenderDataOverride;
import com.fs.starfarer.api.impl.campaign.procgen.themes.BaseThemeGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.BaseThemeGenerator.LocationType;
import com.fs.starfarer.api.impl.campaign.procgen.themes.SalvageSpecialAssigner;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.special.ShipRecoverySpecial;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import com.fs.starfarer.api.util.WeightedRandomPicker;
import org.jetbrains.annotations.Nullable;
import org.lazywizard.lazylib.MathUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
//////////////////////
//Initially created by Nia Tahl and modified from Tahlan Shipworks, licenced under CC BY-NC-SA
//////////////////////
/**
 * Script to generate a whole bunch of teaser ships in random orbits throughout the sector
 */
public class aic_protspawner {

    //List of teaser ships to spawn and their count
    private static final List<Pair<String, Integer>> SHIP_SPAWNS = new ArrayList<>();
    static {
        SHIP_SPAWNS.add(new Pair<>("aic_cruiserprot1_std", 3));
        SHIP_SPAWNS.add(new Pair<>("aic_dessprot1_std", 4));
    }

    //pretty sure this does nothing
    public static final List<String> ALLOWED_SYSTEM_TAGS = new ArrayList<>();
    static {
        ALLOWED_SYSTEM_TAGS.add("THEME_REMNANT_DESTROYED");
        ALLOWED_SYSTEM_TAGS.add("THEME_REMNANT_SUPPRESSED");
        ALLOWED_SYSTEM_TAGS.add("THEME_REMNANT_RESURGENT");
    }

    //Weights for the different types of locations our teasers can spawn in
    private static final LinkedHashMap<LocationType, Float> WEIGHTS = new LinkedHashMap<>();
    static {
        WEIGHTS.put(LocationType.GAS_GIANT_ORBIT, 5f);
        WEIGHTS.put(LocationType.IN_ASTEROID_BELT, 2f);
        WEIGHTS.put(LocationType.IN_ASTEROID_FIELD, 5f);
        WEIGHTS.put(LocationType.STAR_ORBIT, 1f);
        WEIGHTS.put(LocationType.IN_SMALL_NEBULA, 2f);
        WEIGHTS.put(LocationType.NEAR_STAR, 5f);
        WEIGHTS.put(LocationType.JUMP_ORBIT, 3f);
    }


    // Functions

    /**
     * Spawns all the teaser ships into the sector: should be run once on sector generation
     * @param sector the sector to spawn the ships in
     */
    public static void spawnprots(SectorAPI sector) {
        for (Pair<String, Integer> spawnData : SHIP_SPAWNS) {
            int numberOfSpawns = 0;
            while (numberOfSpawns < spawnData.two) {
                //Continue until we've found a place to spawn
                BaseThemeGenerator.EntityLocation placeToSpawn = null;
                StarSystemAPI system = null;
                while (placeToSpawn == null) {
                    system = getRandomSystemWithBlacklist(sector);
                    if (system == null) {
                        //We've somehow blacklisted every system in the sector: just don't spawn anything
                        return;
                    }

                    //Gets a list of random locations in the system, and picks one
                    WeightedRandomPicker<BaseThemeGenerator.EntityLocation> validPoints = BaseThemeGenerator.getLocations(new Random(), system, 50f, WEIGHTS);
                    placeToSpawn = validPoints.pick();
                }

                //Now, simply spawn the ship in the spawn location
                boolean recoverable = Math.random()>0.75f;

                float condition = (float)Math.random();
                ShipRecoverySpecial.ShipCondition shipCondition;
                if (condition < 0.3) {
                    shipCondition = ShipRecoverySpecial.ShipCondition.WRECKED;
                } else if (condition < 0.6) {
                    shipCondition = ShipRecoverySpecial.ShipCondition.BATTERED;
                } else if (condition < 0.9) {
                    shipCondition = ShipRecoverySpecial.ShipCondition.AVERAGE;
                } else {
                    shipCondition = ShipRecoverySpecial.ShipCondition.GOOD;
                }

                addDerelict(system, spawnData.one, placeToSpawn.orbit, shipCondition, recoverable, null);

                numberOfSpawns++;
            }
        }
    }


    /**
     *  @param sector The SectorAPI to check for systems in
     **/
    private static StarSystemAPI getRandomSystemWithBlacklist(SectorAPI sector) {
        //First, get all the valid systems and put them in a separate list
        List<StarSystemAPI> validSystems = new ArrayList<>();

        //only picks remnant systems
        for (StarSystemAPI system : sector.getStarSystems()) {
            boolean isValid = false;
            if (system.hasTag(Tags.THEME_REMNANT)) {
                isValid = true;
            }

            if (system.getStar() == null || !Misc.getMarketsInLocation(system).isEmpty() || system.getConstellation() == null) {
                isValid = false;
            }

            if (isValid) {
                validSystems.add(system);
            }
        }

        //If that list is empty, return null
        if (validSystems.isEmpty()) {
            return null;
        }

        //Otherwise, get a random element in it and return that
        else {
            int rand = MathUtils.getRandomNumberInRange(0, validSystems.size()-1);
            return validSystems.get(rand);
        }
    }


    //Mini-function for generating derelicts
    private static SectorEntityToken addDerelict(StarSystemAPI system, String variantId, OrbitAPI orbit,
                                                 ShipRecoverySpecial.ShipCondition condition, boolean recoverable,
                                                 @Nullable DefenderDataOverride defenders) {

        DerelictShipEntityPlugin.DerelictShipData params = new DerelictShipEntityPlugin.DerelictShipData(new ShipRecoverySpecial.PerShipData(variantId, condition), false);
        SectorEntityToken ship = BaseThemeGenerator.addSalvageEntity(system, Entities.WRECK, Factions.NEUTRAL, params);
        ship.setDiscoverable(true);

        ship.setOrbit(orbit);

        if (recoverable) {
            SalvageSpecialAssigner.ShipRecoverySpecialCreator creator = new SalvageSpecialAssigner.ShipRecoverySpecialCreator(null, 0, 0, false, null, null);
            Misc.setSalvageSpecial(ship, creator.createSpecial(ship, null));
        }
        if (defenders != null) {
            Misc.setDefenderOverride(ship, defenders);
        }
        return ship;
    }
}
