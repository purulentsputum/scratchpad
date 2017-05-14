package org.openmrs.module.scratchpad.api.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.ConceptService;
import org.openmrs.api.LocationService;
import org.openmrs.api.ObsService;
import org.openmrs.api.OrderService;
import org.openmrs.api.PatientService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.scratchpad.api.ESSService;
import org.openmrs.module.scratchpad.api.db.ScratchPadDAO;
import org.openmrs.module.scratchpad.api.emums.ESSAnswerType;
import org.openmrs.module.scratchpad.api.emums.ESSQuestionType;
import org.openmrs.module.scratchpad.api.model.EpworthItem;
import org.openmrs.module.scratchpad.api.utils.Array2D;
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;
import org.openmrs.module.scratchpad.api.utils.ObsByDateDescendingComparator;
import org.openmrs.module.scratchpad.api.utils.ObsDescriptor;
import org.openmrs.module.scratchpad.api.utils.ObsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class ESSServiceImpl extends BaseOpenmrsService implements ESSService{

protected final Log log = LogFactory.getLog(this.getClass());
	
	private ScratchPadDAO dao;
	
	@Autowired
	@Qualifier("conceptService")
	private ConceptService conceptService;

	@Autowired
	@Qualifier("obsService")
	private ObsService obsService;
	
	@Autowired
	@Qualifier("patientService")
	private PatientService patientService;
	
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;

	@Autowired
	@Qualifier("locationService")
	private LocationService locationService;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(ScratchPadDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public ScratchPadDAO getDao() {
	    return dao;
    }
	
	public ConceptService getConceptService() {
		return conceptService;
	}

	public void setConceptService(ConceptService conceptService) {
		this.conceptService = conceptService;
	}

	public ObsService getObsService() {
		return obsService;
	}

	public void setObsService(ObsService obsService) {
		this.obsService = obsService;
	}

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@Override
	public Concept getConcept(String uuid){
		return conceptService.getConceptByUuid(uuid);
	}
	@Override
	public List<Obs> getResults() {
		List<Obs> retVar=getObsForAllPatients(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_RESULT_UUID));
		return retVar; 
	}

	@Override
	@Cascade({CascadeType.SAVE_UPDATE})
	public void saveObs(Obs obs) {
		obs = obsService.saveObs(obs, "");
	}
	
	@Override
	@Cascade({CascadeType.SAVE_UPDATE})
	public void saveObs(Obs obs, String reason) {
		obs = obsService.saveObs(obs, reason);
	}
	
	@Override
	public List<Obs> getResults(Patient patient) {
		List<Obs> retVar=obsService.getObservationsByPersonAndConcept(patient.getPerson(), 
				conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_RESULT_UUID));
		Collections.sort(retVar, new ObsByDateDescendingComparator());
		return retVar;
	}
	public List<Obs> getObsForAllPatients(Concept concept){
		List<Obs> retVar = new ArrayList<Obs>();
		List<Patient> patients = patientService.getAllPatients();
		for (Patient patient:patients){
			retVar.addAll(obsService.getObservationsByPersonAndConcept(patient,concept));
		}
		Collections.sort(retVar, new ObsByDateDescendingComparator());  
		return retVar;
	}
	
	private ObsUtils obsList;
	@Override
	public Array2D<Obs> getObsArray(Patient patient){
		obsList = new ObsUtils(patient, getConcepts(), obsService); 
		return obsList.getObsArray();
	}
	
	@Override
	public Array2D<String> getCompletedObsArray(Patient patient){
		return new ObsUtils(patient, getConcepts(), obsService).convertObsArrayToString();
	}
	
	@Override
	public Array2D<String> getProcessedObsArray(Patient patient){
		obsList = new ObsUtils(patient, getConcepts(), obsService);
		Array2D<String> retVar = new Array2D<String>(); 
		for (int i=0; i<obsList.getObsArray().sizeRows();i++) {
			for (int j=0; j<obsList.getObsArray().sizeColumns();j++) {
				
				if ((obsList.getObsArray().get(i, j))==null){
					retVar.set(i,j," ");
				} else {
					retVar.set(i, j,ObsUtils.obsResult(obsList.getObsArray().get(i,j)));
				}	
			}
		}
		return retVar;
	}
	public  Map<Integer,String> getConceptMap(){
		return obsList.getConceptMap();
	}
	public  Map<Integer,ObsDescriptor> getObsDescriptorMap(){
		return obsList.getObsMap();
	}
	
	public Array2D<String> getESSViewList(Patient patient, Boolean vertical) {
		return getViewList(patient, vertical, getConcepts());
	}
	
	private List<Concept> getConcepts(){
		List<Concept> conceptList = new ArrayList<Concept>();
		conceptList.add(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_RESULT_UUID));
		conceptList.add(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ1_UUID));
		conceptList.add(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ2_UUID));
		conceptList.add(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ3_UUID));
		conceptList.add(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ4_UUID));
		conceptList.add(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ5_UUID));
		conceptList.add(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ6_UUID));
		conceptList.add(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ7_UUID));
		conceptList.add(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ8_UUID));
		return conceptList;
	}
	
	private Array2D<String> getViewList(Patient patient, Boolean vertical,List<Concept> concepts ){
		ObsUtils obsList = new ObsUtils(patient,concepts, obsService);
		Array2D<String> obsTable = obsList.convertObsArrayToString();
		if (!vertical) {
			obsTable = obsTable.flipXandYaxis();
		}
		return obsTable;
	}
	
	@Override
	public Obs buildEssObsSet(EpworthItem ess){
		if (ess.getObsId()>0) {
			return buildESSObsSetUpdated(ess);
		} else {
			return buildEssObsSetNew(ess);
		}
	}

	private Obs buildESSObsSetUpdated(EpworthItem ess){
		
		String conceptUuid;
		int questionNum;
		
		Obs essObsSet = obsService.getObs(ess.getObsId());
		if (essObsSet.getConcept().getUuid().equalsIgnoreCase(ConceptUUID.CONCEPT_EPWORTHSET_UUID)){
			essObsSet.setComment(ess.getComments());
			essObsSet.setAccessionNumber(ess.getLabNumber());
			
			Set<Obs> obsMemberListExisting = essObsSet.getGroupMembers();
			Set<Obs> obsMemberListNew = new HashSet<Obs>();
			
			Boolean oldObsQuestions = (obsMemberListExisting.size()>1);
			Boolean newObsQuestions = ess.areEssQuestionsValid();
			
			if (newObsQuestions) {
				if (oldObsQuestions) {
					//both existing and updated obs include questions
					for (Obs essObsItem:obsMemberListExisting) {
						conceptUuid=essObsItem.getConcept().getUuid();
						if(conceptUuid.equals(ConceptUUID.CONCEPT_ESS_RESULT_UUID)){
							obsMemberListNew.add(CreateEssResultObs(essObsItem.getId(),ess));
						} else {
							questionNum = ESSQuestionType.getESSQuestionTypeFromUuid(conceptUuid).getKey();
							if (questionNum>0&&questionNum<9) {
								obsMemberListNew.add(createEssAnswerObs(questionNum,essObsItem.getId(),ess));
							}
						}
					}
				}else{
					//new questions, old result only
					for (Obs essObsItem:obsMemberListExisting){
						conceptUuid=essObsItem.getConcept().getUuid();
						if(conceptUuid.equals(ConceptUUID.CONCEPT_ESS_RESULT_UUID)){
							obsMemberListNew.add(CreateEssResultObs(essObsItem.getId(),ess));
						}
					}
					for (int loopVar=1; loopVar<9; loopVar++) {
						obsMemberListNew.add(createEssAnswerObs(loopVar,0,ess));
					}
				}
					
			} else {
				// new obs result only
				for (Obs essObsItem:obsMemberListExisting){
					conceptUuid=essObsItem.getConcept().getUuid();
					if(conceptUuid.equals(ConceptUUID.CONCEPT_ESS_RESULT_UUID)){
						obsMemberListNew.add(CreateEssResultObs(essObsItem.getId(),ess));
					}
				}
			}
			essObsSet.setGroupMembers(obsMemberListNew);
		}	
		return essObsSet;
	}
	
	private Obs buildEssObsSetNew(EpworthItem ess){
		
		Obs essSetObs = new Obs();
			essSetObs.setConcept(getConcept(ConceptUUID.CONCEPT_EPWORTHSET_UUID));
			essSetObs.setObsDatetime(ess.getObsDate());
			essSetObs.setLocation(ess.getLocation());
			essSetObs.setPerson(ess.getPatient().getPerson());
			essSetObs.setComment(ess.getComments());
			essSetObs.setAccessionNumber(ess.getLabNumber());
			if (!(ess.getEncounter() ==null)) {
				essSetObs.setEncounter(ess.getEncounter());
			}
		
			Set<Obs> obsList = new HashSet<Obs>();
			obsList.add(CreateEssResultObs(0,ess));
			if (ess.areEssQuestionsValid()) {
				for (int loopVar=1; loopVar<9; loopVar++) {
					obsList.add(createEssAnswerObs(loopVar,0,ess));
				}
			}
			essSetObs.setGroupMembers(obsList);
			
		return essSetObs;
	}
	
	private Obs createEssAnswerObs(int questionNo, int obsId, EpworthItem ess) {
		Obs obs;
		String uuidConcept = "";
		String uuidAnswer = "";
		if (obsId>0) {
			obs = obsService.getObs(obsId);
		}else{
			obs = new Obs();
		}
		switch (questionNo) {
		case 1:
			uuidConcept=ConceptUUID.CONCEPT_ESSQ1_UUID;
			uuidAnswer=ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ1()).getUuid();
			break;
		case 2:
			uuidConcept=ConceptUUID.CONCEPT_ESSQ2_UUID;
			uuidAnswer=ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ2()).getUuid();
			break;
		case 3:
			uuidConcept=ConceptUUID.CONCEPT_ESSQ3_UUID;
			uuidAnswer=ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ3()).getUuid();
			break;
		case 4:
			uuidConcept=ConceptUUID.CONCEPT_ESSQ4_UUID;
			uuidAnswer=ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ4()).getUuid();
			break;
		case 5:
			uuidConcept=ConceptUUID.CONCEPT_ESSQ5_UUID;
			uuidAnswer=ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ5()).getUuid();
			break;
		case 6:
			uuidConcept=ConceptUUID.CONCEPT_ESSQ6_UUID;
			uuidAnswer=ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ6()).getUuid();
			break;
		case 7:
			uuidConcept=ConceptUUID.CONCEPT_ESSQ7_UUID;
			uuidAnswer=ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ7()).getUuid();
			break;
		case 8:
			uuidConcept=ConceptUUID.CONCEPT_ESSQ8_UUID;
			uuidAnswer=ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ8()).getUuid();
			break;
		}	
		if (uuidConcept.length()>5) {
			obs.setConcept(getConcept(uuidConcept));
			obs.setObsDatetime(ess.getObsDate());
			obs.setLocation(ess.getLocation());
			obs.setPerson(ess.getPatient().getPerson());
			obs.setComment(ess.getComments());
			obs.setAccessionNumber(ess.getLabNumber());
			obs.setValueCoded(getConcept(uuidAnswer));
		}
		
		return obs;
	}
	
	private Obs CreateEssResultObs(int obsId, EpworthItem ess) {
		Obs obs;
		if (obsId>0) {
			obs = obsService.getObs(obsId);
		}else{
			obs = new Obs();
		}
		
		obs.setConcept(getConcept(ConceptUUID.CONCEPT_ESS_RESULT_UUID));
		obs.setObsDatetime(ess.getObsDate());
		obs.setLocation(ess.getLocation());
		obs.setPerson(ess.getPatient().getPerson());
		obs.setComment(ess.getComments());
		obs.setAccessionNumber(ess.getLabNumber());
		obs.setValueNumeric((double) ess.getResult());
		
		return obs;
	}
	
	@Override
	public EpworthItem buildEpworthItem(Integer obsId){
		
		String conceptUuid;
		EpworthItem epworthItem = new EpworthItem();
		Obs essObsSet = new Obs();
		
		if (obsId >0){
			essObsSet = obsService.getObs(obsId);
			if (essObsSet.isObsGrouping()){
				if (essObsSet.getConcept().getUuid().equals(ConceptUUID.CONCEPT_EPWORTHSET_UUID)) {
					
					epworthItem.setObsGroupUUID(essObsSet.getUuid());
					epworthItem.setId(obsId);
					epworthItem.setObsDate(essObsSet.getObsDatetime());
					epworthItem.setComments(essObsSet.getComment());
					epworthItem.setLabNumber(essObsSet.getAccessionNumber());
					epworthItem.setLocation(essObsSet.getLocation());
					//epworthItem.setPatient((Patient)essSetObs.getPerson());
					
					
					Set<Obs> essObsList = essObsSet.getGroupMembers();
					for (Obs ess:essObsList) {
						conceptUuid=ess.getConcept().getUuid();
						if(conceptUuid.equals(ConceptUUID.CONCEPT_ESS_RESULT_UUID)){
							epworthItem.setESSresult(Integer.toString(ess.getValueNumeric().intValue()));
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ1_UUID)){
							epworthItem.setQ1(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ2_UUID)){
							epworthItem.setQ2(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ3_UUID)){
							epworthItem.setQ3(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ4_UUID)){
							epworthItem.setQ4(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ5_UUID)){
							epworthItem.setQ5(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ6_UUID)){
							epworthItem.setQ6(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ7_UUID)){
							epworthItem.setQ7(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ8_UUID)){
							epworthItem.setQ8(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}
					}
					
				}
			}
		}
		return epworthItem;
	}
	
	
	
	
	/*
	
	@Deprecated
	public EpworthItem buildEpworthItem(String uuid){
		
		String conceptUuid;
		EpworthItem epworthItem = new EpworthItem();
		Obs essSetObs = new Obs();
		
		if (!uuid.equals(ConceptUUID.UUID_EMPTY)){
			essSetObs = obsService.getObsByUuid(uuid);
			if (essSetObs.isObsGrouping()){
				if (essSetObs.getConcept().getUuid().equals(ConceptUUID.CONCEPT_EPWORTHSET_UUID)) {
					
					epworthItem.setObsGroupUUID(uuid);
					epworthItem.setId(essSetObs.getId());
					epworthItem.setObsDate(essSetObs.getObsDatetime());
					epworthItem.setComments(essSetObs.getComment());
					epworthItem.setLabNumber(essSetObs.getAccessionNumber());
					epworthItem.setLocation(essSetObs.getLocation());
					//epworthItem.setPatient((Patient)essSetObs.getPerson());
					
					
					Set<Obs> essObsList = essSetObs.getGroupMembers();
					for (Obs ess:essObsList) {
						conceptUuid=ess.getConcept().getUuid();
						if(conceptUuid.equals(ConceptUUID.CONCEPT_ESS_RESULT_UUID)){
							epworthItem.setESSresult(Integer.toString(ess.getValueNumeric().intValue()));
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ1_UUID)){
							epworthItem.setQ1(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ2_UUID)){
							epworthItem.setQ2(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ3_UUID)){
							epworthItem.setQ3(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ4_UUID)){
							epworthItem.setQ4(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ5_UUID)){
							epworthItem.setQ5(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ6_UUID)){
							epworthItem.setQ6(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ7_UUID)){
							epworthItem.setQ7(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						}else if (conceptUuid.equals( ConceptUUID.CONCEPT_ESSQ8_UUID)){
							epworthItem.setQ8(ESSAnswerType.getESSAnswerTypeFromUuid(ess.getValueCoded().getUuid()).getKey());
						
						
					}
					
				}
			}
		}
		return epworthItem;
	}
	
	
	 // old methods
	 

	
	
	
	public Encounter saveEpworthSleepinessScore(EpworthItem epworthItem) {
		if (epworthItem.validate()) {
			
		}
		
		return null;
	}
	public Encounter saveEss(Obs obsGroup) 
			throws EncounterDateBeforeVisitStartDateException, EncounterDateAfterVisitStopDateException{
		Encounter encounter = new Encounter();
		//encounter.setEncounterType();
		
		return null;
	}
	
	
		@Deprecated
		private Obs buildEssObsSetNewOLD(EpworthItem ess){
		
		String uuid;
		int answer;
		
		
		Obs essSetObs = new Obs();
			essSetObs.setConcept(getConcept(ConceptUUID.CONCEPT_EPWORTHSET_UUID));
			essSetObs.setObsDatetime(ess.getObsDate());
			essSetObs.setLocation(ess.getLocation());
			essSetObs.setPerson(ess.getPatient().getPerson());
			essSetObs.setComment(ess.getComments());
			essSetObs.setAccessionNumber(ess.getLabNumber());
			
		
		Obs obs = new Obs();
			uuid=ConceptUUID.CONCEPT_ESS_RESULT_UUID;
			obs.setConcept(getConcept(uuid));
			obs.setObsDatetime(ess.getObsDate());
			obs.setLocation(ess.getLocation());
			obs.setPerson(ess.getPatient().getPerson());
			obs.setComment(ess.getComments());
			obs.setAccessionNumber(ess.getLabNumber());
			obs.setValueNumeric((double) ess.getResult());
			essSetObs.addGroupMember(obs);
		
		if (ess.areEssQuestionsValid()) {
			
		
			obs = new Obs();
				uuid=ConceptUUID.CONCEPT_ESSQ1_UUID;
				answer = ess.getQ1();
				obs.setConcept(getConcept(uuid));
				obs.setObsDatetime(ess.getObsDate());
				obs.setLocation(ess.getLocation());
				obs.setPerson(ess.getPatient().getPerson());
				obs.setComment(ess.getComments());
				obs.setAccessionNumber(ess.getLabNumber());
				obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
				essSetObs.addGroupMember(obs);
			obs = new Obs();
				uuid=ConceptUUID.CONCEPT_ESSQ2_UUID;
				answer = ess.getQ2();
				obs.setConcept(getConcept(uuid));
				obs.setObsDatetime(ess.getObsDate());
				obs.setLocation(ess.getLocation());
				obs.setPerson(ess.getPatient().getPerson());
				obs.setComment(ess.getComments());
				obs.setAccessionNumber(ess.getLabNumber());
				obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
				essSetObs.addGroupMember(obs);
			obs = new Obs();
				uuid=ConceptUUID.CONCEPT_ESSQ3_UUID;
				answer = ess.getQ3();
				obs.setConcept(getConcept(uuid));
				obs.setObsDatetime(ess.getObsDate());
				obs.setLocation(ess.getLocation());
				obs.setPerson(ess.getPatient().getPerson());
				obs.setComment(ess.getComments());
				obs.setAccessionNumber(ess.getLabNumber());
				obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
				essSetObs.addGroupMember(obs);
			obs = new Obs();
				uuid=ConceptUUID.CONCEPT_ESSQ4_UUID;
				answer = ess.getQ4();
				obs.setConcept(getConcept(uuid));
				obs.setObsDatetime(ess.getObsDate());
				obs.setLocation(ess.getLocation());
				obs.setPerson(ess.getPatient().getPerson());
				obs.setComment(ess.getComments());
				obs.setAccessionNumber(ess.getLabNumber());
				obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
				essSetObs.addGroupMember(obs);	
			obs = new Obs();
				uuid=ConceptUUID.CONCEPT_ESSQ5_UUID;
				answer = ess.getQ5();
				obs.setConcept(getConcept(uuid));
				obs.setObsDatetime(ess.getObsDate());
				obs.setLocation(ess.getLocation());
				obs.setPerson(ess.getPatient().getPerson());
				obs.setComment(ess.getComments());
				obs.setAccessionNumber(ess.getLabNumber());
				obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
				essSetObs.addGroupMember(obs);	
			obs = new Obs();
				uuid=ConceptUUID.CONCEPT_ESSQ6_UUID;
				answer = ess.getQ6();
				obs.setConcept(getConcept(uuid));
				obs.setObsDatetime(ess.getObsDate());
				obs.setLocation(ess.getLocation());
				obs.setPerson(ess.getPatient().getPerson());
				obs.setComment(ess.getComments());
				obs.setAccessionNumber(ess.getLabNumber());
				obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
				essSetObs.addGroupMember(obs);	
			obs = new Obs();
				uuid=ConceptUUID.CONCEPT_ESSQ7_UUID;
				answer = ess.getQ7();
				obs.setConcept(getConcept(uuid));
				obs.setObsDatetime(ess.getObsDate());
				obs.setLocation(ess.getLocation());
				obs.setPerson(ess.getPatient().getPerson());
				obs.setComment(ess.getComments());
				obs.setAccessionNumber(ess.getLabNumber());
				obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
				essSetObs.addGroupMember(obs);
			obs = new Obs();
				uuid=ConceptUUID.CONCEPT_ESSQ8_UUID;
				answer = ess.getQ8();
				obs.setConcept(getConcept(uuid));
				obs.setObsDatetime(ess.getObsDate());
				obs.setLocation(ess.getLocation());
				obs.setPerson(ess.getPatient().getPerson());
				obs.setComment(ess.getComments());
				obs.setAccessionNumber(ess.getLabNumber());
				obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
				essSetObs.addGroupMember(obs);
				
		}
		
		return essSetObs;
	}
		@Deprecated
		private Obs buildESSObsSetUpdatedOLD(EpworthItem ess){
			
			String conceptUuid;
			String uuid;
			int answer;
			Obs obs = new Obs();
			
			Obs essObsSet = obsService.getObs(ess.getObsId());
			if (essObsSet.getConcept().getUuid().equalsIgnoreCase(ConceptUUID.CONCEPT_EPWORTHSET_UUID)){
				essObsSet.setComment(ess.getComments());
				essObsSet.setAccessionNumber(ess.getLabNumber());
				
				Set<Obs> essObsList = essObsSet.getGroupMembers();
				if ((essObsList.size()>1)&&(ess.areEssQuestionsValid())) {
					//both existing and updated obs include questions
					for (Obs essObsItem:essObsList) {
						conceptUuid=essObsItem.getConcept().getUuid();
						if(conceptUuid.equals(ConceptUUID.CONCEPT_ESS_RESULT_UUID)){
							essObsItem.setValueNumeric((double) ess.getResult());
							essObsItem.setComment(ess.getComments());
							essObsItem.setAccessionNumber(ess.getLabNumber());
							essObsSet.addGroupMember(essObsItem);
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ1_UUID)){
							essObsItem.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ1()).getUuid()));
							essObsItem.setComment(ess.getComments());
							essObsItem.setAccessionNumber(ess.getLabNumber());
							essObsSet.addGroupMember(essObsItem);
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ2_UUID)){
							essObsItem.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ2()).getUuid()));
							essObsItem.setComment(ess.getComments());
							essObsItem.setAccessionNumber(ess.getLabNumber());
							essObsSet.addGroupMember(essObsItem);
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ3_UUID)){
							essObsItem.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ3()).getUuid()));
							essObsItem.setComment(ess.getComments());
							essObsItem.setAccessionNumber(ess.getLabNumber());
							essObsSet.addGroupMember(essObsItem);
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ4_UUID)){
							essObsItem.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ4()).getUuid()));
							essObsItem.setComment(ess.getComments());
							essObsItem.setAccessionNumber(ess.getLabNumber());
							essObsSet.addGroupMember(essObsItem);
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ5_UUID)){
							essObsItem.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ5()).getUuid()));
							essObsItem.setComment(ess.getComments());
							essObsItem.setAccessionNumber(ess.getLabNumber());
							essObsSet.addGroupMember(essObsItem);
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ6_UUID)){
							essObsItem.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ6()).getUuid()));
							essObsItem.setComment(ess.getComments());
							essObsItem.setAccessionNumber(ess.getLabNumber());
							essObsSet.addGroupMember(essObsItem);
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ7_UUID)){
							essObsItem.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ7()).getUuid()));
							essObsItem.setComment(ess.getComments());
							essObsItem.setAccessionNumber(ess.getLabNumber());
							essObsSet.addGroupMember(essObsItem);
						}else if(conceptUuid.equals(ConceptUUID.CONCEPT_ESSQ8_UUID)){
							essObsItem.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(ess.getQ8()).getUuid()));
							essObsItem.setComment(ess.getComments());
							essObsItem.setAccessionNumber(ess.getLabNumber());
							essObsSet.addGroupMember(essObsItem);
						}
					}
				} else if ((!(essObsList.size()>1))&&(ess.areEssQuestionsValid())) {
					//existing obs result ony, updated includes questions
					if (essObsList.size()==1) {
						for (Obs essObsItem:essObsList) {
							conceptUuid=essObsItem.getConcept().getUuid();
							if(conceptUuid.equals(ConceptUUID.CONCEPT_ESS_RESULT_UUID)){
								essObsItem.setValueNumeric((double) ess.getResult());
								essObsItem.setComment(ess.getComments());
								essObsItem.setAccessionNumber(ess.getLabNumber());
								essObsSet.addGroupMember(essObsItem);
							}
						}
					}else {
						// shouldn't get here but create result if didnt exist
						obs = new Obs();
						uuid=ConceptUUID.CONCEPT_ESS_RESULT_UUID;
						obs.setConcept(getConcept(uuid));
						obs.setObsDatetime(ess.getObsDate());
						obs.setLocation(ess.getLocation());
						obs.setPerson(ess.getPatient().getPerson());
						obs.setComment(ess.getComments());
						obs.setAccessionNumber(ess.getLabNumber());
						obs.setValueNumeric((double) ess.getResult());
						essObsSet.addGroupMember(obs);
					}
					obs = new Obs();
						uuid=ConceptUUID.CONCEPT_ESSQ1_UUID;
						answer = ess.getQ1();
						obs.setConcept(getConcept(uuid));
						obs.setObsDatetime(ess.getObsDate());
						obs.setLocation(ess.getLocation());
						obs.setPerson(ess.getPatient().getPerson());
						obs.setComment(ess.getComments());
						obs.setAccessionNumber(ess.getLabNumber());
						obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
						essObsSet.addGroupMember(obs);
					obs = new Obs();
						uuid=ConceptUUID.CONCEPT_ESSQ2_UUID;
						answer = ess.getQ2();
						obs.setConcept(getConcept(uuid));
						obs.setObsDatetime(ess.getObsDate());
						obs.setLocation(ess.getLocation());
						obs.setPerson(ess.getPatient().getPerson());
						obs.setComment(ess.getComments());
						obs.setAccessionNumber(ess.getLabNumber());
						obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
						essObsSet.addGroupMember(obs);
					obs = new Obs();
						uuid=ConceptUUID.CONCEPT_ESSQ3_UUID;
						answer = ess.getQ3();
						obs.setConcept(getConcept(uuid));
						obs.setObsDatetime(ess.getObsDate());
						obs.setLocation(ess.getLocation());
						obs.setPerson(ess.getPatient().getPerson());
						obs.setComment(ess.getComments());
						obs.setAccessionNumber(ess.getLabNumber());
						obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
						essObsSet.addGroupMember(obs);
					obs = new Obs();
						uuid=ConceptUUID.CONCEPT_ESSQ4_UUID;
						answer = ess.getQ4();
						obs.setConcept(getConcept(uuid));
						obs.setObsDatetime(ess.getObsDate());
						obs.setLocation(ess.getLocation());
						obs.setPerson(ess.getPatient().getPerson());
						obs.setComment(ess.getComments());
						obs.setAccessionNumber(ess.getLabNumber());
						obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
						essObsSet.addGroupMember(obs);	
					obs = new Obs();
						uuid=ConceptUUID.CONCEPT_ESSQ5_UUID;
						answer = ess.getQ5();
						obs.setConcept(getConcept(uuid));
						obs.setObsDatetime(ess.getObsDate());
						obs.setLocation(ess.getLocation());
						obs.setPerson(ess.getPatient().getPerson());
						obs.setComment(ess.getComments());
						obs.setAccessionNumber(ess.getLabNumber());
						obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
						essObsSet.addGroupMember(obs);	
					obs = new Obs();
						uuid=ConceptUUID.CONCEPT_ESSQ6_UUID;
						answer = ess.getQ6();
						obs.setConcept(getConcept(uuid));
						obs.setObsDatetime(ess.getObsDate());
						obs.setLocation(ess.getLocation());
						obs.setPerson(ess.getPatient().getPerson());
						obs.setComment(ess.getComments());
						obs.setAccessionNumber(ess.getLabNumber());
						obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
						essObsSet.addGroupMember(obs);	
					obs = new Obs();
						uuid=ConceptUUID.CONCEPT_ESSQ7_UUID;
						answer = ess.getQ7();
						obs.setConcept(getConcept(uuid));
						obs.setObsDatetime(ess.getObsDate());
						obs.setLocation(ess.getLocation());
						obs.setPerson(ess.getPatient().getPerson());
						obs.setComment(ess.getComments());
						obs.setAccessionNumber(ess.getLabNumber());
						obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
						essObsSet.addGroupMember(obs);
					obs = new Obs();
						uuid=ConceptUUID.CONCEPT_ESSQ8_UUID;
						answer = ess.getQ8();
						obs.setConcept(getConcept(uuid));
						obs.setObsDatetime(ess.getObsDate());
						obs.setLocation(ess.getLocation());
						obs.setPerson(ess.getPatient().getPerson());
						obs.setComment(ess.getComments());
						obs.setAccessionNumber(ess.getLabNumber());
						obs.setValueCoded(getConcept(ESSAnswerType.getESSAnswerTypeFromKey(answer).getUuid()));
						essObsSet.addGroupMember(obs);
					
					
				} else if ((essObsList.size()>1)&&(!(ess.areEssQuestionsValid()))){
					//existing obs contains questions, updated is result only
					for (Obs essObsItem:essObsList) {
						conceptUuid=essObsItem.getConcept().getUuid();
						if(conceptUuid.equals(ConceptUUID.CONCEPT_ESS_RESULT_UUID)){
							essObsItem.setValueNumeric((double) ess.getResult());
							essObsItem.setComment(ess.getComments());
							essObsItem.setAccessionNumber(ess.getLabNumber());
							essObsSet.addGroupMember(essObsItem);
						}else {
							essObsSet.removeGroupMember(essObsItem);
						}
					}
					
					
				} else {
					//both existing and updated contain result only
				}
			}	

			
			//Obs obs = new Obs();
				uuid=ConceptUUID.CONCEPT_ESS_RESULT_UUID;
				obs.setConcept(getConcept(uuid));
				obs.setObsDatetime(ess.getObsDate());
				obs.setLocation(ess.getLocation());
				obs.setPerson(ess.getPatient().getPerson());
				obs.setComment(ess.getComments());
				obs.setAccessionNumber(ess.getLabNumber());
				obs.setValueNumeric((double) ess.getResult());
				essObsSet.addGroupMember(obs);
			
			if (ess.areEssQuestionsValid()) {
				
					
			}
			
			return essObsSet;
		}
	*/
	
}
