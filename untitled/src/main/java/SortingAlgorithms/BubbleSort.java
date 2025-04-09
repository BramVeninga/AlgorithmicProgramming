package SortingAlgorithms;

import Datastructures.MyArray;

import java.util.Arrays;
import java.util.Comparator;

import java.util.Comparator;

public class BubbleSort
{
    public static <T> void sort(T[] array, Comparator<? super T> comparator)
    {
        boolean swapped;
        int n = array.length;
        do
        {
            swapped = false;
            for (int i = 1; i < n; i++)
            {
                if (comparator.compare(array[i - 1], array[i]) > 0)
                {
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
            n--;
        }
        while (swapped);
    }
}

