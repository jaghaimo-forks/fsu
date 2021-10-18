package data.scripts.world.systems;


import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import data.scripts.world.systems.oms.Oms;
import data.scripts.world.systems.provi.Provi;



public class AicGen implements SectorGeneratorPlugin {
    @Override
    public void generate(SectorAPI sector) {

        //gen
        new Oms().generate(sector);
        new Provi().generate(sector);
        //bounty
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("aic");

        FactionAPI hegemony = sector.getFaction(Factions.HEGEMONY);
        FactionAPI tritachyon = sector.getFaction(Factions.TRITACHYON);
        FactionAPI pirates = sector.getFaction(Factions.PIRATES);
        FactionAPI church = sector.getFaction(Factions.LUDDIC_CHURCH);
        FactionAPI path = sector.getFaction(Factions.LUDDIC_PATH);
        FactionAPI indep = sector.getFaction(Factions.INDEPENDENT);
        FactionAPI league = sector.getFaction(Factions.PERSEAN);
        FactionAPI diktat = sector.getFaction(Factions.DIKTAT);
		FactionAPI player = sector.getFaction(Factions.PLAYER);
        FactionAPI aic = sector.getFaction("aic");
        FactionAPI air = sector.getFaction("air");
    //Union
        aic.setRelationship(path.getId(), RepLevel.VENGEFUL);
        aic.setRelationship(hegemony.getId(), RepLevel.VENGEFUL);
        aic.setRelationship(church.getId(), RepLevel.HOSTILE);
        aic.setRelationship(pirates.getId(), RepLevel.WELCOMING);
        aic.setRelationship(tritachyon.getId(), RepLevel.WELCOMING);
        aic.setRelationship(indep.getId(), RepLevel.SUSPICIOUS);
        aic.setRelationship(league.getId(), RepLevel.FAVORABLE);
        aic.setRelationship(diktat.getId(), RepLevel.SUSPICIOUS);
        aic.setRelationship(air.getId(), RepLevel.FAVORABLE);
		player.setRelationship(aic.getId(), RepLevel.NEUTRAL);
    //revolution
        air.setRelationship(path.getId(), RepLevel.VENGEFUL);
        air.setRelationship(hegemony.getId(), RepLevel.VENGEFUL);
        air.setRelationship(church.getId(), RepLevel.VENGEFUL);
        air.setRelationship(pirates.getId(), RepLevel.WELCOMING);
        air.setRelationship(tritachyon.getId(), RepLevel.INHOSPITABLE);
        air.setRelationship(indep.getId(), RepLevel.HOSTILE);
        air.setRelationship(league.getId(), RepLevel.INHOSPITABLE);
        air.setRelationship(diktat.getId(), RepLevel.HOSTILE);
        air.setRelationship(aic.getId(), RepLevel.FAVORABLE);
        player.setRelationship(air.getId(), RepLevel.HOSTILE);
 //modded factions
        FactionAPI sra = sector.getFaction("shadow_industry");
		FactionAPI II = sector.getFaction("interstellarimperium");
        FactionAPI brdy = sector.getFaction("blackrock_driveyards");
        FactionAPI dme = sector.getFaction("dassault_mikoyan");
		FactionAPI sixieme = sector.getFaction("6eme_bureau");
        FactionAPI bb = sector.getFaction("blade_breakers");
        FactionAPI da = sector.getFaction("diableavionics");
        FactionAPI ora = sector.getFaction("ORA");
        FactionAPI scy = sector.getFaction("SCY");
        FactionAPI sylphon = sector.getFaction("sylphon");
        FactionAPI cabal = sector.getFaction("cabal");
        FactionAPI thi = sector.getFaction("tiandong");
		FactionAPI maya = sector.getFaction("mayasura");
        FactionAPI sad = sector.getFaction("sad");
        FactionAPI gmda = sector.getFaction("gmda");
        FactionAPI gmdap = sector.getFaction("gmda_patrol");
        FactionAPI ars = sector.getFaction("al_ars");
        FactionAPI mayorate = sector.getFaction("mayorate");
        FactionAPI kadur = sector.getFaction("kadur_remnant");
        FactionAPI junk = sector.getFaction("junk_pirates");
        FactionAPI pack = sector.getFaction("pack");
        FactionAPI asp = sector.getFaction("syndicate_asp");
        FactionAPI metel = sector.getFaction("metelson");
        FactionAPI immortallight = sector.getFaction("immortallight");
        FactionAPI approlight = sector.getFaction("approlight");
        FactionAPI legioinfernalis = sector.getFaction("tahlan_legioinfernalis");
        FactionAPI exlane = sector.getFaction("exlane");
        FactionAPI HMI = sector.getFaction("HMI");
        FactionAPI brighton = sector.getFaction("brighton");
        FactionAPI draco = sector.getFaction("draco");
        FactionAPI fang = sector.getFaction("fang");
        FactionAPI ocua = sector.getFaction("ocua");
        FactionAPI neutrinocorp = sector.getFaction("neutrinocorp");
        FactionAPI anvil_industries = sector.getFaction("anvil_industries");
        FactionAPI blackjack = sector.getFaction("blackjack");
        FactionAPI exalted = sector.getFaction("exalted");
        FactionAPI kingdom_of_terra = sector.getFaction("kingdom_of_terra");
        FactionAPI almighty_dollar = sector.getFaction("almighty_dollar");
        FactionAPI science_fuckers = sector.getFaction("science_fuckers");
        FactionAPI communist_clouds = sector.getFaction("communist_clouds");
        FactionAPI ashen_keepers = sector.getFaction("ashen_keepers");
        FactionAPI warhawk_republic = sector.getFaction("warhawk_republic");
        FactionAPI scalartech = sector.getFaction("scalartech");
        FactionAPI templars = sector.getFaction("templars");
        FactionAPI fpe = sector.getFaction("fpe");
        FactionAPI prv = sector.getFaction("prv");
        FactionAPI rb = sector.getFaction("rb");
        FactionAPI ice = sector.getFaction("sun_ice");
        FactionAPI ici = sector.getFaction("sun_ici");
        FactionAPI galaxytigers = sector.getFaction("galaxytigers");
        FactionAPI pearson_exotronics = sector.getFaction("pearson_exotronics");
        FactionAPI polaris = sector.getFaction("plsp");
        FactionAPI xhan = sector.getFaction("xhanempire");
        FactionAPI borken = sector.getFaction("fob");
        FactionAPI cmc = sector.getFaction("cmc");
        FactionAPI animebad1 = sector.getFaction("yrxp");
        FactionAPI animebad2 = sector.getFaction("mkn");
        FactionAPI animebad3 = sector.getFaction("ryaz");
        FactionAPI animebad4 = sector.getFaction("aria");
        FactionAPI feds = sector.getFaction("star_federation");

    //set relations

        if (sra != null) {
            aic.setRelationship(sra.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(sra.getId(), RepLevel.VENGEFUL);

        }
        if (thi != null) {
            aic.setRelationship(thi.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(thi.getId(), RepLevel.HOSTILE);

        }
        if (brdy != null) {
            aic.setRelationship(brdy.getId(), RepLevel.HOSTILE);
            air.setRelationship(brdy.getId(), RepLevel.VENGEFUL);

        }
        if (sixieme != null) {
            aic.setRelationship(sixieme.getId(), RepLevel.HOSTILE);
            air.setRelationship(sixieme.getId(), RepLevel.VENGEFUL);

        }
        if (dme != null) {
            aic.setRelationship(dme.getId(), RepLevel.HOSTILE);
            air.setRelationship(dme.getId(), RepLevel.VENGEFUL);

        }
        if (bb != null) {
            aic.setRelationship(bb.getId(), RepLevel.HOSTILE);
            air.setRelationship(bb.getId(), RepLevel.VENGEFUL);

        }
        if (da != null) {
            aic.setRelationship(da.getId(), RepLevel.HOSTILE);
            air.setRelationship(da.getId(), RepLevel.VENGEFUL);
        }

        if (ora != null) {
            aic.setRelationship(ora.getId(), RepLevel.HOSTILE);
            air.setRelationship(ora.getId(), RepLevel.VENGEFUL);
        }

        if (sylphon != null) {
            aic.setRelationship(sylphon.getId(), RepLevel.NEUTRAL);
            air.setRelationship(sylphon.getId(), RepLevel.HOSTILE);
        }

        if (cabal != null) {
            aic.setRelationship(cabal.getId(), RepLevel.NEUTRAL);
            air.setRelationship(cabal.getId(), RepLevel.HOSTILE);
        }
        if (scy != null) {
            aic.setRelationship(scy.getId(), RepLevel.FAVORABLE);
            air.setRelationship(scy.getId(), RepLevel.HOSTILE);
        }

        if (II != null) {
            aic.setRelationship(II.getId(), RepLevel.INHOSPITABLE);
            air.setRelationship(II.getId(), RepLevel.HOSTILE);
        }
        if (maya != null) {
            aic.setRelationship(maya.getId(), RepLevel.FAVORABLE);
            air.setRelationship(maya.getId(), RepLevel.HOSTILE);
        }
        if (ars != null) {
            aic.setRelationship(ars.getId(), RepLevel.NEUTRAL);
            air.setRelationship(ars.getId(), RepLevel.HOSTILE);
        }
        if (sad != null) {
            aic.setRelationship(sad.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(sad.getId(), RepLevel.HOSTILE);
        }
        if (gmda != null) {
            aic.setRelationship(gmda.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(gmda.getId(), RepLevel.HOSTILE);
        }
        if (gmdap != null) {
            aic.setRelationship(gmdap.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(gmdap.getId(), RepLevel.HOSTILE);
        }
        if (kadur != null) {
            aic.setRelationship(kadur.getId(), RepLevel.FAVORABLE);
            air.setRelationship(kadur.getId(), RepLevel.HOSTILE);
        }
        if (mayorate != null) {
            aic.setRelationship(mayorate.getId(), RepLevel.NEUTRAL);
            air.setRelationship(mayorate.getId(), RepLevel.HOSTILE);
        }
        if (junk != null) {
            aic.setRelationship(junk.getId(), RepLevel.INHOSPITABLE);
            air.setRelationship(junk.getId(), RepLevel.HOSTILE);
        }
        if (asp != null) {
            aic.setRelationship(asp.getId(), RepLevel.HOSTILE);
            air.setRelationship(asp.getId(), RepLevel.VENGEFUL);
        }
        if (pack != null) {
            aic.setRelationship(pack.getId(), RepLevel.FAVORABLE);
            air.setRelationship(pack.getId(), RepLevel.INHOSPITABLE);
        }
        if (metel != null) {
            aic.setRelationship(metel.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(metel.getId(), RepLevel.HOSTILE);
        }
        if (approlight != null) {
            aic.setRelationship(approlight.getId(), RepLevel.INHOSPITABLE);
            air.setRelationship(approlight.getId(), RepLevel.HOSTILE);
        }
        if (immortallight != null) {
            aic.setRelationship(immortallight.getId(), RepLevel.HOSTILE);
            air.setRelationship(immortallight.getId(), RepLevel.HOSTILE);
        }
        if (legioinfernalis != null) {
            aic.setRelationship(legioinfernalis.getId(), RepLevel.HOSTILE);
            air.setRelationship(legioinfernalis.getId(), RepLevel.HOSTILE);
        }
        if (exlane != null) {
            aic.setRelationship(exlane.getId(), RepLevel.NEUTRAL);
            air.setRelationship(exlane.getId(), RepLevel.HOSTILE);
        }
        if (ocua != null) {
            aic.setRelationship(ocua.getId(), RepLevel.NEUTRAL);
            air.setRelationship(ocua.getId(), RepLevel.HOSTILE);
        }
        if (HMI != null) {
            aic.setRelationship(HMI.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(HMI.getId(), RepLevel.HOSTILE);
        }
        if (brighton != null) {
            aic.setRelationship(brighton.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(brighton.getId(), RepLevel.HOSTILE);
        }
        if (draco != null) {
            aic.setRelationship(draco.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(draco.getId(), RepLevel.HOSTILE);
        }
        if (fang != null) {
            aic.setRelationship(fang.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(fang.getId(), RepLevel.HOSTILE);
        }
        if (anvil_industries != null) {
            aic.setRelationship(anvil_industries.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(anvil_industries.getId(), RepLevel.HOSTILE);
        }
        if (neutrinocorp != null) {
            aic.setRelationship(neutrinocorp.getId(), RepLevel.INHOSPITABLE);
            air.setRelationship(neutrinocorp.getId(), RepLevel.HOSTILE);
        }
        if (blackjack != null) {
            aic.setRelationship(blackjack.getId(), RepLevel.NEUTRAL);
            air.setRelationship(blackjack.getId(), RepLevel.HOSTILE);
        }
        if (exalted != null) {
            aic.setRelationship(exalted.getId(), RepLevel.HOSTILE);
            air.setRelationship(exalted.getId(), RepLevel.HOSTILE);
        }
        if (kingdom_of_terra != null) {
            aic.setRelationship(kingdom_of_terra.getId(), RepLevel.NEUTRAL);
            air.setRelationship(kingdom_of_terra.getId(), RepLevel.HOSTILE);
        }
        if (almighty_dollar != null) {
            aic.setRelationship(almighty_dollar.getId(), RepLevel.HOSTILE);
            air.setRelationship(almighty_dollar.getId(), RepLevel.VENGEFUL);
        }
        if (science_fuckers != null) {
            aic.setRelationship(science_fuckers.getId(), RepLevel.FAVORABLE);
            air.setRelationship(science_fuckers.getId(), RepLevel.HOSTILE);
        }
        if (communist_clouds != null) {
            aic.setRelationship(communist_clouds.getId(), RepLevel.FAVORABLE);
            air.setRelationship(communist_clouds.getId(), RepLevel.FRIENDLY);
        }
        if (ashen_keepers != null) {
            aic.setRelationship(ashen_keepers.getId(), RepLevel.INHOSPITABLE);
            air.setRelationship(ashen_keepers.getId(), RepLevel.HOSTILE);
        }
        if (warhawk_republic != null) {
            aic.setRelationship(warhawk_republic.getId(), RepLevel.WELCOMING);
            air.setRelationship(warhawk_republic.getId(), RepLevel.INHOSPITABLE);
        }
        if (templars != null) {
            aic.setRelationship(templars.getId(), RepLevel.HOSTILE);
            air.setRelationship(templars.getId(), RepLevel.VENGEFUL);
        }
        if (scalartech != null) {
            aic.setRelationship(scalartech.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(scalartech.getId(), RepLevel.HOSTILE);
        }
        if (fpe != null) {
            aic.setRelationship(fpe.getId(), RepLevel.HOSTILE);
            air.setRelationship(fpe.getId(), RepLevel.VENGEFUL);
        }
        if (prv != null) {
            aic.setRelationship(prv.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(prv.getId(), RepLevel.HOSTILE);
        }
        if (rb != null) {
            aic.setRelationship(rb.getId(), RepLevel.NEUTRAL);
            air.setRelationship(rb.getId(), RepLevel.HOSTILE);
        }
        if (ice != null) {
            aic.setRelationship(ice.getId(), RepLevel.NEUTRAL);
            air.setRelationship(ice.getId(), RepLevel.HOSTILE);
        }
        if (ici != null) {
            aic.setRelationship(ici.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(ici.getId(), RepLevel.HOSTILE);
        }
        if (galaxytigers != null) {
            aic.setRelationship(galaxytigers.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(galaxytigers.getId(), RepLevel.HOSTILE);
        }
        if (pearson_exotronics != null) {
            aic.setRelationship(pearson_exotronics.getId(), RepLevel.HOSTILE);
            air.setRelationship(pearson_exotronics.getId(), RepLevel.VENGEFUL);
        }
        if (polaris != null) {
            aic.setRelationship(polaris.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(polaris.getId(), RepLevel.HOSTILE);
        }
        if (xhan != null) {
            aic.setRelationship(xhan.getId(), RepLevel.INHOSPITABLE);
            air.setRelationship(xhan.getId(), RepLevel.HOSTILE);
        }
        if (cmc != null) {
            aic.setRelationship(cmc.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(cmc.getId(), RepLevel.HOSTILE);
        }
        if (borken != null) {
            aic.setRelationship(borken.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(borken.getId(), RepLevel.HOSTILE);
        }
        if (animebad1 != null) {
            aic.setRelationship(animebad1.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(animebad1.getId(), RepLevel.HOSTILE);
        }
        if (animebad2 != null) {
            aic.setRelationship(animebad2.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(animebad2.getId(), RepLevel.HOSTILE);
        }
        if (animebad3 != null) {
            aic.setRelationship(animebad3.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(animebad3.getId(), RepLevel.HOSTILE);
        }
        if (animebad4 != null) {
            aic.setRelationship(animebad4.getId(), RepLevel.HOSTILE);
            air.setRelationship(animebad4.getId(), RepLevel.VENGEFUL);
        }
        if (feds != null) {
            aic.setRelationship(feds.getId(), RepLevel.SUSPICIOUS);
            air.setRelationship(feds.getId(), RepLevel.HOSTILE);
        }

    }

}
