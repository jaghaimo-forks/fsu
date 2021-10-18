package data.missions.aic_raid;

import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "FSUN", FleetGoal.ATTACK, false);
		api.initFleet(FleetSide.ENEMY, "HSS", FleetGoal.ATTACK, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Union Navy Raid Party");
		api.setFleetTagline(FleetSide.ENEMY, "Hegemony System Patrol");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("FSUN Moros must survive");
		api.addBriefingItem("Destroy the patrol");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters

		api.defeatOnShipLoss("FSUN Moros");


		api.addToFleet(FleetSide.PLAYER, "aic_cruiser4_elite", FleetMemberType.SHIP, "FSUN Moros", true);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser2_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser2_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);

		api.addToFleet(FleetSide.PLAYER, "aic_dess2_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess2_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess2_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.PLAYER, "aic_frig2_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig2_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig2_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig4_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig5_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
	
		
		// Set up the enemy fleet.
		
		
		
		api.addToFleet(FleetSide.ENEMY, "dominator_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "dominator_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "falcon_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "falcon_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);

		api.addToFleet(FleetSide.ENEMY, "hammerhead_Support", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Support", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
						
		api.addToFleet(FleetSide.ENEMY, "heron_Attack", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		
		api.addToFleet(FleetSide.ENEMY, "lasher_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "lasher_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "lasher_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "lasher_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "brawler_Elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);

		
		// Set up the map.
		float width = 12000f;
		float height = 12000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// Add an asteroid field
		api.addAsteroidField(minX, minY + height / 2, 0, 8000f,
							 20f, 70f, 100);
		
		api.addObjective(minX + width * 0.15f + 6000, minY + height * 0.3f + 2800, "sensor_array");
		api.addObjective(minX + width * 0.8f - 6000, minY + height * 0.3f + 2600, "nav_buoy");
		
		
		api.addPlanet(0, 0, 30f, "star_yellow", 100f, true);
		
	}

}
