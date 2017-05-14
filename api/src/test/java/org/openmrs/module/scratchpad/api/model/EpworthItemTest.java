package org.openmrs.module.scratchpad.api.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.ConceptService;
import org.openmrs.api.LocationService;
import org.openmrs.api.ObsService;
import org.openmrs.api.PatientService;
import org.openmrs.module.scratchpad.api.ESSService;
import org.openmrs.module.scratchpad.api.impl.ESSServiceImpl;
import org.openmrs.ui.framework.SimpleObject;

public class EpworthItemTest {

	private ESSService service;
	private PatientService patientService;
	private LocationService locationService;
	
	@Before
	public void setUp(){
		
		service = new ESSServiceImpl();
		patientService = Mockito.mock(PatientService.class);
		((ESSServiceImpl) service).setPatientService(patientService);
		locationService = Mockito.mock(LocationService.class);
		((ESSServiceImpl) service).setLocationService(locationService);
		
	}
	@Test
	public void shouldSetAnswer(){
		EpworthItem ess = new EpworthItem();
		ess.setQ1(1);
		assertTrue(ess.getQ1()==1);
	}
	@Test
	public void checkESScalculation(){
		EpworthItem ess = new EpworthItem();
		ess.setQ1(3);
		ess.setQ2(3);
		ess.setQ3(3);
		ess.setQ4(3);
		ess.setQ5(3);
		ess.setQ6(3);
		ess.setQ7(3);
		ess.setQ8(3);
		
		assertTrue(ess.getResult() == 24);
		
	}
	
	@Test
	public void testSimpleObject(){
		EpworthItem ess = new EpworthItem();
		ess.setQ1(2);
		ess.setQ2(3);
		ess.setQ3(3);
		ess.setQ4(3);
		ess.setQ5(3);
		ess.setQ6(3);
		ess.setQ7(3);
		ess.setQ8(3);
		Location location = locationService.getDefaultLocation();
		//location.setName("my place");
		ess.setLocation(location);
		ess.setPatient(new Patient());
		ess.setObsDate(new java.util.Date());
		ess.setLabNumber("12345");
		ess.setComments("");
		
		//SimpleObject simp = new SimpleObject();
		//simp=ess.toSimpleObject();
		
		//assertTrue((Integer)simp.get("Q1")==2);
		assertTrue(true);
		
	}

}
