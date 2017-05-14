package org.openmrs.module.scratchpad.api;

import java.util.List;
import java.util.Map;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.scratchpad.api.model.EpworthItem;
import org.openmrs.module.scratchpad.api.utils.Array2D;
import org.openmrs.module.scratchpad.api.utils.ObsDescriptor;

public interface ESSService extends OpenmrsService {
	public List<Obs> getResults();
	public List<Obs> getResults(Patient patient);
	public Array2D<String> getESSViewList(Patient patient, Boolean vertical);
	public Concept getConcept(String uuid);
	public void saveObs(Obs obs);
	public void saveObs(Obs obs, String reason);
	public Obs buildEssObsSet(EpworthItem ess);
	public Array2D<Obs> getObsArray(Patient patient);
	public Map<Integer,String> getConceptMap();
	public Map<Integer,ObsDescriptor> getObsDescriptorMap();
	public Array2D<String> getProcessedObsArray(Patient patient);
	//public EpworthItem buildEpworthItem(String uuid);
	public EpworthItem buildEpworthItem(Integer id);
	public Array2D<String> getCompletedObsArray(Patient patient);
	
	
	
	
}
