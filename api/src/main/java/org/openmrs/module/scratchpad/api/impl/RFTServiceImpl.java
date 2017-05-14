package org.openmrs.module.scratchpad.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.ConceptService;
import org.openmrs.api.ObsService;
import org.openmrs.api.OrderService;
import org.openmrs.api.PatientService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.scratchpad.api.RFTService;
import org.openmrs.module.scratchpad.api.db.ScratchPadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class RFTServiceImpl extends BaseOpenmrsService implements RFTService{
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

}
