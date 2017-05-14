package org.openmrs.module.scratchpad.api.impl;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.TestOrder;
import org.openmrs.api.ConceptService;
import org.openmrs.api.OrderService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.scratchpad.api.RespOrderService;
import org.openmrs.module.scratchpad.api.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class RespOrderServiceImpl extends BaseOpenmrsService implements RespOrderService{

	@Autowired
	@Qualifier("conceptService")
	private ConceptService conceptService;
	
	public void setConceptService(ConceptService conceptService) {
		this.conceptService = conceptService;
	}
	public  ConceptService getConceptService() {
		return conceptService;
	}
	
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService=orderService;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	
	public Concept getConcept(String uuid){
		return conceptService.getConceptByUuid(uuid);
	}
	/**
	 * 
	 * @param patient
	 * @return
	 */
	@Override
	public List<Order> getOrders(Patient patient){
		return orderService.getAllOrdersByPatient(patient);
	}
	/**
	 * 
	 * @param patient
	 * @param concept
	 * @return
	 */
	@Override
	public List<Order> getOrdersByConcept(Patient patient,Concept concept){
		return orderService.getOrderHistoryByConcept(patient, concept);
	}
	/**
	 * 
	 * @param patient
	 * @param conceptUuid
	 * @return
	 */
	@Override
	public List<Order> getOrdersByConcept(Patient patient,String conceptUuid){
		return orderService.getOrderHistoryByConcept(patient, getConcept(conceptUuid));
	}
	/**
	 * builds a new order from data entered
	 * @return newly generated order
	 */
	@Override
	public TestOrder buildOrder(OrderItem orderItem) {
		TestOrder retVar = new TestOrder();
		return populateOrder(retVar,orderItem);
	}
	
	/**
	 * Builds order based on existing order.
	 * @param id existing order to replace
	 * @return updated order
	 */
	@Override
	public TestOrder buildOrder(Integer id, OrderItem orderItem) {
		TestOrder retVar = (TestOrder)orderService.getOrder(id).cloneForRevision();
		return populateOrder(retVar,orderItem);
	}
	/**
	 * private method to populate order details
	 * @param retVar
	 * @param orderItem
	 * @return
	 */
	private TestOrder populateOrder(TestOrder retVar, OrderItem orderItem){
		retVar.setAction(orderItem.getAction());
		retVar.setAutoExpireDate(orderItem.getAutoExpireDate());
		retVar.setCareSetting(orderItem.getCareSetting());
		retVar.setClinicalHistory(orderItem.getClinicalHistory());
		retVar.setCommentToFulfiller(orderItem.getCommentToFulfiller());
		retVar.setConcept(orderItem.getConcept());
		retVar.setDateActivated(orderItem.getDateActivated());
		//retVar.setDateStopped(orderItem.getDateStopped());
		retVar.setEncounter(orderItem.getEncounter());
		retVar.setFrequency(orderItem.getFrequency());
		retVar.setInstructions(orderItem.getInstructions());
		retVar.setNumberOfRepeats(orderItem.getNumberOfRepeats());
		retVar.setOrderReason(orderItem.getOrderReason());
		retVar.setScheduledDate(orderItem.getScheduledDate());
		retVar.setSpecimenSource(orderItem.getSpecimenSource());
		retVar.setUrgency(orderItem.getUrgency());
		
		return retVar;
	}
}
