/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.utils;

/**
 *
 * @author octavian
 */
public class Utils {
    public static String bytesToString(byte[] text){
        StringBuilder sb = new StringBuilder();
        
        for(byte chr : text){
            sb.append(Byte.toString(chr));
        }
        
        return sb.toString();
    }
}
