package org.openmrs.module.scratchpad.api.model;

import java.util.Date;

public class EcogItem {
	private String uuidECOG;
	private Date dateECOG;
	private Integer newECOG;
	private String commentsECOG;
	
	public EcogItem(){}
	
	/**
	 * @return the uuidECOG
	 */
	public String getUuidECOG() {
		return uuidECOG;
	}
	/**
	 * @param uuidECOG the uuidECOG to set
	 */
	public void setUuidECOG(String uuidECOG) {
		this.uuidECOG = uuidECOG;
	}
	/**
	 * @return the dateECOG
	 */
	public Date getDateECOG() {
		return dateECOG;
	}
	/**
	 * @param dateECOG the dateECOG to set
	 */
	public void setDateECOG(Date dateECOG) {
		this.dateECOG = dateECOG;
	}
	/**
	 * @return the newECOG
	 */
	public Integer getNewECOG() {
		return newECOG;
	}
	/**
	 * @param newECOG the newECOG to set
	 */
	public void setNewECOG(Integer newECOG) {
		this.newECOG = newECOG;
	}
	/**
	 * @return the commentsECOG
	 */
	public String getCommentsECOG() {
		return commentsECOG;
	}
	/**
	 * @param commentsECOG the commentsECOG to set
	 */
	public void setCommentsECOG(String commentsECOG) {
		this.commentsECOG = commentsECOG;
	}
}
