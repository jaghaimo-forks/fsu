package data.missions.aic_verge;

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
		api.initFleet(FleetSide.PLAYER, "FSUN", FleetGoal.ATTACK, false);
		api.initFleet(FleetSide.ENEMY, "HSS", FleetGoal.ATTACK, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Union Navy Pre-emptive strike");
		api.setFleetTagline(FleetSide.ENEMY, "Assembling main Task Force");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Both the FSUN Erebus and the FSUN Ishtar must survive");
		api.addBriefingItem("The Task force must be stopped");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters

		api.defeatOnShipLoss("FSUN Erebus");
		api.defeatOnShipLoss("FSUN Ishtar");
		
		api.addToFleet(FleetSide.PLAYER, "aic_cap1_std", FleetMemberType.SHIP, "FSUN Erebus", true);
		api.addToFleet(FleetSide.PLAYER, "aic_legionc_elite", FleetMemberType.SHIP, "FSUN Ishtar", false).getCaptain().setPersonality(Personalities.AGGRESSIVE);

		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_carrierc1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);

		api.addToFleet(FleetSide.PLAYER, "aic_carrierd1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.CAUTIOUS);
		api.addToFleet(FleetSide.PLAYER, "aic_dess1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess3_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.CAUTIOUS);
		
		api.addToFleet(FleetSide.PLAYER, "aic_frig1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.PLAYER, "aic_frig1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.PLAYER, "aic_frig5_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.PLAYER, "aic_frig5_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.STEADY);

		// Set up the enemy fleet.
		
		
		
		api.addToFleet(FleetSide.ENEMY, "aic_onslaught_verge", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "aic_onslaught_verge", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);

		api.addToFleet(FleetSide.ENEMY, "dominator_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "dominator_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "dominator_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
				
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Support", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);		
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Support", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);

		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, "LSS Prosperity", false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, "LSS Redemption", false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "condor_Strike", FleetMemberType.SHIP, "LSS Glory", false).getCaptain().setPersonality(Personalities.STEADY);

		api.addToFleet(FleetSide.ENEMY, "lasher_luddic_church_Standard", FleetMemberType.SHIP, "LSS Prosperous Gale", false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "lasher_luddic_church_Standard", FleetMemberType.SHIP, "LSS Impartial Eclipse", false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "lasher_luddic_church_Standard", FleetMemberType.SHIP, "LSS Shevia", false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "lasher_luddic_church_Standard", FleetMemberType.SHIP, "LSS Lucy", false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "hound_luddic_church_Standard", FleetMemberType.SHIP, "LSS Ulturnus", false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "hound_luddic_church_Standard", FleetMemberType.SHIP, "LSS Sorceptor", false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "hound_luddic_church_Standard", FleetMemberType.SHIP, "LSS Broadside", false).getCaptain().setPersonality(Personalities.STEADY);
		api.addToFleet(FleetSide.ENEMY, "hound_luddic_church_Standard", FleetMemberType.SHIP, "LSS Auror", false).getCaptain().setPersonality(Personalities.STEADY);

		
		// Set up the map.
		float width = 18000f;
		float height = 12000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;

		api.addObjective(minX + width * 0.15f + 7000, minY + height * 0.3f + 2800, "sensor_array");
		api.addObjective(minX + width * 0.8f - 8000, minY + height * 0.3f + 2600, "nav_buoy");
		api.addObjective(minX + width * 0.15f + 12000, minY + height * 0.3f + 2800, "comm_relay");
		api.addObjective(minX + width * 0.8f - 12000, minY + height * 0.3f + 2600, "comm_relay");

		
	}

}
