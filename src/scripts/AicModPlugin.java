package scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.PluginPick;
import com.fs.starfarer.api.campaign.CampaignPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.MissileAIPlugin;
import com.fs.starfarer.api.combat.MissileAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.ids.Skills;
import scripts.world.systems.AicGen;
import org.dark.shaders.light.LightData;
import org.dark.shaders.util.ShaderLib;
import org.dark.shaders.util.TextureData;
import scripts.ai.*;
import scripts.campaign.aic_protspawner;
import scripts.campaign.fleets.AirFleetManager;
import scripts.campaign.fleets.DisposableAirFleetManager;
import exerelin.campaign.SectorManager;


public class AicModPlugin extends BaseModPlugin {

    public static final String aic_minireaper = "aic_minireaper";
    public static final String aic_minireaper_fi = "aic_minireaper_fi";
    public static final String aic_cruisel = "aic_cruisel";
    public static final String aic_cruisem = "aic_cruisem";
    public static final String aic_ssms = "aic_ssms";
    public static final String aic_hook = "aic_hook";



    @Override
    public PluginPick<MissileAIPlugin> pickMissileAI(MissileAPI missile, ShipAPI launchingShip) {
        switch (missile.getProjectileSpecId()) {
            case aic_minireaper:
                return new PluginPick<MissileAIPlugin>(new aicScytheAI(missile, launchingShip), CampaignPlugin.PickPriority.MOD_SPECIFIC);
            case aic_minireaper_fi:
                return new PluginPick<MissileAIPlugin>(new aicScytheAI(missile, launchingShip), CampaignPlugin.PickPriority.MOD_SPECIFIC);
            case aic_ssms:
                return new PluginPick<MissileAIPlugin>(new aicBarghestAI(missile, launchingShip), CampaignPlugin.PickPriority.MOD_SPECIFIC);
            case aic_cruisem:
                return new PluginPick<MissileAIPlugin>(new aicNovaAI(missile, launchingShip), CampaignPlugin.PickPriority.MOD_SPECIFIC);
            case aic_cruisel:
                return new PluginPick<MissileAIPlugin>(new aicNovaAI(missile, launchingShip), CampaignPlugin.PickPriority.MOD_SPECIFIC);
            case aic_hook:
                return new PluginPick<MissileAIPlugin>(new aicHookAI(missile, launchingShip), CampaignPlugin.PickPriority.MOD_SPECIFIC);


        }
        return null;
    }

    @Override
        public void onApplicationLoad() throws ClassNotFoundException {
            try {
                Global.getSettings().getScriptClassLoader().loadClass("org.dark.shaders.util.ShaderLib");
                ShaderLib.init();
                TextureData.readTextureDataCSV("data/lights/aic_bump.csv");
                LightData.readLightDataCSV("data/lights/aic_light.csv");
            } catch (ClassNotFoundException ex) { }
        }




    @Override
    public void onNewGame() {
        boolean haveNexerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
        if (!haveNexerelin || SectorManager.getCorvusMode()){
            new AicGen().generate(Global.getSector());
        }
    }

    @Override
    public void onNewGameAfterProcGen() {
        aic_protspawner.spawnprots(Global.getSector());

    }

    @Override
    public void onNewGameAfterEconomyLoad() {
        if(Global.getSettings().getModManager().isModEnabled("deconomics")){

            MarketAPI market = Global.getSector().getEconomy().getMarket("aic_peri");
            if(market == null) return;
            if(!market.hasIndustry("deconomics_dryDock")) market.addIndustry("deconomics_dryDock");

            MarketAPI market2 = Global.getSector().getEconomy().getMarket("aic_citadel");
            if(market2 == null) return;
            if(!market2.hasIndustry("deconomics_pirateHaven")) market2.addIndustry("deconomics_pirateHaven");
            if(!market2.hasIndustry("deconomics_pirateHavenSecondary")) market2.addIndustry("deconomics_pirateHavenSecondary");
        }
        if(Global.getSettings().getModManager().isModEnabled("IndEvo")){

            MarketAPI market = Global.getSector().getEconomy().getMarket("aic_peri");
            if(market == null) return;
            if(!market.hasIndustry("IndEvo_dryDock")) market.addIndustry("IndEvo_dryDock");

            MarketAPI market2 = Global.getSector().getEconomy().getMarket("aic_citadel");
            if(market2 == null) return;
            if(!market2.hasIndustry("IndEvo_pirateHaven")) market2.addIndustry("IndEvo_pirateHaven");
            if(!market2.hasIndustry("IndEvo_pirateHavenSecondary")) market2.addIndustry("IndEvo_pirateHavenSecondary");
        }
        {
            {
                MarketAPI market = null;

                market = Global.getSector().getEconomy().getMarket("aic_peri");
                if (market != null && market.getFactionId().equals("aic")) {
                    PersonAPI person = Global.getFactory().createPerson();
                    person.setFaction("aic");
                    person.setGender(FullName.Gender.MALE);
                    person.setRankId(Ranks.FACTION_LEADER);
                    person.setPostId(Ranks.POST_FACTION_LEADER);
                    person.getName().setFirst("Aku");
                    person.getName().setLast("Ganymede");
                    person.setPortraitSprite("graphics/portraits/portrait38.png");
                    person.getStats().setSkillLevel(Skills.SPACE_OPERATIONS, 1);
                    person.getStats().setSkillLevel(Skills.PLANETARY_OPERATIONS, 1);

                    market.setAdmin(person);
                    market.getCommDirectory().addPerson(person, 0);
                    market.addPerson(person);
                }
                MarketAPI market2 = null;

                market2 = Global.getSector().getEconomy().getMarket("aic_citadel");
                if (market2 != null && market2.getFactionId().equals("aic")) {
                    PersonAPI person = Global.getFactory().createPerson();
                    person.setFaction("aic");
                    person.setGender(FullName.Gender.MALE);
                    person.setRankId(Ranks.SPACE_ADMIRAL);
                    person.setPostId(Ranks.POST_BASE_COMMANDER);
                    person.getName().setFirst("Alistar");
                    person.getName().setLast("Deimos");
                    person.setPortraitSprite("graphics/portraits/portrait_pirate01.png");
                    person.getStats().setSkillLevel(Skills.SPACE_OPERATIONS, 1);
                    person.getStats().setSkillLevel(Skills.PLANETARY_OPERATIONS, 1);

                    market2.setAdmin(person);
                    market2.getCommDirectory().addPerson(person, 0);
                    market2.addPerson(person);
                }
                MarketAPI market3 = null;

                market3 = Global.getSector().getEconomy().getMarket("aic_hek");
                if (market3 != null && market3.getFactionId().equals("air")) {
                    PersonAPI person = Global.getFactory().createPerson();
                    person.setFaction("air");
                    person.setGender(FullName.Gender.MALE);
                    person.setRankId(Ranks.FACTION_LEADER);
                    person.setPostId(Ranks.POST_FACTION_LEADER);
                    person.getName().setFirst("Viktor");
                    person.getName().setLast("Mainyu");
                    person.setPortraitSprite("graphics/portraits/portrait_mercenary04.png");
                    person.getStats().setSkillLevel(Skills.SPACE_OPERATIONS, 1);
                    person.getStats().setSkillLevel(Skills.PLANETARY_OPERATIONS, 1);

                    market3.setAdmin(person);
                    market3.getCommDirectory().addPerson(person, 0);
                    market3.addPerson(person);
                }
            }
        }

    }

    @Override
    public void onNewGameAfterTimePass() {

     Global.getSector().addScript(new AirFleetManager());
     Global.getSector().addScript(new DisposableAirFleetManager());

    }

}

