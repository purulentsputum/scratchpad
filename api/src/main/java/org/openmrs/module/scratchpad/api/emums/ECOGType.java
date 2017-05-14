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
package org.openmrs.module.scratchpad.api.emums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This provides the values for ECOG status
 * based on Eastern Cooperative Oncology Group's quality of life measure
 * returns key of -1 if invalid, or description of "invalid".
 * 
 * @author ross sellars
 * 
 * 
 *
 */
public enum ECOGType {
	
	ECOG0 (0,0,"Asymptomatic","Fully active, able to carry on all predisease activities without restriction"),
	ECOG1 (1,1,"Symptomatic but completely ambulatory","Restricted in physically strenuous activity but ambulatory and able to carry out work of a light or sedentary nature. For example, light housework, office work"),
	ECOG2 (2,2,"Symptomatic, <50% in bed during the day","Ambulatory and capable of all self care but unable to carry out any work activities. Up and about more than 50% of waking hours"),
	ECOG3 (3,3,"Symptomatic, >50% in bed, but not bedbound", "Capable of only limited self-care, confined to bed or chair 50% or more of waking hours"),
	ECOG4 (4,4,"Bedbound","Completely disabled. Cannot carry on any self-care. Totally confined to bed or chair"),
	ECOG5 (5,5,"Dead","Dead");
	
	private int key;	//what is stored in database
	private int index;	//order to display in options
	private String desc;
	private String shortDesc;
	
	private static Map<String,ECOGType>shortDescToENUMMap;
	private static Map<String,ECOGType>descToENUMMap;
	private static Map<Integer,ECOGType>keyToENUMMap;
	private static Map<Integer,ECOGType>indexToENUMMap;

	
	private ECOGType(int index, int key, String shortDesc, String desc){
		this.index=index;
		this.key=key;
		this.desc=desc;
		this.shortDesc=shortDesc;
	}
	
	public String getDesc(){
		return desc;
	}
    public String getShortDesc() {
    	return shortDesc;
	}
	public int getKey(){
		return key;
	}
	public int getIndex(){
		return index;
	}
	
	@Override
	public String toString(){
		return toString(true);
	}
	public String toString(boolean longDesc){
		if (longDesc) {
			return Integer.toString(getKey()) +": " + getDesc();
		}else{
			return Integer.toString(getKey()) +": " + getShortDesc();
		}
	}
    public static ECOGType getDefault(){
        return ECOGType.ECOG0;
    }
    
    private static void initMappingIndex() {
        indexToENUMMap = new HashMap<Integer, ECOGType>();
        for (ECOGType item : values()) {
            indexToENUMMap.put(item.index, item);
        }
    }
    private static void initMappingKey() {
        keyToENUMMap = new HashMap<Integer, ECOGType>();
        for (ECOGType item : values()) {
            keyToENUMMap.put(item.key, item);
        }
    }
    private static void initMappingDesc() {
        descToENUMMap = new HashMap<String, ECOGType>();
        for (ECOGType item : values()) {
            descToENUMMap.put(item.desc, item);
        }
    }
	private static void initMappingShortDesc() {
        shortDescToENUMMap = new HashMap<String, ECOGType>();
        for (ECOGType item : values()) {
        	shortDescToENUMMap.put(item.getShortDesc(), item);
        }
    }
    
	public static ECOGType getECOGTypeFromIndex(int index){
        if (indexToENUMMap == null) {
            initMappingIndex();
        }
        if (indexToENUMMap.get(index) !=null){
        	return indexToENUMMap.get(index);
        }else{
        	return null;
        }
    }
	
	public static ECOGType getECOGTypeFromKey(int key){
        if (keyToENUMMap == null) {
            initMappingKey();
        }
        if (keyToENUMMap.get(key) !=null){
        	return keyToENUMMap.get(key);
        }else{
        	return null;
        }
    }
    
    public static ECOGType getECOGTypeFromDesc(String desc){
        if (descToENUMMap == null) {
            initMappingDesc();
        }
        if (descToENUMMap.get(desc) !=null){
        	return descToENUMMap.get(desc);
        }else{
        	return null;
        }
    }
    
    public static ECOGType getECOGTypeFromShortDesc(String desc){
        if (shortDescToENUMMap == null) {
            initMappingShortDesc();
        }
        if (shortDescToENUMMap.get(desc) !=null){
        	return shortDescToENUMMap.get(desc);
        }else{
        	return null;
        }
    }
	
	
    public static String[] ECOGlist(){
    	if (keyToENUMMap == null) {
            initMappingKey();
        }
    	String[] retVar = new String [keyToENUMMap.size()];
    	for (int i = 0;i>keyToENUMMap.size();i++){
    		retVar[i]= getECOGTypeFromKey(i).toString();
    	}
    	return retVar;
    }
	
    public static String[] ECOGlistShort(){
    	if (keyToENUMMap == null) {
            initMappingKey();
        }
    	String[] retVar = new String [keyToENUMMap.size()];
    	for (int i = 0;i>keyToENUMMap.size();i++){
    		retVar[i]= getECOGTypeFromKey(i).toString(false);
    	}
    	return retVar;
    }
    public static List<ECOGType> getList(){
    	ArrayList<ECOGType> retVar = new ArrayList<ECOGType>();
    	for (ECOGType item : values()) {
    		retVar.add(item);
    	}
    	
    	return retVar;
    	
    }
}

