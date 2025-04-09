package SortingAlgorithms;

import java.util.Arrays;

public class BubbleSort
{
   public static <T extends Comparable<T>> T[] sort(T[] array)
   {
       int n = array.length;
       T[] sortedArray = Arrays.copyOf(array, n);
       for (int i = 0; i < n - 1; i++)
       {
           for (int j = 0; j < n - i - 1; j++)
           {
               if (sortedArray[j].compareTo(sortedArray[j + 1]) > 0)
               {
                   T temp = sortedArray[j];
                   sortedArray[j] = sortedArray[j+ 1];
                   sortedArray[j+ 1] = temp;
               }
           }
       }

       return sortedArray;
   }
}
