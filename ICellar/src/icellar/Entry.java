package icellar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alex Mazzeo
 */
public class Entry 
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
    private int rating;
    private int countRatings;
    private ArrayList<Comment> comments;
    
    public Entry ( String maker, String type, String year )
    {
        this.maker = maker;
        this.type = type;
        this.year = year;
        comments = new ArrayList<Comment>();
        rating = 0;
        countRatings = 0;
    }
    
    public Entry ( String maker, String type, String year, String region, String vineyard )
    {
        this.maker = maker;
        this.type = type;
        this.year = year;
        this.region = region;
        this.vineyard = vineyard;
        comments = new ArrayList<Comment>();
        rating = 0;
        countRatings = 0;
    }
    
    public Entry ( String maker, String type, String year, String last, int fieldIdentifier )
    {
        this.maker = maker;
        this.type = type;
        this.year = year;
        
        if ( fieldIdentifier == REGION )
        {
            this.region = last;
        }
        else if ( fieldIdentifier == VINEYARD )
        {
            this.vineyard = last;
        }
        else
        {
            System.err.println( "Field identifier not recognized. Only maker, year, and type assigned" );
        }
        comments = new ArrayList<Comment>();
        rating = 0;
        countRatings = 0;
    }
    
    public void addRating( int rate )
    {
        //Keep the average rating in the rating variable at all times.
        
        if ( rate > this.MAX_RATING )   //Force the highest possible.
        {
            //undo the last averaging to add another element.
            this.rating *= this.countRatings;
            //calculate the new average.
            this.rating += this.MAX_RATING;
            this.countRatings++;
            this.rating /= this.countRatings;
        }
        else if ( rate < this.MIN_RATING )  //force the lowest possible.
        {
            this.rating *= this.countRatings;
            this.countRatings++;
            this.rating /= this.countRatings;
        }
        else    //valid rating
        {
            this.rating *= this.countRatings;
            this.rating += rate;
            this.countRatings++;
            this.rating /= this.countRatings;
        }
    }
    
    public int getRating()
    {
        return rating;
    }
    
    public boolean addComment( String comment, String user )
    {
        return this.comments.add( new Comment ( comment, user ) );
    }
    
    public boolean deleteComment(Comment cm)
    {
        return comments.remove(cm);
    }
    
    public void setMaker( String maker )
    {
        this.maker = maker;
    }
    
    public String getMaker()
    {
        return maker;
    }
    
    public void setType( String type )
    {
        this.type = type;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setYear( String year )
    {
        this.year = year;
    }
    
    public String getYear()
    {
        return year;
    }
    
    public void setRegion( String region )
    {
        this.region = region;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setVineyard( String vy )
    {
        this.vineyard = vy;
    }
    
    public String getVineyard()
    {
        return vineyard;
    }
}

class Comment
{
    private String text;
    private String user;
    private String date;
    
    public Comment ( String text, String user )
    {
        this.text = text;
        this.user = user;
        DateFormat df = new SimpleDateFormat("h:mma MM/dd/yyyy");
        Date dt = new Date();
        this.date = df.format(dt);
        
    }
    
    public String getText()
    {
        return text;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public String getDate()
    {
        return date;
    }
    
    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals( Object o )   //This allows use of arrayList.remove(Object o) to delete the correct comment.
    {
        Comment cm = (Comment) o;
        return this.text.equals(cm.getText()) && this.user.equals(cm.getUser());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.text != null ? this.text.hashCode() : 0);
        hash = 23 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }
}
