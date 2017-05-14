package org.openmrs.module.scratchpad.api.utils;

import java.util.List;

import org.openmrs.CareSetting;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.Order.Urgency;
import org.openmrs.OrderFrequency;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.TestOrder;
import org.openmrs.api.OrderService;
import org.openmrs.api.context.Context;

public class OrderUtils {
	
	private OrderService orderService;
	private Order newOrder;
	private Order oldOrder;
	private Patient patient;
	private Concept concept;
	private Provider provider;
	private Encounter encounter;
	private CareSetting careSetting;
	private Urgency urgency;
	private OrderFrequency orderFrequency;
	private Concept specimenSource;
	private Integer numberOfRepeats;
	private String clinicalHistory;
	private String reasonForModification;

	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public Order getOrder() {
		return newOrder;
	}
	public void setOrder(Order newOrder) {
		this.newOrder = newOrder;
	}
	public Order getOldOrder() {
		return oldOrder;
	}
	public void setOldOrder(Order oldOrder) {
		this.oldOrder = oldOrder;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Concept getConcept() {
		return concept;
	}
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public Encounter getEncounter() {
		return encounter;
	}
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	public CareSetting getCareSetting() {
		return careSetting;
	}
	public void setCareSetting(CareSetting careSetting) {
		this.careSetting = careSetting;
	}
	public Urgency getUrgency() {
		return urgency;
	}
	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}
	public void setUrgency(String urgencyName) {
		if (urgencyName.equalsIgnoreCase("routine")){
			this.urgency = Order.Urgency.ROUTINE;
		} else if (urgencyName.equalsIgnoreCase("stat")){
			this.urgency = Order.Urgency.STAT;
		} else if (urgencyName.equalsIgnoreCase("on scheduled date")){
			this.urgency = Order.Urgency.ON_SCHEDULED_DATE;
		} else {
			// invalid
			this.urgency = null;
		}
	}
	public OrderFrequency getOrderFrequency() {
		return orderFrequency;
	}
	public void setOrderFrequency(OrderFrequency orderFrequency) {
		this.orderFrequency = orderFrequency;
	}
	public Concept getSpecimenSource() {
		return specimenSource;
	}
	public void setSpecimenSource(Concept specimenSource) {
		this.specimenSource = specimenSource;
	}
	public Integer getNumberOfRepeats() {
		return numberOfRepeats;
	}
	public void setNumberOfRepeats(Integer numberOfRepeats) {
		this.numberOfRepeats = numberOfRepeats;
	}
	public String getClinicalHistory() {
		return clinicalHistory;
	}
	public void setClinicalHistory(String clinicalHistory) {
		this.clinicalHistory = clinicalHistory;
	}
	public String getReasonForModification() {
		return reasonForModification;
	}
	public void setReasonForModification(String reasonForModification) {
		this.reasonForModification = reasonForModification;
	}
	
	public Order generateOrder() {
		TestOrder order = new TestOrder();
		order.setPatient(getPatient());
		order.setConcept(getConcept());
		order.setOrderer(getProvider());
		order.setCareSetting(getCareSetting());
		order.setEncounter(getEncounter());
		 
		//The fields below are optional, you only set them where they apply
		order.setFrequency(getOrderFrequency());
		order.setSpecimenSource(getSpecimenSource());
		order.setNumberOfRepeats(getNumberOfRepeats());
		order.setClinicalHistory(getClinicalHistory());
		 
		return Context.getOrderService().saveOrder(order, null);
	}
	
	public Order discontinueOrder() {
		Order discontinuationOrder = getOrder().cloneForDiscontinuing();
		discontinuationOrder.setOrderer(getProvider());
		discontinuationOrder.setEncounter(getEncounter());
		 
		return orderService.saveOrder(discontinuationOrder, null);
	}
	
	
	
	
	
	public static List<Order> getAllOrders(Patient patient) {
		return Context.getOrderService().getAllOrdersByPatient(patient); 
	}
	public static List<Order> getActiveOrders(Patient patient) {
		return Context.getOrderService().getActiveOrders(patient, null, null,null);
	}
	public static List<Order> getOrderHistory(Patient patient, Concept concept) {
		return Context.getOrderService().getOrderHistoryByConcept(patient, concept);
	}
}
