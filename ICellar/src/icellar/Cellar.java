package icellar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alex Mazzeo
 */
public class Cellar {

    private ArrayList<Bottle> bottles;

    public Cellar() 
    {
        bottles = new ArrayList<Bottle>();
    }
    
    public Cellar( String filepath )
    {
        bottles = new ArrayList<Bottle>();
        this.buildFromFile(filepath);
    }
    
    public Cellar ( InputStream input )
    {
        bottles = new ArrayList<Bottle>();
        this.buildFromFile(input);
    }
    
    public boolean addBottle( Bottle btl )
    {
        return bottles.add(btl);
    }
    
    /**
     * Adds a Bottle to the cellar.
     * 
     * @param maker the wine maker
     * @param type  the type of wine
     * @param year  the vintage of the wine
     * @return      true if successful
     */
    public boolean addBottle(String maker, String type, String year) {
        return bottles.add(new Bottle(maker, type, year));
    }
    
    /**
     *Adds a Bottle to the cellar. If region or vineyard is not filled in,
     * use NULL to call this method.
     * 
     * @param maker     the wine maker
     * @param type      the type of wine
     * @param year      the vintage of the wine
     * @param region    the region the wine is from
     * @param vineyard  the vineyard the wine is from
     * @return          true if successful
     */
    public boolean addBottle(String maker, String type, String year, String region, String vineyard, double rating, Comment cm) {
        return bottles.add(new Bottle(maker, type, year, region, vineyard, rating, cm));
    }

    /**
     * Removes a Bottle from the cellar.
     * 
     * @param btl   the bottle to be removed
     * @return      true on success
     */
    public boolean removeBottle(Bottle btl) {
        return bottles.remove(btl);
    }

    /**
     * Returns a pointer to the Bottle in the cellar.
     * 
     * @param btl   a copy of the {@link Bottle} to look for
     * @return      A {@link Bottle}
     */
    public Bottle getBottle(Bottle btl) {
        for (Bottle curBtl : bottles) {
            if (curBtl.equals(btl)) {
                return curBtl;
            }
        }
        return null;
    }

    /**
     * Returns a pointer to the {@link Bottle} in the cellar at the
     *  specified index.
     * 
     * @param index the index of the requested {@link Bottle}
     * @return      a {@link Bottle}
     */
    public Bottle getBottle(int index) {
        return bottles.get(index);
    }

    /**
     * Applies every non-null field to the {@link Bottle} specified. For example,
     * if maker is non-null and every other field is null, only the maker field will
     * be updated.
     * 
     * @param btl       A copy of the {@link Bottle} to edit.
     * @param maker     the new maker
     * @param type      the new type
     * @param year      the new year
     * @param region    the new region
     * @param vineyard  the new vineyard
     */
    public void editBottle(Bottle btl, String maker, String type, String year, String region, String vineyard) {
        //check for nulls before overwritting.
        Bottle bt = bottles.get(bottles.indexOf(btl));
        if (maker != null) {
            bt.setMaker(maker);
        }
        if (type != null) {
            bt.setType(type);
        }
        if (year != null) {
            bt.setYear(year);
        }
        if (region != null) {
            bt.setRegion(region);
        }
        if (vineyard != null) {
            bt.setVineyard(vineyard);
        }
    }

    /**
     * Finds and returns a {@link Bottle} from the cellar.
     * 
     * @param btl   a copy of the {@link Bottle} that is being searched for.
     * @return      a {@link Bottle} if a match is found, else null.
     */
    public Bottle search(Bottle btl) {
        int index = bottles.indexOf(btl);
        if (index >= 0) {
            return bottles.get(index);
        } else {
            return null;
        }
    }

