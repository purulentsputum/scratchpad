package org.openmrs.module.scratchpad.api.utils;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openmrs.Concept;
import org.openmrs.ConceptDatatype;
import org.openmrs.ConceptNumeric;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Person;
import org.openmrs.api.ConceptService;
import org.openmrs.api.LocationService;
import org.openmrs.api.ObsService;

public class ObsUtilsTest {

	private Obs obs1;
	private Obs obs2;
	private Obs obs3;
	
	private ObsService obsService;
	private ConceptService conceptService;
	private LocationService locationService;
	private Person person;
	private Location location;
	private Date date1;
	private Date date2;
	
	private Concept concept1;
	private Concept concept2;
	
	
	@Before
	public void generateObs(){
		
		obsService = Mockito.mock(ObsService.class);
		//((ESSServiceImpl) service).setObsService(obsService);
		conceptService = Mockito.mock(ConceptService.class);
		//((ESSServiceImpl) service).setConceptService(conceptService);
		locationService = Mockito.mock(LocationService.class);
		person = new Person();
		location = locationService.getDefaultLocation();
		date1 = new GregorianCalendar(2010, 4, 1).getTime();
		date2 = new GregorianCalendar(2015, 4, 1).getTime();
		
		concept1=conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_RESULT_UUID);
		
		concept2=conceptService.getConceptByUuid(ConceptUUID.CONCEPT_STAR_FEV1_UUID);
		//conceptBuilder later....
		
		
		
		
		obs1=new Obs();
		obs1.setConcept(concept1);
		obs1.setObsDatetime(date1);
		obs1.setPerson(person);
		obs1.setLocation(location);
		obs1.setAccessionNumber("obs1");
		obs1.setValueNumeric(1.0);
		obsService.saveObs(obs1, null);
		 
		//same date, number, different concept
		obs2=new Obs();
		obs2.setConcept(concept2);
		obs2.setObsDatetime(date2);
		obs2.setPerson(person);
		obs2.setLocation(location);
		obs2.setAccessionNumber("obs2");
		obs2.setValueNumeric(2.0);
		obsService.saveObs(obs2, null);
		
		//different date, number, same concept
		obs3=new Obs();
		obs3.setConcept(concept1);
		obs3.setObsDatetime(date2);
		obs3.setPerson(person);
		obs3.setLocation(location);
		obs3.setAccessionNumber("obs3");
		obs3.setValueNumeric(2.0);
		obsService.saveObs(obs3, null);
		
	}
	@Test
	public void testObsDiffConcept() {
		// cant test this as obs.get doesnt work in testing mode but does in real 
		Array2D<Obs> temp = new Array2D<Obs>();
		temp.set(0, 0, obs1);
		temp.set(1, 0, obs2);
		assertTrue(temp.sizeRows()==2);
		assertTrue(temp.sizeColumns()==1);
		
		ObsUtils obsList = new ObsUtils(temp);
		Array2D<String> tempString = obsList.convertObsArrayToStringForTestingONLY();
		
		assertTrue(tempString.sizeRows()==3);
		assertTrue(tempString.sizeColumns()==4);
		
		
	}
	
	@Test
	public void testArrayToString(){
		Array2D<Obs> temp = new Array2D<Obs>();
		temp.set(0, 0, obs1);
		temp.set(1, 0, obs2);
		temp.set(0, 1, obs3);
		assertTrue(temp.sizeRows()==2);
		assertTrue(temp.sizeColumns()==2);
		
		ObsUtils obsUtilTest = new ObsUtils(temp);
		Array2D<String> temp2 = obsUtilTest.convertObsArrayToStringForTestingONLY();
		assertTrue(temp2.sizeRows()==3);
		assertTrue(temp2.sizeColumns()==5);
		
		
		assertTrue(temp2.get(0, 0).equalsIgnoreCase("Date"));
		assertTrue(temp2.get(0, 1).equalsIgnoreCase("Number"));
		assertTrue(temp2.get(0, 2).equalsIgnoreCase("Location"));
		assertTrue(temp2.get(0, 3).equalsIgnoreCase("ConceptName"));
		
		//obs1 
		assertTrue(temp2.get(1, 0).equalsIgnoreCase(Utilities.ConvertDateTimeToString(date1,Utilities.DefaultDateFormat)));
		assertTrue(temp2.get(1, 1).equalsIgnoreCase("obs1"));
		assertTrue(temp2.get(1, 2).equalsIgnoreCase("location"));
		assertTrue(temp2.get(1, 3).equalsIgnoreCase("1.0"));
		//obs2 
		assertTrue(temp2.get(2, 0).equalsIgnoreCase(Utilities.ConvertDateTimeToString(date2,Utilities.DefaultDateFormat)));
		assertTrue(temp2.get(2, 1).equalsIgnoreCase("obs2"));
		assertTrue(temp2.get(2, 2).equalsIgnoreCase("location"));
		assertTrue(temp2.get(2, 3).equalsIgnoreCase("2.0"));
		//obs3
		assertTrue(temp2.get(1, 4).equalsIgnoreCase("2.0"));
		 
		 
	}
	

}
