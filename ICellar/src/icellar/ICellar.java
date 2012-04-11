/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icellar;

import java.util.ArrayList;

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
        ArrayList<Comment> arr = new ArrayList<Comment>();
        for (int i = 0; i < 19; i++)
        {
            arr.add(new Comment("Hello World!", "" + i));
        }
        
        for(Comment cm : arr)
        {
            System.out.println(cm.getUser());
        }
        
        Comment cm = new Comment("Hello World!", "5");
        
        arr.remove(cm);
        System.out.print("\n\n");
        for(Comment cm1 : arr)
        {
            System.out.println(cm1.getUser());
        }
    }
}
