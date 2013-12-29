/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Davide
 */
public class Parser {
    
     private Pattern pattern;
    private Matcher matcher;
 
    private static final String EMAIL_PATTERN = 
	"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public Parser() {
 	pattern = Pattern.compile(EMAIL_PATTERN);
    }
    
    public boolean isEmail(final String hex) {
	matcher = pattern.matcher(hex);
	return matcher.matches();
    }
    
    public static boolean isNumeric(String num){
        Pattern pattern = Pattern.compile("^-?\\d+\\.?\\d*$");
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }
    
}
