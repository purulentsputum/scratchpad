package org.openmrs.module.scratchpad.page.controller;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.module.coreapps.CoreAppsProperties;
import org.openmrs.module.emrapi.patient.PatientDomainWrapper;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.InjectBeans;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.openmrs.ui.framework.page.Redirect;
import org.springframework.web.bind.annotation.RequestParam;

public class ESS_ListPageController extends BasePageController{

	public Object controller(
			PageModel model,
			@InjectBeans PatientDomainWrapper patientDomainWrapper,
			@RequestParam(value="patientId", required = false) Patient patient,
			@RequestParam(value="encounterId", required = false) Encounter encounter,
			@RequestParam(value="returnUrl", required = false) String returnUrl,
			@RequestParam(value="returnLabel", required = false) String returnLabel,
			@RequestParam(value="returnPage", required = false) String returnPage,
			@RequestParam(value="returnProvider", required = false) String returnProvider,
			@RequestParam(value="breadcrumbOverride", required = false) String breadcrumbOverride,
			@SpringBean("coreAppsProperties") CoreAppsProperties coreAppsProperties,
            UiUtils ui
			){
		String breadcrumbMiddle = null;
		
		if (patient==null) {
			//return new Redirect("index.htm");
			return new Redirect("coreapps/findpatient/findPatient.page?app=coreapps.findPatient");
		}
		patientDomainWrapper.setPatient(patient);
		
		if (StringUtils.isBlank(returnProvider) && StringUtils.isBlank(returnPage)) { 
			returnProvider = "scratchpad"; 
			returnPage = "test";
		}
		if (StringUtils.isBlank(returnLabel)) { 
			returnLabel = "Observations";
		} 
		
		Visit visit = null;
		if (encounter != null) { visit = encounter.getVisit(); }
		   
		returnUrl = determineReturnURL(returnUrl, returnProvider, returnPage, patient, visit, ui);
        returnLabel = determineReturnLabel(returnLabel, patient, ui);
		
        if (StringUtils.isBlank(breadcrumbOverride)) {
			breadcrumbMiddle = "{ label: '" + returnLabel + "', link: '" + returnUrl + "' }";
		} else {
			breadcrumbMiddle = breadcrumbOverride;
		}

        model.addAttribute("encounter", encounter);
        model.addAttribute("patient", patientDomainWrapper);
        model.addAttribute("returnUrl", returnUrl);
        model.addAttribute("returnLabel", returnLabel);
        model.addAttribute("breadcrumbOverride", breadcrumbOverride);
        model.addAttribute("breadcrumbMiddle", breadcrumbMiddle);
        model.addAttribute("dashboardUrl", coreAppsProperties.getDashboardUrl());
		
        return null;
	}
}
