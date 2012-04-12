/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icellar;

import java.util.ArrayList;
import java.util.Random;

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
        Cellar cellar = new Cellar("resources/bottles.txt");
        System.out.println(cellar);
        
    }
}
