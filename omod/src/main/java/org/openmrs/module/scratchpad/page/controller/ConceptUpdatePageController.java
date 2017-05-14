package org.openmrs.module.scratchpad.page.controller;

import org.openmrs.Patient;
import org.openmrs.module.emrapi.patient.PatientDomainWrapper;
import org.openmrs.module.scratchpad.api.utils.CheckConcepts;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.InjectBeans;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class ConceptUpdatePageController {

	public void controller(
			PageModel model,
			@InjectBeans PatientDomainWrapper patientDomainWrapper,
			@RequestParam("patientId") Patient patient,
            UiUtils ui){
		
		patientDomainWrapper.setPatient(patient);
		
		@SuppressWarnings("unused")
		CheckConcepts checkConcepts = new CheckConcepts();
		
		model.addAttribute("patient", patientDomainWrapper);
	}
}
