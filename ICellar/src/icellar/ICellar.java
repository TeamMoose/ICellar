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
        Cellar cellar = new Cellar();
        Random rand = new Random();
        for (int i = 0; i < 30; i++ )
        {
            cellar.addBottle("Tester" , "Test", "" + (2000 + rand.nextInt(10)));
        }
        
        System.out.println(cellar);
        System.out.println();
        cellar.sortByYear();
        System.out.println(cellar);
    }
}
