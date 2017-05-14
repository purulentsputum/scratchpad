package org.openmrs.module.scratchpad.api.emums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openmrs.Concept;
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;

public enum ESSAnswerType {

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
	
	ESSANS0 (0,0,"would never doze",ConceptUUID.CONCEPT_ESS_ANSWER0_UUID),
	ESSANS1 (1,1,"slight chance of dozing",ConceptUUID.CONCEPT_ESS_ANSWER1_UUID),
	ESSANS2 (2,2,"moderate chance of dozing",ConceptUUID.CONCEPT_ESS_ANSWER2_UUID),
	ESSANS3 (3,3,"high chance of dozing",ConceptUUID.CONCEPT_ESS_ANSWER3_UUID);
	
	private int key;	//what is stored in database
	private int index;	//order to display in options
	private String desc;
	private String uuid;
	
	private static Map<String,ESSAnswerType>descToENUMMap;
	private static Map<Integer,ESSAnswerType>keyToENUMMap;
	private static Map<Integer,ESSAnswerType>indexToENUMMap;
	private static Map<String,ESSAnswerType>uuidToENUMMap;
	
	private ESSAnswerType(int index, int key, String desc, String uuid){
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
	public String getUuid(){
		return uuid;
	}
	
	@Override
	public String toString(){
		return Integer.toString(getKey()) +": " + getDesc();
	}
    public static ESSAnswerType getDefault(){
        return ESSAnswerType.ESSANS0;
    }
	private static void initMappingIndex() {
        indexToENUMMap = new HashMap<Integer, ESSAnswerType>();
        for (ESSAnswerType item : values()) {
            indexToENUMMap.put(item.index, item);
        }
    }
    private static void initMappingKey() {
        keyToENUMMap = new HashMap<Integer, ESSAnswerType>();
        for (ESSAnswerType item : values()) {
            keyToENUMMap.put(item.key, item);
        }
    }
    private static void initMappingDesc() {
        descToENUMMap = new HashMap<String, ESSAnswerType>();
        for (ESSAnswerType item : values()) {
            descToENUMMap.put(item.desc, item);
        }
    }
    private static void initMappingUuid() {
        uuidToENUMMap = new HashMap<String, ESSAnswerType>();
        for (ESSAnswerType item : values()) {
            uuidToENUMMap.put(item.uuid, item);
        }
    }
    public static ESSAnswerType getESSAnswerTypeFromIndex(int index){
        if (indexToENUMMap == null) {
            initMappingIndex();
        }
        if (indexToENUMMap.get(index) !=null){
        	return indexToENUMMap.get(index);
        }else{
        	return null;
        }
    }
	
	public static ESSAnswerType getESSAnswerTypeFromKey(int key){
        if (keyToENUMMap == null) {
            initMappingKey();
        }
        if (keyToENUMMap.get(key) !=null){
        	return keyToENUMMap.get(key);
        }else{
        	return null;
        }
    }
    
    public static ESSAnswerType getESSAnswerTypeFromDesc(String desc){
        if (descToENUMMap == null) {
            initMappingDesc();
        }
        if (descToENUMMap.get(desc) !=null){
        	return descToENUMMap.get(desc);
        }else{
        	return null;
        }
    }
    public static ESSAnswerType getESSAnswerTypeFromUuid(String uuid){
        if (uuidToENUMMap == null) {
            initMappingUuid();
        }
        if (uuidToENUMMap.get(uuid) !=null){
        	return uuidToENUMMap.get(uuid);
        }else{
        	return null;
        }
    }
    public static List<ESSAnswerType> getList(){
    	ArrayList<ESSAnswerType> retVar = new ArrayList<ESSAnswerType>();
    	for (ESSAnswerType item : values()) {
    		retVar.add(item);
    	}
    	return retVar;
    }
    public static List<String> getListAsString(){
    	ArrayList<String> retVar = new ArrayList<String>();
    	for (ESSAnswerType item : values()) {
    		retVar.add(item.getDesc());
    	}
    	return retVar;
    }
	
}
