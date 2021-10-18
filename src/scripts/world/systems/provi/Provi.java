package scripts.world.systems.provi;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.impl.campaign.ids.Terrain;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.ids.StarTypes;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.MagneticFieldTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;


public class Provi {

    public void generate(SectorAPI sector) {
        StarSystemAPI system = sector.createStarSystem("Providence");
        system.getLocation().set(-5000,-7800);
        system.setBackgroundTextureFilename("graphics/backgrounds/aic_background6.jpg");

        // Main star

		PlanetAPI provi = system.initStar("aic_provi", // unique id for this star 
						StarTypes.ORANGE,  // id in planets.json
						800f, 		  // radius (in pixels at default zoom)
						350); // corona radius, from star edge
		
						
		system.setLightColor(new Color(255,236,201));


		//Ark Terran Luddic
        PlanetAPI ark = system.addPlanet("aic_ark", provi, "Ark", "terran", 10, 150, 5000, 200);
		ark.getSpec().setAtmosphereColor(new Color(112,191,224,150));
		ark.getSpec().setCloudColor(new Color(195,236,255,200));
		ark.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "volturn"));
		ark.applySpecChanges();
		ark.setCustomDescriptionId("aic_ark");
		ark.setInteractionImage("illustrations", "aic_luddic_church");					
		
		//rocky
        PlanetAPI avesber = system.addPlanet("aic_avesber", provi, "Avesber", "rocky_unstable", 250, 50, 1500, 40);
		avesber.setCustomDescriptionId(null);
		
		
		//toxic
        PlanetAPI pator = system.addPlanet("aic_pator", provi, "Pator", "toxic", 200, 80, 2000, 60);
		pator.setCustomDescriptionId(null);
		

        //Naxos barren-desert Persean
        PlanetAPI naxos = system.addPlanet("aic_naxos", provi, "Naxos", "barren-desert", 280, 200, 3500, 100);
		naxos.getSpec().setAtmosphereColor(new Color(168,121,34,150));
		naxos.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "barren"));
		naxos.applySpecChanges();
        naxos.setCustomDescriptionId("aic_naxos");
		naxos.setInteractionImage("illustrations", "aic_mine");
        
		
		
		//Acapolco Desert Hegemony
        PlanetAPI aca = system.addPlanet("aic_aca", provi, "Acapolco", "desert1", 100, 300, 7000, 300);
		aca.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "barren"));
		aca.getSpec().setAtmosphereColor(new Color(168,143,34,150));
		aca.applySpecChanges();
		aca.setCustomDescriptionId("aic_aca");
		aca.setInteractionImage("illustrations", "aic_industrial_megafacility");
		
		
		//Gas giant Heydieles
		PlanetAPI gas = system.addPlanet("aic_heyd", provi, "Heydieles", "gas_giant", 145, 350, 10000, 500);
		gas.getSpec().setPlanetColor(new Color(171, 144, 118,255));
		gas.getSpec().setAtmosphereColor(new Color(250,232,210,250));
		gas.getSpec().setCloudColor(new Color(220, 232, 250,200));
		gas.getSpec().setPitch(-9f);
		gas.getSpec().setTilt(-20f);
		gas.applySpecChanges();

		SectorEntityToken barad_field = system.addTerrain(Terrain.MAGNETIC_FIELD,
				new MagneticFieldTerrainPlugin.MagneticFieldParams(gas.getRadius() + 150f, // terrain effect band width
						(gas.getRadius() + 150f) / 2f, // terrain effect middle radius
						gas, // entity that it's around
						gas.getRadius() + 50f, // visual band start
						gas.getRadius() + 50f + 200f, // visual band end
						new Color(50, 20, 100, 40), // base color
						0.5f, // probability to spawn aurora sequence, checked once/day when no aurora in progress
						new Color(119, 63, 252),
						new Color(203, 82, 255),
						new Color(137, 107, 255),
						new Color(140, 190, 210),
						new Color(90, 200, 170),
						new Color(65, 230, 160),
						new Color(20, 220, 70)
				));
		barad_field.setCircularOrbit(gas, 0, 0, 100);
		
		system.addRingBand(gas, "misc", "rings_dust0", 256f, 2, Color.orange, 256f, 600, 31f);
		system.addRingBand(gas, "misc", "rings_ice0", 256f, 3, Color.lightGray, 256f, 750, 35f);
			
		
		
		
		//rocky pirates
		PlanetAPI gas1 = system.addPlanet("aic_olo", gas, "Olo", "rocky_ice", 0, 65, 1000, 40);
		gas1.getSpec().setPlanetColor(new Color(255,251,222,255));
		gas1.applySpecChanges();
		gas1.setCustomDescriptionId("aic_olo");
		gas1.setInteractionImage("illustrations", "aic_vacuum_colony");
		
		//citadel Free Stars Union
		SectorEntityToken citadel = system.addCustomEntity("aic_citadel", "Citadel Providence", "station_lowtech3", "aic");
		citadel.setInteractionImage("illustrations", "aic_raid_preparation");
		citadel.setCircularOrbitWithSpin(gas, 200, 1300, 150, 2, 5);
		citadel.setCustomDescriptionId("aic_citadel");
		
		
        SectorEntityToken relay = system.addCustomEntity("aic_provi_relay", "Providence Relay", "comm_relay", "hegemony");
        relay.setCircularOrbit(provi, 300, 12700, 320);
		
		SectorEntityToken buoy = system.addCustomEntity("aic_provi_buoy", "Providence Nav Buoy", "nav_buoy_makeshift", "aic");
        buoy.setCircularOrbit(provi, 100, 12700, 320);
		
		SectorEntityToken sensor = system.addCustomEntity("aic_provi_sensor", "Providence Sensor Array", "sensor_array_makeshift", "persean");
        sensor.setCircularOrbit(provi, 180, 12700, 320);
        
        //SectorEntityToken stableloc3 = system.addCustomEntity(null,null, "stable_location",Factions.NEUTRAL); 
		//stableloc3.setCircularOrbitPointingDown(provi, 180, 12700, 320);

		// Main asteroid belts and its decorations
        //system.addAsteroidBelt(provi, 100, 6200, 300, 460, 500);
        //system.addRingBand(provi, "misc", "rings_dust0", 280f, 1, Color.orange, 280f, 6200, 200);
		
		system.addAsteroidBelt(provi, 100, 2400, 300, 460, 500);
        system.addRingBand(provi, "misc", "rings_dust0", 280f, 1, Color.orange, 280f, 2400, 200);

        // Inner jump point
		JumpPointAPI jumpPoint1 = Global.getFactory().createJumpPoint("aic_provi_inner_jump", "Providence Inner System Jump-point");
		OrbitAPI orbit1 = Global.getFactory().createCircularOrbit(provi, 170, 1500, 70);
		jumpPoint1.setOrbit(orbit1);
		jumpPoint1.setRelatedPlanet(avesber);
		jumpPoint1.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint1);

		// Jump point near Ark
		JumpPointAPI jumpPoint2 = Global.getFactory().createJumpPoint("aic_provi_ark_jump", "Ark Jump-point");
		OrbitAPI orbit2 = Global.getFactory().createCircularOrbit(provi, 300, 5800, 300);
		jumpPoint2.setOrbit(orbit2);
		jumpPoint2.setRelatedPlanet(ark);
		jumpPoint2.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint2);       

        float radiusAfter = StarSystemGenerator.addOrbitingEntities(system, provi, StarAge.AVERAGE,
                                                                    3, 4, // min/max entities to add
                                                                    13000, // radius to start adding at
                                                                    6, // name offset - next planet will be <system name> <roman numeral of this parameter + 1>
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
