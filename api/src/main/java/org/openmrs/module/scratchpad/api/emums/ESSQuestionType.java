package org.openmrs.module.scratchpad.api.emums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openmrs.module.scratchpad.api.utils.ConceptUUID;

public enum ESSQuestionType {
	/*
	 * Questions
	 * 
	Sitting and reading
	Watching TV
	Sitting, inactive in a public place (e.g. a theatre or a meeting)
	As a passenger in a car for an hour without a break
	Lying down to rest in the afternoon when circumstances permit
	Sitting and talking to someone 
	Sitting quietly after a lunch without alcohol 
	In a car, while stopped for a few minutes in the traffic
	*/
	
	/*
	 * Answers
	 * 
	0 = would never doze
	1 = slight chance of dozing
	2 = moderate chance of dozing 
	3 = high chance of dozing
	*/
	
	ESS_Q1 		(1,1, ConceptUUID.CONCEPT_ESSQ1_UUID,"Sitting and reading"),
	ESS_Q2 		(2,2, ConceptUUID.CONCEPT_ESSQ2_UUID,"Watching TV"),
	ESS_Q3 		(3,3, ConceptUUID.CONCEPT_ESSQ3_UUID,"Sitting, inactive in a public place (e.g. a theatre or a meeting)"),
	ESS_Q4 		(4,4, ConceptUUID.CONCEPT_ESSQ4_UUID,"As a passenger in a car for an hour without a break"),
	ESS_Q5 		(5,5, ConceptUUID.CONCEPT_ESSQ5_UUID,"Lying down to rest in the afternoon when circumstances permit"),
	ESS_Q6 		(6,6, ConceptUUID.CONCEPT_ESSQ6_UUID,"Sitting and talking to someone"),
	ESS_Q7 		(7,7, ConceptUUID.CONCEPT_ESSQ7_UUID,"Sitting quietly after a lunch without alcohol"),
	ESS_Q8 		(8,8, ConceptUUID.CONCEPT_ESSQ8_UUID,"In a car, while stopped for a few minutes in the traffic");
//	ESS_RESULT 	(9,9, ConceptUUID.CONCEPT_ESS_RESULT_UUID,"ESS Result");
	
	private int key;	//what is stored in database
	private int index;	//order to display in options
	private String desc;
	private String uuid;
	
	private static Map<String,ESSQuestionType>descToENUMMap;
	private static Map<String,ESSQuestionType>uuidToENUMMap;
	private static Map<Integer,ESSQuestionType>keyToENUMMap;
	private static Map<Integer,ESSQuestionType>indexToENUMMap;
	
	private ESSQuestionType(int index, int key, String uuid, String desc){
		this.index=index;
		this.key=key;
		this.desc=desc;
		this.uuid=uuid;
	}
	public String getDesc(){
		return desc;
	}
	public int getKey(){
		return key;
	}
	public int getIndex(){
		return index;
	}
	public String getConceptUuid(){
		return uuid;
	}
	
	@Override
	public String toString(){
		return getDesc();
	}
    public static ESSQuestionType getDefault(){
        return ESSQuestionType.ESS_Q1;
    }
	private static void initMappingIndex() {
        indexToENUMMap = new HashMap<Integer, ESSQuestionType>();
        for (ESSQuestionType item : values()) {
            indexToENUMMap.put(item.index, item);
        }
    }
    private static void initMappingKey() {
        keyToENUMMap = new HashMap<Integer, ESSQuestionType>();
        for (ESSQuestionType item : values()) {
            keyToENUMMap.put(item.key, item);
        }
    }
    private static void initMappingDesc() {
        descToENUMMap = new HashMap<String, ESSQuestionType>();
        for (ESSQuestionType item : values()) {
            descToENUMMap.put(item.desc, item);
        }
    }
    private static void initMappingUuid() {
        uuidToENUMMap = new HashMap<String, ESSQuestionType>();
        for (ESSQuestionType item : values()) {
            uuidToENUMMap.put(item.uuid, item);
        }
    }
    public static ESSQuestionType getESSQuestionTypeFromIndex(int index){
        if (indexToENUMMap == null) {
            initMappingIndex();
        }
        if (indexToENUMMap.get(index) !=null){
        	return indexToENUMMap.get(index);
        }else{
        	return null;
        }
    }
	
	public static ESSQuestionType getESSQuestionTypeFromKey(int key){
        if (keyToENUMMap == null) {
            initMappingKey();
        }
        if (keyToENUMMap.get(key) !=null){
        	return keyToENUMMap.get(key);
        }else{
        	return null;
        }
    }
    
    public static ESSQuestionType getESSQuestionTypeFromDesc(String desc){
        if (descToENUMMap == null) {
            initMappingDesc();
        }
        if (descToENUMMap.get(desc) !=null){
        	return descToENUMMap.get(desc);
        }else{
        	return null;
        }
    }
    public static ESSQuestionType getESSQuestionTypeFromUuid(String uuid){
        if (uuidToENUMMap == null) {
            initMappingUuid();
        }
        if (uuidToENUMMap.get(uuid) !=null){
        	return uuidToENUMMap.get(uuid);
        }else{
        	return null;
        }
    }
    public static List<ESSQuestionType> getList(){
    	ArrayList<ESSQuestionType> retVar = new ArrayList<ESSQuestionType>();
    	for (ESSQuestionType item : values()) {
    		retVar.add(item);
    	}
    	return retVar;
    }
    public static List<String> getListAsString(){
    	ArrayList<String> retVar = new ArrayList<String>();
    	for (ESSQuestionType item : values()) {
    		retVar.add(item.getDesc());
    	}
    	return retVar;
    }
    
}
