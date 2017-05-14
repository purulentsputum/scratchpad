package org.openmrs.module.scratchpad;

import org.openmrs.Obs;
import org.openmrs.module.scratchpad.api.utils.Utilities;

public class RFT {

	private Obs fev1;
	private Obs fev1_post;
	private Obs fev1_pred;
	private Obs fvc;
	private Obs fvc_post;
	private Obs FVCpred;
	private Obs mmefr;
	private Obs mmefr_post;
	private Obs mmefr_pred;
	private Obs mef50;
	private Obs mef50_post;
	private Obs mef50_pred;
	private Obs pef;
	private Obs pef_post;
	private Obs pef_pred;
	private Obs fif50;
	private Obs fif50_post;
	private Obs fif50_pred;
	//volumes
	private Obs tlc;
	private Obs tcl_pred;
	private Obs frc;
	private Obs frc_pred;
	private Obs rv;
	private Obs rv_pred;
	private Obs vc;
	private Obs vc_pred;
	//gas transfer
	private Obs dlco;
	private Obs dlco_pred;
	private Obs kco;
	private Obs kco_pred;
	private Obs va;
	private Obs va_pred;
	private Obs vin;
	private Obs vin_pred;
	
	
	public Obs getFEV1() {
		return fev1;
	}
	public void setFEV1(Obs fev1) {
		this.fev1 = fev1;
	}
	public Obs getFEV1post() {
		return fev1_post;
	}
	public void setFEV1post(Obs fEV1post) {
		fev1_post = fEV1post;
	}
	public Obs getFEV1pred() {
		return fev1_pred;
	}
	public void setFEV1pred(Obs fEV1pred) {
		fev1_pred = fEV1pred;
	}
	public double fev1PercentPredicted(){
		return percentRatio(this.getFEV1(),this.getFEV1pred());
	}
	public double fev1PostPercentPredicted(){
		return percentRatio(this.getFEV1post(),this.getFEV1pred());
	}
	public double fev1PercentPostChanged(){
		return percentChange(this.getFEV1(),this.getFEV1post());
	}
	
	
	public Obs getFVC() {
		return fvc;
	}
	public void setFVC(Obs fVC) {
		fvc = fVC;
	}
	public Obs getFVCpost() {
		return fvc_post;
	}
	public void setFVCpost(Obs fVCpost) {
		fvc_post = fVCpost;
	}
	public Obs getFVCpred() {
		return FVCpred;
	}
	public void setFVCpred(Obs fVCpred) {
		FVCpred = fVCpred;
	}
	public double fvcPercentPredicted(){
		return percentRatio(this.getFVC(),this.getFVCpred());
	}
	public double fvcPostPercentPredicted(){
		return percentRatio(this.getFVCpost(),this.getFVCpred());
	}
	public double fvcPercentPostChanged(){
		return percentChange(this.getFVC(),this.getFVCpost());
	}
	
	
	public Obs getMMEFR() {
		return mmefr;
	}
	public void setMMEFR(Obs mMEFR) {
		mmefr = mMEFR;
	}
	public Obs getMMEFRpost() {
		return mmefr_post;
	}
	public void setMMEFRpost(Obs mMEFRpost) {
		mmefr_post = mMEFRpost;
	}
	public Obs getMMEFRpred() {
		return mmefr_pred;
	}
	public void setMMEFRpred(Obs mMEFRpred) {
		mmefr_pred = mMEFRpred;
	}
	public double mmefrPercentPredicted(){
		return percentRatio(this.getMMEFR(),this.getMMEFRpred());
	}
	public double mmefrPostPercentPredicted(){
		return percentRatio(this.getMMEFRpost(),this.getMMEFRpred());
	}
	public double mmefrPercentPostChanged(){
		return percentChange(this.getMMEFR(),this.getMMEFRpost());
	}
	
	
	
	public Obs getMEF50() {
		return mef50;
	}
	public void setMEF50(Obs mEF50) {
		mef50 = mEF50;
	}
	public Obs getMEF50post() {
		return mef50_post;
	}
	public void setMEF50post(Obs mef50_post) {
		this.mef50_post = mef50_post;
	}
	public Obs getMEF50pred() {
		return mef50_pred;
	}
	public void setMEF50pred(Obs mEF50pred) {
		mef50_pred = mEF50pred;
	}
	public double mef50PercentPredicted(){
		return percentRatio(this.getMEF50(),this.getMEF50pred());
	}
	public double mef50PostPercentPredicted(){
		return percentRatio(this.getMEF50post(),this.getMEF50pred());
	}
	public double mef50PercentPostChanged(){
		return percentChange(this.getMEF50(),this.getMEF50post());
	}
	
	
	public Obs getPEF() {
		return pef;
	}
	public void setPEF(Obs pEF) {
		pef = pEF;
	}
	public Obs getPEFpost() {
		return pef_post;
	}
	public void setPEFpost(Obs pef_post) {
		this.pef_post = pef_post;
	}
	public Obs getPEFpred() {
		return pef_pred;
	}
	public void setPEFpred(Obs pEFpred) {
		pef_pred = pEFpred;
	}
	public double pefPercentPredicted(){
		return percentRatio(this.getPEF(),this.getPEFpred());
	}
	public double pefPostPercentPredicted(){
		return percentRatio(this.getPEFpost(),this.getPEFpred());
	}
	public double pefPercentPostChanged(){
		return percentChange(this.getPEF(),this.getPEFpost());
	}
	
	
	public Obs getFIF50() {
		return fif50;
	}
	public void setFIF50(Obs fIF50) {
		fif50 = fIF50;
	}
	public Obs getFIF50post() {
		return fif50_post;
	}
	public void setFIF50post(Obs fif50_post) {
		this.fif50_post = fif50_post;
	}
	public Obs getFIF50pred() {
		return fif50_pred;
	}
	public void setFIF50pred(Obs fIF50pred) {
		fif50_pred = fIF50pred;
	}
	public double fif50PercentPredicted(){
		return percentRatio(this.getFIF50(),this.getFIF50pred());
	}
	public double fif50PostPercentPredicted(){
		return percentRatio(this.getFIF50post(),this.getFIF50pred());
	}
	public double fif50PercentPostChanged(){
		return percentChange(this.getFIF50(),this.getFIF50post());
	}
	
