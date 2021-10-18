package scripts.campaign.fleets;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.FleetTypes;
import com.fs.starfarer.api.impl.campaign.ids.MemFlags;
import com.fs.starfarer.api.impl.campaign.fleets.DisposableFleetManager;
import com.fs.starfarer.api.impl.campaign.fleets.FleetParamsV3;
import com.fs.starfarer.api.impl.campaign.fleets.FleetFactoryV3;
import org.lazywizard.lazylib.MathUtils;


public class DisposableAirFleetManager extends DisposableFleetManager {

    protected Object readResolve() {
        super.readResolve();
        return this;
    }

    //vanilla code but it just-worksTM

    @Override
    protected String getSpawnId() {
        return "air_spawn"; // not a faction id, just an identifier for this spawner
    }

    @Override
    protected int getDesiredNumFleetsForSpawnLocation() {
        MarketAPI air = getLargestMarket("air");

        float desiredNumFleets = 1f;

        if (air != null) {
            desiredNumFleets += air.getSize();
        }


        return (int) Math.round(desiredNumFleets);
    }



    protected MarketAPI getLargestMarket(String faction) {
        if (currSpawnLoc == null) return null;
        MarketAPI largest = null;

        int maxSize = 0;
        for (MarketAPI market : Global.getSector().getEconomy().getMarkets(currSpawnLoc)) {
            if (market.isHidden()) continue;
            if (!market.getFactionId().equals(faction)) continue;

            if (market.getSize() > maxSize) {
                maxSize = market.getSize();
                largest = market;
            }
        }
        return largest;
    }

    protected CampaignFleetAPI spawnFleetImpl() {

        StarSystemAPI system = currSpawnLoc;
        if (system == null) return null;
        //Don't spawn fleets if Hek doesn't exist or isn't owned by Revolution
        if (Global.getSector().getEconomy().getMarket("aic_hek") == null) return null;
        if (!Global.getSector().getEconomy().getMarket("aic_hek").getFactionId().equals("air")) return null;

        String fleetType = FleetTypes.PATROL_SMALL;


        //level scaling

        float Playerfleet = 0f;
        if (Global.getSector().getPlayerFleet() != null) {
            Playerfleet = (Global.getSector().getPlayerFleet().getFleetPoints() * 0.01f) * MathUtils.getRandomNumberInRange(5f, 100f);
        }

        float combat = MathUtils.getRandomNumberInRange(5f, 25f) + Playerfleet;



        FleetParamsV3 params = new FleetParamsV3(
                null, // source market
                system.getLocation(),
                "air",
                null,
                fleetType,
                combat, // combatPts
                0, // freighterPts
                0, // tankerPts
                0f, // transportPts
                0f, // linerPts
                0f, // utilityPts
                0f // qualityMod
        );

        params.ignoreMarketFleetSizeMult = true;

        CampaignFleetAPI fleet = FleetFactoryV3.createFleet(params);
        if (fleet == null || fleet.isEmpty()) return null;

        // setting the below means: transponder off and more "go dark" use when traveling

        fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_NO_MILITARY_RESPONSE, true);

        float nf = getDesiredNumFleetsForSpawnLocation();

        if (nf == 1) {
            setLocationAndOrders(fleet, 1f, 1f);
        } else {
            setLocationAndOrders(fleet, 0.5f, 1f);
        }

        return fleet;
    }

}








