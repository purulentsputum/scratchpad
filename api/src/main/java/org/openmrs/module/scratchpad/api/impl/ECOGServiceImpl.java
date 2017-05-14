/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.scratchpad.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.api.ConceptService;
import org.openmrs.api.ObsService;
import org.openmrs.api.OrderContext;
import org.openmrs.api.OrderService;
import org.openmrs.api.PatientService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.scratchpad.api.ECOGService;
import org.openmrs.module.scratchpad.api.db.ScratchPadDAO;
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

public class ECOGServiceImpl extends BaseOpenmrsService implements ECOGService{
	
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

	@Transactional(readOnly = true)
	@Override
	public List<Obs> getObs() {
		//List<Obs> retVar = new ArrayList<Obs>();
		
		
		List<Obs> retVar=getObsForAllPatients(getECOGConcept());
		
		
		return retVar; 

	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Obs> getObs(Patient patient) {
		List<Obs> retVar=obsService.getObservationsByPersonAndConcept(patient.getPerson(), getECOGConcept());
		//Collections.sort(retVar, new ObsByDateComparator());
		return retVar;
		
	}
	
	@Transactional(readOnly = true)
	public List<Obs> getObsForPatient(Concept concept, Patient patient){
		//List<Obs> retVar = new ArrayList<Obs>();
		List<Obs> retVar;
		retVar=obsService.getObservationsByPersonAndConcept(patient.getPerson(), concept);
		
		//Collections.sort(retVar, new ObsByDateComparator());
		
		return retVar;
	}

	@Override
	public void saveObs(Obs obs) {
		obs = obsService.saveObs(obs, "");
		
	}

	
	
	public List<Obs> getObsForAllPatients(Concept concept){
		List<Obs> retVar = new ArrayList<Obs>();
		List<Patient> patients = patientService.getAllPatients();
		
		for (Patient patient:patients){
			retVar.addAll(obsService.getObservationsByPersonAndConcept(patient,concept));
		}
		
		//Collections.sort(retVar, new ObsByDateComparator());  fix later
		
		return retVar;
	}

	@Override
	public Obs getObs(String uuid) {
		
		return obsService.getObsByUuid(uuid);
	}
	
	@Override
	public List<Obs> getObsSet(String uuid){
		List<Obs> retVar = new ArrayList<Obs>();
		retVar.add(obsService.getObsByUuid(uuid));
		return retVar;
	}
	
	@Override
	public Concept getECOGConcept(){
		return conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ECOG_UUID);
	}
	
	@Override
	public Order getOrder(String uuid) {
		return orderService.getOrderByUuid(uuid);
	}
	
	@Override 
	public void saveOrder(Order order, OrderContext orderContext) {
		order = orderService.saveOrder(order, orderContext);
	}

	@Override
	public List<Order> getAllOrders(Patient patient){
		List <Order> retVar;
		retVar = orderService.getAllOrdersByPatient(patient);
		return retVar;
	}
	
	@Override
	public List<Order> getActiveOrdersByPatient(Patient patient) {
		
		List<Order> retVar;
		retVar=orderService.getActiveOrders(patient, orderService.getOrderTypeByConcept(getECOGConcept()), null, null);
		return retVar;
	}
	
	@Override
	public void discontinueOrder(Order order) {
		Order orderToGo = order.cloneForDiscontinuing();
		orderService.saveOrder(orderToGo, null);
	}
}
