package data.missions.aic_test;

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
		api.initFleet(FleetSide.ENEMY, "IBH", FleetGoal.ATTACK, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Union Navy Hunter Killers");
		api.setFleetTagline(FleetSide.ENEMY, "Independent Bounty Hunters");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Trash Them");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
				
														
		api.addToFleet(FleetSide.PLAYER, "aic_cap1_std", FleetMemberType.SHIP, true).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cap1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cap1_ult", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_cap1_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);		
		api.addToFleet(FleetSide.PLAYER, "aic_cap1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_cap2_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_cap2_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_cap2_ult", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_cap2_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_cap2_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_legionc_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);		
		api.addToFleet(FleetSide.PLAYER, "aic_legionc_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_legionc_ult", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_legionc_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);			
		api.addToFleet(FleetSide.PLAYER, "aic_legionc_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		
		api.addToFleet(FleetSide.PLAYER, "aic_cap2boss_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiserprot1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dessprot1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser3_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);		
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser3_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser3_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser3_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser4_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser4_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser4_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser4_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1s_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1s_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1s_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1s_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_carrierc1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_carrierc1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_carrierc1_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_carrierc1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser2_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser2_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser2_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser2_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterc1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);		
		api.addToFleet(FleetSide.PLAYER, "aic_freighterc1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterc1_ut", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterc1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.PLAYER, "aic_carrierd1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_carrierd1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_carrierd1_bomb", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_carrierd1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess1_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess3_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess3_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess3_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess3_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess4_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess4_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess4_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess4_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess2_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess2_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess2_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess2_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterd1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_freighterd1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterd1_ut", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterd1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frigmk2_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_frigmk2_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_frigmk2_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frigmk2_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.PLAYER, "aic_frig2_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig2_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig2_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig2_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig5_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig5_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig5_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig5_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig4_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig4_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig4_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig4_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterf1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterf1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterf1_ut", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterf1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig1_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig1_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig1_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig1_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3_sup", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3_elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.PLAYER, "aic_colossusc_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_buffaloc_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_kitec_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_phaetonc_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dramc_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.PLAYER, "aic_cap1r_boss", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1r_boss", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1r_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);	
		api.addToFleet(FleetSide.PLAYER, "aic_cruiser1r_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterc1r_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterc1r_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.PLAYER, "aic_dess1r_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess1r_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess2r_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dess2r_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterd1r_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_freighterd1r_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.PLAYER, "aic_frig2r_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig2r_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig1r_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig1r_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3r_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_frig3r_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cerberusr_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_cerberusr_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_houndr_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_houndr_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_kiter_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_kiter_so", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);

		//api.addToFleet(FleetSide.PLAYER, "aic_colossusr_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_buffalor_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_phaetonr_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.PLAYER, "aic_dramr_std", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		
		
		
		
		
		// Set up the enemy fleet.
		
		
		
		api.addToFleet(FleetSide.ENEMY, "aic_onslaught_verge", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.ENEMY, "falcon_CS", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "eagle_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "dominator_Assault", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.ENEMY, "medusa_Attack", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Support", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "enforcer_Elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.ENEMY, "heron_Attack", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "drover_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		api.addToFleet(FleetSide.ENEMY, "lasher_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "lasher_Strike", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "brawler_Elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);				
		api.addToFleet(FleetSide.ENEMY, "brawler_Elite", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "tempest_Attack", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);
		api.addToFleet(FleetSide.ENEMY, "tempest_Attack", FleetMemberType.SHIP, false).getCaptain().setPersonality(Personalities.AGGRESSIVE);



		
		// Set up the map.
		float width = 18000f;
		float height = 12000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// Add an asteroid field
		api.addAsteroidField(minX, minY + height / 2, 0, 8000f,
							 20f, 70f, 100);
		
		api.addObjective(minX + width * 0.15f + 7000, minY + height * 0.3f + 2800, "sensor_array");
		api.addObjective(minX + width * 0.8f - 8000, minY + height * 0.3f + 2600, "nav_buoy");
		
		
		api.addPlanet(0, 0, 15f, "star_white", 10f, true);
		
	}

}
