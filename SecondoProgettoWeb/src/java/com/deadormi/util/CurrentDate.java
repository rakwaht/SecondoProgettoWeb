package com.deadormi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 *
 * @author francesco
 */
public class CurrentDate {
   
    public static String getCurrentDate () {
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd 'alle' HH:mm:ss");
        String res = formato.format(date);
        return res;
    }
    
    public static String getDatePlus3Min (String time) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 'alle' HH:mm:ss");
        Date d = df.parse(time); 
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, 3);
        String time_plus_3_min = df.format(cal.getTime());
        
        return time_plus_3_min;
    }
    
    public static Date toDate(String time) throws ParseException {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 'alle' HH:mm:ss");
       Date d = df.parse(time);
       
       return d;
    }
    
}