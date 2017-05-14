package org.openmrs.module.scratchpad.api.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.ObsService;

public class ObsUtils {
	//values for 
	private  Map<Integer,String> conceptMap = new HashMap<Integer,String>();
	private  Map<Integer,ObsDescriptor> obsDescriptorMap = new HashMap<Integer, ObsDescriptor>();
	private Array2D<Obs> obsArray = new Array2D<Obs>();
	
	public ObsUtils(){
	}
	public ObsUtils(Array2D<Obs> obsList){
		obsArray = obsList;
	}
	public ObsUtils(Patient patient, List<Concept> concepts, ObsService obsService){
		getObsArray(patient, concepts, obsService);
	}
	
	public  Map<Integer,String> getConceptMap(){
		return conceptMap;
	}
	public  Map<Integer, ObsDescriptor> getObsMap(){
		return obsDescriptorMap;
	}
	public Array2D<Obs> getObsArray(){
		return obsArray;
	}
	
	public String getObsResult(int x, int y) {
		String retVar = "";
		Obs obs = obsArray.get(x, y);
		if (obs != null) {
			retVar = ObsUtils.obsResult(obs);
		}
		return retVar;
	}
	
	public static String obsResult(Obs obsItem) {
		String retVar = "";
		String conceptUUID = obsItem.getConcept().getDatatype().getUuid(); 
		
		if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_NUMERIC)) {
			//TODO precision
			retVar = Double.toString(obsItem.getValueNumeric());	
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_TEXT)) {
			retVar = obsItem.getValueText();	
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_DATE)) {
			retVar = Utilities.ConvertDateTimeToString(obsItem.getValueDate(),"DD-MMM-YY");
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_TIME)) {
			retVar = Utilities.ConvertDateTimeToString(obsItem.getValueTime(),"HH:MM");
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_DATETIME)) {
			retVar = Utilities.ConvertDateTimeToString(obsItem.getValueDatetime(),"DD-MMM-YY HH:MM");
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_CODED)) {
			retVar = obsItem.getValueCoded().getName().getName();
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_DOCUMENT)) {
			retVar = "document";
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_NA)) {
			retVar = "NA";
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_BOOLEAN)) {
			if (obsItem.getValueBoolean()) {
				retVar = "yes";
			} else {
				retVar = "no";
			}
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_RULE)) {
			retVar = " ";
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_STRUCTUREDNUMERIC)) {
			retVar = " ";
		}else if (conceptUUID.equals(ConceptUUID.CONCEPT_OMRS_DATATYPE_COMPLEX)) {
			retVar = " ";
		}else{
			retVar = "invalid type";
		}
		
		return retVar;
	}
	
	
	
	/**
	 * 
	 * @param obsList Array2D<Obs>
	 * @return string values for obe array
	 */
	public Array2D<String> convertObsArrayToString(){
		
		
		Array2D<String> retVar = new Array2D<String>();
		// headings
		//retVar.add(0, new ArrayList<String>());
		int retVarRow;  
		int retVarCol;
		
		//set difference between obs list location and retVar location
		int retVarDateCol = 0;
		int retVarNumCol = 1; 
		int retVarLocCol = 2;
		int colOffset = 3; //allow for date, location and acc number
		int retVarHeaderRow = 0;
		int rowOffset = 1; //allow for column headers
		
		retVar.set(0,retVarDateCol,"Date");
		retVar.set(0,retVarNumCol, "Number");
		retVar.set(0,retVarLocCol, "Location");
		
		Obs obs;
		
		String tempStr;
		
		for (int loopInputRow=0; loopInputRow < obsArray.sizeRows(); loopInputRow++) {
			retVarRow = loopInputRow+rowOffset;
			
			for (int loopInputCol=0; loopInputCol < obsArray.sizeColumns(); loopInputCol++){
				retVarCol = loopInputCol+colOffset; 
				obs = obsArray.get(loopInputRow, loopInputCol);
				if (!(obs == null)){
					// an observation exists, 
					// concept header
					if (StringUtils.isBlank(retVar.get(retVarHeaderRow,retVarCol))) {
						retVar.set(retVarHeaderRow,retVarCol,obs.getConcept().getName().getName());
					} 
					// date, number and location
					if (StringUtils.isBlank(retVar.get(retVarRow,retVarDateCol))) {
						retVar.set(retVarRow,retVarDateCol,Utilities.ConvertDateTimeToString(obs.getObsDatetime(),Utilities.DefaultDateFormat));
						tempStr=obs.getAccessionNumber();
						if (Utilities.validUuid(tempStr)) {
							tempStr="UUID";
						} 
						retVar.set(retVarRow,retVarNumCol,tempStr);
						retVar.set(retVarRow,retVarLocCol,obs.getLocation().getName());
					}
					retVar.set(retVarRow,retVarCol,obsResult(obs));
				} //if (!(obs == null)
			}
		}
		return retVar;
	}
