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
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;


public class EcogConcepts {

	private ConceptService conceptService = Context.getConceptService();
	private ConceptSource conceptSourceCIEL = conceptService.getConceptSourceByUuid(ConceptUUID.CONCEPT_SOURCE_CIEL_UUID);
	private ConceptSource conceptSourceSNOMEDCT = conceptService.getConceptSourceByUuid(ConceptUUID.CONCEPT_SOURCE_SNOMEDCT_UUID);
	
	public EcogConcepts(){
		updateConceptECOG(false);
	}
	public EcogConcepts(Boolean forceUpdate){
		updateConceptECOG(forceUpdate);
	}
	
	
	private void updateConceptECOG(boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ECOG_UUID;

		ConceptNumeric concept = conceptService.getConceptNumericByUuid(uuid);
		
		if (concept == null) { 
			updateMe=true; 
			}
			
		if (updateMe) {	
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_MISC);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setUnits("");
			conceptBuilder.setHiAbsolute(5.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(false);
			
			String longName = "ECOG Performance Status";
			
			//int conceptId = ConceptIds.getConceptId(uuid);
			
			
			ConceptName conceptName;
			
			conceptName = new ConceptName();
				conceptName.setUuid("108528BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
				conceptName.setName("longName");
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);		
			conceptName = new ConceptName();
				conceptName.setUuid("108527BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
				conceptName.setName("Eastern Cooperative Oncology Group performance status");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);	
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("108526BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
				conceptName.setName("ECOG Performance Score");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
				
			ConceptDescription description = new ConceptDescription();
				description.setUuid("16733FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
				description.setDescription("REF: http://ecog.dfci.harvard.edu/general/perf_stat.html ");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);

		    conceptBuilder.addMapping(
		    		"6435e27c-bb1d-409a-bbb0-ac38b75abf98",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSNOMEDCT, "424122007", longName)
		    		);  
		    conceptBuilder.addMapping(
		    		"b7b325ec-ccdf-40ef-9155-5d0c258b661e",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceCIEL,"160379",longName));  
			
			conceptBuilder.saveAndGet()	;

		   			
		}
	}
	
}
