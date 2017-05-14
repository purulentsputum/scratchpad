package org.openmrs.module.scratchpad.fragment.controller;

import org.openmrs.Patient;
import org.openmrs.module.emrapi.patient.PatientDomainWrapper;
import org.openmrs.module.scratchpad.api.ESSService;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.InjectBeans;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;

public class ESS_ViewFragmentController {
	
	public void controller(
			FragmentConfiguration config,
			FragmentModel model,
			UiUtils ui,
			@SpringBean ESSService service,
			@InjectBeans PatientDomainWrapper patientWrapper){
		

		config.require("patient");
		Object patient = config.get("patient");
		
		if (patient instanceof Patient) {
			patientWrapper.setPatient((Patient) patient);
			config.addAttribute("patient",  patientWrapper);
		} else if (patient instanceof PatientDomainWrapper) {
			patientWrapper = (PatientDomainWrapper) patient;
		}
		
		
		//getObsArray must be first as it initialises the Maps.
		model.addAttribute("obsList", service.getProcessedObsArray(patientWrapper.getPatient()));
		model.addAttribute("conceptMap", service.getConceptMap());
		model.addAttribute("obsDescriptorsMap", service.getObsDescriptorMap());

		//model.addAttribute("obsListString", service.getCompletedObsArray(patientWrapper.getPatient()));
	}
	
	
}
