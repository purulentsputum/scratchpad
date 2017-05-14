package org.openmrs.module.scratchpad.fragment.controller;

import java.util.Date;
import java.util.List;

import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.module.emrapi.patient.PatientDomainWrapper;
import org.openmrs.module.scratchpad.api.ECOGService;
import org.openmrs.module.scratchpad.api.emums.ECOGType;
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.InjectBeans;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

					
public class ECOG_ViewFragmentController {



	public void controller(
			FragmentConfiguration config,
			FragmentModel model,
			UiUtils ui,
			@SpringBean ECOGService service,
			@InjectBeans PatientDomainWrapper patientWrapper){
		

		config.require("patient");
		Object patient = config.get("patient");
		
		if (patient instanceof Patient) {
			patientWrapper.setPatient((Patient) patient);
			config.addAttribute("patient",  patientWrapper);
		} else if (patient instanceof PatientDomainWrapper) {
			patientWrapper = (PatientDomainWrapper) patient;
		}
		
		List<Obs> list = service.getObs(patientWrapper.getPatient());
		
		model.addAttribute("ECOG", list);
		model.addAttribute("ENUM", ECOGType.getList());
		model.addAttribute("showSelectButton", false);


	}
	
	

	
	
	
}
