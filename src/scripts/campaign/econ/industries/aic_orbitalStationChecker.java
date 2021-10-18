package scripts.campaign.econ.industries;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.econ.impl.OrbitalStation;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
//////////////////////
//Initially created by Mshadowy and modified from Shadowyards, licenced under CC BY-NC-SA
//////////////////////
public class aic_orbitalStationChecker extends OrbitalStation {
    
    @Override
    public boolean isAvailableToBuild() {
        SectorAPI sector = Global.getSector();
        
        FactionAPI player = sector.getFaction(Factions.PLAYER);
        FactionAPI air = sector.getFaction("air");
        
        boolean canBuild = market.getPlanetEntity() != null &&
                (player.getRelationshipLevel(air).isAtWorst(RepLevel.WELCOMING) ||
                    Global.getSector().getPlayerFaction().knowsIndustry(getId()));
        
        return canBuild;
    }

    @Override
    public String getUnavailableReason() {
        return "Station type unavailable.";
    }

    @Override
    public boolean showWhenUnavailable() {
        return false;
    }
}
