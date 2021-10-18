package scripts.campaign.submarkets;

import java.awt.Color;
import java.util.Random;

import com.fs.starfarer.api.impl.campaign.submarkets.BaseSubmarketPlugin;
import com.fs.starfarer.api.util.WeightedRandomPicker;
import org.apache.log4j.Logger;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.CoreUIAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.CampaignUIAPI.CoreUITradeMode;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import com.fs.starfarer.api.util.Highlights;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.campaign.FactionDoctrineAPI;



public class aic_citadeldock extends BaseSubmarketPlugin {

    public static Logger log = Global.getLogger(com.fs.starfarer.api.impl.campaign.submarkets.MilitarySubmarketPlugin.class);

    public void init(SubmarketAPI submarket) {
        super.init(submarket);
    }

    public void updateCargoPrePlayerInteraction() {
        float seconds = Global.getSector().getClock().convertToSeconds(sinceLastCargoUpdate);
        addAndRemoveStockpiledResources(seconds, false, true, true);
        sinceLastCargoUpdate = 0f;

        if (okToUpdateShipsAndWeapons()) {
            sinceSWUpdate = 0f;
            float stability = market.getStabilityValue();

            pruneWeapons(0f);

            //factions
            WeightedRandomPicker<String> factionPicker = new WeightedRandomPicker<String>();
            factionPicker.add(market.getFactionId(), 20f - stability);
            factionPicker.add(Factions.INDEPENDENT, 4f);
            factionPicker.add(Factions.TRITACHYON, 8f);
            factionPicker.add(Factions.PERSEAN, 8f);
            factionPicker.add(submarket.getFaction().getId(), 6f);

            int weapons = 30 + Math.max(0, market.getSize() - 2) * 2;
            int fighters = 14 + Math.max(0, market.getSize() - 2);

            addWeapons(weapons, weapons + 16, 3, factionPicker);
            addFighters(fighters, fighters + 10, 3, factionPicker);

            float sMult = 0.5f + Math.max(0, (1f - stability / 10f)) * 0.5f;
            getCargo().getMothballedShips().clear();
            float pOther = 0.1f;

            FactionDoctrineAPI doctrine = market.getFaction().getDoctrine().clone();
            //Spawn stuff for listed factions
            addShips(market.getFactionId(),
                    70f * sMult, // combat
                    itemGenRandom.nextFloat() > pOther ? 0f : 10f, // freighter
                    itemGenRandom.nextFloat() > pOther ? 0f : 10f, // tanker
                    itemGenRandom.nextFloat() > pOther ? 0f : 10f, // transport
                    itemGenRandom.nextFloat() > pOther ? 0f : 10f, // liner
                    itemGenRandom.nextFloat() > pOther ? 0f : 10f, // utilityPts
                    null,
                    0f, // qualityMod
                    null,
                    doctrine);
            FactionDoctrineAPI doctrineOverride = submarket.getFaction().getDoctrine().clone();
            doctrineOverride.setWarships(4);
            doctrineOverride.setPhaseShips(1);
            doctrineOverride.setCarriers(2);
            doctrineOverride.setCombatFreighterProbability(1f);
            doctrineOverride.setShipSize(5);
            addShips(submarket.getFaction().getId(),
                    70f, // combat
                    5f, // freighter
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // tanker
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // transport
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // liner
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // utilityPts
                    //0.8f,
                    Math.min(1f, Misc.getShipQuality(market, market.getFactionId()) + 0.5f),
                    0f, // qualityMod
                    null,
                    doctrineOverride);
            addShips(Factions.INDEPENDENT,
                    70f, // combat
                    5f, // freighter
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // tanker
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // transport
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // liner
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // utilityPts
                    //0.8f,
                    Math.min(1f, Misc.getShipQuality(market, market.getFactionId()) + 0.5f),
                    0f, // qualityMod
                    null,
                    doctrineOverride);
            addShips(Factions.TRITACHYON,
                    70f, // combat
                    5f, // freighter
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // tanker
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // transport
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // liner
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // utilityPts
                    //0.8f,
                    Math.min(1f, Misc.getShipQuality(market, market.getFactionId()) + 0.5f),
                    0f, // qualityMod
                    null,
                    doctrineOverride);
            addShips(Factions.PERSEAN,
                    70f, // combat
                    5f, // freighter
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // tanker
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // transport
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // liner
                    itemGenRandom.nextFloat() > pOther ? 0f : 5f, // utilityPts
                    //0.8f,
                    Math.min(1f, Misc.getShipQuality(market, market.getFactionId()) + 0.5f),
                    0f, // qualityMod
                    null,
                    doctrineOverride);

            addHullMods(4, 5 + itemGenRandom.nextInt(4));
        }

        getCargo().sort();
    }

