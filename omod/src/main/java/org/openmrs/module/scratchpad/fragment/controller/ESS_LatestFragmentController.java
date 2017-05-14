package org.openmrs.module.scratchpad.fragment.controller;

import java.util.List;

import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.module.emrapi.patient.PatientDomainWrapper;
import org.openmrs.module.scratchpad.api.ESSService;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.InjectBeans;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;

public class ESS_LatestFragmentController {
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

		
		List<Obs> obsList = service.getResults(patientWrapper.getPatient());
		if (obsList.size()>0) {
			model.addAttribute("ESSdate", obsList.get(0).getObsDatetime());
			model.addAttribute("ESSresult", Integer.toString(obsList.get(0).getValueNumeric().intValue())+"/24");
		}else{
			model.addAttribute("ESSdate", "");
			model.addAttribute("ESSresult", "");
		}
		
		
	}
}