    /**
     * Sorts the cellar by the maker field. Order is alphabetic.
     */
    public void sortByMaker() {
        Object[] arr = bottles.toArray();
        arr = quicksortMaker(arr, arr.length / 2);
        bottles = new ArrayList<Bottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((Bottle) o);
        }
    }

    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    private Object[] quicksortMaker(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        //get the pivot
        Bottle curBtl = (Bottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<Bottle> less = new ArrayList<Bottle>();
        ArrayList<Bottle> greater = new ArrayList<Bottle>();
        ArrayList<Bottle> same = new ArrayList<Bottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            Bottle btl = (Bottle) o;
            compare = curBtl.compareMaker(btl);
            if (compare < 0) {
                greater.add(btl);
            } else if (compare > 0) {
                less.add(btl);
            } else {
                same.add(btl);
            }
        }
        
        //change the ArrayLists back to arrays for further sorting.
        Object[] lessArr = less.toArray();
        Object[] greaterArr = greater.toArray();
        Object[] sameArr = same.toArray();
        
        //return the sorted set of "less", the equivalent set, and the sorted set of "greater"
        return mergeArrays(quicksortMaker(lessArr, lessArr.length / 2), sameArr,
                quicksortMaker(greaterArr, greaterArr.length / 2));

    }

    /**
     * Sorts the cellar by the type field. Order is alphabetic.
     */
    public void sortByType() {
        Object[] arr = bottles.toArray();
        arr = quicksortType(arr, arr.length / 2);
        bottles = new ArrayList<Bottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((Bottle) o);
        }
    }

    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    public Object[] quicksortType(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        
        //get the pivot
        Bottle curBtl = (Bottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<Bottle> less = new ArrayList<Bottle>();
        ArrayList<Bottle> greater = new ArrayList<Bottle>();
        ArrayList<Bottle> same = new ArrayList<Bottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            Bottle btl = (Bottle) o;
            compare = curBtl.compareType(btl);
            if (compare < 0) {
                greater.add(btl);
            } else if (compare > 0) {
                less.add(btl);
            } else {
                same.add(btl);
            }
        }
        //change the ArrayLists back to arrays for further sorting.
        Object[] lessArr = less.toArray();
        Object[] greaterArr = greater.toArray();
        Object[] sameArr = same.toArray();
        
        //return the sorted set of "less", the equivalent set, and the sorted set of "greater"
        return mergeArrays(quicksortType(lessArr, lessArr.length / 2), sameArr,
                quicksortType(greaterArr, greaterArr.length / 2));

    }

    /**
     * Sorts the cellar by the year field. Order is ascending numerical.
     */
    public void sortByYear() {
        Object[] arr = bottles.toArray();
        arr = quicksortYear(arr, arr.length / 2);
        bottles = new ArrayList<Bottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((Bottle) o);
        }
    }

    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    public Object[] quicksortYear(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        
        //get the pivot
        Bottle curBtl = (Bottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<Bottle> less = new ArrayList<Bottle>();
        ArrayList<Bottle> greater = new ArrayList<Bottle>();
        ArrayList<Bottle> same = new ArrayList<Bottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            Bottle btl = (Bottle) o;
            compare = curBtl.compareYear(btl);
            if (compare < 0) {
                greater.add(btl);
            } else if (compare > 0) {
                less.add(btl);
            } else {
                same.add(btl);
            }
        }
        //change the ArrayLists back to arrays for further sorting.
        Object[] lessArr = less.toArray();
        Object[] greaterArr = greater.toArray();
        Object[] sameArr = same.toArray();
        
        //return the sorted set of "less", the equivalent set, and the sorted set of "greater"
        return mergeArrays(quicksortYear(lessArr, lessArr.length / 2), sameArr,
                quicksortYear(greaterArr, greaterArr.length / 2));
    }

    /**
     * Sorts the cellar by the region field. Order is alphabetic.
     */
    public void sortByRegion() {
        Object[] arr = bottles.toArray();
        arr = quicksortRegion(arr, arr.length / 2);
        bottles = new ArrayList<Bottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((Bottle) o);
        }
    }

    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    public Object[] quicksortRegion(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        //get the pivot
        Bottle curBtl = (Bottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<Bottle> less = new ArrayList<Bottle>();
        ArrayList<Bottle> greater = new ArrayList<Bottle>();
        ArrayList<Bottle> same = new ArrayList<Bottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            Bottle btl = (Bottle) o;
            compare = curBtl.compareRegion(btl);
            if (compare < 0) {
                greater.add(btl);
            } else if (compare > 0) {
                less.add(btl);
            } else {
                same.add(btl);
            }
        }
        //change the ArrayLists back to arrays for further sorting.
        Object[] lessArr = less.toArray();
        Object[] greaterArr = greater.toArray();
        Object[] sameArr = same.toArray();
        
        //return the sorted set of "less", the equivalent set, and the sorted set of "greater"
        return mergeArrays(quicksortRegion(lessArr, lessArr.length / 2), sameArr,
                quicksortRegion(greaterArr, greaterArr.length / 2));
    }

    /**
     * Sorts the cellar by the vineyard field. Order is alphabetic.
     */
    public void sortByVineyard() {
        Object[] arr = bottles.toArray();
        arr = quicksortVineyard(arr, arr.length / 2);
        bottles = new ArrayList<Bottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((Bottle) o);
        }
    }

    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    public Object[] quicksortVineyard(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        //get the pivot
        Bottle curBtl = (Bottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<Bottle> less = new ArrayList<Bottle>();
        ArrayList<Bottle> greater = new ArrayList<Bottle>();
        ArrayList<Bottle> same = new ArrayList<Bottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            Bottle btl = (Bottle) o;
            compare = curBtl.compareVineyard(btl);
            if (compare < 0) {
                greater.add(btl);
            } else if (compare > 0) {
                less.add(btl);
            } else {
                same.add(btl);
            }
        }
        //change the ArrayLists back to arrays for further sorting.
        Object[] lessArr = less.toArray();
        Object[] greaterArr = greater.toArray();
        Object[] sameArr = same.toArray();
        
        //return the sorted set of "less", the equivalent set, and the sorted set of "greater"
        return mergeArrays(quicksortVineyard(lessArr, lessArr.length / 2), sameArr,
                quicksortVineyard(greaterArr, greaterArr.length / 2));
    }

    /**
     * Sorts the cellar by the rating field. Order is numeric.
     */
    public void sortByRating() {
        Object[] arr = bottles.toArray();
        arr = quicksortRating(arr, arr.length / 2);
        bottles = new ArrayList<Bottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((Bottle) o);
        }
    }

    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    public Object[] quicksortRating(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        //get the pivot
        Bottle curBtl = (Bottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<Bottle> less = new ArrayList<Bottle>();
        ArrayList<Bottle> greater = new ArrayList<Bottle>();
        ArrayList<Bottle> same = new ArrayList<Bottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            Bottle btl = (Bottle) o;
            compare = curBtl.compareRating(btl);
            if (compare < 0) {
                greater.add(btl);
            } else if (compare > 0) {
                less.add(btl);
            } else {
                same.add(btl);
            }
        }
        //change the ArrayLists back to arrays for further sorting.
        Object[] lessArr = less.toArray();
        Object[] greaterArr = greater.toArray();
        Object[] sameArr = same.toArray();
        
        //return the sorted set of "less", the equivalent set, and the sorted set of "greater"
        return mergeArrays(quicksortRating(lessArr, lessArr.length / 2), sameArr,
                quicksortRating(greaterArr, greaterArr.length / 2));
    }
    
    /**
     * Combines three arrays into one larger array.
     * @param first     the first array to be inserted
     * @param second    the second array to be inserted
     * @param third     the third array to be inserted
     * @return          an array made of the three parameter arrays
     */
    public Object[] mergeArrays(Object[] first, Object[] second, Object[] third) {
        Object[] result = new Bottle[first.length + second.length + third.length];
        int resultIndex = 0;
        //add the first array
        for (Object btl : first) {
            result[resultIndex] = btl;
            resultIndex++;
        }
        //add the second array
        for (Object btl : second) {
            result[resultIndex] = btl;
            resultIndex++;
        }
        //add the third array
        for (Object btl : third) {
            result[resultIndex] = btl;
            resultIndex++;
        }

        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (Bottle btl : bottles) {
            result += btl.toString() + "\n";
        }
        return result;
    }
    
    /**
     * Builds a 2D Array of {@link String}s that is used to export bottle info to the table.
     * @return  a 2D Array of {@link String}s
     */
    public String[][] toStringArray()
    {
        int i = 0;
        String[][] result = new String[bottles.size()][7];
        
        for ( Bottle btl : bottles )
        {
           result[i] = btl.toStringArray();
           i++;
        }
        
        return result;
    }
    
    /**
     * Builds an Array of {@link String}s that is used to export bottle info on the
     * Android platform. The format of the string is "maker-type-year". The "-" is to
     * be used to split the {@link String}.
     * @return an array of {@link String}s
     */
    public String[] toAndroidStringArray()
    {
        int i = 0;
        String[] result = new String[bottles.size()];
        
        for ( Bottle btl : bottles )
        {
            result[i] = btl.getMaker() + "-" + btl.getType() + "-" + btl.getYear();
            i++;
        }
        
        return result;
    }
    
    private void buildFromFile( String filepath )
    {
        try 
        {
            File inFile = new File( filepath );
            Scanner scan = new Scanner( inFile );
            String curLine;
            String[] fields;
            String[] comments;
            Bottle btl;
            while ( scan.hasNext() )
            {
                curLine = scan.nextLine();
                fields = curLine.split(",");
                comments = fields[6].split(";");
                
                btl = new Bottle( fields[0], fields[1], fields[2], fields[3], fields[4], Double.parseDouble(fields[5]), null );
                for ( String cm : comments )
                {
                    String[] arr = cm.split( "-" );
                    btl.addComment(new Comment( arr[0], arr[1], arr[2]));
                }
                bottles.add(btl);
            }
        } 
        catch (FileNotFoundException ex) 
        {
            System.err.println( ex );
        }
    }
    
    private void buildFromFile( InputStream input )
    {
            Scanner scan = new Scanner( input );
            String curLine;
            String[] fields;
            String[] comments;
            Bottle btl;
            while ( scan.hasNext() )
            {
                curLine = scan.nextLine();
                fields = curLine.split(",");
                comments = fields[6].split(";");
                
                btl = new Bottle( fields[0], fields[1], fields[2], fields[3], fields[4], Double.parseDouble(fields[5]), null );
                for ( String cm : comments )
                {
                    String[] arr = cm.split( "-" );
                    btl.addComment(new Comment( arr[0], arr[1], arr[2]));
                }
                bottles.add(btl);
            }
    }
}
