package org.openmrs.module.scratchpad.page.controller;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;

public abstract class BasePageController {
	
	protected String determineReturnURL(String returnURL, String returnProviderName, String returnPageName, Patient patient, Visit visit, UiUtils ui) {
		
		SimpleObject returnParams = null;
		
		if (patient != null) {
			if (visit == null) {
				returnParams = SimpleObject.create("patientId", patient.getId());
			} else {
				returnParams = SimpleObject.create("patientId", patient.getId(), "visitId", visit.getId());
			}
		}
		
		// see if return provider and page specified
		if (StringUtils.isNotBlank(returnProviderName) && StringUtils.isNotBlank(returnPageName)) {
			return ui.pageLink(returnProviderName, returnPageName, returnParams);
		}
		
		//see if returnURL specified
		if (StringUtils.isBlank(returnURL)) {
			return returnURL;
		}
		
		//otherwise return to patient dashboard or index depending on whether patient is specified
		if ((returnParams != null) && (returnParams.containsKey("patientId"))) {
			return ui.pageLink("coreapps", "patientdashboard/patientDashboard", returnParams);
		} else {
			return "/" + ui.contextPath() + "index.htm";
		}
	}
	
	protected String determineReturnLabel (String returnLabel, Patient patient, UiUtils ui) {
		
		if (StringUtils.isNotBlank(returnLabel)) {
			return ui.message(returnLabel);
		} else {
			return ui.escapeJs(ui.format(patient));
		}
	}

}
