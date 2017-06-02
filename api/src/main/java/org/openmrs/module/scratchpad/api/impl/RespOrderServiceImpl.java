package org.openmrs.module.scratchpad.api.impl;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.TestOrder;
import org.openmrs.api.ConceptService;
import org.openmrs.api.OrderContext;
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
	public void saveOrderNew(TestOrder order){
		OrderContext orderContext = new OrderContext();
		
		
		
		
		
		
		
		orderContext.setCareSetting(orderService.getCareSetting(careSettingId));   //INPATIENT or OUTPATIENT    
        orderContext.setOrderType(orderService.getOrderType(orderTypeId));
		order = orderService.saveOrder(order, orderContext);
	}
	public void saveOrderExisting(Order order){
		
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
	 * 
	 */
	@Override
	public void buildOrder(OrderItem orderItem) {
		orderItem.setOrder(new TestOrder());
		populateOrder(orderItem);
	}
	/**
	 * Builds order based on existing order.
	 * @param id existing order to replace
	 * @return updated order
	 */
	@Override
	public void buildOrder(Integer id, OrderItem orderItem) {
		TestOrder testOrder = (TestOrder)orderService.getOrder(id).cloneForRevision();
		if (testOrder == null){
			testOrder = new TestOrder();
		}
		orderItem.setOrder(testOrder);
		populateOrder(orderItem);
	}
	/**
	 * 
	 * Should only be called from building order
	 * @param orderItem
	 */
	private void populateOrder(OrderItem orderItem){
		orderItem.getOrder().setAction(orderItem.getAction());
		orderItem.getOrder().setAutoExpireDate(orderItem.getAutoExpireDate());
		orderItem.getOrder().setCareSetting(orderItem.getCareSetting());
		orderItem.getOrder().setClinicalHistory(orderItem.getClinicalHistory());
		orderItem.getOrder().setCommentToFulfiller(orderItem.getCommentToFulfiller());
		orderItem.getOrder().setConcept(orderItem.getConcept());
		orderItem.getOrder().setDateActivated(orderItem.getDateActivated());
		//retVar.setDateStopped(orderItem.getDateStopped());
		orderItem.getOrder().setEncounter(orderItem.getEncounter());
		orderItem.getOrder().setFrequency(orderItem.getFrequency());
		orderItem.getOrder().setInstructions(orderItem.getInstructions());
		orderItem.getOrder().setNumberOfRepeats(orderItem.getNumberOfRepeats());
		orderItem.getOrder().setOrderer(orderItem.getOrderer());
		orderItem.getOrder().setOrderReason(orderItem.getOrderReason());
		orderItem.getOrder().setPatient(orderItem.getPatient());
		orderItem.getOrder().setScheduledDate(orderItem.getScheduledDate());
		orderItem.getOrder().setSpecimenSource(orderItem.getSpecimenSource());
		orderItem.getOrder().setUrgency(orderItem.getUrgency());
	}
	
	
	/**
	 * builds a new order from data entered
	 * @return newly generated order
	 *
	@Override
	public TestOrder buildOrder(OrderItem orderItem) {
		TestOrder retVar = new TestOrder();
		return populateOrder(retVar,orderItem);
	}
	
	/**
	 * Builds order based on existing order.
	 * @param id existing order to replace
	 * @return updated order
	 *
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
	 *
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
	*/
}
