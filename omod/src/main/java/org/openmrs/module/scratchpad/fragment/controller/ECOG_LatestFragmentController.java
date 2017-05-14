package org.openmrs.module.scratchpad.fragment.controller;

import java.util.HashMap;
import java.util.List;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.module.emrapi.patient.PatientDomainWrapper;
import org.openmrs.module.scratchpad.api.ECOGService;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.InjectBeans;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;

import org.openmrs.module.scratchpad.api.emums.ECOGType;

public class ECOG_LatestFragmentController {

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
	
		HashMap<Integer, Object> data = new HashMap<Integer,Object>();
		
		List<Obs> list = service.getObs(patientWrapper.getPatient());
		if (list.size()>0) {
			data.put(0, 3); // three columns
			data.put(1, list.get(0).getObsDatetime());
			data.put(2, list.get(0).getConcept().getName().getName());
			data.put(3, ECOGType.getECOGTypeFromKey(list.get(0).getValueNumeric().intValue()));
			
		}else{
			data.put(0, 3);
			data.put(1, new java.util.Date());
			data.put(2,"ECOG Status");
			data.put(3,"no values recorded");
		}
		
		model.addAttribute("ECOG", data);
		model.addAttribute("showSelectButton", false);
	}
}