	/*
	 * volumes
	 */
	
	public Obs getTLC() {
		return tlc;
	}
	public void setTLC(Obs tlc) {
		this.tlc = tlc;
	}
	public Obs getTLCpred() {
		return tcl_pred;
	}
	public void setTLCpred(Obs tLc_pred) {
		this.tcl_pred = tLc_pred;
	}
	public double tlcPercentPredicted(){
		return percentRatio(this.getTLC(),this.getTLCpred());
	}
	
	
	public Obs getFRC() {
		return frc;
	}
	public void setFRC(Obs frc) {
		this.frc = frc;
	}
	public Obs getFRCpred() {
		return frc_pred;
	}
	public void setFRCpred(Obs frc_pred) {
		this.frc_pred = frc_pred;
	}
	public double frcPercentPredicted(){
		return percentRatio(this.getFRC(),this.getFRCpred());
	}
	
	public Obs getRV() {
		return rv;
	}
	public void setRV(Obs rv) {
		this.rv = rv;
	}
	public Obs getRVpred() {
		return rv_pred;
	}
	public void setRVpred(Obs rv_pred) {
		this.rv_pred = rv_pred;
	}
	public double rvPercentPredicted(){
		return percentRatio(this.getRV(),this.getRVpred());
	}
	
	public Obs getVC() {
		return vc;
	}
	public void setVC(Obs vc) {
		this.vc = vc;
	}
	public Obs getVCpred() {
		return vc_pred;
	}
	public void setVCpred(Obs vc_pred) {
		this.vc_pred = vc_pred;
	}
	public double vcPercentPredicted(){
		return percentRatio(this.getVC(),this.getVCpred());
	}
	
	/*
	 * gas transfer
	 */
	public Obs getDLCO() {
		return dlco;
	}
	public void setDLCO(Obs dlco) {
		this.dlco = dlco;
	}
	public Obs getDLCOpred() {
		return dlco_pred;
	}
	public void setDLCOpred(Obs dlco_pred) {
		this.dlco_pred = dlco_pred;
	}
	public double dlcoPercentPredicted(){
		return percentRatio(this.getDLCO(),this.getDLCOpred());
	}
	
	public Obs getKCO() {
		return kco;
	}
	public void setKCO(Obs kco) {
		this.kco = kco;
	}
	public Obs getKCOpred() {
		return kco_pred;
	}
	public void setKCOpred(Obs kco_pred) {
		this.kco_pred = kco_pred;
	}
	public double kcoPercentPredicted(){
		return percentRatio(this.getKCO(),this.getKCOpred());
	}
	
	public Obs getVA() {
		return va;
	}
	public void setVA(Obs va) {
		this.va = va;
	}
	public Obs getVApred() {
		return va_pred;
	}
	public void setVApred(Obs va_pred) {
		this.va_pred = va_pred;
	}
	public double vaPercentPredicted(){
		return percentRatio(this.getVA(),this.getVApred());
	}
	
	public Obs getVIN() {
		return vin;
	}
	public void setVIN(Obs vin) {
		this.vin = vin;
	}
	public Obs getVINpred() {
		return vin_pred;
	}
	public void setVINpred(Obs vin_pred) {
		this.vin_pred = vin_pred;
	}
	public double vinPercentPredicted(){
		return percentRatio(this.getVIN(),this.getVINpred());
	}
	
	/*
	 * 
	 */
	private double percentRatio(Obs observed, Obs predicted){
		double retVar = 0.0;
		if ((observed != null)&&(predicted != null)){
			double obsValue = observed.getValueNumeric();
			double predValue = predicted.getValueNumeric();
			if ((obsValue>0)&&(predValue>0)){
				retVar = Utilities.round((obsValue*100)/predValue,1);
			}
		}
		
		return retVar;
	}
	private double percentChange(Obs pre, Obs post){
		double retVar = 0.0;
		if((pre != null)&&(post != null)){
			double preValue = pre.getValueNumeric();
			double postValue = post.getValueNumeric();
			if((preValue>0)&&(postValue>0)){
				retVar = Utilities.round((postValue-preValue)/preValue,1);
			}
		}
		return retVar;
	}
	
	
	
	public String reportNumerical(){
		String retVar = "";
		
		return retVar;
	}
	
	public String reportTestDraft(){
		String retVar = "";
		
		return retVar;
	}
	
}
