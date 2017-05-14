package org.openmrs.module.scratchpad.page.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Patient;
import org.openmrs.module.emrapi.patient.PatientDomainWrapper;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.InjectBeans;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class ECOGListPageController {

	public void controller(
			PageModel model,
			@InjectBeans PatientDomainWrapper patientDomainWrapper,
			@RequestParam("patientId") Patient patient,
			@RequestParam(value="returnUrl", required = false) String returnUrl,
			//UiSessionContext sessionContext,
            UiUtils ui
			){
		
		
		patientDomainWrapper.setPatient(patient);
		
		
		if (StringUtils.isBlank(returnUrl)) {
			Map<String,Object> myMap = new HashMap<String,Object>();
			myMap.put("patentId", patient.getId());
            returnUrl = ui.pageLink("scratchpad", "test", myMap );
        }
		
		
        model.addAttribute("patient", patientDomainWrapper);
        model.addAttribute("returnUrl", returnUrl);
        
	}
}
