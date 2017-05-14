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
package org.openmrs.module.scratchpad.api.utils;

import java.util.HashMap;
import java.util.Map;

public class ConceptIds {
	
	private static Map<String,Integer>uuidToIdMap;

	public static Integer getConceptId(String uuid){
		if (uuidToIdMap == null) {
            initMapping();
        }
        if (uuidToIdMap.get(uuid) !=null){
        	return uuidToIdMap.get(uuid);
        }else{
        	return null;
        }
	}
	
	private static void initMapping() {
		uuidToIdMap = new HashMap<String,Integer>();
        /*
         * CIEL existing concepts
         */
		//ECOG
		uuidToIdMap.put(ConceptUUID.CONCEPT_ECOG_UUID, 			160379);
		/*
		 *  STAR generated concepts
		 */
		//spirometry and FV loops
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FEV1_UUID, 			1216201);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FEV1_POST_UUID, 		1216202);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FEV1_PRED_UUID , 		1216203);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FVC_UUID,				1216204);		
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FVC_POST_UUID , 		1216205);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FVC_PRED_UUID , 		1216206);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_MMEFR_UUID , 			1216207);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_MMEFR_POST_UUID , 		1216208);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_MMEFR_PRED_UUID,		1216209);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_MEF50_UUID , 			1216210);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_MEF50_POST_UUID , 		1216211);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_MEF50_PRED_UUID, 		1216212);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_PEF_UUID , 			1216213);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_PEF_POST_UUID , 		1216214);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_PEF_PRED_UUID, 		1216215);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FIF50_UUID , 			1216216);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FIF50_POST_UUID , 		1216217);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FIF50_PRED_UUID, 		1216218);
		// lung volumes
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_TLC_UUID , 			1216220);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_TLC_PRED_UUID , 		1216221);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FRC_UUID , 			1216222);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_FRC_PRED_UUID , 		1216223);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_RV_UUID ,				1216224);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_RV_PRED_UUID ,			1216225);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_VC_UUID , 				1216226);
		uuidToIdMap.put(ConceptUUID.CONCEPT_STAR_VC_PRED_UUID , 		1216227);
		// gas transfer
		uuidToIdMap.put(ConceptUUID.CONCEPT_DLCO_UUID , 			1216230);
		uuidToIdMap.put(ConceptUUID.CONCEPT_DLCO_PRED_UUID , 		1216231);
		uuidToIdMap.put(ConceptUUID.CONCEPT_KCO_UUID , 				1216232);
		uuidToIdMap.put(ConceptUUID.CONCEPT_KCO_PRED_UUID , 		1216233);
		uuidToIdMap.put(ConceptUUID.CONCEPT_VA_UUID ,				1216234);
		uuidToIdMap.put(ConceptUUID.CONCEPT_VA_PRED_UUID ,			1216235);
		uuidToIdMap.put(ConceptUUID.CONCEPT_VIN_UUID ,				1216236);
		uuidToIdMap.put(ConceptUUID.CONCEPT_VIN_PRED_UUID ,			1216237);
		//Concept sets
		uuidToIdMap.put(ConceptUUID.CONCEPT_SPIROSET_UUID , 		1216240);
		uuidToIdMap.put(ConceptUUID.CONCEPT_SPIROPREPOSTSET_UUID,	1216241);
		uuidToIdMap.put(ConceptUUID.CONCEPT_FVLOOPSET_UUID,			1216242);
		uuidToIdMap.put(ConceptUUID.CONCEPT_FVLOOPINSEXPSET_UUID,	1216243);
		uuidToIdMap.put(ConceptUUID.CONCEPT_VOLSSET_UUID , 			1216244);
		uuidToIdMap.put(ConceptUUID.CONCEPT_GASSET_UUID , 			1216245);
		uuidToIdMap.put(ConceptUUID.CONCEPT_FULLPFTSET_UUID ,		1216246);
		
		/*
		 * Epworth Sleepiness Score
		 */
		uuidToIdMap.put(ConceptUUID.CONCEPT_EPWORTHSET_UUID ,		1216260);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESS_ANSWER0_UUID ,		1216261);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESS_ANSWER1_UUID ,		1216262);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESS_ANSWER2_UUID ,		1216263);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESS_ANSWER3_UUID ,		1216264);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESSQ1_UUID ,			1216265);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESSQ2_UUID ,			1216266);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESSQ3_UUID ,			1216267);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESSQ4_UUID ,			1216268);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESSQ5_UUID ,			1216269);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESSQ6_UUID ,			1216270);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESSQ7_UUID ,			1216271);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESSQ8_UUID ,			1216272);
		uuidToIdMap.put(ConceptUUID.CONCEPT_ESS_RESULT_UUID ,		1216273);
		
		
		
		
		
    }
	
	
	
	
	
}

/*

*/