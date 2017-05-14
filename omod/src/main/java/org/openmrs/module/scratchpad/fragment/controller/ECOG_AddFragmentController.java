package org.openmrs.module.scratchpad.fragment.controller;

import java.util.List;

import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.module.scratchpad.api.ECOGService;
import org.openmrs.module.scratchpad.api.emums.ECOGType;
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;
import org.openmrs.module.scratchpad.api.utils.Utilities;
import org.openmrs.module.scratchpad.api.model.EcogItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.BindParams;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

public class ECOG_AddFragmentController {

	public void controller(
			FragmentModel model
			){
		
		model.addAttribute("uuidNull", ConceptUUID.UUID_EMPTY);
		model.addAttribute("selectList", ECOGType.getList());
		model.addAttribute("showSelectButton", false);
	}
	
	public String post(
			@SpringBean ECOGService service,
			@RequestParam(value="patientId") Patient patient,
			@RequestParam(value="returnUrl", required = false) String returnUrl,			
			@BindParams EcogItem ecogItems,			
			UiUtils ui,
			FragmentConfiguration config,
			FragmentModel model)
	{	
		
		
		String uuid;
		if (Utilities.validUuid(ecogItems.getUuidECOG())){
			uuid=ecogItems.getUuidECOG();
		}else{
			uuid=ConceptUUID.UUID_EMPTY;
		}
		
		
		Obs obs;
		obs = service.getObs(uuid);
		
		if (obs == null) {
			obs = new Obs();
			obs.setConcept(service.getECOGConcept());
		}

		obs.setValueNumeric(ecogItems.getNewECOG().doubleValue());
		obs.setObsDatetime(ecogItems.getDateECOG());
		obs.setComment(ecogItems.getCommentsECOG());
		obs.setPerson(patient.getPerson());
		
		service.saveObs(obs);
		
		return "redirect:" + ui.pageLink("scratchpad", "ECOGList",SimpleObject.create("patientId",patient.getId(),"returnUrl",returnUrl));
		
		
	}
	
	public List<SimpleObject> getSelectedObs(@RequestParam(value="selectedUUID", required=false) String uuid,
											 @SpringBean ECOGService service,
											 @RequestParam(value="patientId") Patient patient,
											 @RequestParam(value="properties", required=false) String[] properties,
											 UiUtils ui){
		
		List<Obs> obs;
		if(properties==null){
			properties = new String[]{"uuid", "obsDatetime", "location", "valueNumeric", "comment"};
		}
		if (uuid==null) {
			obs=service.getObs(patient);
		}else{
			obs=service.getObsSet(uuid);
		}
		
		return SimpleObject.fromCollection(obs, ui, properties);
	}
	
	
	
}
