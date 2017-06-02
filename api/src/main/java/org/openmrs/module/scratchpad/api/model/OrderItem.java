package org.openmrs.module.scratchpad.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openmrs.CareSetting;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Order.Action;
import org.openmrs.Order.Urgency;
import org.openmrs.OrderFrequency;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.TestOrder;

public class OrderItem {

	private TestOrder order;
	private Action action;
	private Date autoExpireDate;
	private CareSetting careSetting;  //INPATIENT or OUTPATIENT
	private String clinicalHistory;
	private String commentToFulfiller;
	private Concept concept;
	private Encounter encounter;
	private Date dateActivated;
	private Date dateStopped;
	private OrderFrequency frequency;
	private String instructions;
	private Integer numberOfRepeats;
	private Provider orderer;
	private Concept orderReason;
	private Patient patient;
	private Date scheduledDate;
	private Urgency urgency;    //ROUTINE or STAT
	private Concept specimenSource;
	private Location locationRequester;
	private Location locationDeliverer;
	private List<String> copyTo;
	
	
	OrderItem() {
	}
	// getters and setters
	public TestOrder getOrder() {
		return order;
	}

	public void setOrder(TestOrder order) {
		this.order = order;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Date getAutoExpireDate() {
		return autoExpireDate;
	}

	public void setAutoExpireDate(Date autoExpireDate) {
		this.autoExpireDate = autoExpireDate;
	}

	public CareSetting getCareSetting() {
		return careSetting;
	}

	public void setCareSetting(CareSetting careSetting) {
		this.careSetting = careSetting;
	}

	public String getClinicalHistory() {
		return clinicalHistory;
	}

	public void setClinicalHistory(String clinicalHistory) {
		this.clinicalHistory = clinicalHistory;
	}

	public String getCommentToFulfiller() {
		return commentToFulfiller;
	}

	public void setCommentToFulfiller(String commentToFulfiller) {
		this.commentToFulfiller = commentToFulfiller;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Date getDateActivated() {
		return dateActivated;
	}

	public void setDateActivated(Date dateActivated) {
		this.dateActivated = dateActivated;
	}

	/**
	 * 
	 * @return Date date stopped or date to autoexpire
	 */
	public Date getDateStopped() {
		return dateStopped;
	}

	/**
	 * 
	 * @param dateStopped date to autostop
	 */
	public void setDateStopped(Date dateStopped) {
		this.dateStopped = dateStopped;
	}

	public OrderFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(OrderFrequency frequency) {
		this.frequency = frequency;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Integer getNumberOfRepeats() {
		return numberOfRepeats;
	}

	public void setNumberOfRepeats(Integer numberOfRepeats) {
		this.numberOfRepeats = numberOfRepeats;
	}

	/**
	 * @return the orderer
	 */
	public Provider getOrderer() {
		return orderer;
	}
	/**
	 * @param orderer the orderer to set
	 */
	public void setOrderer(Provider orderer) {
		this.orderer = orderer;
	}
	public Concept getOrderReason() {
		return orderReason;
	}

	public void setOrderReason(Concept orderReason) {
		this.orderReason = orderReason;
	}

	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public Concept getSpecimenSource() {
		return specimenSource;
	}

	public void setSpecimenSource(Concept specimenSource) {
		this.specimenSource = specimenSource;
	}
	/**
	 * @return the locationRequester
	 */
	public Location getLocationRequester() {
		return locationRequester;
	}
	/**
	 * @param locationRequester the locationRequester to set
	 */
	public void setLocationRequester(Location locationRequester) {
		this.locationRequester = locationRequester;
	}
	/**
	 * @return the locationDeliverer
	 */
	public Location getLocationDeliverer() {
		return locationDeliverer;
	}
	/**
	 * @param locationDeliverer the locationDeliverer to set
	 */
	public void setLocationDeliverer(Location locationDeliverer) {
		this.locationDeliverer = locationDeliverer;
	}
	/**
	 * @return the copyTo
	 */
	public List<String> getCopyTo() {
		if (copyTo == null){
			copyTo = new ArrayList<String>();
		}
		return copyTo;
	}
	/**
	 * @param copyTo the copyTo to set
	 */
	public void setCopyTo(List<String> copyTo) {
		this.copyTo = copyTo;
	}
	/**
	 * 
	 * @param copyPerson
	 */
	public void addCopyTo(String copyPerson){
		if (copyTo == null){
			copyTo = new ArrayList<String>();
		}
		copyTo.add(copyPerson);
	}
	
}
