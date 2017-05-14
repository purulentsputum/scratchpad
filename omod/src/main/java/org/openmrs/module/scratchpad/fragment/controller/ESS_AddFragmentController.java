package org.openmrs.module.scratchpad.fragment.controller;


import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.module.scratchpad.api.ESSService;
import org.openmrs.module.scratchpad.api.emums.ESSAnswerType;
import org.openmrs.module.scratchpad.api.emums.ESSQuestionType;
import org.openmrs.module.scratchpad.api.model.EpworthItem;
import org.openmrs.module.scratchpad.api.model.ObsItem;
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.BindParams;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

public class ESS_AddFragmentController {

	public void controller(
			FragmentModel model
			){
		
		model.addAttribute("uuidNew", java.util.UUID.randomUUID());
		model.addAttribute("uuidNull", ConceptUUID.UUID_EMPTY);
		model.addAttribute("answerList", ESSAnswerType.getList());
		model.addAttribute("questionList", ESSQuestionType.getList());
		model.addAttribute("dateFormat", ObsItem.dateFormatJS);
	}
	
	public String post(
			@SpringBean ESSService service,
			@RequestParam(value="patientId") Patient patient,
			@RequestParam(value="encounterId", required = false) Encounter encounter,
			@RequestParam(value="returnUrl", required = false) String returnUrl,			
			@BindParams EpworthItem epworthItem,			
			UiUtils ui,
			FragmentConfiguration config,
			FragmentModel model)
	{
		
		Obs obs;
		epworthItem.setPatient(patient);
		obs = service.buildEssObsSet(epworthItem);
		if (obs != null) {
			service.saveObs(obs);
		}
		
		
		return "redirect:" + ui.pageLink("scratchpad", "ESS_List",SimpleObject.create("patientId",patient.getId(),"returnUrl",returnUrl));
	}
	
	public SimpleObject getObsGroup(
			@SpringBean ESSService service,
			UiUtils ui,
			@RequestParam(value="obsId", required = true) Integer obsId
			) {
		
		SimpleObject retVar = new SimpleObject();
		retVar=service.buildEpworthItem(obsId).toSimpleObject();
		
		return retVar;
	}
}
