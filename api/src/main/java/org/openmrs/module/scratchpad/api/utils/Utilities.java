package org.openmrs.module.scratchpad.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;



public class Utilities {

	public static Boolean validUuid(String uuid){
		Boolean retVar = true;
		
		
		if (StringUtils.isBlank(uuid)||(uuid.length()<32)||(uuid.length()>40)) {
			retVar = false;
		}

		return retVar;
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static String noNulls(String in){
		if (StringUtils.isBlank(in)) {
			return "";
		} else {
			return in;
		}
	}
	
	//date functions
	public static final String DefaultTimeFormat = "HH:mm";
    public static final String DefaultDateFormat = "dd-MMM-yyyy";
    public static final String DefaultDateTimeFormat = DefaultDateFormat + " " + DefaultTimeFormat;

    
	public static Date ConvertStringToDate(String date,String inFormat){
        SimpleDateFormat df = new SimpleDateFormat(inFormat);
        Date dt=null;
        try
         {
	  dt= df.parse(date);
         } catch (ParseException e)
         {
        //handle exception
        }
        return dt;
    }
	static Date ConvertStringToDate(String date){
        SimpleDateFormat df = new SimpleDateFormat(DefaultDateFormat);
        Date dt=null;
        try
         {
	  dt= df.parse(date);
         } catch (ParseException e)
         {
        //handle exception
        }
        return dt;
    }
	static Date ConvertStringToTime(String nTime,String inFormat){
        SimpleDateFormat df = new SimpleDateFormat(inFormat);
        Date dt=null;
        try
         {
	  dt= df.parse(nTime);
         } catch (ParseException e)
         {
        //handle exception
        }
        return dt;
    }
    
    static Date ConvertStringToTime(String nTime){
        SimpleDateFormat df = new SimpleDateFormat(DefaultTimeFormat);
        Date dt=null;
        try
         {
	  dt= df.parse(nTime);
         } catch (ParseException e)
         {
        //handle exception
        }
        return dt;
    }
    public static String ConvertDateTimeToString(java.util.Date dt){
        /**
         * returns the full  portion of date dt as a string
         */
        String RetVar;
        try {
            SimpleDateFormat formatter=new SimpleDateFormat(DefaultDateTimeFormat);
            RetVar =formatter.format(dt);
        } catch (Exception e) {
            RetVar = "";
    }

    return RetVar;
 }
    public static String ConvertDateTimeToString(java.util.Date dt, String dtFormat){
        /**
         * returns the full  portion of date dt as a string
         */
        String RetVar;
        try {
            SimpleDateFormat formatter=new SimpleDateFormat(dtFormat);
            RetVar =formatter.format(dt);
        } catch (Exception e) {
            RetVar = "";
    }

    return RetVar;
 }

    
}
