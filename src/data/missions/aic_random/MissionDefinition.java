package data.missions.aic_random;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;
import org.lazywizard.lazylib.MathUtils;

public class MissionDefinition implements MissionDefinitionPlugin {

	private List ships = new ArrayList();
	private void addShip(String variant, int weight) {
		for (int i = 0; i < weight; i++) {
			ships.add(variant);
		}
	}

	private void generateFleet(int maxFP, FleetSide side, List ships, MissionDefinitionAPI api) {
		int currFP = 0;

		if (side == FleetSide.PLAYER) {
			String [] choices = {
					"aic_cap1_std",
					"aic_cap2_std",
					"aic_legionc_std",
					"aic_cruiser4_std",
					"aic_cruiser3_std",
					"aic_cruiser2_std",
                    "onslaught_Elite",
                    "paragon_Elite",
                    "odyssey_Balanced",
                    "doom_Strike"
			};
			String flagship = choices[(int) (Math.random() * (float) choices.length)];
			api.addToFleet(side, flagship, FleetMemberType.SHIP, true);
			currFP += api.getFleetPointCost(flagship);
		}

		while (true) {
			int index = (int)(Math.random() * ships.size());
			String id = (String) ships.get(index);
			currFP += api.getFleetPointCost(id);
			if (currFP > maxFP) {
				return;
			}

			if (id.endsWith("_wing")) {
				api.addToFleet(side, id, FleetMemberType.FIGHTER_WING, false);
			} else {
				api.addToFleet(side, id, FleetMemberType.SHIP, false);
			}
		}
	}



	//min max FP for fleets
    private static final float MAX_EXTRA_FP = 220f;
    private static final float MIN_EXTRA_FP = 20f;


 //This is a stupid solution
 //but I have more important stuff to work on so, Too Bad!

