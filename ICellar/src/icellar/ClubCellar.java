/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icellar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ryan Lessley
 */
public class ClubCellar extends Cellar
{
    
    private ArrayList<ClubBottle> bottles;

    public ClubCellar() 
    {
        bottles = new ArrayList<ClubBottle>();
    }
    
    public ClubCellar( String filepath )
    {
        bottles = new ArrayList<ClubBottle>();
        this.buildFromFile(filepath);
    }
    
    public ClubCellar( InputStream is )
    {
        bottles = new ArrayList<ClubBottle>();
        this.buildFromFile( is );
    }
    
    public boolean addBottle( ClubBottle btl )
    {
        return bottles.add(btl);
    }

    /**
     * Adds a Bottle to the cellar.
     * 
     * @param maker the wine maker
     * @param type  the type of wine
     * @param year  the vintage of the wine
     * @param user  the user who uploaded the wine information
     * @return      true if successful
     */
    public boolean addBottle(String maker, String type, String year, String user) {
        return bottles.add(new ClubBottle(maker, type, year, user));
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
     * @param user  the user who uploaded the wine information
     * @return          true if successful
     */
    public boolean addBottle(String maker, String type, String year, String region, String vineyard, double rating, Comment cm, String user) {
        return bottles.add(new ClubBottle(maker, type, year, region, vineyard, rating, cm, user));
    }
    
    /**
     * Removes a Bottle from the cellar.
     * 
     * @param btl   the bottle to be removed
     * @return      true on success
     */
    public boolean removeBottle(ClubBottle btl) {
        return bottles.remove(btl);
    }

    /**
     * Returns a pointer to the Bottle in the cellar.
     * 
     * @param btl   a copy of the {@link Bottle} to look for
     * @return      A {@link Bottle}
     */
    public ClubBottle getBottle(ClubBottle btl) {
        for (ClubBottle curBtl : bottles) {
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
    @Override
    public ClubBottle getBottle(int index) {
        return bottles.get(index);
    }
    
    /**
     * Finds and returns a {@link Bottle} from the cellar.
     * 
     * @param btl   a copy of the {@link Bottle} that is being searched for.
     * @return      a {@link Bottle} if a match is found, else null.
     */
    public ClubBottle search(ClubBottle btl) {
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
    @Override
    public void sortByMaker() {
        Object[] arr = bottles.toArray();
        arr = quicksortMaker(arr, arr.length / 2);
        bottles = new ArrayList<ClubBottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((ClubBottle) o);
        }
    }

    private Object[] quicksortMaker(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        //get the pivot
        ClubBottle curBtl = (ClubBottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<ClubBottle> less = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> greater = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> same = new ArrayList<ClubBottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            ClubBottle btl = (ClubBottle) o;
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
    @Override
    public void sortByType() {
        Object[] arr = bottles.toArray();
        arr = quicksortType(arr, arr.length / 2);
        bottles = new ArrayList<ClubBottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((ClubBottle) o);
        }
    }

    @Override
    public Object[] quicksortType(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        
        //get the pivot
        ClubBottle curBtl = (ClubBottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<ClubBottle> less = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> greater = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> same = new ArrayList<ClubBottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            ClubBottle btl = (ClubBottle) o;
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
    @Override
    public void sortByYear() {
        Object[] arr = bottles.toArray();
        arr = quicksortYear(arr, arr.length / 2);
        bottles = new ArrayList<ClubBottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((ClubBottle) o);
        }
    }

    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    @Override
    public Object[] quicksortYear(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        
        //get the pivot
        ClubBottle curBtl = (ClubBottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<ClubBottle> less = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> greater = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> same = new ArrayList<ClubBottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            ClubBottle btl = (ClubBottle) o;
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
    @Override
    public void sortByRegion() {
        Object[] arr = bottles.toArray();
        arr = quicksortRegion(arr, arr.length / 2);
        bottles = new ArrayList<ClubBottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((ClubBottle) o);
        }
    }
    
    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    @Override
    public Object[] quicksortRegion(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        //get the pivot
        ClubBottle curBtl = (ClubBottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<ClubBottle> less = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> greater = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> same = new ArrayList<ClubBottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            ClubBottle btl = (ClubBottle) o;
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
    @Override
    public void sortByVineyard() {
        Object[] arr = bottles.toArray();
        arr = quicksortVineyard(arr, arr.length / 2);
        bottles = new ArrayList<ClubBottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((ClubBottle) o);
        }
    }

    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    @Override
    public Object[] quicksortVineyard(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        //get the pivot
        ClubBottle curBtl = (ClubBottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<ClubBottle> less = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> greater = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> same = new ArrayList<ClubBottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            ClubBottle btl = (ClubBottle) o;
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
     * Sorts the cellar by the user field. Order is alphabetic.
     */
    public void sortByUser() {
        Object[] arr = bottles.toArray();
        arr = quicksortUser(arr, arr.length / 2);
        bottles = new ArrayList<ClubBottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((ClubBottle) o);
        }
    }

    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    public Object[] quicksortUser(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        //get the pivot
        ClubBottle curBtl = (ClubBottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<ClubBottle> less = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> greater = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> same = new ArrayList<ClubBottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            ClubBottle btl = (ClubBottle) o;
            compare = curBtl.compareUser(btl);
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
        return mergeArrays(quicksortUser(lessArr, lessArr.length / 2), sameArr,
                quicksortUser(greaterArr, greaterArr.length / 2));
    }
    
    
    /**
     * Sorts the cellar by the rating field. Order is numeric.
     */
    @Override
    public void sortByRating() {
        Object[] arr = bottles.toArray();
        arr = quicksortRating(arr, arr.length / 2);
        bottles = new ArrayList<ClubBottle>();
        //populate the new ArrayList with the elements in the correct order
        for (Object o : arr) {
            bottles.add((ClubBottle) o);
        }
    }

    /**
     * Helper method to quicksort the cellar.
     * 
     * @param arr       the array to quicksort
     * @param pivot     the quicksort pivot
     * @return          a sorted array
     */
    @Override
    public Object[] quicksortRating(Object[] arr, int pivot) {
        int compare;
        if (arr.length <= 1) {
            return arr;
        }
        //get the pivot
        ClubBottle curBtl = (ClubBottle) arr[pivot];
        //make lists for "less", "greater", and "equivalent" objects
        ArrayList<ClubBottle> less = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> greater = new ArrayList<ClubBottle>();
        ArrayList<ClubBottle> same = new ArrayList<ClubBottle>();

        //split the array into the correct catagories.
        for (Object o : arr) {
            ClubBottle btl = (ClubBottle) o;
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
    @Override
    public Object[] mergeArrays(Object[] first, Object[] second, Object[] third) {
        Object[] result = new ClubBottle[first.length + second.length + third.length];
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
        for (ClubBottle btl : bottles) {
            result += btl.toString() + "\n";
        }
        return result;
    }

    public String[][] toStringArray()
    {
        int i = 0;
        String[][] result = new String[bottles.size()][7];
        
        for ( ClubBottle btl : bottles )
        {
           result[i] = btl.toStringArray();
           i++;
        }
        
        return result;
    }

    public void buildFromFile( String filepath )
    {
        try 
        {
            File inFile = new File( filepath );
            Scanner scan = new Scanner( inFile );
            String curLine;
            String[] fields;
            String[] comments;
            ClubBottle btl;
            while ( scan.hasNext() )
            {
                curLine = scan.nextLine();
                fields = curLine.split(",");
                comments = fields[7].split(";");
                
                btl = new ClubBottle( fields[0], fields[1], fields[2], fields[3], fields[4], Double.parseDouble(fields[5]), null, fields[6] );
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

}
