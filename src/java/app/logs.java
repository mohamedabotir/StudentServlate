/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Threading
 */
public class logs {
    
   private BufferedWriter output;
   public void Write(String DateLog){
        
            try {
                output=new BufferedWriter(new FileWriter("C:\\Users\\Threading\\Documents\\NetBeansProjects\\BrowseApplication\\StudentServlate\\Logs.txt",true));
                output.write(DateLog+"\n");
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(logs.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
            }
        catch(SecurityException e){
        System.err.println("You haven't Access For File");
       System.exit(0);
    }
}
}