    protected Object writeReplace() {
        if (okToUpdateShipsAndWeapons()) {
            pruneWeapons(0f);
            getCargo().getMothballedShips().clear();
        }
        return this;
    }

    //all the vanilla market stuff to make thing work

    public boolean shouldHaveCommodity(CommodityOnMarketAPI com) {
        if (Commodities.CREW.equals(com.getId())) return true;
        return com.getCommodity().hasTag(Commodities.TAG_MILITARY);
    }

    @Override
    public int getStockpileLimit(CommodityOnMarketAPI com) {
        int demand = com.getMaxDemand();
        int available = com.getAvailable();

        float limit = BaseIndustry.getSizeMult(available) - BaseIndustry.getSizeMult(Math.max(0, demand - 2));
        limit *= com.getCommodity().getEconUnit();


        Random random = new Random(market.getId().hashCode() + submarket.getSpecId().hashCode() + Global.getSector().getClock().getMonth() * 170000);
        limit *= 0.9f + 0.2f * random.nextFloat();

        float sm = market.getStabilityValue() / 10f;
        limit *= (0.25f + 0.75f * sm);

        if (limit < 0) limit = 0;

        return (int) limit;
    }

    public boolean isIllegalOnSubmarket(String commodityId, TransferAction action) {
        boolean illegal = market.isIllegal(commodityId);
        RepLevel req = getRequiredLevelAssumingLegal(commodityId, action);

        if (req == null) return illegal;

        RepLevel level = submarket.getFaction().getRelationshipLevel(Global.getSector().getFaction(Factions.PLAYER));
        boolean legal = level.isAtWorst(req);
        return !legal;
    }

    public boolean isIllegalOnSubmarket(CargoStackAPI stack, TransferAction action) {
        if (stack.isCommodityStack()) {
            return isIllegalOnSubmarket((String) stack.getData(), action);
        }

        RepLevel req = getRequiredLevelAssumingLegal(stack, action);
        if (req == null) return false;

        RepLevel level = submarket.getFaction().getRelationshipLevel(Global.getSector().getFaction(Factions.PLAYER));

        boolean legal = level.isAtWorst(req);

        return !legal;
    }

    public String getIllegalTransferText(CargoStackAPI stack, TransferAction action) {
        RepLevel req = getRequiredLevelAssumingLegal(stack, action);

        if (req != null) {
            return "Req: " +
                    submarket.getFaction().getDisplayName() + " - " + req.getDisplayName().toLowerCase();
        }

        return "Illegal to trade in " + stack.getDisplayName() + " here";
    }


    public Highlights getIllegalTransferTextHighlights(CargoStackAPI stack, TransferAction action) {
        RepLevel req = getRequiredLevelAssumingLegal(stack, action);
        if (req != null) {
            Color c = Misc.getNegativeHighlightColor();
            Highlights h = new Highlights();
            RepLevel level = submarket.getFaction().getRelationshipLevel(Global.getSector().getFaction(Factions.PLAYER));
            if (!level.isAtWorst(req)) {
                h.append(submarket.getFaction().getDisplayName() + " - " + req.getDisplayName().toLowerCase(), c);
            }
            return h;
        }
        return null;
    }

    private RepLevel getRequiredLevelAssumingLegal(CargoStackAPI stack, TransferAction action) {
        int tier = -1;
        if (stack.isWeaponStack()) {
            WeaponSpecAPI spec = stack.getWeaponSpecIfWeapon();
            tier = spec.getTier();
        } else if (stack.isFighterWingStack()) {
            FighterWingSpecAPI spec = stack.getFighterWingSpecIfWing();
            tier = spec.getTier();
        }

        if (tier >= 0) {
            if (action == TransferAction.PLAYER_BUY) {
                switch (tier) {
                    case 0: return RepLevel.FAVORABLE;
                    case 1: return RepLevel.WELCOMING;
                    case 2: return RepLevel.FRIENDLY;
                    case 3: return RepLevel.COOPERATIVE;
                }
            }
            return RepLevel.VENGEFUL;
        }

        if (!stack.isCommodityStack()) return null;
        return getRequiredLevelAssumingLegal((String) stack.getData(), action);
    }

