/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.string;

/**
 *
 * @author John Ferdinand Antonio
 */
public class StringUtil {

    public static String removeAllNonNumeric(String s) {
        String cleansed = s.replaceAll("[^-?0-9]+", " ");
        return cleansed;
    }
    
    public static String removeWhiteSpaces(String s){
        String cleansed = s.replaceAll("\\s+", ""); // removes all whitespace
        return cleansed;
    }
    
    public static boolean hasSpecialCharaters(String s) {
        return (s == null) ? false : s.matches("[^A-Za-z0-9 ]");
    }
}
