/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icellar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ryan Lessley
 */
public class ClubBottle extends Bottle 
{      
    
    private final int MAX_RATING = 5;
    private final int MIN_RATING = 0;
    private final int REGION = 0;
    private final int VINEYARD = 1;
    private String maker;
    private String type;
    private String year;
    private String region;
    private String vineyard;
    private String user;
    private double rating;
    private int countRatings;
    private ArrayList<Comment> comments;
    
    public ClubBottle(String maker, String type, String year, String user) {
        super(maker, type, year);
        
        this.maker = maker;
        this.type = type;
        this.year = year;
        this.user = user;
    }

    public ClubBottle(String maker, String type, String year, String region, String vineyard, double rating, Comment cm, String user) {
        super(maker, type, year, region, vineyard, rating, cm);
        this.maker = maker;
        this.type = type;
        this.year = year;
        this.region = region;
        this.vineyard = vineyard;
        comments = new ArrayList<Comment>();
        if ( cm != null )
        comments.add( cm );
        this.rating = 0;
        countRatings = 0;
        this.addRating( rating );
        this.user = user;
    }
    
    public void setUser(String user)
    {
        this.user = user;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public int compareUser(ClubBottle bt1)
    {
        return this.user.compareTo(bt1.getUser());
    }
    
    @Override
    public String[] toStringArray()
    {
        String[] result = { this.maker, this.type, this.year, this.region, this.vineyard, "" + this.rating, this.user, this.getLatestComment().getText() };
        return result;
    }
    
    @Override
    public String toString()
    {
        String result = "";
        result += this.maker + " | " + this.type + " | " + this.year + " | " + this.region + " | " + this.vineyard + " | " + this.rating + " | " + this.user + " | ";
        for ( Comment cm : comments )
        {
            result += " ~ " + cm.getDate() + "-" + cm.getUser() + "-" + cm.getText();
        }
        return result;
    }
}
