
package org.openmrs.module.scratchpad.api.enums;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openmrs.Concept;
import org.openmrs.api.ConceptService;
import org.openmrs.module.scratchpad.api.ESSService;
import org.openmrs.module.scratchpad.api.emums.ESSQuestionType;
import org.openmrs.module.scratchpad.api.impl.ESSServiceImpl;
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;


public class ESSQuestionTypeTest {

	private ESSService service;
	private ConceptService conceptService;
	
	@Before
	public void setUp(){
		conceptService = Mockito.mock(ConceptService.class);
		service = new ESSServiceImpl();
		((ESSServiceImpl) service).setConceptService(conceptService);
	}
	
	@Test
	public void testGetESSQuestionTypeFromIndex() {
		String result = ESSQuestionType.getESSQuestionTypeFromIndex(8).getDesc();
		Assert.assertTrue(result.equalsIgnoreCase("In a car, while stopped for a few minutes in the traffic"));
	}

	@Test
	public void testGetESSQuestionTypeFromKey() {
		String result = ESSQuestionType.getESSQuestionTypeFromKey(2).getDesc();
		Assert.assertTrue(result.equalsIgnoreCase("Watching TV"));
	}

	@Test
	public void testGetESSQuestionTypeFromDesc() {
		String result = ESSQuestionType.getESSQuestionTypeFromDesc("Sitting and reading").getDesc();
		Assert.assertTrue(result.equals("Sitting and reading"));
	}

	@Test
	public void testGetList() {
		List<ESSQuestionType> tempList= ESSQuestionType.getList();
		Assert.assertTrue(tempList.size()==8);
	}

	@Test
	public void testGetListAsString() {
		List<String> tempList= ESSQuestionType.getListAsString();
		Assert.assertTrue(tempList.size()==8);
	}
	
	@Test
	public void testGetUUID() {
		String uuid = ESSQuestionType.getESSQuestionTypeFromUuid(ConceptUUID.CONCEPT_ESSQ5_UUID).getConceptUuid();
		Assert.assertTrue(uuid.equals(ConceptUUID.CONCEPT_ESSQ5_UUID));
	}
	

}
