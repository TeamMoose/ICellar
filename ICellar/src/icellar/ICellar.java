/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icellar;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Mazzeo
 */
public class ICellar 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        String filepath = "test.txt";
        
        /*BufferedWriter write = FTPHelper.getFTPOutputStream(filepath);
        try {
            for (int i = 0; i < 100; i++)
            {
                write.write("Test succesful!\n"); 
            }
            write.flush();
            write.close();
        } catch (IOException ex) {
            Logger.getLogger(ICellar.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
       
        
        InputStream in = FTPHelper.getFTPInputStream(filepath);
        Scanner scan = new Scanner( in );
        
        while ( scan.hasNext() )
        {
            System.out.println( scan.nextLine() );
        }
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(ICellar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
