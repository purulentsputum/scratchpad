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

import org.jfree.util.Log;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptDescription;
import org.openmrs.ConceptMap;
import org.openmrs.ConceptMapType;
import org.openmrs.ConceptName;
import org.openmrs.ConceptNumeric;
import org.openmrs.ConceptReferenceTerm;
import org.openmrs.ConceptSource;
import org.openmrs.api.ConceptNameType;
import org.openmrs.api.ConceptService;
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;

public class ConceptNumericBuilder {
	private ConceptService conceptService;
    private ConceptNumeric concept;
    
    public final String  SAME = "SAME-AS";
    public final String  NARROWER = "NARROWER-THAN"; 
    public final String  BROADER = "BROADER-THAN"; 
    public final String  ASSOCFIND = "Associated finding"; 
    public final String  ASSOCMORP = "Associated morphology"; 
    public final String  ASSOCPROC = "Associated procedure"; 
    public final String  ASSOCWITH = "Associated with";
    public final String  CAUSE = "Causative agent"; 
    public final String  SITE = "Finding site"; 
    public final String  SPECI = "Has specimen"; 
    public final String  LATERAL = "Laterality"; 
    public final String  SEVERE = "Severity";
    
    public ConceptNumericBuilder(ConceptService conceptService, ConceptClass conceptClass) {
        this.conceptService = conceptService;
        concept = new ConceptNumeric();
        concept.setDatatype(conceptService.getConceptDatatypeByUuid(ConceptUUID.CONCEPT_OMRS_DATATYPE_NUMERIC));
        concept.setConceptClass(conceptClass);
    }
    public ConceptNumericBuilder(ConceptService conceptService, String conceptClassUuid) {
        this.conceptService = conceptService;
        concept = new ConceptNumeric();
        concept.setDatatype(conceptService.getConceptDatatypeByUuid(ConceptUUID.CONCEPT_OMRS_DATATYPE_NUMERIC));
        concept.setConceptClass(conceptService.getConceptClassByUuid(conceptClassUuid));
    }
    public Concept get() {
        return concept;
    }

    public ConceptNumericBuilder setUuid(String uuid) {
        concept.setUuid(uuid);
        return this;
    }
    
    public ConceptNumericBuilder setId(int id){
    	concept.setId(id);
    	return this;
    }

    public ConceptNumericBuilder add(ConceptName conceptName) {
        concept.addName(conceptName);
        return this;
    }
    public ConceptNumericBuilder add(String conceptNameString) {
    	ConceptName conceptName = new ConceptName();
    	conceptName.setName(conceptNameString);
    	if (concept.getNames().size() == 0) {
            conceptName.setLocalePreferred(true);
            conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
        }
        concept.addName(conceptName);
        return this;
    }
    public ConceptNumericBuilder addName(
    		String longName, 
    		String nameUuid, 
    		Locale locale, 
    		Boolean preferred, 
    		ConceptNameType conceptNameType){
    	ConceptName conceptName = new ConceptName();
    	conceptName.setUuid(nameUuid);
		conceptName.setName(longName);
		conceptName.setConceptNameType(conceptNameType);
		conceptName.setLocale(locale);
		conceptName.setLocalePreferred(preferred);	
		concept.addName(conceptName);
    	return this;
    }
    public ConceptNumericBuilder addVoidedName(ConceptName voidedName) {
        voidedName.setVoided(true);
        return add(voidedName);
    }
    public ConceptNumericBuilder setDescription(ConceptDescription description){
    	concept.addDescription(description);
    	return this;
    }
    public ConceptNumericBuilder setDescription(String description, String descriptionUuid, Locale locale){
    	ConceptDescription conceptDescription = new ConceptDescription();
    	conceptDescription.setUuid(descriptionUuid);
    	conceptDescription.setDescription(description);
    	conceptDescription.setLocale(locale);	
    	concept.addDescription(conceptDescription);
    	return this;
    }
    public ConceptNumericBuilder addMapping(ConceptMapType mapType, ConceptSource source, String code) {
        if (mapType == null || source == null || code == null) {
            throw new IllegalArgumentException("mapType, source, and code are all required");
        }
        ConceptReferenceTerm term = new ConceptReferenceTerm(source, code, null);
        if (conceptService != null) {
            conceptService.saveConceptReferenceTerm(term);
        }
        ConceptMap conceptMap = new ConceptMap(term, mapType);
        concept.addConceptMapping(conceptMap);
        return this;
    }
    public ConceptReferenceTerm referenceTerm(ConceptSource source, String code, String name) {
    	ConceptReferenceTerm term = conceptService.getConceptReferenceTermByCode(code, source);
    	if (term == null) {
    		term = new ConceptReferenceTerm(source,code,name);
    		conceptService.saveConceptReferenceTerm(term);
    	}
    	return term;
    }
    public ConceptReferenceTerm referenceTerm(String sourceUuid, String code, String name) {
    	ConceptSource source = conceptService.getConceptSourceByUuid(sourceUuid);
    	ConceptReferenceTerm term = conceptService.getConceptReferenceTermByCode(code, source);
    	if (term == null) {
    		term = new ConceptReferenceTerm(source,code,name);
    		conceptService.saveConceptReferenceTerm(term);
    	}
    	return term;
    }
    public ConceptNumericBuilder addMapping(String uuid, String mapTypeName, ConceptReferenceTerm term) {
        if (uuid == null || mapTypeName == null || term == null) {
            throw new IllegalArgumentException("uuid, mapType, source, and code are all required");
        }
        //ConceptMap mappp = conceptService.getConceptMapByUuid(uuid);
        if (conceptService != null) { 
            ConceptMapType mapType = conceptService.getConceptMapTypeByName(mapTypeName);
            ConceptMap conceptMap = new ConceptMap(term, mapType);
            conceptMap.setUuid(uuid);
            concept.addConceptMapping(conceptMap);
        }
        
        return this;
    }
    public ConceptNumericBuilder setPrecise(Boolean precise){
    	concept.setAllowDecimal(precise);
    	return this;
    }
    public ConceptNumericBuilder setLowAbsolute(Double value){
    	concept.setLowAbsolute(value);
    	return this;
    }
    public ConceptNumericBuilder setLowCritical(Double value){
    	concept.setLowCritical(value);
    	return this;
    }
    public ConceptNumericBuilder setLowNormal(Double value){
    	concept.setLowNormal(value);
    	return this;
    }
    public ConceptNumericBuilder setHiNormal(Double value){
    	concept.setHiNormal(value);
    	return this;
    }
    public ConceptNumericBuilder setHiCritical(Double value){
    	concept.setHiCritical(value);
    	return this;
    }
    public ConceptNumericBuilder setHiAbsolute(Double value){
    	concept.setHiAbsolute(value);
    	return this;
    }
    public ConceptNumericBuilder setUnits(String units){
    	concept.setUnits(units);
    	return this;
    }

    public ConceptNumericBuilder addName(String englishName) {
        return add(new ConceptName(englishName, Locale.ENGLISH));
    }

    public Concept saveAndGet() {
        try {
        	if (conceptService == null) {
                throw new IllegalStateException("No conceptService available");
            }
            return conceptService.saveConcept(concept);
        } catch (Exception ex){
        	Log.error("failed to create concept " + concept.getName().getName() + " because " + ex.getMessage());
        	return null;
        }
    	
    }

}
