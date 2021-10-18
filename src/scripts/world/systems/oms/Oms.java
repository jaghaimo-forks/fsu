package data.scripts.world.systems.oms;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.ids.StarTypes;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;


public class Oms {

    public void generate(SectorAPI sector) {
        StarSystemAPI system = sector.createStarSystem("Old Man");
        system.getLocation().set(1000, -24200);
        system.setBackgroundTextureFilename("graphics/backgrounds/aic_background1.jpg");

        // Main star

		PlanetAPI oms = system.initStar("aic_oms", // unique id for this star 
						StarTypes.BLUE_GIANT,  // id in planets.json
						1500f, 		  // radius (in pixels at default zoom)
						650); // corona radius, from star edge
				oms.setName("Old Man Star");

		
						
		system.setLightColor(new Color(201, 253, 255));


		//Perihelion jungle FSU
        PlanetAPI peri = system.addPlanet("aic_peri", oms, "Perihelion", "jungle", 80, 250, 6200, 220);
		peri.getSpec().setAtmosphereColor(new Color(168,121,34,150));
		peri.getSpec().setCloudColor(new Color(255,250,195,200));
		peri.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
		peri.getSpec().setGlowColor(new Color(255,128,16,255));
		peri.getSpec().setPlanetColor(new Color(207,130,83,255));
		peri.getSpec().setUseReverseLightForGlow(true);
		peri.applySpecChanges();
		peri.setCustomDescriptionId("aic_peri");
		peri.setInteractionImage("illustrations", "aic_eventide");

		//high command
		SectorEntityToken aicStation = system.addCustomEntity("aic_phc", "Union High Command", "station_lowtech2", "aic");
		aicStation.setCircularOrbitPointingDown(system.getEntityById("aic_peri"), 0, 350, 30);
		aicStation.setInteractionImage("illustrations", "orbital");
		aicStation.setCustomDescriptionId("aic_phc");


		//Aphelion cryovolcanic Pirates
        PlanetAPI ap = system.addPlanet("aic_ap", oms, "Aphelion", "cryovolcanic", 250, 200, 14500, 600);
		ap.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "sindria"));
		ap.applySpecChanges();
		ap.setCustomDescriptionId("aic_ap");
		ap.setInteractionImage("illustrations", "aic_vacuum_colony");
		
		//lava_minor
        PlanetAPI rens = system.addPlanet("aic_rens", oms, "Rens", "lava_minor", 150, 150, 2500, 60);
		rens.setCustomDescriptionId(null);
		
		
		//barren Revolution
        PlanetAPI hek = system.addPlanet("aic_hek", oms, "Hek", "barren", 100, 120, 13000, 500);
		hek.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "barren"));
		peri.getSpec().setPlanetColor(new Color(207,91,83,255));
		hek.applySpecChanges();
        hek.setCustomDescriptionId("aic_hek");
		hek.setInteractionImage("illustrations", "aic_raid_plunder");
		

        //Raa forge volcanic FSU
        PlanetAPI raa = system.addPlanet("aic_raa", oms, "Raa Forge", "lava_minor", 280, 200, 4000, 100);
		raa.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "barren"));
		raa.getSpec().setAtmosphereColor(new Color(168,121,34,150));
		raa.applySpecChanges();
        raa.setCustomDescriptionId("aic_raa");
		raa.setInteractionImage("illustrations", "aic_urban01");
        
		//monitor TT
		SectorEntityToken monitor = system.addCustomEntity("aic_monitor", "Verge Monitor", "station_hightech2", "tritachyon");
		monitor.setInteractionImage("illustrations", "aic_corporate_lobby");
		monitor.setCircularOrbitWithSpin(oms, 0, 11500, 450, 2, 5);
		monitor.setCustomDescriptionId("aic_monitor");
		
		
		
        SectorEntityToken relay = system.addCustomEntity("aic_oms_relay", "Old Man Star Relay", "comm_relay", "aic");
        relay.setCircularOrbit(oms, 300, 8700, 120);
		
		SectorEntityToken buoy = system.addCustomEntity("aic_oms_buoy", "Old Man Star Nav Buoy", "nav_buoy_makeshift", "aic");
        buoy.setCircularOrbit(oms, 100, 8700, 120);
        
        SectorEntityToken stableloc3 = system.addCustomEntity(null,null, "stable_location",Factions.NEUTRAL); 
		stableloc3.setCircularOrbitPointingDown(oms, 180, 8700, 120);

		// Main asteroid belts and its decorations
        system.addAsteroidBelt(oms, 100, 8000, 300, 460, 500);
        system.addRingBand(oms, "misc", "rings_ice0", 280f, 1, Color.orange, 280f, 8000, 200);
		
		system.addAsteroidBelt(oms, 100, 3400, 300, 460, 500);
        system.addRingBand(oms, "misc", "rings_dust0", 280f, 1, Color.blue, 280f, 3400, 200);

        // Inner jump point
		JumpPointAPI jumpPoint1 = Global.getFactory().createJumpPoint("aic_oms_inner_jump", "Old Man Star Inner System Jump-point");
		OrbitAPI orbit1 = Global.getFactory().createCircularOrbit(oms, 170, 2850, 75);
		jumpPoint1.setOrbit(orbit1);
		jumpPoint1.setRelatedPlanet(rens);
		jumpPoint1.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint1);

		// Jump point near Perihelion
		JumpPointAPI jumpPoint2 = Global.getFactory().createJumpPoint("aic_oms_peri_jump", "Perihelion Jump-point");
		OrbitAPI orbit2 = Global.getFactory().createCircularOrbit(oms, 70, 7500, 300);
		jumpPoint2.setOrbit(orbit2);
		jumpPoint2.setRelatedPlanet(peri);
		jumpPoint2.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint2);       

        float radiusAfter = StarSystemGenerator.addOrbitingEntities(system, oms, StarAge.AVERAGE,
                                                                    3, 4, // min/max entities to add
                                                                    15000, // radius to start adding at
                                                                    5, // name offset - next planet will be <system name> <roman numeral of this parameter + 1>
                                                                    true); // whether to use custom or system-name based names

        system.autogenerateHyperspaceJumpPoints(true, true);
		
		//Get rid of hyperspace nebula
        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin();
        NebulaEditor editor = new NebulaEditor(plugin);
        float minRadius = plugin.getTileSize() * 2f;

        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f, 0.35f);
    }


}
