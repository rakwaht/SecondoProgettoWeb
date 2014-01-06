/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author francesco
 */
public final class HashGenerator {
    private SecureRandom random = new SecureRandom();

    public String generateId() {
        return new BigInteger(130, random).toString(32);
    }
}
