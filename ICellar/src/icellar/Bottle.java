package icellar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Alex Mazzeo
 */
public class Bottle 
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
    private double rating;
    private int countRatings;
    private ArrayList<Comment> comments;
    
    /**
     * 
     * @param maker The maker of the wine
     * @param type  The type of the wine
     * @param year  The vintage of the wine
     */
    public Bottle ( String maker, String type, String year )
    {
        this.maker = maker;
        this.type = type;
        this.year = year;
        comments = new ArrayList<Comment>();
        rating = 0;
        countRatings = 0;
    }
    
    /**
     * 
     * @param maker     the maker of the wine
     * @param type      the type of the wine
     * @param year      the vintage of the wine
     * @param region    the region the wine is from; Enter null if not available
     * @param vineyard  the vineyard the wine is from; Enter null if not available
     */
    public Bottle ( String maker, String type, String year, String region, String vineyard, double rating, Comment cm )
    {
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
    }
    
    /**
     * Adds a rating to the current rating of the {@link Bottle}. Keeps the average in the rating field.
     * @param rate  the new rating to be added to the average
     */
    public void addRating( double rate )
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
            //undo the last averaging to add another element
            this.rating *= this.countRatings;
            //calculate the new average
            this.countRatings++;
            this.rating /= this.countRatings;
        }
        else    //valid rating
        {
            //undo the last averaging to add another element
            this.rating *= this.countRatings;
            //calculate the new average
            this.rating += rate;
            this.countRatings++;
            this.rating /= this.countRatings;
        }
    }
    
    /**
     * 
     * @param rating    the new rating
     */
    public void setRating( double rating )
    {
        this.rating = rating;
        countRatings = 1;
    }
    
    /**
     * 
     * @return  the current rating of the {@link Bottle}
     */
    public double getRating()
    {
        return rating;
    }
    
    /**
     * Adds a comment for the {@link Bottle}
     * @param comment   the text of the comment
     * @param user      the user who wrote the comment
     * @return          true if successful
     */
    public boolean addComment( String comment, String user )
    {
        return this.comments.add( new Comment ( comment, user ) );
    }
    
    public boolean addComment( Comment cm )
    {
        return this.comments.add( cm );
    }
    
    /**
     * 
     * @return  the current comments for the {@link Bottle}
     */
    public ArrayList<Comment> getComments()
    {
        return comments;
    }
    
    /**
     * Removes the comment matching the parameter
     * @param cm    a copy of the comment to be removed
     * @return      true if successful
     */
    public boolean removeComment(Comment cm)
    {
        return comments.remove(cm);
    }
    
    /**
     * 
     * @param maker the new maker
     */
    public void setMaker( String maker )
    {
        this.maker = maker;
    }
    
    /**
     * 
     * @return  the maker
     */
    public String getMaker()
    {
        return maker;
    }
    
    /**
     * 
     * @param type  the new type
     */
    public void setType( String type )
    {
        this.type = type;
    }
    
    /**
     * 
     * @return  the type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * 
     * @param year  the new year
     */
    public void setYear( String year )
    {
        this.year = year;
    }
    
    /**
     * 
     * @return the year
     */
    public String getYear()
    {
        return year;
    }
    
    /**
     * 
     * @param region    the new region
     */
    public void setRegion( String region )
    {
        this.region = region;
    }
    
    /**
     * 
     * @return  the region
     */
    public String getRegion()
    {
        return region;
    }
    
    /**
     * 
     * @param vy    the new vineyard
     */
    public void setVineyard( String vy )
    {
        this.vineyard = vy;
    }
    
    /**
     * 
     * @return  the vineyard
     */
    public String getVineyard()
    {
        return vineyard;
    }
    
    /**
     * Compares two {@link Bottle}s according to their maker.
     * @param btl   the {@link Bottle} to be compared to
     * @return
     */
    public int compareMaker( Bottle btl )
    {
        return this.maker.compareTo( btl.getMaker() );
    }
    
    /**
     * Compares two {@link Bottle}s according to their type.
     * @param btl   the {@link Bottle} to be compared to
     * @return
     */
    public int compareType( Bottle btl )
    {
        return this.type.compareTo( btl.getType() );
    }
    
    /**
     * Compares two {@link Bottle}s according to their year.
     * @param btl   the {@link Bottle} to be compared to
     * @return
     */
    public int compareYear( Bottle btl )
    {
        return this.year.compareTo( btl.getYear() );
    }
    
    /**
     * Compares two {@link Bottle}s according to their region.
     * @param btl   the {@link Bottle} to be compared to
     * @return
     */
    public int compareRegion( Bottle btl )
    {
        return this.region.compareTo( btl.getRegion() );
    }
    
    /**
     * Compares two {@link Bottle}s according to their vineyard.
     * @param btl   the {@link Bottle} to be compared to
     * @return
     */
    public int compareVineyard( Bottle btl )
    {
        return this.vineyard.compareTo( btl.getVineyard() );
    }
    
    /**
     * Compares two {@link Bottle}s according to their rating.
     * @param btl   the {@link Bottle} to be compared to
     * @return
     */
    public int compareRating( Bottle btl )
    {
        Double rate = this.rating;
        Double compare = btl.getRating();
        return rate.compareTo( compare );
    }
    
    public Comment getLatestComment()
    {
        if ( comments.size() > 0 )
        {
            return comments.get(comments.size()-1);
        }
        else
        {
            return new Comment("","");
        }
    }
    
    /**
     * Builds an array of {@link String}s for exporting onto a table.
     * @return  an array of {@link String}s that represent the bottle in text.
     */
    public String[] toStringArray()
    {
        String[] result = { this.maker, this.type, this.year, this.region, this.vineyard, "" + this.rating, this.getLatestComment().getText() };
        return result;
    }
    
    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals( Object o )
    {
        Bottle btl = (Bottle) o;
        return (this.maker.equals(btl.getMaker()) && this.type.equals(btl.getType()) && this.year.equals(btl.getYear()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.maker != null ? this.maker.hashCode() : 0);
        hash = 79 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 79 * hash + (this.year != null ? this.year.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString()
    {
        String result = "";
        result += this.maker + "," + this.type + "," + this.year + "," + this.region + "," + this.vineyard + "," + this.rating + ",";
        for ( Comment cm : comments )
        {
            result += cm.getDate() + "-" + cm.getUser() + "-" + cm.getText() + ";";
        }
        return result.substring(0, result.length()-1);
    }
}

class Comment
{
    private String text;
    private String user;
    private String date;
    
    public Comment ( String date, String user, String text )
    {
        this.date = date;
        this.user = user;
        this.text = text;
    }
    
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
    
    public int compareTo( Comment otherComment )
    {
        return this.date.compareTo( otherComment.getDate() );
    }
    
    @Override
    public String toString()
    {
        return this.user + " said \"" + this.text + "\" on " + this.date;
    }
}
