package com.deadormi.util;

import java.text.SimpleDateFormat;
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
    
}