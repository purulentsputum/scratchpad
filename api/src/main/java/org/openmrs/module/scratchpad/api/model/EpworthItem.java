package org.openmrs.module.scratchpad.api.model;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.ui.framework.SimpleObject;

/**
 * 
 * @author rosssellars
 *
 * This is the model for data to be transfered from the fragment to the controller
 * 
 * 
 */
public class EpworthItem extends ObsItem{
	//items from view
	
	private Boolean essByQuestions;
	private String ESSresult = "";		//directly entered by user
	private String ESSresultCalc = ""; 	//calculated from questions
	private int Q1 =-1;
	private int Q2 =-1;
	private int Q3 =-1;
	private int Q4 =-1;
	private int Q5 =-1;
	private int Q6 =-1;
	private int Q7 =-1;
	private int Q8 =-1;
	
	//items used in model
	private int essResultInt =-1;
	
	private final int essMax = 24;
	private final int essMin = 0;
	
	public EpworthItem(){}
	
	public static String[] getProperties(){
		return  new String[] { "obsId", "date", "patient", "location", "encounter","visit","comments","labNumber","obsGroupUUID","ESSresult","Q1","Q2","Q3","Q4","Q5","Q6","Q7","Q8" };
		
	}
	

	public Boolean getEssByQuestions() {
		return essByQuestions;
	}

	public void setEssByQuestions(Boolean essByQuestions) {
		this.essByQuestions = essByQuestions;
	}

	
	
	
	/**
	 * 
	 * @return String result entered directly by user
	 */
	public String getESSresult() {
		return ESSresult;
	}
	public void setESSresult(String eSSresult) {
		ESSresult = eSSresult;
	}

	
	/**
	 * 
	 * @return String result calculated from questions by view
	 */
	public String getESSresultCalc() {
		return ESSresultCalc;
	}
	public void setESSresultCalc(String eSSresult) {
		ESSresultCalc = eSSresult;
	}
	public int getQ1() {
		return Q1;
	}

	public void setQ1(int q1) {
		Q1 = q1;
	}

	public int getQ2() {
		return Q2;
	}

	public void setQ2(int q2) {
		Q2 = q2;
	}

	public int getQ3() {
		return Q3;
	}

	public void setQ3(int q3) {
		Q3 = q3;
	}

	public int getQ4() {
		return Q4;
	}

	public void setQ4(int q4) {
		Q4 = q4;
	}

	public int getQ5() {
		return Q5;
	}

	public void setQ5(int q5) {
		Q5 = q5;
	}

	public int getQ6() {
		return Q6;
	}

	public void setQ6(int q6) {
		Q6 = q6;
	}

	public int getQ7() {
		return Q7;
	}

	public void setQ7(int q7) {
		Q7 = q7;
	}

	public int getQ8() {
		return Q8;
	}

	public void setQ8(int q8) {
		Q8 = q8;
	}
	
