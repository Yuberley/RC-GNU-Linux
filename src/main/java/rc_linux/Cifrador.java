/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc_linux;

import java.security.MessageDigest;
/**
 *
 * @author Nitro 5
 */
public class Cifrador {
    
    public Cifrador() {
    }
    
    public String hash(String clear) throws Exception { 
        
        MessageDigest md = MessageDigest.getInstance("SHA256"); 
        md.reset();
        byte[] b = md.digest(clear.getBytes()); 
        
        int size = b.length;
        StringBuffer h = new StringBuffer(size); 
        for (int i = 0; i < size; i++) { 
            int u = b[i]&255; // unsigned conversion 
            if (u<16) { 
                h.append("0"+Integer.toHexString(u)); 
            } else { 
                h.append(Integer.toHexString(u)); 
            } 
        } 
        return h.toString(); 
    } 

}

