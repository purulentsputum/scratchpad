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

import org.openmrs.ConceptSource;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.scratchpad.api.utils.concepts.EcogConcepts;
import org.openmrs.module.scratchpad.api.utils.concepts.EpworthSleepinessScaleConcepts;
import org.openmrs.module.scratchpad.api.utils.concepts.LungFunctionConcepts;

public class CheckConcepts {

	private ConceptService conceptService = Context.getConceptService();
	
	@SuppressWarnings("unused")
	public CheckConcepts(){		
		
		Boolean forceUpdate = false;  // leave as false as cannot find a way to edit concepts programatically yet...
		/*
		 * ensure appropriate privileges
		 */
		Boolean hasViewConceptPrivilege = Context.hasPrivilege("View Concept Sources");
		if (!hasViewConceptPrivilege) { Context.addProxyPrivilege("View Concept Sources"); }
		Boolean hasManageConceptPrivilege = Context.hasPrivilege("Manage Concept Sources");
		if (!hasManageConceptPrivilege) { Context.addProxyPrivilege("Manage Concept Sources"); }
		/*
		 * concept classes to update
		 */
		ConceptSourceSTAR();
		EcogConcepts ecogConcepts = new EcogConcepts(forceUpdate);
		//LungFunctionConcepts  lungFunctionConcepts = new LungFunctionConcepts(forceUpdate);
		EpworthSleepinessScaleConcepts epworthSleepinessScale = new EpworthSleepinessScaleConcepts(forceUpdate);
		
		/*
		 * tidy up privileges
		 */
		if (!hasViewConceptPrivilege) { Context.removeProxyPrivilege("View Concept Sources"); }
		if (!hasManageConceptPrivilege) { Context.removeProxyPrivilege("Manage Concept Sources"); }
	}
	
	/**
	 * default implementation of 
	 * ConceptSourceStar(boolean forceUpdate)
	 */
	private void ConceptSourceSTAR(){
		ConceptSourceSTAR(false);
	}
	
	
	
	/**
	 * Concept source for local concepts
	 * this checks for the existence and creates it if not present
	 * @param forceUpdate if set will force update of concept 
	 */
	private void ConceptSourceSTAR(boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		ConceptSource conceptSourceSTAR = conceptService.getConceptSourceByUuid(ConceptUUID.CONCEPT_SOURCE_STAR_UUID);
		
		if (conceptSourceSTAR == null) {
			conceptSourceSTAR = new ConceptSource();
			updateMe=true;
		}
		if (updateMe) {
			conceptSourceSTAR.setUuid(ConceptUUID.CONCEPT_SOURCE_STAR_UUID);
			conceptSourceSTAR.setName("STAR");
			conceptSourceSTAR.setDescription("Sleep, Tuberculosis and Respiratory system local concept");
			conceptService.saveConceptSource(conceptSourceSTAR);
		}
	}

}
