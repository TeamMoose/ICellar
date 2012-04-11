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
    private final int REGION = 0;
    private final int VINEYARD = 1;
    private String maker;
    private String type;
    private String year;
    private String region;
    private String vineyard;
    private int rating;
    private ArrayList<Comment> comments;
    
    public Entry ( String maker, String type, String year )
    {
        this.maker = maker;
        this.type = type;
        this.year = year;
        comments = new ArrayList<Comment>();
    }
    
    public Entry ( String maker, String type, String year, String region, String vineyard )
    {
        this.maker = maker;
        this.type = type;
        this.year = year;
        this.region = region;
        this.vineyard = vineyard;
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
    
    public void setType( String type )
    {
        this.type = type;
    }
    
    //@TODO: Add in sets and gets for all fields to allow editing.
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
        Date date = new Date();
        this.date = df.format(date);
        
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
    public boolean equals( Object o )
    {
        Comment cm = (Comment) o;
        return this.text.equals(cm.getText()) && this.user.equals(cm.getUser());
    }
}