    public void defineMission(MissionDefinitionAPI api) {



		addShip("doom_Strike", 15);
		addShip("shade_Assault", 27);
		addShip("afflictor_Strike", 27);
		addShip("hyperion_Attack", 3);
		addShip("hyperion_Strike", 3);
		addShip("onslaught_Standard", 3);
		addShip("onslaught_Outdated", 3);
		addShip("onslaught_Elite", 12);
		addShip("astral_Elite", 3);
		addShip("astral_Strike", 3);
		addShip("astral_Attack", 15);
		addShip("paragon_Elite", 15);
		addShip("legion_Strike", 5);
		addShip("legion_Assault", 5);
		addShip("legion_Escort", 5);
		addShip("legion_FS", 10);
		addShip("odyssey_Balanced", 12);
		addShip("conquest_Elite", 13);
		addShip("eagle_Assault", 15);
		addShip("falcon_Attack", 15);
		addShip("venture_Balanced", 15);
		addShip("apogee_Balanced", 15);
		addShip("aurora_Balanced", 15);
		addShip("aurora_Balanced", 15);
		addShip("gryphon_FS", 17);
		addShip("gryphon_Standard", 17);
		addShip("mora_Assault", 12);
		addShip("mora_Strike", 13);
		addShip("mora_Support", 13);
		addShip("dominator_Assault", 25);
		addShip("dominator_Support", 25);
		addShip("medusa_Attack", 25);
		addShip("condor_Support", 25);
		addShip("condor_Strike", 25);
		addShip("condor_Attack", 25);
		addShip("enforcer_Assault", 25);
		addShip("enforcer_CS", 25);
		addShip("hammerhead_Balanced", 20);
		addShip("hammerhead_Elite", 15);
		addShip("drover_Strike", 20);
		addShip("sunder_CS", 20);
		addShip("gemini_Standard", 20);
		addShip("buffalo2_FS", 20);
		addShip("lasher_CS", 40);
		addShip("lasher_Standard", 40);
		addShip("hound_Standard", 25);
		addShip("tempest_Attack", 25);
		addShip("brawler_Assault", 25);
		addShip("wolf_CS", 20);
		addShip("hyperion_Strike", 10);
		addShip("vigilance_Standard", 20);
		addShip("vigilance_FS", 25);
		addShip("tempest_Attack", 2);
		addShip("brawler_Assault", 10);


		addShip("aic_frig1_std", 10);
		addShip("aic_frig1_elite", 10);
		addShip("aic_frig1_sup", 10);
		addShip("aic_frig2_std", 10);
		addShip("aic_frig2_elite", 10);
		addShip("aic_frig2_sup", 10);
		addShip("aic_frig3_std", 10);
		addShip("aic_frig3_elite", 10);
		addShip("aic_frig3_sup", 10);
		addShip("aic_frig4_std", 10);
		addShip("aic_frig4_elite", 10);
		addShip("aic_frig4_sup", 10);
		addShip("aic_frig5_std", 10);
		addShip("aic_frig5_elite", 10);
		addShip("aic_frig5_sup", 10);
		addShip("aic_dess1_std", 10);
		addShip("aic_dess1_elite", 10);
		addShip("aic_dess1_sup", 10);
		addShip("aic_dess2_std", 10);
		addShip("aic_dess2_elite", 10);
		addShip("aic_dess2_sup", 10);
		addShip("aic_dess3_std", 10);
		addShip("aic_dess3_elite", 10);
		addShip("aic_dess3_sup", 10);
		addShip("aic_cruiser1_sup", 10);
		addShip("aic_cruiser1_elite", 10);
		addShip("aic_cruiser1_std", 10);
		addShip("aic_cruiser2_sup", 10);
		addShip("aic_cruiser2_elite", 10);
		addShip("aic_cruiser2_std", 10);
		addShip("aic_cruiser3_sup", 10);
		addShip("aic_cruiser3_elite", 10);
		addShip("aic_cruiser3_std", 10);
		addShip("aic_cruiser4_sup", 10);
		addShip("aic_cruiser4_elite", 10);
		addShip("aic_cruiser4_std", 10);
		addShip("aic_carrierc1_sup", 10);
		addShip("aic_carrierc1_elite", 10);
		addShip("aic_carrierc1_std", 10);
		addShip("aic_carrierd1_sup", 10);
		addShip("aic_carrierd1_elite", 10);
		addShip("aic_carrierd1_std", 10);
		addShip("aic_freighterc1_sup", 10);
		addShip("aic_freighterc1_elite", 10);
		addShip("aic_freighterc1_std", 10);
		addShip("aic_freighterd1_sup", 10);
		addShip("aic_freighterd1_elite", 10);
		addShip("aic_freighterd1_std", 10);
		addShip("aic_freighterf1_sup", 10);
		addShip("aic_freighterf1_elite", 10);
		addShip("aic_freighterf1_std", 10);
		addShip("aic_legionc_sup", 5);
		addShip("aic_legionc_elite", 5);
		addShip("aic_legionc_std", 5);
		addShip("aic_cap1_sup", 5);
		addShip("aic_cap1_elite", 5);
		addShip("aic_cap1_std", 5);
		addShip("aic_cap2_sup", 5);
		addShip("aic_cap2_elite", 5);
		addShip("aic_cap2_std", 5);

		//FP randomizer
		float FLEET_FP = MathUtils.getRandomNumberInRange(MIN_EXTRA_FP, MAX_EXTRA_FP);

		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "ISS", FleetGoal.ATTACK, false, 5);
		api.initFleet(FleetSide.ENEMY, "ISS", FleetGoal.ATTACK, true, 5);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Your forces");
		api.setFleetTagline(FleetSide.ENEMY, "Enemy forces");

		// These show up as items in the bulleted list under
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Defeat all enemy forces");




		// Set up the fleets
		generateFleet(30 + (int)((FLEET_FP)), FleetSide.PLAYER, ships, api);
		generateFleet(30 + (int)((FLEET_FP)), FleetSide.ENEMY, ships, api);


		// Set up the map.
		float width = 24000f;
		float height = 18000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);

		float minX = -width/2;
		float minY = -height/2;


		for (int i = 0; i < 50; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 100f + (float) Math.random() * 400f;
			api.addNebula(x, y, radius);
		}

		// Add objectives
		api.addObjective(minX + width * 0.25f + 2000, minY + height * 0.25f + 2000, "nav_buoy");
		api.addObjective(minX + width * 0.75f - 2000, minY + height * 0.25f + 2000, "comm_relay");
		api.addObjective(minX + width * 0.75f - 2000, minY + height * 0.75f - 2000, "nav_buoy");
		api.addObjective(minX + width * 0.25f + 2000, minY + height * 0.75f - 2000, "comm_relay");
		api.addObjective(minX + width * 0.5f, minY + height * 0.5f, "sensor_array");

		String [] planets = {"barren", "terran", "gas_giant", "ice_giant", "cryovolcanic", "frozen", "jungle", "desert", "arid"};
		String planet = planets[(int) (Math.random() * (double) planets.length)];
		float radius = 100f + (float) Math.random() * 150f;
		api.addPlanet(0, 0, radius, planet, 200f, true);
	}

}





