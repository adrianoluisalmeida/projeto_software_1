/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rhau
 */
public class FormatUtils {
   
    public static Date toDate(String dataString, String mask) {
        SimpleDateFormat formatter = new SimpleDateFormat(mask);
        Date data = null;
       
        try {
            data = formatter.parse(dataString);
        } catch (ParseException ex) {
            Logger.getLogger(FormatUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
       
    public static String toString(Date data, String mask) {
        SimpleDateFormat formatter = new SimpleDateFormat(mask);        
        return formatter.format(data);
    }

    /**
     * Remove the input mask from string and return numbers only
     * @param maskedString - Strign to remove mask
     * @return String - unmaskedString
     */
    public static String unmaskNumber(String maskedString){
        StringBuilder builder = new StringBuilder();
        char array[] = maskedString.toCharArray();
        for (char c : array) {
            if (Character.isDigit(c))
                builder.append(c);
        }
        return builder.toString();
    }
    
}