    private RepLevel getRequiredLevelAssumingLegal(String commodityId, TransferAction action) {
        if (action == TransferAction.PLAYER_SELL) {
            return RepLevel.VENGEFUL;
        }

        CommodityOnMarketAPI com = market.getCommodityData(commodityId);
        boolean isMilitary = com.getCommodity().getTags().contains(Commodities.TAG_MILITARY);
        if (isMilitary) {
            if (com.isPersonnel()) {
                return RepLevel.COOPERATIVE;
            }
            return RepLevel.FAVORABLE;
        }
        return null;
    }

    public boolean isIllegalOnSubmarket(FleetMemberAPI member, TransferAction action) {
        RepLevel req = getRequiredLevelAssumingLegal(member, action);
        if (req == null) return false;

        RepLevel level = submarket.getFaction().getRelationshipLevel(Global.getSector().getFaction(Factions.PLAYER));

        boolean legal = level.isAtWorst(req);

        return !legal;
    }

    public String getIllegalTransferText(FleetMemberAPI member, TransferAction action) {
        RepLevel req = getRequiredLevelAssumingLegal(member, action);
        if (req != null) {
            String str = "";
            RepLevel level = submarket.getFaction().getRelationshipLevel(Global.getSector().getFaction(Factions.PLAYER));
            if (!level.isAtWorst(req)) {
                str += "Req: " + submarket.getFaction().getDisplayName() + " - " + req.getDisplayName().toLowerCase();
            }
            return str;
        }

        if (action == TransferAction.PLAYER_BUY) {
            return "Illegal to buy"; // this shouldn't happen
        } else {
            return "Illegal to sell";
        }
    }

    public Highlights getIllegalTransferTextHighlights(FleetMemberAPI member, TransferAction action) {
        RepLevel req = getRequiredLevelAssumingLegal(member, action);
        if (req != null) {
            Color c = Misc.getNegativeHighlightColor();
            Highlights h = new Highlights();
            RepLevel level = submarket.getFaction().getRelationshipLevel(Global.getSector().getFaction(Factions.PLAYER));
            if (!level.isAtWorst(req)) {
                h.append("Req: " + submarket.getFaction().getDisplayName() + " - " + req.getDisplayName().toLowerCase(), c);
            }
            return h;
        }
        return null;
    }

    private RepLevel getRequiredLevelAssumingLegal(FleetMemberAPI member, TransferAction action) {
        if (action == TransferAction.PLAYER_BUY) {
            int fp = member.getFleetPointCost();
            HullSize size = member.getHullSpec().getHullSize();

            if (size == HullSize.CAPITAL_SHIP || fp > 20) return RepLevel.COOPERATIVE;
            if (size == HullSize.CRUISER || fp > 15) return RepLevel.WELCOMING;
            if (size == HullSize.DESTROYER || fp > 5) return RepLevel.FAVORABLE;
            return RepLevel.FAVORABLE;
        }
        return null;
    }



    private RepLevel minStanding = RepLevel.FAVORABLE;
    public boolean isEnabled(CoreUIAPI ui) {
        if (ui.getTradeMode() == CoreUITradeMode.SNEAK) return false;

        RepLevel level = submarket.getFaction().getRelationshipLevel(Global.getSector().getFaction(Factions.PLAYER));
        return level.isAtWorst(minStanding);
    }

    public OnClickAction getOnClickAction(CoreUIAPI ui) {
        return OnClickAction.OPEN_SUBMARKET;
    }

    public String getTooltipAppendix(CoreUIAPI ui) {
        if (!isEnabled(ui)) {
            return "Requires: " + submarket.getFaction().getDisplayName() + " - " + minStanding.getDisplayName().toLowerCase();
        }
        if (ui.getTradeMode() == CoreUITradeMode.SNEAK) {
            return "Requires: proper docking authorization";
        }
        return null;
    }

    public Highlights getTooltipAppendixHighlights(CoreUIAPI ui) {
        String appendix = getTooltipAppendix(ui);
        if (appendix == null) return null;

        Highlights h = new Highlights();
        h.setText(appendix);
        h.setColors(Misc.getNegativeHighlightColor());
        return h;
    }

    @Override
    public PlayerEconomyImpactMode getPlayerEconomyImpactMode() {
        return PlayerEconomyImpactMode.PLAYER_SELL_ONLY;
    }


}