	/**
	 * 
	 * @return Boolean true if all questions have been answered
	 */
	public boolean areEssQuestionsValid() {
		if ((!(getQ1()<0)) && (!(getQ1()>3)) &&
			(!(getQ2()<0)) && (!(getQ2()>3)) &&
			(!(getQ3()<0)) && (!(getQ3()>3)) &&
			(!(getQ4()<0)) && (!(getQ4()>3)) &&
			(!(getQ5()<0)) && (!(getQ5()>3)) &&
			(!(getQ6()<0)) && (!(getQ6()>3)) &&
			(!(getQ7()<0)) && (!(getQ7()>3)) &&
			(!(getQ8()<0)) && (!(getQ8()>3)))
		{
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @return boolean true if user entered ESS result is valid
	 */
	public boolean isEssResultOnlyValid() {
		int temp = getEssResultDirectlyEntered();
		if (temp ==-1) {
			return false;
		}else{
			return true;
		}
	}
	/**
	 * Calculates ESS from questions,
	 * -1 if invalid answers
	 * @return integer ESS score
	 */
	public int getEssResultFromQuestions() {
		if (areEssQuestionsValid()){
			return getQ1()+getQ2()+getQ3()+getQ4()+getQ5()+getQ6()+getQ7()+getQ8();
		}else{
			return -1;
		}
	}

	/**
	 * ESS result entered by the user
	 * -1 if no valid result is obtained.
	 * @return integer ESS score
	 */
	public int getEssResultDirectlyEntered() {
		int retVar;
		try {
			retVar = Integer.parseInt(this.getESSresult());
			if ((retVar<essMin)||(retVar>essMax)) {
				retVar = -1;
			}
		} catch (Exception ex) {
			retVar = -1;
		}
		return retVar;
	}
	
	/**
	 * Provides the total ESS result
	 * If questions are complete, this is used irrespective of the total entered.
	 * If questions are not complete, the entered total score is used.  
	 * -1 is returned if no valid ESS result is founs
	 * @return integer the ESS result 
	 */
	public int getResult() {
		int retVar = getEssResultFromQuestions();
		if (retVar==-1){
			retVar = getEssResultDirectlyEntered();
		}
		return retVar;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean validate(){
		boolean retVar = false;
		if (areEssQuestionsValid()||isEssResultOnlyValid()){
			if (getPatient() != null) {
				if (getDate()==null){
					setObsDate(new java.util.Date());
				}
				if (StringUtils.isBlank(getLabNumber())) {
					setLabNumber(java.util.UUID.randomUUID().toString());
				}
				if (getLocation() == null) {
					setLocation(Context.getLocationService().getDefaultLocation());
				}
				retVar = true;
			}
		}
		return retVar;
	}
	
	public SimpleObject toSimpleObject(){
		SimpleObject retVar = super.getObsItemSimpleObject();
		retVar.put("essByQuestions",getEssByQuestions());
		retVar.put("ESSresult",getESSresult());		
		retVar.put("Q1",getQ1());
		retVar.put("Q2",getQ2());
		retVar.put("Q3",getQ3());
		retVar.put("Q4",getQ4());
		retVar.put("Q5",getQ5());
		retVar.put("Q6",getQ6());
		retVar.put("Q7",getQ7());
		retVar.put("Q8",getQ8());
		return retVar;
	}
	
	
	@Deprecated
	private int getESSresultInt() {
		int retVar;
		if ((this.essResultInt >essMin)||(this.essResultInt >essMax)){
			try {
				retVar = Integer.parseInt(ESSresult);
				if ((retVar<0)||(retVar>3)) {
					essResultInt = -1;
				}
			} catch (Exception ex) {
				essResultInt = -1;
			}
		}
		
		return essResultInt;
	}
	/**
	 * 
	 * @return 
	 */
	@Deprecated
	public int getEssResult(){
		validESSquestions();
		return essResultInt;
	}
	@Deprecated
	public boolean validESSresultOnly() {
		int result = getESSresultInt();
		if ((!(result<0))&&(!(result>24))){
			return true;
		}else {
			return false;
		}
	}
	@Deprecated
	public boolean validESSquestions() {
		if ((!(getQ1()<0)) && (!(getQ1()>3)) &&
			(!(getQ2()<0)) && (!(getQ2()>3)) &&
			(!(getQ3()<0)) && (!(getQ3()>3)) &&
			(!(getQ4()<0)) && (!(getQ4()>3)) &&
			(!(getQ5()<0)) && (!(getQ5()>3)) &&
			(!(getQ6()<0)) && (!(getQ6()>3)) &&
			(!(getQ7()<0)) && (!(getQ7()>3)) &&
			(!(getQ8()<0)) && (!(getQ8()>3)))
		{
			essResultInt=getQ1()+getQ2()+getQ3()+getQ4()+getQ5()+getQ6()+getQ7()+getQ8();
			return true;
		}else{
			essResultInt=getESSresultInt();
			return false;
		}
	}
	
	
}
