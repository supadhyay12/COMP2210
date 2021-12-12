import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Shanti Upadhyay (spu0004@auburn.edu)
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
   
      if (coll == null)
      { 
         throw new IllegalArgumentException();
      }
      
      if (comp == null)
      {
         throw new IllegalArgumentException();
      }   
      
      
      if (coll.isEmpty()) //if collection is empty, no such element exception
      {
         throw new NoSuchElementException();
      }
      
      Iterator<T> minVal = coll.iterator();
      T minValue = minVal.next();
      if (minVal.hasNext())
      {
         for (T val : coll)
         {
      
            if (comp.compare(val, minValue) < 0);
            {
               minValue = val;
         
         }
      }
     } 
   
      return minValue;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
   
      if (coll == null) //if collection is null, illegal argument exception
      { 
         throw new IllegalArgumentException();
      }
      
      if (comp == null) //if comparator is null, illegal argument exception
      {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) //if collection is empty, no such element exception
      {
         throw new NoSuchElementException();
      }
      
      Iterator<T> maxVal = coll.iterator();
      T maxValue = maxVal.next();
      if (maxVal.hasNext())
      {
      
         for (T val : coll) 
         {
         if (comp.compare(val, maxValue) > 0);
      
         {
            maxValue = val;
         
         }
      } 
     } 
      return maxValue;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) 
   {
      if (coll == null) //if collection is null, illegal argument exception
      { 
         throw new IllegalArgumentException("Collection is null");
      }
      
      if (comp == null) //if comparator is null, illegal argument exception
      {
         throw new IllegalArgumentException("Comparator is null");
      }
      
      if (coll.isEmpty()) //if collection is empty, no such element exception
      {
         throw new NoSuchElementException("Collection is empty");
      } 
      
      if (k > coll.size()) //if k is larger than collection
      { 
         throw new NoSuchElementException("There is no kth minimum");
      }        
      
      if (k < 0) //if k is negative
      {
         throw new NoSuchElementException("There is no kth maximum");
      }
     
      if (coll.size() == 1) //if only one value in collection
      {
         return coll.iterator().next();
      }
      
      List<T> subColl = new ArrayList<T>(coll);
      List<T> subArray = new ArrayList<T>(0);
      java.util.Collections.<T>sort(subColl, comp);
      
      int temp = 1;
      int index = 1;
      
      for (T val : subColl) 
      {
         int result = comp.compare(val, subColl.get(index));
      
         if (result != 0) 
         {
         
            if (k == temp) 
            {
               return val;
            }
            
            else 
            {
               subArray.add(val);
               temp++;
            }
         }
         index++;
         
      }
      
      T kMinValue = subArray.get(k - 1);
      
      return kMinValue;
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
   
      if (coll == null) 
      { 
         throw new IllegalArgumentException("Collection is null");
      }
      
      if (comp == null) 
      {
         throw new IllegalArgumentException("Comparator is null");
      }
      
      if (coll.isEmpty()) 
      {
         throw new NoSuchElementException("Collection is empty");
      } 
      
      if (k > coll.size() || k < 1) 
      {
         throw new NoSuchElementException("No Kth maximum value");
      } 
      
      if (k < 0) 
      {
         throw new NoSuchElementException("No Kth maximum value");
      }
      
      if (coll.size() == 1) {
         return coll.iterator().next();
      }       
      
      List<T> subColl = new ArrayList<T>(coll);
      List<T> subArray = new ArrayList<T>(0);
      java.util.Collections.<T>sort(subColl, java.util.Collections.reverseOrder(comp));
      
      int temp = 1;
      int index = 1;
      
      for (T val : subColl) 
      {
      
         int result = comp.compare(val, subColl.get(index));
      
         if (result != 0) 
         {
         
            if (k == temp) 
            {
               return val;
            }
            
            else 
            {
               subArray.add(val);
               temp++;
            }
         }
         index++;
         
      }
      
      T kMaxValue = subArray.get(k - 1);
      
      return kMaxValue;
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high, Comparator<T> comp) 
   {
                                         
      if (coll == null) 
      { 
         throw new IllegalArgumentException("Collection is null");
      }
      
      if (comp == null) 
      {
         throw new IllegalArgumentException("Comparator is null");
      }
      
      if (coll.isEmpty()) 
      {
         throw new NoSuchElementException("Collection is empty");
      }
      
      ArrayList<T> subColl = new ArrayList<T>(coll);
      ArrayList<T> newColl = new ArrayList<T>(0);
      
      int result = comp.compare(low, high);
      
      if (result <= 0) 
      {
         for (T val : subColl) 
         {
            int subLow = comp.compare(val, low);
            int subHigh = comp.compare(val, high);
         
            if (subLow >= 0 && subHigh <= 0) 
            {
               newColl.add(val);
            }
            
         }
      }
      
      if (newColl.size() == 0) 
      {
         throw new NoSuchElementException("No values fall in specified range");
      }
      
      return newColl;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
   
      if (coll == null) 
      { 
         throw new IllegalArgumentException("Collection is null");
      }
      
      if (comp == null) 
      {
         throw new IllegalArgumentException("Comparator is null");
      }
      
      if (coll.isEmpty()) 
      {
         throw new NoSuchElementException("Collection is empty");
      }
      
      if (coll.size() == 1) 
      {
         throw new NoSuchElementException("Collection has no qualifying value");
      } 
      
      ArrayList<T> subColl = new ArrayList<T>(coll);
      T ceilingValue = subColl.iterator().next();
      
      for (T val : subColl) 
      {
         int result = comp.compare(val, key);
         int smallestValue = comp.compare(ceilingValue, val);
      
         if (result >= 0 && smallestValue >= 0) 
         {
            ceilingValue = val;
         }
      } 
      
      return ceilingValue;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) 
   {
   
      if (coll == null) 
      { 
         throw new IllegalArgumentException("Collection is null");
      }
      
      if (comp == null) 
      {
         throw new IllegalArgumentException("Comparator is null");
      }
      
      if (coll.isEmpty()) 
      {
         throw new NoSuchElementException("Collection is empty");
      }
      
      if (coll.size() == 1) 
      {
         throw new NoSuchElementException("Collection has no qualifying value");
      }    
      
      ArrayList<T> subColl = new ArrayList<T>(coll);
      T floorValue = subColl.iterator().next();
      
      for (T val : subColl) 
      {
         int result = comp.compare(val, key);
         int greatestValue = comp.compare(floorValue, val);
      
         if (result >= 0 && greatestValue <= 0) 
         {
            floorValue = val;
         }
      }       
      
      return floorValue;
   }

}