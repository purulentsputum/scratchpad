/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.scratchpad.api.utils.concepts;

import java.util.Locale;

import org.openmrs.ConceptDescription;
import org.openmrs.ConceptName;
import org.openmrs.ConceptNumeric;
import org.openmrs.ConceptSource;
import org.openmrs.api.ConceptNameType;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.scratchpad.api.utils.ConceptIds;
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;


public class LungFunctionConcepts {

	private ConceptService conceptService = Context.getConceptService();
	private ConceptSource conceptSourceSTAR = conceptService.getConceptSourceByUuid(ConceptUUID.CONCEPT_SOURCE_STAR_UUID);
	private ConceptSource conceptSourceSNOMEDCT = conceptService.getConceptSourceByUuid(ConceptUUID.CONCEPT_SOURCE_SNOMEDCT_UUID);

	
	/**
	 * Updates lung function concepts, creating ones that do not exist, and leaving existing concepts unaltered
	 */
	public LungFunctionConcepts(){
		UpdateConcepts(false);
	}
	/**
	 * Updates lung function concepts, creating ones that do not exist and updating existing if selected
	 * @param forceUpdate  Boolean force update of existing concepts
	 */
	public LungFunctionConcepts(Boolean forceUpdate){
		UpdateConcepts(forceUpdate);
	}
	private void UpdateConcepts(boolean forceUpdate){
		//spirometry
		updateConceptFEV1(forceUpdate);
		updateConceptFEV1post(forceUpdate);
		updateConceptFEV1pred(forceUpdate);
		updateConceptFVC(forceUpdate);
		updateConceptFVCpost(forceUpdate);
		updateConceptFVCpred(forceUpdate);
		updateConceptMMEFR(forceUpdate);
		updateConceptMMEFRpost(forceUpdate);
		updateConceptMMEFRpred(forceUpdate);
		updateConceptMEF50(forceUpdate);
		updateConceptMEF50post(forceUpdate);
		updateConceptMEF50pred(forceUpdate);
		updateConceptPEF(forceUpdate);
		updateConceptPEFpost(forceUpdate);
		updateConceptPEFpred(forceUpdate);
		updateConceptFIF50(forceUpdate);
		updateConceptFIF50pred(forceUpdate);
		//volumes
		UpdateTLC(forceUpdate);
		UpdateTLCpred(forceUpdate);
		UpdateFRC(forceUpdate);
		UpdateFRCpred(forceUpdate);
		UpdateRV(forceUpdate);
		UpdateRVpred(forceUpdate);
		UpdateVC(forceUpdate);
		UpdateVCpred(forceUpdate);
		//gas transfer
		UpdateDLCO(forceUpdate);
		UpdateDLCOpred(forceUpdate);
		UpdateKCO(forceUpdate);
		UpdateKCOpred(forceUpdate);
		UpdateVA(forceUpdate);
		UpdateVApred(forceUpdate);
		UpdateVIN(forceUpdate);
		UpdateVINpred(forceUpdate);
		//concept sets
		UpdateSpiroSet(forceUpdate);
		UpdateSpiroPrePost(forceUpdate);
		UpdateFullLungFunction(forceUpdate);
		
	}
	
	
	
	/**
	 * 
	 * @param forceUpdate
	 */
	private void updateConceptFEV1(boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_FEV1_UUID;
		
		ConceptNumeric concept = conceptService.getConceptNumericByUuid(uuid);
		if (concept == null) { updateMe=true; }
			
		if (updateMe) {	
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "FEV1 before bronchodilation";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("271ae48d-2b37-44d1-a9b5-4e51139e7276");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("e6ce61bf-2fa6-4848-9f0a-ff88e8333490");
				conceptName.setName("FEV1 (pre)");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("abe6b3ff-aea0-4d90-9fe6-0bc0207ceaf4");
				conceptName.setName("FEV1");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			conceptBuilder.setDescription(
					"Forced expiratory volume in one (1) second ",
					"25a02b50-03a6-4833-b57e-0e2dfe620fdd",
					Locale.ENGLISH
					);
	        conceptBuilder.addMapping(
		    		"a5912144-7cc9-430b-9914-241bbc7982cf",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "401012008", longName)
		    		);
	        conceptBuilder.addMapping(
		    		"58666ffc-19dc-4d13-b89e-e1d76b064814",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        conceptBuilder.saveAndGet()	;
		}
	}
	
