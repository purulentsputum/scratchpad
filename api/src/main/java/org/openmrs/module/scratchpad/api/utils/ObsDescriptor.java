package org.openmrs.module.scratchpad.api.utils;

import org.openmrs.Location;
import org.openmrs.Obs;

public class ObsDescriptor {
	private java.util.Date date;
	private String accessionNumber;
	private Location location;
	private Obs obs;
	
	ObsDescriptor(){}
	
	ObsDescriptor(java.util.Date date, String accessionNumber, Location location) {
		setDate(date);
		setAccessionNumber(accessionNumber);
		setLocation(location);
	}
	ObsDescriptor(java.util.Date date, String accessionNumber, Location location, Obs obs) {
		setDate(date);
		setAccessionNumber(accessionNumber);
		setLocation(location);
		setObs(obs);
	}
	
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public String getAccessionNumber() {
		return accessionNumber;
	}
	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

	public Obs getObs() {
		return obs;
	}

	public void setObs(Obs obs) {
		this.obs = obs;
	}
	public String getObsGroupUuid() {
		if (!(obs==null)) {
			return obs.getUuid();
		}else{
			return ConceptUUID.UUID_EMPTY;
		}
	}
	public Integer getObsId() {
		if (!(obs==null)) {
			return obs.getId();
		}else{
			return 0;
		}
	}
	
	
}
