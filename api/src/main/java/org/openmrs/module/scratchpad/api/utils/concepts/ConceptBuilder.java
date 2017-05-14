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
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptDatatype;
import org.openmrs.ConceptDescription;
import org.openmrs.ConceptMap;
import org.openmrs.ConceptMapType;
import org.openmrs.ConceptName;
import org.openmrs.ConceptReferenceTerm;
import org.openmrs.ConceptSource;
import org.openmrs.api.ConceptNameType;
import org.openmrs.api.ConceptService;

public class ConceptBuilder {
	private ConceptService conceptService;
    private Concept concept;
    
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

    public ConceptBuilder(ConceptService conceptService, ConceptDatatype datatype, ConceptClass conceptClass) {
        this.conceptService = conceptService;
        concept = new Concept();
        concept.setDatatype(datatype);
        concept.setConceptClass(conceptClass);
    }
    public ConceptBuilder(ConceptService conceptService, String datatypeUuid, String conceptClassUuid) {
        this.conceptService = conceptService;
        concept = new Concept();
        concept.setDatatype(conceptService.getConceptDatatypeByUuid(datatypeUuid));
        concept.setConceptClass(conceptService.getConceptClassByUuid(conceptClassUuid));
    }
    

    public Concept get() {
        return concept;
    }

    public ConceptBuilder setUuid(String uuid) {
        concept.setUuid(uuid);
        return this;
    }
    
    public ConceptBuilder setId(int id){
    	concept.setId(id);
    	return this;
    }
    public ConceptBuilder addName(
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
    public ConceptBuilder addName(
    		String longName, 
    		String nameUuid, 
    		Locale locale, 
    		Boolean preferred){
    	ConceptName conceptName = new ConceptName();
    	conceptName.setUuid(nameUuid);
		conceptName.setName(longName);
		conceptName.setLocale(locale);
		conceptName.setLocalePreferred(preferred);	
		concept.addName(conceptName);
    	return this;
    }
    public ConceptBuilder add(ConceptName conceptName) {
        concept.addName(conceptName);
        return this;
    }
    public ConceptBuilder add(String conceptNameString) {
    	ConceptName conceptName = new ConceptName();
    	conceptName.setName(conceptNameString);
    	if (concept.getNames().size() == 0) {
            conceptName.setLocalePreferred(true);
            conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
        }
        concept.addName(conceptName);
        return this;
    }
    public ConceptBuilder setDescription(String description, String descriptionUuid, Locale locale){
    	ConceptDescription conceptDescription = new ConceptDescription();
    	conceptDescription.setUuid(descriptionUuid);
    	conceptDescription.setDescription(description);
    	conceptDescription.setLocale(locale);	
    	concept.addDescription(conceptDescription);
    	return this;
    }
    public ConceptBuilder setDescription(ConceptDescription description){
    	concept.addDescription(description);
    	return this;
    }

    public ConceptBuilder addMapping(ConceptMapType mapType, ConceptSource source, String code) {
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
    public ConceptBuilder addMapping(String uuid, String mapTypeName, ConceptReferenceTerm term) {
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
    
    @Deprecated
    public ConceptBuilder addMapping(String uuid, String mapTypeName, ConceptSource source, String code) {
        if (uuid == null || mapTypeName == null || source == null || code == null) {
            throw new IllegalArgumentException("uuid, mapType, source, and code are all required");
        }
        
        ConceptReferenceTerm term = new ConceptReferenceTerm(source, code, null);
        if (conceptService != null) {
            conceptService.saveConceptReferenceTerm(term);
            ConceptMapType mapType = conceptService.getConceptMapTypeByName("SAME-AS");
            ConceptMap conceptMap = new ConceptMap(term, mapType);
            conceptMap.setUuid(uuid);
            concept.addConceptMapping(conceptMap);
        }
        
        return this;
    }
    
    
    public ConceptBuilder addVoidedName(ConceptName voidedName) {
        voidedName.setVoided(true);
        return add(voidedName);
    }
    
    public ConceptBuilder addSetMember(Concept setMember) {
    	concept.addSetMember(setMember);
        return this;
    
    }
    
    /**
     * 
     * index of 0 is before the first concept
     * index of -1 is after last.
     * index of 1 is after the first but before the second, etc
     * 
     * @param setMember
     * @param index
     * @return
     */
    public ConceptBuilder addSetMember(Concept setMember, int index) {
    	/*
    	 
    	 */
    	concept.addSetMember(setMember,index);
        return this;
    }

    public ConceptBuilder addName(String englishName) {
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

    public ConceptBuilder addAnswers(Concept... answers) {
        if (!concept.getDatatype().isCoded()) {
            throw new IllegalStateException("Cannot add answers to a concept with datatype " + concept.getDatatype().getName());
        }
        for (Concept answer : answers) {
            concept.addAnswer(new ConceptAnswer(answer));
        }
        return this;
    }

    public ConceptBuilder addSetMembers(Concept... setMembers) {
        for (Concept concept : setMembers) {
            addSetMember(concept);
        }
        return this;
    }


}
