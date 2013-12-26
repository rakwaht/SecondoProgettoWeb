/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.util;

/**
 *
 * @author Davide
 */
public class Parser {
    
    public static Boolean isEmail(String email){
        //todo parser da migliorare
        if(!email.contains("@") || !email.contains(".") || email.length()<=6){
            return false;
        }
        return true;
    }
    
}
