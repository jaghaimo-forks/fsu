package data.missions.aic_catch1;

import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "LSS", FleetGoal.ATTACK, false);
		api.initFleet(FleetSide.ENEMY, "FSUN", FleetGoal.ESCAPE, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Church Long Range Patrol");
		api.setFleetTagline(FleetSide.ENEMY, "Union Navy Resupply Mission");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Destroy the pesky pirates");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
		
		
		api.addToFleet(FleetSide.PLAYER, "aic_falc_catch", FleetMemberType.SHIP, "LSS Salvation", true);
		api.addToFleet(FleetSide.PLAYER, "aic_falc_catch", FleetMemberType.SHIP, "LSS Knight's Vow", false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.PLAYER, "hammerhead_Elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "enforcer_Elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "drover_Starting", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);	
		api.addToFleet(FleetSide.PLAYER, "drover_Starting", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);	
		api.addToFleet(FleetSide.PLAYER, "condor_Support", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);	
		
		api.addToFleet(FleetSide.PLAYER, "centurion_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);		
		api.addToFleet(FleetSide.PLAYER, "centurion_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);		
		api.addToFleet(FleetSide.PLAYER, "centurion_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);		
		api.addToFleet(FleetSide.PLAYER, "lasher_luddic_church_Standard", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "lasher_luddic_church_Standard", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "lasher_luddic_church_Standard", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);		
		api.addToFleet(FleetSide.PLAYER, "hound_luddic_church_Standard", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "hound_luddic_church_Standard", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "hound_luddic_church_Standard", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);


		
		// Set up the enemy fleet.
		
		api.addToFleet(FleetSide.ENEMY, "aic_cruiser1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "aic_carrierd1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
						
		api.addToFleet(FleetSide.ENEMY, "aic_dess2_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "aic_dess1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "aic_dess1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.ENEMY, "aic_frig5_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "aic_frig3_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "aic_frig1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
						
		api.addToFleet(FleetSide.ENEMY, "aic_colossusc_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "aic_buffaloc_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "aic_buffaloc_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "aic_buffaloc_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "aic_kitec_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "aic_phaetonc_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);

		
		// Set up the map.
		float width = 10000f;
		float height = 24000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// Add an asteroid field
		api.addAsteroidField(minX, minY + height / 2, 0, 8000f,
							 20f, 70f, 100);
		
		api.addObjective(minX + width * 0.8f, minY + height * 0.6f, "nav_buoy");
		api.addObjective(minX + width * 0.3f, minY + height * 0.3f, "nav_buoy");
		
		
		api.addPlanet(0, 0, 50f, "star_orange", 100f, true);
		
	}

}
