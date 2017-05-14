package org.openmrs.module.scratchpad.api.model;

import java.util.Date;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.module.scratchpad.api.utils.Utilities;
import org.openmrs.ui.framework.SimpleObject;

public class ObsItem {
	private Integer obsId = 0;
	private String date;
	private Date obsDate;
	private Location location;
	private Patient patient;
	private Encounter encounter;
	private Order order;
	private String comments;
	private String labNumber = "";
	private String obsGroupUUID;				//used if editing/replacing an existing result
	private String changeReason = "";
	
	public static String dateFormatJava = "dd-MM-yyyy";
	public static String dateFormatJS = "dd-mm-yy";
	
	//items from view
		public Integer getObsId(){
			return obsId;
		}
		public void setId(Integer obsId){
			this.obsId = obsId;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
			obsDate =Utilities.ConvertStringToDate(date,dateFormatJava);
		}
		public Date getObsDate(){
			if (obsDate==null){
				obsDate=new Date();
			}
			return obsDate;
		}
		public void setObsDate(Date obsDate){
			this.obsDate=obsDate;
			this.date=Utilities.ConvertDateTimeToString(obsDate, dateFormatJava);
		}
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		public Patient getPatient() {
			return patient;
		}
		public void setPatient(Patient patient) {
			this.patient = patient;
		}
		public Encounter getEncounter() {
			return encounter;
		}
		public void setEncounter(Encounter encounter) {
			this.encounter = encounter;
		}
		public Order getOrder() {
			return order;
		}
		public void setOrder(Order order) {
			this.order = order;
		}
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}
		public String getLabNumber() {
			return labNumber;
		}
		public void setLabNumber(String labNumber) {
			this.labNumber = labNumber;
		}
		public String getObsGroupUUID() {
			return obsGroupUUID;
		}
		public void setObsGroupUUID(String obsGroupUUID) {
			this.obsGroupUUID = obsGroupUUID;
		}
		public String getChangeReason() {
			return changeReason;
		}
		public void setChangeReason(String changeReason) {
			this.changeReason = changeReason;
		}
		
		protected SimpleObject getObsItemSimpleObject(){
			SimpleObject retVar = new SimpleObject();
			retVar.put("obsId", getObsId());
			retVar.put("date", getDate());
			retVar.put("location",getLocation().getName());
			//retVar.put("patient", getPatient().getId()); not used and not liked by AJAX anyway
			retVar.put("encounter",getEncounter());
			retVar.put("order", getOrder());
			retVar.put("comments", getComments());
			retVar.put("labNumber", getLabNumber());
			retVar.put("obsGroupUUID", getObsGroupUUID());
			
			return retVar;
		}
		
}