	private void updateConceptFVC(Boolean forceUpdate){
		boolean updateMe = forceUpdate;
		
		String uuid = ConceptUUID.CONCEPT_STAR_FVC_UUID;
		
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "FVC before bronchodilation";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("08774af7-868c-4f87-9361-c51335f166a6");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);	
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("67fbb7a8-b5e9-4434-91f8-c6b774a80fdf");
				conceptName.setName("FVC (pre)");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);	
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("9b420bff-49ee-4679-8265-b2fdb3c7c257");
				conceptName.setName("FVC");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocalePreferred(false);	
				conceptBuilder.add(conceptName);
				
			conceptBuilder.setDescription(
					"Forced expiratory volume",
					"edb4229d-a11e-40e8-a5d4-436ba9150a1b", 
					Locale.ENGLISH
					);	        
	        conceptBuilder.addMapping(
		    		"0c6eb6f8-bfad-4d0d-bc5e-10785de2732c",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "50834005", longName)
		    		);
	        conceptBuilder.addMapping(
		    		"a11c4b34-0917-4789-a34f-f83961a6937f",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        conceptBuilder.saveAndGet()	;
		}
	}
	
	private void updateConceptFEV1post(Boolean forceUpdate){
		boolean updateMe = forceUpdate;
		
		String uuid = ConceptUUID.CONCEPT_STAR_FEV1_POST_UUID;
		ConceptNumeric concept = conceptService.getConceptNumericByUuid(uuid);
		if (concept == null) { updateMe=true; }
			
		if (updateMe) {	
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "FEV1 after bronchodilation";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("6cf2e1fa-dcd8-42c7-95b1-4dfe61b5fcc5");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);	
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("110af7fa-cda4-4eb8-b70f-059d09da8aea");
				conceptName.setName("FEV1 (post)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
				
			ConceptDescription description = new ConceptDescription();
				description.setUuid("53ecfafb-7455-448f-ab74-de6977239030");
				description.setDescription("Forced expiratory volume in 1 second, after inhaled short acting beta agonist");
				description.setLocale(Locale.ENGLISH);	
				conceptBuilder.setDescription(description);
		        
	        conceptBuilder.addMapping(
		    		"014010a1-814c-453e-b617-f87ddb664c94",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "401013003", longName)
		    		);
	        conceptBuilder.addMapping(
		    		"ebaad77b-9977-447e-814d-584a590217e2",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        conceptBuilder.saveAndGet()	;
		}
	}
	
	private void updateConceptFVCpost(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_FVC_POST_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "FVC after bronchodilation";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
			conceptName.setUuid("e500e396-4a75-4a4f-b3e3-81562dcd0162");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);	
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("2bacc868-dc53-4445-a33b-86bfd4ecef62");
				conceptName.setName("FVC (post)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
				
			ConceptDescription description = new ConceptDescription();
				description.setUuid("c976d886-6a2a-4e01-bf67-511041b7f900");
				description.setDescription("Forced expiratory volume after inhaled short actinng beta agonist");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
		        
	        conceptBuilder.addMapping(
		    		"b102a40b-04ae-479f-aa1d-df9184ee3fc9",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "407561008", longName)
		    		);
	        conceptBuilder.addMapping(
		    		"dddb7506-0409-4bad-8391-c26f407be4ed",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        conceptBuilder.saveAndGet()	;
		}
	}
	
	private void updateConceptFEV1pred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_FEV1_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected forced expired volume in 1 second";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
			conceptName.setUuid("bccaf725-e67b-493a-8451-68f6dc12af22");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);	
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("6e70a56e-ef80-4b08-bb81-f6f49c7d2714");
				conceptName.setName("FEV1 (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);	
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("20c2a395-c26d-4470-b055-959f5559298e");
				conceptName.setName("FEV1 predicted");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
				
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("dae1af40-4088-4991-9175-4ed512085bce");
				description.setDescription("Expected forced expiratory volume in 1 second, based on height, age, sex +/- weight");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
		      
		    conceptBuilder.addMapping(
		    		"2b827ddd-4c26-4f04-af41-f1785e570885",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "310520004", longName)
		    		);
		    conceptBuilder.addMapping(
		    		"2bcb25a2-5250-4d13-bcb5-cf0a35e08365",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	

	private void updateConceptFVCpred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_FVC_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected forced vital capacity";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("db381b4b-752d-4b39-b360-822a7aed4bfe");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("58a2474c-d4d4-4340-a4f2-e05ceafdc230");
				conceptName.setName("FVC (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("2cb7b7d5-311f-43aa-9545-783175313b91");
				conceptName.setName("FVC predicted");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("f0ec3132-8b32-4a7c-a138-733662cb76a4");
				description.setDescription("Expected forced expiratory lung capacity based on height, age +/- weight");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
	        
		    conceptBuilder.addMapping(
		    		"696a54a1-fae8-4cfe-a885-132eef91f88f",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "310521000", longName)
		    		);
		    conceptBuilder.addMapping(
		    		"0b3b786a-d5b0-46f6-a238-82932412866c",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	
	
	private void updateConceptMMEFR(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_MMEFR_UUID;		
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(15.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Maximum mid-expiratory flow rate";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("c93b0de2-4ca1-46e1-a676-2e583b37d681");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("36d2fb1a-41a3-4b1c-ab96-0c13b4b1a380");
				conceptName.setName("MMEFR");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("015d85e5-b964-4afb-a3e9-de130078f1bd");
				conceptName.setName("FEF25-75");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
				
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("58f70f40-7cca-4b2a-8a1f-b6de247f5131");
				description.setDescription("Average expiratory flow over middle half of the FVC");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);   
	        
		    conceptBuilder.addMapping(
		    		"da9ac55f-5ab9-484c-b3a2-2c7f7635c6fb",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
private void updateConceptMMEFRpost(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_MMEFR_POST_UUID;		
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(15.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Maximum mid-expiratory flow rate following bronchodiilator";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("d4df8497-3ae3-4a3b-8dc5-3bd144281614");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("b02834ba-9a77-48fc-9c45-3e033f6f49c6");
				conceptName.setName("MMEFR post");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("d2659fae-1b9d-4800-b60b-1f17d62d02d9");
				conceptName.setName("FEF25-75 post");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
				
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("37c3f8df-368a-42a7-8afb-f7ce53530ae4");
				description.setDescription("Average expiratory flow over middle half of the FVC after bronchodilators");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);   
	        
		    conceptBuilder.addMapping(
		    		"93b34fe6-6685-4720-9856-f0a560889d53",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	
	private void updateConceptMMEFRpred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_MMEFR_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(15.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected Maximum mid-expiratory flow rate";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
		
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("3c8529cb-6101-4e4a-a196-ea310a427d38");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("9eb4bcf9-0cee-413e-8c37-e56a3bbd0653");
				conceptName.setName("MMEFR (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("ad9dd71f-276f-4b4e-a083-8badea4da04c");
				conceptName.setName("FEF25-75 (pred)");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("8ecfec7c-7890-406e-8b04-7e0c1d2b1b2e");
				description.setDescription("Expected Average expiratory flow over middle half of the FVC");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);  
	        
		    conceptBuilder.addMapping(
		    		"50581575-f46e-4280-bebf-fa7923dd2f59",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	private void updateConceptMEF50(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_MEF50_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true;}
			
		if (updateMe) {
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(15.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Maximum expiratory flow at 50% FVC";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("0858dae6-33e8-4d4b-b97c-92935227832e");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("98b35ab7-d146-412f-b91d-d2d22a743cf7");
				conceptName.setName("MEF50");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("37c72d6d-ca4b-474b-b11e-810b3684e111");
				conceptName.setName("FEF50");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("4f119677-f5b0-4ea7-b854-aa9e2439f340");
				description.setDescription("Expected forced expiratory flow at 50% of forced vital capacity");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
				
		    conceptBuilder.addMapping(
		    		"e5c59f6d-a24e-4ba4-9a96-63d25b4c110e",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	
private void updateConceptMEF50post(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_MEF50_POST_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true;}
			
		if (updateMe) {
			
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(15.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Maximum expiratory flow at 50% FVC after bronchodilator";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("885f3695-7108-4348-b3fb-e4983d35ae77");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("aea8c0bc-f100-4957-9c60-0078a1cc92c7");
				conceptName.setName("MEF50 post");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("7c3ba524-ce4c-420e-9d92-35e985f191d3");
				conceptName.setName("FEF50 post");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("3493d779-b38a-446d-80c2-017c61b4ef3c");
				description.setDescription("Expected forced expiratory flow at 50% of forced vital capacity after bronchodilator");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
				
		    conceptBuilder.addMapping(
		    		"030c574e-b595-4334-80c6-7e8eb62f9a11",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	
	private void updateConceptMEF50pred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_MEF50_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected maximum expiratory flow at 50% FVC";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("34c79ce4-c735-4069-9a11-0b09f16c15c9");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("1c6564c4-1dca-450f-90df-4ed082b54c5f");
				conceptName.setName("MEF50 (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("d50f3c8c-2cee-46ef-ab7e-63d409127dda");
				conceptName.setName("FEF (pred)");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("3b8b4e20-1d17-4f0b-8f24-1ffe4d2852dc");
				description.setDescription("Expected forced expiratory flow at 50% of forced vital capacity");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
	        
		    conceptBuilder.addMapping(
		    		"370da1ee-6861-47a2-bda4-56f3179f2f28",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	private void updateConceptPEF(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_PEF_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Peak expiratory flow";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("efefe784-a54c-4e98-82e9-b191b087463f");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("2fc9c84d-9aa5-4007-a574-27749b832651");
				conceptName.setName("PEF");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("6c69005c-21df-4550-beed-da9797593424");
				description.setDescription("Maximal forced expiratory flow measured during spirometry");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
				
		    conceptBuilder.addMapping(
		    		"7ce733a9-0942-41e4-90b0-c029f6476609",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "18491006", longName)
		    		);
		    conceptBuilder.addMapping(
		    		"98b853e8-1a59-4023-a3f0-c5a0756a96ac",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    conceptBuilder.saveAndGet()	;
		}
	}
private void updateConceptPEFpost(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_PEF_POST_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Peak expiratory flow after bronchodilator";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("204cd567-c95c-4fb5-bce3-fcc9314668da");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("9a00d40d-ff05-432c-afdc-73be544d8e6b");
				conceptName.setName("PEF post");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("0bb27e3a-2e39-4296-84d5-10cfe026c096");
				description.setDescription("Maximal forced expiratory flow measured during spirometry after bronchodilator");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
				
		    conceptBuilder.addMapping(
		    		"de4bcb3b-ae58-4609-9e9d-473c8a58e5bd",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "18491006", longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void updateConceptPEFpred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_PEF_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected peak expiratory flow";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("8184e1b6-cc7d-4016-8d91-c89076d0a9d6");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("7d2a4506-8de1-4d0b-9b1f-e2de6e591e6a");
				conceptName.setName("PEF (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("a1c032ac-3565-4a97-8bcc-c8fa2bef2c6a");
				description.setDescription("Expected maximal forced expiratory flow measured during spirometry");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
				
		    conceptBuilder.addMapping(
		    		"b46f9e9a-fd10-41f6-9109-4aececf763da",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	
	private void updateConceptFIF50(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_FIF50_UUID;		
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Forced inspiratory Flow at 50% FVC";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("ca700648-fb27-483c-9aa9-502f4e78fc56");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("c7c1a788-40b9-42ab-b3ac-b0c09196b85b");
				conceptName.setName("FIF50");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("bc4f1b58-e08f-4564-babf-b269d70b9d74");
				description.setDescription("Forced inspiratory flow at 50% forced vital capacity");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
	        
		    conceptBuilder.addMapping(
		    		"ceccc3ba-0f1c-4639-89c7-2ca399aa9f9a",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void updateConceptFIF50pred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_FIF50_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L/s");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected forced inspiratory Flow at 50% FVC";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("628efd55-53fa-48cb-8786-cf7c944e5121");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("77e14e02-8a24-4707-8b5e-d0948627c83a");
				conceptName.setName("FIF50 (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("8cc92370-8a92-4dc6-a791-d47f08644848");
				description.setDescription("Expected forced inspiratory flow at 50% forced vital capacity");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
				
		    conceptBuilder.addMapping(
		    		"b4035c97-12ff-4ed9-896e-07eb74562843",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	
	
	/*
	 * lung volumes
	 */
	
	private void UpdateTLC(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_TLC_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(50.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Total lung capacity";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("8514b28b-6073-46d1-9e50-c02829719c94");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("f71c8702-bee4-4d32-ba6c-07a2e97205f4");
				conceptName.setName("TLC");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("fac9aa3b-6b4c-4542-bad6-cb9db09759d9");
				description.setDescription("Total intrathoracic gas volume (total lung capacity)");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
			 
		    conceptBuilder.addMapping(
		    		"38e6bb07-fb94-44ea-bf4f-06b42aa74864",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "57566009", longName)
		    		);
		    conceptBuilder.addMapping(
		    		"fe970fbc-a4ad-4c17-9cea-427d55a6ff83",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    conceptBuilder.saveAndGet()	;
		    
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateTLCpred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_TLC_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(50.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected total lung capacity";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("ed7aba7f-14c7-4785-99fa-64f8c2298ffe");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("88b14519-9506-488e-85e7-e66abdad86be");
				conceptName.setName("TLC (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("bec82977-b337-445d-860c-94d1c7d2ea50");
				description.setDescription("Expected total lung capacity");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
				
		    conceptBuilder.addMapping(
		    		"469c86ce-abb4-43c6-baa7-6ec68f7c1ff1",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	private void UpdateFRC(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_FRC_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Functional Residual Capacity";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("d964e4c8-b5ee-4689-b84f-6c368745f16d");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("1594b5e2-e6a6-4b66-a0ba-6feb2e04db0e");
				conceptName.setName("FRC");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("b2ca88e2-a2a5-459b-9bd3-b8946e675953");
				description.setDescription("The volume of air remaining in the LUNGS at the end of a normal, quiet expiration. It is the sum of the RV and the ERV.");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);

		    conceptBuilder.addMapping(
		    		"4185a453-81ac-4e90-b415-03f88b248aba",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "65825000", longName)
		    		);
		    conceptBuilder.addMapping(
		    		"63001326-3643-405f-a569-8328f2f344c0",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateFRCpred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_FRC_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected Functional Residual Capacity";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("70def82e-cb3a-439c-a8e6-c26ff2764b30");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("90eb504b-5b9e-443d-8640-6c6d2762aa71");
				conceptName.setName("FRC (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("ac3c6b26-6ec5-408f-a9dc-e0958af6d140");
				description.setDescription("Expected functional residual capacity");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
			
		    conceptBuilder.addMapping(
		    		"9ff4b713-cd9c-4ca0-be07-4345ed3c34db",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateRV(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_RV_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Residual Volume";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("ec7105c2-4309-4c5a-8c5e-1fcfbc45aea6");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("a3566ccc-d0a3-4ad5-9017-deadf4a575d9");
				conceptName.setName("RV");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("e3ea7798-dd24-4e1e-8b7f-45f8d4e76c1d");
				description.setDescription("Volume of gas remaining in the lung after a complete exhalation.");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
				
		    conceptBuilder.addMapping(
		    		"bc62bf55-fae8-405e-a975-876dc714ce28",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "127803005", longName)
		    		);
		    conceptBuilder.addMapping(
		    		"162923e0-f56d-4a83-befb-5faf18577b7f",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateRVpred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_RV_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected Residual Volume";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("bacc71bd-5f76-471b-9b01-b5b694237d5e");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("28ace1f8-f385-40ef-a451-b061b33dd982");
				conceptName.setName("RV (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("38aa9360-b0db-4546-b753-4fed93188367");
				description.setDescription("Expected residual volume");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
				
			 conceptBuilder.addMapping(
		    		"9e52c0d4-35ea-493a-ba8b-bc28b43d6684",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateVC(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_VC_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Vital capacity";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("2b1e41cd-b351-401b-ac3e-9d1db11d938d");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("9386b909-f1a6-41a4-a6c0-19aa4df8439a");
				conceptName.setName("VC");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("fd91b28e-fb7b-4dc0-a4be-ca5dc019a6c7");
				description.setDescription("The maximum volume of gas an individual can inhale from the point of maximal exhalation.");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);   
	        
		    conceptBuilder.addMapping(
		    		"42bb7545-1eed-4f52-a23b-d8d02b17a437",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "268379003", longName)
		    		);
		    conceptBuilder.addMapping(
		    		"9ea0e213-d9a6-46d1-9854-b973153319d3",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateVCpred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_STAR_VC_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected vital capacity";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("7ac0e841-c17a-4295-ba2f-20811b132feb");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("572f0edf-e44f-457d-8bf5-eb318a539b8f");
				conceptName.setName("VC (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("2be0fe9c-9be7-4c03-87b9-82744c8b3e23");
				description.setDescription("Expected volume of gas an individual can inhale from the point of maximal exhalation.");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
	        
		    conceptBuilder.addMapping(
		    		"3ddb091b-4513-422e-9a64-55a463223890",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	
	/*
	 * GAS TRANSFER
	 */
	
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateDLCO(Boolean forceUpdate){

		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_DLCO_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("mL/min/mmHg");
			conceptBuilder.setHiAbsolute(50.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Total lung diffusion for carbon monoxide";
			
			int conceptId = ConceptIds.getConceptId(uuid);	
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("b26085db-df3c-425d-9b86-f93a221d8818");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("6bd6e47f-2e92-4d10-b1f4-fd9f24755214");
				conceptName.setName("TLco");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("1fb7197b-2933-4099-a698-e623ad6858ea");
				conceptName.setName("DLco");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("85307d71-3723-4260-bc69-28f44d36e299");
				description.setDescription("A measurement of carbon monoxide (CO) transfer from inspired gas to pulmonary capillary blood.");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
	
		    conceptBuilder.addMapping(
		    		"db9a47c2-b4df-4bf2-a7ab-2f74dc9bcd4a",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "54715006", longName)
		    		);
		    conceptBuilder.addMapping(
		    		"315c31b9-22cd-4b58-b70a-bf2fc8934b94",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateDLCOpred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_DLCO_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("mL/min/mmHg");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected total lung diffusion for carbon monoxide";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("eb36b762-cde6-4c3e-9ec2-8be9f168b86d");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("fc02312c-3c9e-4a44-8d2d-df27ec81cd07");
				conceptName.setName("TLco (pred)");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("dd5b147f-3692-478f-be11-2971311c6eac");
				conceptName.setName("DLco (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("4b10e0cd-0d87-485e-987b-d34ce45eb6b5");
				description.setDescription("Expected measurement of carbon monoxide (CO) transfer from inspired gas to pulmonary capillary blood.");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);    
	        
		    conceptBuilder.addMapping(
		    		"94370934-60d4-4608-bddc-e82f872199bc",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateKCO(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_KCO_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("mL/min/mmHg/L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Gas transfer corrected for lung volume";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);

			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("39ad8e5c-1417-4f89-bab8-b98272f664eb");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("7c979c61-b5e5-40c9-a515-5251b42e8036");
				conceptName.setName("Kco");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("77afe675-9de1-4a43-90a8-bd82fc416a80");
				conceptName.setName("DLco/VA");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("21010dc5-8193-42b1-a469-48ea449e98fd");
				conceptName.setName("TLco/VA");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("d02b0f0a-85ea-4dcb-a85e-5240c803e446");
				description.setDescription("Measurement of carbon monoxide (CO) transfer from inspired gas to pulmonary capillary blood, corrected for lung volume (ie DLco/VA).");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);  
	        
		    conceptBuilder.addMapping(
		    		"4af5f08b-52f3-4a81-bae8-ce184c2da925",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "251952002", longName)
		    		);
		    conceptBuilder.addMapping(
		    		"a0f828da-56f0-4481-baec-d6e6a6e59cbd",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateKCOpred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_KCO_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("mL/min/mmHg/L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected gas transfer corrected for lung volume";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("86605c38-7cfc-4dd3-8c5f-642f491728ef");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("665eab5c-d103-4140-a272-6e8f69c72afa");
				conceptName.setName("Kco (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("12b693cd-1ccd-42b4-8bcc-4fec2cc73457");
				conceptName.setName("DLco/VA (pred)");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("0c434fee-a4d0-4a37-8ce1-e2b92b57f393");
				conceptName.setName("TLco/VA (pred)");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("ac9fb3cd-6c21-43bd-a712-935d850011f0");
				description.setDescription("Expected measurement of carbon monoxide (CO) transfer from inspired gas to pulmonary capillary blood, corrected for lung volume (ie DLco/VA).");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);    
	        
		    conceptBuilder.addMapping(
		    		"2ba3670d-17ed-478c-95c5-91394f0d2c23",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateVA(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_VA_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Single breath lung volume";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("fef5de42-0598-4ef7-9750-0b89edfffbd1");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("b31a961a-ffe3-4f0a-bb36-39fa74b889a1");
				conceptName.setName("VA");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("e7d0bc33-8d53-4a7c-be5b-5954c3cdbbbc");
				description.setDescription("Single breath lung volume measured by helium dilution, and used for gas transfer correction.");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);
	        
		    conceptBuilder.addMapping(
		    		"04e054fd-d9ae-4028-a3c2-78b428a0f176",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "251953007", longName)
		    		);
		    conceptBuilder.addMapping(
		    		"b428f0f5-2fa5-401a-b535-547720e7674f",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateVApred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_VA_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected single breath lung volume measurement";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("fb73f8c3-2f0c-4063-a7c5-1bf53d79bbfc");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("f3579fe5-60b0-4a61-b8fa-1fe20dd7ce2d");
				conceptName.setName("VA (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("e15b1d88-1c63-47cb-a2aa-eb43b0e80776");
				description.setDescription("Expected single breath lung volume measured by helium dilution, and used for gas transfer correction.");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);   
	        
		    conceptBuilder.addMapping(
		    		"bdbc4c60-d1e9-445f-a6ea-79618f820f2b",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateVIN(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_VIN_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Volume inspired gas";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("deae6fc5-dd23-4080-af60-e6da581c817b");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("d66e89cf-ce34-4523-921f-55619493ac1c");
				conceptName.setName("VIN");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("");
				description.setDescription("Volume of inspired gas prior to gas transfer measurement.  (VIN = VA + VD)");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);    
	        
		    conceptBuilder.addMapping(
		    		"e1a49cbc-4366-4ffb-9008-88f479e12392",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateVINpred(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_VIN_PRED_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("L");
			conceptBuilder.setHiAbsolute(10.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(true);
			
			String longName = "Expected inspired volume";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("a2acae79-1c79-49a5-b756-726649b5a689");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("79071b89-d493-40c1-a4fa-6efb3fad5fe5");
				conceptName.setName("VIN (pred)");
				conceptName.setConceptNameType(ConceptNameType.SHORT);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("5012feaa-21cf-439e-b568-8e74219450da");
				description.setDescription("Expected inspired volume prior to gas transfer measurement.");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);    
	        
		    conceptBuilder.addMapping(
		    		"43f4884e-b61a-4c4b-84fa-de977d86c9d0",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * concept sets
	 */
	
	
	
	
	private void UpdateSpiroSet(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_SPIROSET_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_NA,
					ConceptUUID.CONCEPT_OMRS_CLASS_PROCEDURE);
			
			String longName = "Simple Spirometry";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);

			conceptBuilder.addName(
					longName, 
					"d29f6c02-6068-4505-a706-65580d4ead9d", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
			conceptBuilder.addName(
					"Spirometry", 
					"9ca04949-41ab-4085-82f6-b00c27aa1251", 
					Locale.ENGLISH, 
					false,
					ConceptNameType.SHORT
					);
			conceptBuilder.addName(
					"FEV1/FVC", 
					"33e5972b-499d-4f09-9438-731db3635a93", 
					Locale.ENGLISH, 
					false
					);	
		    conceptBuilder.setDescription(
		    		"Simple spirometry including FEV1, FVC and predicted values", 
		    		"4fcddb9a-913b-4844-a022-880bfba96b33", 
		    		Locale.ENGLISH
		    		);
	        conceptBuilder.addMapping(
		    		"e99382e6-5a00-4af1-95c0-bd85abb7c96a",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FEV1_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FVC_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FEV1_PRED_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FVC_PRED_UUID));
		    	
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateSpiroPrePost(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_SPIROPREPOSTSET_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_NA,
					ConceptUUID.CONCEPT_OMRS_CLASS_PROCEDURE
					);

			String longName = "Pre and Post Spirometry";
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);
			
			conceptBuilder.addName(
					longName, 
					"59daa2ac-8c2f-45f9-8449-ac098bb550df", 
					Locale.ENGLISH, 
					true, 
					ConceptNameType.FULLY_SPECIFIED
					);	
			conceptBuilder.addName(
					"Spirometry (pre & post)", 
					"170f216c-5800-4dd4-ab9d-7f8e043201a5", 
					Locale.ENGLISH, 
					false, 
					ConceptNameType.SHORT
					);	
			conceptBuilder.addName(
					"FEV1/FVC (pre & post)", 
					"c91cc321-3212-4355-8960-0437abf4268a", 
					Locale.ENGLISH, 
					false
					);	
			conceptBuilder.setDescription(
					"Simple spirometry including FEV1, FVC, incl bronchodilator responce, and predicted values", 
					"3972fcdd-9e3d-4c41-9577-5dde66abf8af", 
					Locale.ENGLISH
					);
	        conceptBuilder.addMapping(
		    		"6594347f-62a7-4044-acdb-37a7cea29c5b",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
		   
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FEV1_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FVC_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FEV1_POST_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FVC_POST_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FEV1_PRED_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FVC_PRED_UUID));
	    	
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	/*
	// FV exp, FV insp/exp
	private void UpdateSpiroGas(Boolean forceUpdate){
		
	}
	private void UpdateSpiroPrePostGas(Boolean forceUpdate){
		
	}
	private void UpdateSpiroVolGas(Boolean forceUpdate){
		
	}
	*/
	
	/**
	 * 
	 * @param forceUpdate
	 */
	private void UpdateFullLungFunction(Boolean forceUpdate){

		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_FULLPFTSET_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_NA,
					ConceptUUID.CONCEPT_OMRS_CLASS_PROCEDURE
					);
			
			String longName = "Full lung function";
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);
			
			conceptBuilder.addName(
					longName, 
					"7d36086a-a2d5-45ee-91ff-6e5439fba715", 
					Locale.ENGLISH, 
					true, 
					ConceptNameType.FULLY_SPECIFIED
					);
			conceptBuilder.addName(
					"PFTs", 
					"40ad3b97-783e-4391-97c2-0eb3c7808048", 
					Locale.ENGLISH, 
					false
					);
			conceptBuilder.addName(
					"RFTs", 
					"e03c7be8-3b71-4198-8f0d-3f7f6db90606", 
					Locale.ENGLISH, 
					false, 
					ConceptNameType.SHORT
					);
			conceptBuilder.setDescription(
					"Full lung function tests", 
					"1d1c036a-4d5b-4b0c-a192-852f5d0d7834", 
					Locale.ENGLISH
					);
	        conceptBuilder.addMapping(
		    		"330ab5d4-1f8f-46eb-ba3e-5177871524f4",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FEV1_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FVC_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FEV1_POST_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FVC_POST_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FEV1_PRED_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FVC_PRED_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_MMEFR_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_MMEFR_POST_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_MMEFR_PRED_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_MEF50_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_MEF50_POST_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_MEF50_PRED_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_PEF_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_PEF_POST_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_PEF_PRED_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FIF50_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FIF50_PRED_UUID));

	    	// lung volumes
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_TLC_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_TLC_PRED_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FRC_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FRC_PRED_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_RV_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_RV_PRED_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_VC_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_VC_PRED_UUID ));

	    	// gas transfer
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_DLCO_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_DLCO_PRED_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_KCO_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_KCO_PRED_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_VA_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_VA_PRED_UUID ));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_VIN_UUID));
	    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_VIN_PRED_UUID ));

	    	
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	
	/*
	
	*/
	
}
