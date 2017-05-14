package org.openmrs.module.scratchpad.page.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.coreapps.CoreAppsConstants;
import org.openmrs.module.emrapi.patient.PatientDomainWrapper;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.InjectBeans;
import org.openmrs.ui.framework.page.PageModel;
import org.openmrs.ui.framework.page.Redirect;
import org.springframework.web.bind.annotation.RequestParam;
//import org.openmrs.module.appui.UiSessionContext;
//import org.openmrs.module.coreapps.CoreAppsProperties;
//import org.openmrs.module.coreapps.helper.BreadcrumbHelper;



public class testPageController {

	public Object controller(
			PageModel model,
			@InjectBeans PatientDomainWrapper patientDomainWrapper,
			@RequestParam("patientId") Patient patient,
			@RequestParam(value="returnUrl", required = false) String returnUrl,
			//UiSessionContext sessionContext,
            UiUtils ui
			){
		if (!(Context.isAuthenticated())) {
			return new Redirect("login.htm");
		}
		
		if (patient==null) {
			return new Redirect("index.htm");
		}
		/*
		
		if (!Context.hasPrivilege(CoreAppsConstants.PRIVILEGE_PATIENT_VISITS)) {
            return new Redirect("coreapps", "noAccess", "");
        }
        else if (patient.isVoided() || patient.isPersonVoided()) {
            return new Redirect("coreapps", "patientdashboard/deletedPatient", "patientId=" + patient.getId());
        }
		*/
		
		patientDomainWrapper.setPatient(patient);
		
		
		if (StringUtils.isBlank(returnUrl)) {
			Map<String,Object> myMap = new HashMap<String,Object>();
			myMap.put("patentId", patient.getId());
            returnUrl = ui.pageLink("coreapps", "clinicianfacing/patient", myMap );
        }
		
		
        model.addAttribute("patient", patientDomainWrapper);
        model.addAttribute("returnUrl", returnUrl);
        
        return null;
        
	}
}