public Array2D<String> convertObsArrayToStringForTestingONLY(){
		
		
		Array2D<String> retVar = new Array2D<String>();
		// headings
		//retVar.add(0, new ArrayList<String>());
		int retVarRow;  
		int retVarCol;
		
		//set difference between obs list location and retVar location
		int retVarDateCol = 0;
		int retVarNumCol = 1; 
		int retVarLocCol = 2;
		int colOffset = 3; //allow for date, location and acc number
		int retVarHeaderRow = 0;
		int rowOffset = 1; //allow for column headers
		
		retVar.set(0,retVarDateCol,"Date");
		retVar.set(0,retVarNumCol, "Number");
		retVar.set(0,retVarLocCol, "Location");
		
		Obs obs;
		
		
		for (int loopInputRow=0; loopInputRow < obsArray.sizeRows(); loopInputRow++) {
			retVarRow = loopInputRow+rowOffset;
			
			for (int loopInputCol=0; loopInputCol < obsArray.sizeColumns(); loopInputCol++){
				retVarCol = loopInputCol+colOffset; 
				obs = obsArray.get(loopInputRow, loopInputCol);
				if (!(obs == null)){
					// an observation exists, 
					// concept header
					if (StringUtils.isBlank(retVar.get(retVarHeaderRow,retVarCol))) {
						retVar.set(retVarHeaderRow,retVarCol,"ConceptName");
						//retVar.set(retVarHeaderRow,retVarCol,obs.getConcept().getName().getName());
					} 
					// date, number and location
					if (StringUtils.isBlank(retVar.get(retVarRow,retVarDateCol))) {
						retVar.set(retVarRow,retVarDateCol,Utilities.ConvertDateTimeToString(obs.getObsDatetime(),Utilities.DefaultDateFormat));
						retVar.set(retVarRow,retVarNumCol,obs.getAccessionNumber());
						retVar.set(retVarRow,retVarLocCol,"location");
						//retVar.set(retVarRow,retVarLocCol,obs.getLocation().getName());
					}
					retVar.set(retVarRow,retVarCol,Double.toString(obs.getValueNumeric()));
					//retVar.set(retVarRow,retVarCol,obsResult(obs));
				} //if (!(obs == null)
			}
		}
		return retVar;
	}
	
/**
 * 
 * @param concept
 * @param patient
 * @param obsService
 * @return
 */
	public static List<Obs> getObsForPatient(Concept concept, Patient patient, ObsService obsService){
		List<Obs> retVar;
		retVar=obsService.getObservationsByPersonAndConcept(patient.getPerson(), concept);
		Collections.sort(retVar, new ObsByDateDescendingComparator());
		return retVar;
	}
	
	/**
	 * create a two dimensional array of obs based on concepts provided
	 * @param patient
	 * @param concepts List<Concept> 
	 * @param obsService 
	 * @return
	 */
	private void getObsArray(Patient patient, List<Concept> concepts, ObsService obsService){
		
		obsArray = new Array2D<Obs>();
		List<Obs> obsListForConcept = new ArrayList<Obs>();
		
		conceptMap = new HashMap<Integer,String>();
		obsDescriptorMap = new HashMap<Integer, ObsDescriptor>();	
		
		//local index for x axis, concepts
		Map<String,Integer> conceptAxisMap = new HashMap<String, Integer>();
		//index for y axis, unique 'key' based on datetime, and accession number
		Map<String,Integer> dateAxisMap = new HashMap<String, Integer>();
			
		int i=0;
		for (Concept conceptItem : concepts){
			conceptMap.put(i, conceptItem.getName().getName());
			conceptAxisMap.put(conceptItem.getUuid(),i++);
		}
		
		//process raw data
		String keyValue;
		Integer conceptAxisValue; // Integer not int as needs to be nullable for the .get() return value not found
		Integer dateAxisValue;
		String tempStr;
		for (Concept conceptItem : concepts){
			obsListForConcept = getObsForPatient(conceptItem, patient, obsService);
			conceptAxisValue = conceptAxisMap.get(conceptItem.getUuid());
			if (!(conceptAxisValue == null)) {
				// value returned
				for (Obs obsItem : obsListForConcept){
					keyValue = getKeyValue(obsItem);
					if (((dateAxisMap.get(keyValue) == null))) {
						//add new row
						dateAxisValue = obsArray.sizeRows();
						dateAxisMap.put(keyValue,dateAxisValue );
						tempStr=Utilities.noNulls(obsItem.getAccessionNumber());
						if (Utilities.validUuid(tempStr)) {
							tempStr="uuid";
						}
						obsDescriptorMap.put(dateAxisValue, new ObsDescriptor(obsItem.getObsDatetime(),tempStr,obsItem.getLocation(),obsItem.getObsGroup()));
						
					}else{
						dateAxisValue = dateAxisMap.get(keyValue);
					}
					obsArray.set(dateAxisValue,conceptAxisValue,obsItem);
				}
			}			
		}
	}
	
	private String getKeyValue(Obs obs) {
		String retVar = Utilities.ConvertDateTimeToString(obs.getObsDatetime(), "YYYYMMDDHHMMSS");
		if (StringUtils.isBlank(obs.getAccessionNumber())){
			retVar = retVar+"accNo";
		} else {
			retVar = retVar + obs.getAccessionNumber();
		}
		if (obs.getLocation() != null) {
			retVar = retVar + obs.getLocation().getName();
		} else {
			retVar = retVar + "location";
		}
		
		return retVar;
	}
	
	@Deprecated
	private String getKeyValue(Date date, String accNo, Location location){
		String locationName = "location";
		if (StringUtils.isBlank(accNo)){
			accNo = "accNo";
		}
		if (location != null) {
			locationName = location.getName();
		}
		return Utilities.ConvertDateTimeToString(date, "YYYYMMDDHHMMSS")+accNo+locationName;
	}
	
	@Deprecated
	public Array2D<Obs> getObsArrayUnsorted(Patient patient, List<Concept> concepts, ObsService obsService){
		Array2D<Obs> retVar = new Array2D<Obs>();
		List<Obs> obsListForConcept = new ArrayList<Obs>();
		for (Concept conceptItem : concepts){
			obsListForConcept = getObsForPatient(conceptItem, patient, obsService);
			retVar.addRow(obsListForConcept);
		}
		return retVar;
	}
	
	
}


