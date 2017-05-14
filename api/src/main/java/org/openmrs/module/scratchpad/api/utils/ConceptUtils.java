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

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptDescription;
import org.openmrs.ConceptMap;
import org.openmrs.ConceptName;
import org.openmrs.ConceptNumeric;
import org.openmrs.ConceptSet;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;

public class ConceptUtils {
	// Logger 
	private Log log = LogFactory.getLog(getClass());
	
	private ConceptService conceptService = Context.getConceptService();
	
	public ConceptUtils(){
		
	}
	
	/**
	 * Updates numeric concepts 
	 * 
	 * @param conceptUuid
	 * @param conceptNames List<ConceptName> 
	 * @param description
	 * @param conceptClass
	 * @param conceptMaps List<ConceptMap> 
	 * @param hiAbsolute
	 * @param hiCritical
	 * @param hiNormal
	 * @param lowAbsolute
	 * @param lowCritical
	 * @param lowNormal
	 * @param allowDecimal Boolean 
	 */
	public void updateConceptNumeric( 
			String conceptUuid, 
			List <ConceptName> conceptNames,
			ConceptDescription description,
			ConceptClass conceptClass,
			List <ConceptMap> conceptMaps,
			Double hiAbsolute, Double hiCritical, Double hiNormal,
			Double lowAbsolute, double lowCritical, Double lowNormal,
			String units,
			Boolean allowDecimal
			){

		log.info("concept check commenced");
				
		ConceptNumeric concept = conceptService.getConceptNumericByUuid(conceptUuid);
		int conceptId = ConceptIds.getConceptId(conceptUuid);
		
		if (concept == null) { concept = new ConceptNumeric(); }
		
		if (concept.getId() == null) { concept.setId(conceptId); }
		concept.setUuid(conceptUuid);
		concept.setDatatype(conceptService.getConceptDatatypeByUuid(ConceptUUID.CONCEPT_OMRS_DATATYPE_NUMERIC));
		concept.setConceptClass(conceptClass);
		concept.addDescription(description);
		concept.setUnits(units);
				
		if (conceptNames != null) {
			Collection<ConceptName> existingConceptNames = concept.getNames();
			if (existingConceptNames != null) {
				for (ConceptName conceptName: conceptNames) {
					log.info(conceptName.getName());
					for (ConceptName existingConceptName : existingConceptNames){
						if (!conceptName.getUuid().equals(existingConceptName.getUuid())) {
							concept.addName(conceptName);
						}
					}
				}
			}else{
				concept.setNames(conceptNames);
			}
		}
			
		if (conceptMaps != null) {
			concept.setConceptMappings(conceptMaps);
			/*
			Collection<ConceptMap> existingConceptMaps = concept.getConceptMappings();
			
			if (existingConceptMaps != null) {
				for (ConceptMap conceptMap: conceptMaps) {
					for (ConceptMap existingConceptMap : existingConceptMaps) {				
						if (!conceptMap.getUuid().equals(existingConceptMap.getUuid())) {
							concept.addConceptMapping(conceptMap);
						}
					}
				}
			}else{
				concept.setConceptMappings(conceptMaps);
			}
*/
		}
		
		concept.setHiAbsolute(hiAbsolute);
		concept.setHiCritical(hiCritical);
		concept.setHiNormal(hiNormal);
		concept.setLowAbsolute(lowAbsolute);
		concept.setLowCritical(lowCritical);
		concept.setLowNormal(lowNormal);
		concept.setAllowDecimal(allowDecimal);
		
		conceptService.saveConcept(concept)	;


}
	public void updateConceptSet( 
			String conceptUuid, 
			List <ConceptName> conceptNames,
			ConceptDescription description,
			ConceptClass conceptClass,
			List <ConceptMap> conceptMaps,
			List<ConceptSet> conceptSets 
			){

		ConceptNumeric concept = conceptService.getConceptNumericByUuid(conceptUuid);
		int conceptId = ConceptIds.getConceptId(conceptUuid);
		
		if (concept == null) {
			concept = new ConceptNumeric();
		}
		
		concept.setId(conceptId);
		concept.setUuid(conceptUuid);
		concept.setDatatype(conceptService.getConceptDatatypeByUuid(ConceptUUID.CONCEPT_OMRS_DATATYPE_NA));
		concept.setConceptClass(conceptClass);
		concept.addDescription(description);
		
		if (conceptNames != null) {
			Collection<ConceptName> existingConceptNames = concept.getNames();
			for (ConceptName conceptName: conceptNames){
				if (existingConceptNames != null) {
					for (ConceptName existingConceptName : existingConceptNames){
						if (!conceptName.getUuid().equals(existingConceptName.getUuid())) {
							concept.addName(conceptName);
						}
					}
				} else {
				concept.addName(conceptName);
				}
			}
		}
		
		if (conceptMaps != null) {
			Collection<ConceptMap> existingConceptMaps = concept.getConceptMappings();
			for (ConceptMap conceptMap: conceptMaps){
				if (existingConceptMaps != null) {
					for (ConceptMap existingConceptMap : existingConceptMaps){
						if (!conceptMap.getUuid().equals(existingConceptMap.getUuid())) {
							concept.addConceptMapping(conceptMap);
						}
					}
				} else {
					concept.addConceptMapping(conceptMap);
				}
			}
		}
		
		if (conceptSets != null) {
			concept.setConceptSets(conceptSets);
		}
		
		conceptService.saveConcept(concept)	;

	}

}
