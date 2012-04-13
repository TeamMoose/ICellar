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
        String filepath = "Users/gja8Bottle.txt";
        
        Cellar cellar; //= new Cellar ("resources/bottles.txt");
        //FTPHelper.writeCellarToFile(filepath, cellar);
        
        cellar = FTPHelper.readCellarFromFile(filepath);
        System.out.println(cellar + "\n");
        
        FTPHelper.appendToFile(filepath, "Cottonwood Canyon,Pinot Noir,2000,Santa Ynez,Sharon's Vineyard,4.6,8:05PM 4/11/2012-AMazzeo-This is AMAZING!!!!;8:07PM 4/11/2012-CMarshall-Best wine I own!");
        cellar = FTPHelper.readCellarFromFile(filepath);
        
        System.out.println(cellar);
        
    }
}
